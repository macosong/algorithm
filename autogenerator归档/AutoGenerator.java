import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * PetriNode
 *
 * petri Net节点对象
 *
 * @author: songqiang
 * @date: 2019/12/20
 */
class PetriNode {
    /**
     * 节点id
     */
    String id;

    /**
     * 节点的值，最后输出的路径就是变迁name属性的序列
     */
    String name;

    /**
     * 节点是否是库所，false表示当前节点是变迁
     */
    boolean isPlace;

    /**
     * 节点的前序节点集合
     */
    List<PetriNode> pre;

    /**
     * 节点的后继节点集合
     */
    List<PetriNode> post;

    public PetriNode(String id, String name, boolean isPlace) {
        this.id = id;
        this.name = name;
        this.isPlace = isPlace;
        this.pre = new ArrayList<>();
        this.post = new ArrayList<>();
    }
}


/**
 * AutoGenerator
 *
 * @author: songqiang
 * @date: 2019/12/20
 */
public class AutoGenerator {
    /**
     * 解决方案入口方法，解析pnml文件，初始化petri Net
     *
     * @param modelFile 模型文件路径
     * @param logFile 过程日志文件保存路径
     * @throws Exception
     */
    public static void getLogOfModel(String modelFile, String logFile) throws Exception {
        //用一个hashMap表示petri Net对象
        Map<String, PetriNode> petriNet = new HashMap<>();

        PetriNode sourcePlace = null;
        PetriNode targetPlace = null;

        //解析pnml文件
        NodeList nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                parse(new File(modelFile)).getDocumentElement().getChildNodes().item(1).getChildNodes();

        //遍历nodeList，初始化petri Net
        for (int i = 1; i < nodeList.getLength(); i += 2) {
            //当前节点对象
            Node node = nodeList.item(i);
            //找到库所或变迁
            if ("transition".equals(node.getNodeName()) || "place".equals(node.getNodeName())) {
                NodeList childNodes = node.getChildNodes();
                for (int j = 1; j < childNodes.getLength(); j += 2) {
                    Node curNode = childNodes.item(j);
                    if ("name".equals(curNode.getNodeName())) {
                        PetriNode petriNode = new PetriNode(node.getAttributes().getNamedItem("id").getTextContent(),
                                curNode.getChildNodes().item(1).getTextContent(), "place".equals(node.getNodeName()));
                        petriNet.put(petriNode.id, petriNode);
                        break;
                    }
                }
            }
        }

        //给变迁和库所添加前序节点和后继节点
        for (int i = 1; i < nodeList.getLength(); i += 2) {
            Node node = nodeList.item(i);
            if ("arc".equals(node.getNodeName())) {
                NamedNodeMap attr = nodeList.item(i).getAttributes();
                petriNet.get(attr.getNamedItem("source").getTextContent()).post.
                        add(petriNet.get(attr.getNamedItem("target").getTextContent()));
                petriNet.get(attr.getNamedItem("target").getTextContent()).pre.
                        add(petriNet.get(attr.getNamedItem("source").getTextContent()));
            }
        }

        //节点id list
        List<String> keyList = new ArrayList<>(petriNet.keySet());

        //找到source place和target place
        for(PetriNode node : petriNet.values()){
            if (node.isPlace && node.pre.size() == 0 && node.post.size() > 0) {
                sourcePlace = node;
            }else if (node.isPlace && node.pre.size() > 0 && node.post.size() == 0){
                targetPlace = node;
            }
        }

        if (sourcePlace == null || targetPlace == null)    throw new Exception();
        int[] tokens = new int[keyList.size()];
        //source place的初始token为1
        tokens[keyList.indexOf(sourcePlace.id)] = 1;

        //保存结果
        Set<String> res = new HashSet<>();

        findPath(new StringBuilder(), sourcePlace, res, new HashSet<>(), tokens.clone(), new int[keyList.size()], keyList, targetPlace);

        logHelper(logFile, res);
    }

