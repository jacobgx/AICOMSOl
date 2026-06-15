/*
 * bracket_topology_optimization_stl_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:47 by COMSOL 6.3.0.290. */
public class bracket_topology_optimization_stl_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D1", "1.4[cm]", "\u7ea6\u675f\u76f4\u5f84");
    model.param().set("D2", "5[cm]", "\u8f7d\u8377\u76f4\u5f84");
    model.param().set("H", "10[cm]", "\u9ad8\u5ea6");
    model.param().set("W", "21.5[cm]", "\u5bbd\u5ea6");
    model.param().set("L", "35[cm]", "\u957f\u5ea6");
    model.param().set("thickness", "8[mm]", "\u652f\u67b6\u539a\u5ea6");
    model.param().set("rfillet", "8[mm]", "\u5706\u89d2\u534a\u5f84");
    model.param().set("Y1", "2[cm]", "\u7ea6\u675f\u5b54\u7684\u4f4d\u7f6e");
    model.param().set("X1", "4[cm]", "\u7ea6\u675f\u5b54\u7684\u4f4d\u7f6e");
    model.param().set("DY1", "6[cm]", "\u7ea6\u675f\u5b54\u7684\u95f4\u8ddd");
    model.param().set("YC", "-30[cm]", "\u52a0\u8f7d\u5b54\u4f4d\u7f6e");
    model.param().set("L1", "10[cm]", "\u8fde\u63a5\u677f\u5bbd\u5ea6");
    model.param().set("rfillet2", "2[cm]", "\u7ea6\u675f\u677f\u5706\u89d2\u534a\u5f84");
    model.param().set("W1", "7[cm]", "\u7ea6\u675f\u677f\u5bbd\u5ea6");
    model.param().set("Da", "1[cm]", "\u56fa\u5b9a\u5b54\u586b\u5145");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "D2/2+Da/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-W/2", "YC", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "D1/2+Da/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"W/2-X1", "-Y1", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("selresult", true);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("cyl2");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-DY1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"rfillet+thickness", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "L1+rfillet", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "rfillet+thickness", 2);
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"W/2-rfillet-thickness", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-L1-rfillet", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "H/2-rfillet-thickness", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("blk1", "cyl2", "mir1", "mov1");
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "-1", "-H/2"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool")
         .set("blk1", "blk2", "cyl1", "cyl2", "mir1", "mir2", "mov1");
    model.component("comp1").geom("geom1").feature("par1").set("selresult", true);
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u7ea6\u675f 1");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "D1/2*1.01");
    model.component("comp1").geom("geom1").feature("cylsel1").set("pos", new String[]{"W/2-X1", "-Y1", "0"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("cylsel2", "cylsel1");
    model.component("comp1").geom("geom1").feature("cylsel2").label("\u7ea6\u675f 2");
    model.component("comp1").geom("geom1").feature("cylsel2").set("pos", new String[]{"W/2-X1", "-Y1-DY1", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cylsel3", "cylsel2");
    model.component("comp1").geom("geom1").feature("cylsel3").label("\u7ea6\u675f 3");
    model.component("comp1").geom("geom1").feature("cylsel3").set("pos", new String[]{"-W/2+X1", "-Y1-DY1", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cylsel4", "cylsel3");
    model.component("comp1").geom("geom1").feature("cylsel4").label("\u7ea6\u675f 4");
    model.component("comp1").geom("geom1").feature("cylsel4").set("pos", new String[]{"-W/2+X1", "-Y1", "0"});
    model.component("comp1").geom("geom1").run("cylsel4");
    model.component("comp1").geom("geom1").create("cylsel5", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel5").label("\u8f7d\u8377 1a");
    model.component("comp1").geom("geom1").feature("cylsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel5").set("r", "D2/2*1.01");
    model.component("comp1").geom("geom1").feature("cylsel5").set("bottom", 0);
    model.component("comp1").geom("geom1").feature("cylsel5").set("angle1", 90);
    model.component("comp1").geom("geom1").feature("cylsel5").set("angle2", 270);
    model.component("comp1").geom("geom1").feature("cylsel5").set("pos", new String[]{"0", "YC", "0"});
    model.component("comp1").geom("geom1").feature("cylsel5").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cylsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("cylsel6", "cylsel5");
    model.component("comp1").geom("geom1").feature("cylsel6").label("\u8f7d\u8377 1b");
    model.component("comp1").geom("geom1").feature("cylsel6").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("cylsel6").set("angle2", 90);
    model.component("comp1").geom("geom1").feature().duplicate("cylsel7", "cylsel6");
    model.component("comp1").geom("geom1").feature("cylsel7").label("\u8f7d\u8377 2a");
    model.component("comp1").geom("geom1").feature("cylsel7").set("top", 0);
    model.component("comp1").geom("geom1").feature("cylsel7").set("bottom", Double.NEGATIVE_INFINITY);
    model.component("comp1").geom("geom1").feature().duplicate("cylsel8", "cylsel7");
    model.component("comp1").geom("geom1").feature("cylsel8").label("\u8f7d\u8377 2b");
    model.component("comp1").geom("geom1").feature("cylsel8").set("angle1", 90);
    model.component("comp1").geom("geom1").feature("cylsel8").set("angle2", 270);
    model.component("comp1").geom("geom1").run("cylsel8");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u8f7d\u8377 1");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"cylsel5", "cylsel7"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u8f7d\u8377 2");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"cylsel6", "cylsel8"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .label("\u8bbe\u8ba1\u7a7a\u95f4\u4e4b\u5916\u7684\u57df");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 3);
    model.component("comp1").geom("geom1").feature("adjsel1")
         .set("input", new String[]{"cylsel1", "cylsel2", "cylsel3", "cylsel4"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8bbe\u8ba1\u7a7a\u95f4\u57df");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"par1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"cyl1", "cyl2"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("cylsel9", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel9").label("\u6750\u6599\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("cylsel9").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel9").set("r", "Da+D2/2*1.01");
    model.component("comp1").geom("geom1").feature("cylsel9").set("pos", new String[]{"0", "YC", "0"});
    model.component("comp1").geom("geom1").feature("cylsel9").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cylsel9").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel9");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u6e90\u57df");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u76ee\u6807\u57df");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "-eps");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u81ea\u7531\u56db\u9762\u4f53\u7f51\u683c\u57df");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "-W/2+thickness+rfillet*0.4");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "-W/2+thickness+rfillet*0.6");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u626b\u63a0\u57df");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"boxsel3"});
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("unisel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("adjsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cylsel9");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("boxsel3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("difsel2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u7f51\u683c\u5212\u5206");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u955c\u50cf\u57df");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "0.001*W");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-L/2");

    model.title(null);

    model.description("");

    model.label("bracket_topology_optimization_stl_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
