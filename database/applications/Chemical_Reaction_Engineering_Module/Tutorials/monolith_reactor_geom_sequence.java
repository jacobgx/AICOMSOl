/*
 * monolith_reactor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:30 by COMSOL 6.3.0.290. */
public class monolith_reactor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("inletInnerD", "0.061087[m]", "\u5165\u53e3\u5185\u76f4\u5f84");
    model.param().set("shellThickness", "0.001651[m]", "\u91d1\u5c5e\u539a\u5ea6");
    model.param().set("matThickness", "13[mm]", "\u652f\u6491\u6750\u6599\u539a\u5ea6");
    model.param().set("d_cat", "0.32[m]", "\u6574\u88c5\u53cd\u5e94\u5668\u76f4\u5f84");
    model.param().set("l_SCR", "0.4[m]", "SCR \u6574\u88c5\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("l_ASC", "0.06[m]", "ASC \u6574\u88c5\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("V_SCR", "pi*(d_cat/2)^2*l_SCR", "SCR \u6574\u88c5\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().set("V_ASC", "pi*(d_cat/2)^2*l_ASC", "ASC \u6574\u88c5\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().set("boxThickness", "0.024", "\u51e0\u4f55\u53c2\u6570");
    model.param().set("inletHeight", "0.043", "\u51e0\u4f55\u53c2\u6570");
    model.param().set("freeFlowHeight", "0.02", "\u51e0\u4f55\u53c2\u6570");
    model.param()
         .set("totalWidth", "d_cat/2+matThickness+boxThickness+2*shellThickness", "\u51e0\u4f55\u53c2\u6570");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d_cat/2", "l_SCR"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2")
         .set("size", new String[]{"matThickness+shellThickness", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "l_SCR+freeFlowHeight+l_ASC", 1);
    model.component("comp1").geom("geom1").feature("r2")
         .set("pos", new String[]{"d_cat/2", "-freeFlowHeight-l_ASC"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3")
         .set("size", new String[]{"d_cat/2+shellThickness-shellThickness", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "freeFlowHeight", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, -0.02});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "d_cat/2", 0);
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"d_cat/2", "l_ASC"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "-freeFlowHeight-l_ASC"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("r2", 7);
    model.component("comp1").geom("geom1").feature("thi1").set("keep", true);
    model.component("comp1").geom("geom1").feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi1").set("upthick", "shellThickness");
    model.component("comp1").geom("geom1").feature("thi1").set("convexcorner", "tangent");
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("thi1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"boxThickness", "1"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("size", "l_ASC+freeFlowHeight+l_SCR", 1);
    model.component("comp1").geom("geom1").feature("r5")
         .set("pos", new String[]{"d_cat/2+matThickness+shellThickness+shellThickness", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "-l_ASC-freeFlowHeight", 1);
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").feature("r5").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r5").set("layertop", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"inletInnerD", "1"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("size", "inletHeight", 1);
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "l_SCR+inletHeight*2"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"boxThickness", "1"});
    model.component("comp1").geom("geom1").feature("r7").setIndex("size", "inletHeight", 1);
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"inletInnerD", "0"});
    model.component("comp1").geom("geom1").feature("r7").setIndex("pos", "l_SCR+inletHeight*2", 1);
    model.component("comp1").geom("geom1").feature("r7").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").feature("r7").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r7").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r7").set("layertop", true);
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"totalWidth-inletInnerD", "1"});
    model.component("comp1").geom("geom1").feature("r8").setIndex("size", "inletHeight*2", 1);
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"inletInnerD", "0"});
    model.component("comp1").geom("geom1").feature("r8").setIndex("pos", "l_SCR", 1);
    model.component("comp1").geom("geom1").feature("r8").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").feature("r8").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r8").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r8").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r8").set("layertop", true);
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new String[]{"totalWidth - inletInnerD", "1"});
    model.component("comp1").geom("geom1").feature("r9").setIndex("size", "boxThickness", 1);
    model.component("comp1").geom("geom1").feature("r9").set("pos", new String[]{"inletInnerD", "0"});
    model.component("comp1").geom("geom1").feature("r9").setIndex("pos", "l_SCR+inletHeight*2-boxThickness", 1);
    model.component("comp1").geom("geom1").feature("r9").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("r10")
         .set("size", new String[]{"boxThickness+shellThickness", "1"});
    model.component("comp1").geom("geom1").feature("r10").setIndex("size", "inletHeight*2", 1);
    model.component("comp1").geom("geom1").feature("r10")
         .set("pos", new String[]{"d_cat/2+matThickness+shellThickness", "0"});
    model.component("comp1").geom("geom1").feature("r10").setIndex("pos", "l_SCR", 1);
    model.component("comp1").geom("geom1").feature("r10").setIndex("layer", "shellThickness", 0);
    model.component("comp1").geom("geom1").feature("r10").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r10").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r10").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r10");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.14, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.462, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.174651, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.462, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.1746619234313964, 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.4301309883594513, 1, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.14, 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.463651, 1, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.176302, 0, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.463651, 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.17631292343140004, 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 0.4301309883594513, 1, 2);
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.14, 0, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.484349, 1, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.198651, 0, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.48448984122275995, 1, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.198651, 0, 2);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 0.4301309883594513, 1, 2);
    model.component("comp1").geom("geom1").run("qb3");
    model.component("comp1").geom("geom1").create("qb4", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.14, 0, 0);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.486, 1, 0);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.200302, 0, 1);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.48614084122275997, 1, 1);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.200302, 0, 2);
    model.component("comp1").geom("geom1").feature("qb4").setIndex("p", 0.4301309883594513, 1, 2);
    model.component("comp1").geom("geom1").run("qb4");
    model.component("comp1").geom("geom1").create("qb5", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.062738, 0, 0);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.48, 1, 0);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.062738, 0, 1);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.463651, 1, 1);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.08, 0, 2);
    model.component("comp1").geom("geom1").feature("qb5").setIndex("p", 0.463651, 1, 2);
    model.component("comp1").geom("geom1").run("qb5");
    model.component("comp1").geom("geom1").create("qb6", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.061087, 0, 0);
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.48, 1, 0);
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.061087, 0, 1);
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.462, 1, 1);
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.08, 0, 2);
    model.component("comp1").geom("geom1").feature("qb6").setIndex("p", 0.462, 1, 2);
    model.component("comp1").geom("geom1").run("qb6");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("qb1", "qb2", "qb3", "qb4", "qb5", "qb6", "r10", "r6", "r7", "r8", "r9");
    model.component("comp1").geom("geom1").feature("mir1").set("specify", "edge");
    model.component("comp1").geom("geom1").feature("mir1").selection("edge").set("r8", 7);
    model.component("comp1").geom("geom1").feature("mir1").set("selresult", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("mir1");
    model.component("comp1").geom("geom1").feature("mov1").set("specify", "pos");
    model.component("comp1").geom("geom1").feature("mov1").selection("oldposvertex").set("mir1(10)", 10);
    model.component("comp1").geom("geom1").feature("mov1").selection("newposvertices").set("r5", 9);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("r11")
         .set("size", new String[]{"totalWidth", "l_SCR+inletHeight*2+freeFlowHeight+l_ASC+inletHeight*2"});
    model.component("comp1").geom("geom1").feature("r11")
         .set("pos", new String[]{"0", "-freeFlowHeight-l_ASC-inletHeight*2"});
    model.component("comp1").geom("geom1").run("r11");
    model.component("comp1").geom("geom1").create("r12", "Rectangle");
    model.component("comp1").geom("geom1").feature("r12").set("size", new String[]{"shellThickness", "1"});
    model.component("comp1").geom("geom1").feature("r12").setIndex("size", "boxThickness", 1);
    model.component("comp1").geom("geom1").feature("r12").set("pos", new double[]{0.108, 0.462});
    model.component("comp1").geom("geom1").feature().duplicate("r13", "r12");
    model.component("comp1").geom("geom1").feature("r13").set("pos", new double[]{0.108, -0.166});
    model.component("comp1").geom("geom1").run("r13");
    model.component("comp1").geom("geom1").create("r14", "Rectangle");
    model.component("comp1").geom("geom1").feature("r14").set("size", new String[]{"boxThickness", "1"});
    model.component("comp1").geom("geom1").feature("r14").setIndex("size", "shellThickness", 1);
    model.component("comp1").geom("geom1").feature("r14").set("pos", new double[]{0.1763, 0.3});
    model.component("comp1").geom("geom1").feature().duplicate("r15", "r14");
    model.component("comp1").geom("geom1").feature("r15").set("pos", new double[]{0.17465, 0.1});
    model.component("comp1").geom("geom1").run("r15");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("r5", 10);
    model.component("comp1").geom("geom1").feature("pare1").set("position", "projection");
    model.component("comp1").geom("geom1").feature("pare1").selection("vertexproj").set("r15", 2, 3);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 14, 19, 21, 23, 25, 26, 27, 28, 29, 30, 32, 34, 35, 36, 37, 40, 42, 44, 46, 48, 53, 54, 58, 59, 60, 61, 62, 63, 65, 67, 68, 69, 70, 71, 73, 78, 79, 82, 83, 88, 91, 93, 94, 96, 98, 99, 100, 110, 113, 114, 115, 117, 120, 121, 122, 124, 128, 131, 135, 138, 139, 140, 141, 143, 144, 145, 146, 147, 148, 149, 150, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 172, 174, 175, 177, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 205, 209, 213, 217, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 249, 250, 252, 253, 254, 256, 257, 259, 260, 261, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 283, 284, 285, 286, 313, 314, 320, 321);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ige1", 5);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ige1", 3);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("ige1", 14);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("ige1", 7, 15);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ige1", 12);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("ige1", 6);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("ige1", 4);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection")
         .set("ige1", 8, 9, 10, 11, 12, 13, 16, 17, 18);

    model.title(null);

    model.description("");

    model.label("monolith_reactor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