    /**
     * 深度优先遍历搜索算法
     *
     * @param sb 当前路径字符串
     * @param node 当前访问节点
     * @param res 最终结果集集合对象
     * @param postNodes 后继节点集合
     * @param tokens tokens状态数组
     * @param nodeVisitCount 节点访问记录数组
     * @param keyList 节点id数组
     * @param targetPlace 目标库所
     */
    public static void findPath(StringBuilder sb, PetriNode node, Set<String> res, Set<PetriNode> postNodes, int[] tokens,
                         int[] nodeVisitCount, List<String> keyList, PetriNode targetPlace) {
        //变迁节点
        if (!node.isPlace) {
            ++nodeVisitCount[keyList.indexOf(node.id)];
            sb.append(node.name);
            //看变迁是否满足发射条件，即它的所有前序库所都至少有一个token
            for (PetriNode prePlace : node.pre) {
                if (tokens[keyList.indexOf(prePlace.id)] <= 0)  return;
                tokens[keyList.indexOf(prePlace.id)] -= 1;
            }
            //满足发射条件，给每个后续库所一个token
            for (PetriNode postPlace : node.post) {
                ++nodeVisitCount[keyList.indexOf(postPlace.id)];
                tokens[keyList.indexOf(postPlace.id)] += 1;
                //搜索成功的条件，到达target place
                if (postPlace.equals(targetPlace)) {
                    res.add(sb.toString());
                    return;
                }
            }

            //判断环路，如果当前变迁节点和这个后继库所已经访问了2次，则判断为已经产生了循环访问，直接返回
            for (PetriNode nextPetriNode : node.post) {
                if (node.post.size() > 1 && nodeVisitCount[keyList.indexOf(node.id)] >= 2 && nodeVisitCount[keyList.indexOf(nextPetriNode.id)] >= 2)    return;
            }

            //dfs
            postNodes.addAll(node.post);
            for (PetriNode postPlace : postNodes) {
                Set<PetriNode> tmpNodes = new HashSet<>(postNodes);
                tmpNodes.remove(postPlace);
                findPath(new StringBuilder(sb), postPlace, res, tmpNodes, tokens.clone(), nodeVisitCount.clone(), keyList, targetPlace);
            }
        } else {
            //库所节点
            for (PetriNode postTransition : node.post) {
                //环路
                if (node.post.size() > 1 && nodeVisitCount[keyList.indexOf(node.id)] >= 2 && nodeVisitCount[keyList.indexOf(postTransition.id)] >= 1)    continue;
                Set<PetriNode> tmpNodes = new HashSet<>(postNodes);
                //判断下一个变迁是否满足发射条件
                boolean isWork = true;
                for (PetriNode place : postTransition.pre)  if (tokens[keyList.indexOf(place.id)] <= 0) isWork = false;
                if (isWork)     tmpNodes.add(postTransition);
                for (PetriNode post : tmpNodes) {
                    Set<PetriNode> tmpPost = new HashSet<>(tmpNodes);
                    tmpPost.remove(post);
                    findPath(new StringBuilder(sb), post, res, tmpPost, tokens.clone(), nodeVisitCount.clone(), keyList, targetPlace);
                }
            }
        }
    }

    /**
     * 打印业务过程日志
     *
     * @param filePath 日志文件保存路径
     * @param res 可达路径结果集
     */
    public static void logHelper(String filePath, Set<String> res) {
        List<String> resultList = new LinkedList<>(res);
        Collections.sort(resultList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultList.size(); i++){
            //去除文件最后空行
            if (i != resultList.size() - 1) sb.append(String.format("%d %s\n", i + 1, resultList.get(i)));
            else sb.append(String.format("%d %s", i + 1, resultList.get(i)));
        }
        String content = sb.toString();
        FileOutputStream fileOutputStream = null;
        File file = new File(filePath);
        try {
            if (file.exists()) file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        getLogOfModel("src/Model4.pnml", "src/m4.txt");
    }
}