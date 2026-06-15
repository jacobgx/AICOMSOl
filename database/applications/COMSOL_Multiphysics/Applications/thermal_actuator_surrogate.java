/*
 * thermal_actuator_surrogate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class thermal_actuator_surrogate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.label("thermal_actuator_surrogate.mph");

    model.title("\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b App");

    model
         .description("\u201c\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b\u201dApp \u6f14\u793a\u5982\u4f55\u5229\u7528\u4ee3\u7406\u6a21\u578b\u6765\u52a0\u901f\u591a\u7269\u7406\u573a\u5206\u6790\u7684\u8ba1\u7b97\uff0c\u8fd9\u79cd\u4ee3\u7406\u6a21\u578b\u9002\u7528\u4e8e\u5168\u53c2\u6570\u5316\u7684\u51e0\u4f55\u6a21\u578b\u3002\u8fd9\u662f\u4e00\u79cd\u66f4\u7b80\u5355\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u4f4e\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u8fd1\u4f3c\u6a21\u62df\u66f4\u590d\u6742\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u9ad8\u7684\u6a21\u578b\u7684\u884c\u4e3a\uff1b\u53ef\u4ee5\u5b9e\u73b0\u66f4\u5feb\u7684\u6a21\u578b\u8ba1\u7b97\u901f\u5ea6\uff0c\u4e3a App \u7528\u6237\u5e26\u6765\u4e86\u66f4\u5177\u4ea4\u4e92\u6027\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u540c\u65f6\u4e5f\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u5185\u63a8\u5e7f\u4eff\u771f\u7684\u4f7f\u7528\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u521b\u5efa\u4ee3\u7406\u6a21\u578b\uff0c\u5e76\u5728 App \u4e2d\u7528\u4e8e\u591a\u7269\u7406\u573a\u4eff\u771f\uff0c\u5176\u4e2d\u7684\u51e0\u4f55\u57fa\u4e8e\u53c2\u6570\u5316 CAD \u6a21\u578b\u3002");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.component("comp1").label("Thermal Actuator");

    model.param().loadFile("thermal_actuator_surrogate_parameters.txt");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "(noa==3)");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L3", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"L-L3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new String[]{"L4", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"L-L3-L4", "dw-d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new String[]{"wb", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new String[]{"L2", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"L-L2", "dw+gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"L-L2-wb", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new String[]{"L1", "d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"L-L1", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("size", new String[]{"wb", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0", "dw+d+2*gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"L-d", "dw+gap+d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new String[]{"d", "gap"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("pos", new String[]{"L-d", "dw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("uni1", 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 19, 20, 21, 22, 23, 28);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 2, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"d", "(dw+d+2*gap)+(dw+gap+d)-2.5*(wb-2*d)-d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"L-L2-wb+d", "d"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("size", new String[]{"wb-2*d", "2.5*(wb-2*d)"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .set("pos", new String[]{"L-L3-L4-wb+d", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3").setIndex("pos", "d", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("r3", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "d/3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"L-L3/4", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2")
         .set("pos", new String[]{"L-L3/2", "dw/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("r", "d/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3")
         .set("pos", new String[]{"L-3*L3/4", "dw/2"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 2, 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1", 10);
    model.component("comp1").geom("geom1").feature("sel1").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Ground");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni1", 29);
    model.component("comp1").geom("geom1").feature("sel2").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Applied Voltage");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("uni1", 48);
    model.component("comp1").geom("geom1").feature("sel3").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Third");
    model.component("comp1").geom("geom1").feature("sel3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("sel4").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("Upper Surface");
    model.component("comp1").geom("geom1").feature("sel4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("uni1", 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92);
    model.component("comp1").geom("geom1").feature("sel5").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("Other Surface");
    model.component("comp1").geom("geom1").feature("sel5").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("uni1", 154);
    model.component("comp1").geom("geom1").feature("sel6").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("Tip");
    model.component("comp1").geom("geom1").feature("sel6").set("contributeto", "csel6");
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("uni1", 67, 72, 77);
    model.component("comp1").geom("geom1").feature("sel7").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("Dimples");
    model.component("comp1").geom("geom1").feature("sel7").set("contributeto", "csel7");
    model.component("comp1").geom("geom1").run("endif1");

    model.param().set("noa", "2");

    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "(noa==2)");
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r6").active(false);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r7").active(false);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r8").active(false);
    model.component("comp1").geom("geom1").feature().duplicate("ext3", "ext1");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").runPre("ext3");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().duplicate("wp4", "wp2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").active(false);
    model.component("comp1").geom("geom1").feature().duplicate("ext4", "ext2");
    model.component("comp1").geom("geom1").feature("ext4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").runPre("ext4");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp4");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().duplicate("uni2", "uni1");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ext3", "ext4");
    model.component("comp1").geom("geom1").feature().duplicate("sel8", "sel1");
    model.component("comp1").geom("geom1").runPre("sel8");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("uni2", 28);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").feature().duplicate("sel9", "sel2");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("uni2", 10);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").feature().duplicate("sel10", "sel4");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("uni2", 4);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").feature().duplicate("sel11", "sel5");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection")
         .set("uni2", 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66);
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").feature().duplicate("sel12", "sel6");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").set("uni2", 108);
    model.component("comp1").geom("geom1").run("sel12");
    model.component("comp1").geom("geom1").feature().duplicate("sel13", "sel7");
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").set("uni2", 47, 52, 57);
    model.component("comp1").geom("geom1").run("sel13");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_csel1_bnd", "geom1_csel2_bnd", "geom1_csel3_bnd"});
    model.component("comp1").selection("uni1").label("Surface Contact");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("sigma", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("alpha", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Polysilicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("expr", "1/(2e-5*(1+1.25e-3*(T-298.15)))");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("argders", new String[][]{{"T", "d(1/(2e-5*(1+1.25e-3*(T-298.15))), T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("fununit", "S/m");
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("sigma")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("expr", "(3.725*(1-exp(-5.88e-3*(T-125)))+5.548e-4*T)*1e-6");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("argders", new String[][]{{"T", "d((3.725*(1-exp(-5.88e-3*(T-125)))+5.548e-4*T)*1e-6, T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("alpha")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("expr", "1/(-2.2e-11*T^3 + 9e-8*T^2 - 1e-5*T + 0.014)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("argders", new String[][]{{"T", "d(1/(-2.2e-11*T^3 + 9e-8*T^2 - 1e-5*T + 0.014), T)"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("plotargs", new String[][]{{"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma(T)", "0", "0", "0", "sigma(T)", "0", "0", "0", "sigma(T)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha(T)", "0", "0", "0", "alpha(T)", "0", "0", "0", "alpha(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "169[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "2e-5[\u03a9*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "1.25e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");

    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "DV");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htc_s");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "htc_us");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("uni1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eXX", 0, new String[]{"uX-alphaps*(T-T0)"});
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eYY", 0, new String[]{"vY-alphaps*(T-T0)"});
    model.component("comp1").physics("solid").feature("lemm1").featureInfo("info")
         .set("solid.eZZ", 0, new String[]{"wZ-alphaps*(T-T0)"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Electric Potential (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Electric Field (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Temperature (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("Stress (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def1").set("scale", 5);
    model.result("pg3").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().named("geom1_csel6_pnt");
    model.result().numerical("pev1").setIndex("expr", "solid.disp", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Point Evaluation 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").selection().all();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Volume Maximum 1");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();

    model.param().set("noa", "3");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").appendResult();
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").appendResult();

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("min_dw", "15", "Height of the cold arm, min bound");
    model.param("par2").set("max_dw", "40", "Height of the cold arm, max bound");
    model.param("par2").set("min_gap", "2.5", "Gap between arms, min bound");
    model.param("par2").set("max_gap", "7", "Gap between arms, max bound");
    model.param("par2").set("min_wv", "0", "Difference in length between hot arms, min bound");
    model.param("par2").set("max_wv", "50", "Difference in length between hot arms, max bound");
    model.param("par2").set("min_L", "150", "Actuator length, min bound");
    model.param("par2").set("max_L", "400", "Actuator length, max bound");
    model.param("par2").set("min_DV", "0.5", "Applied voltage, min bound");
    model.param("par2").set("max_DV", "10", "Applied voltage, max bound");

    model.component("comp1").common().create("smgs1", "SurrogateModelGeometrySampling");

    model.study("std1").create("sm", "SurrogateModelTraining");
    model.study("std1").feature("sm").setIndex("funcname", "", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "V", 0);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "0", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "0", "smgs1");
    model.study("std1").feature("sm").setIndex("funcname", "", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "T", 1);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "1", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "1", "smgs1");
    model.study("std1").feature("sm").setIndex("funcname", "", 2);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 2);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "2", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 2);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 2);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "2", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "u", 2);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "2", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "2", "smgs1");
    model.study("std1").feature("sm").setIndex("funcname", "", 3);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 3);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "3", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 3);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 3);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "3", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "v", 3);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "3", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "3", "smgs1");
    model.study("std1").feature("sm").setIndex("funcname", "", 4);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 4);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "4", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 4);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 4);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "4", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "w", 4);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "4", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "4", "smgs1");
    model.study("std1").feature("sm").setIndex("funcname", "", 5);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 5);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "5", "reduce");
    model.study("std1").feature("sm").setIndex("funcname", "", 5);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 5);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "5", "reduce");
    model.study("std1").feature("sm").setIndex("qoiexpression", "solid.misesGp", 5);
    model.study("std1").feature("sm").setEntry("qoiconfigurestudyinput", "5", "configure");
    model.study("std1").feature("sm").setEntry("geometrysampling", "5", "smgs1");
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 0);
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 0);
    model.study("std1").feature("sm").setIndex("pname", "dw", 0);
    model.study("std1").feature("sm").setEntry("lboundselection", "col1", "min_dw");
    model.study("std1").feature("sm").setEntry("uboundselection", "col1", "max_dw");
    model.study("std1").feature("sm").setEntry("punitselection", "col1", "um");
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 1);
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 1);
    model.study("std1").feature("sm").setIndex("pname", "gap", 1);
    model.study("std1").feature("sm").setEntry("lboundselection", "col2", "min_gap");
    model.study("std1").feature("sm").setEntry("uboundselection", "col2", "max_gap");
    model.study("std1").feature("sm").setEntry("punitselection", "col2", "um");
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 2);
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 2);
    model.study("std1").feature("sm").setIndex("pname", "wv", 2);
    model.study("std1").feature("sm").setEntry("lboundselection", "col3", "min_wv");
    model.study("std1").feature("sm").setEntry("uboundselection", "col3", "max_wv");
    model.study("std1").feature("sm").setEntry("punitselection", "col3", "um");
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 3);
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 3);
    model.study("std1").feature("sm").setIndex("pname", "L", 3);
    model.study("std1").feature("sm").setEntry("lboundselection", "col4", "min_L");
    model.study("std1").feature("sm").setEntry("uboundselection", "col4", "max_L");
    model.study("std1").feature("sm").setEntry("punitselection", "col4", "um");
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 4);
    model.study("std1").feature("sm").setIndex("pname", "alphaps", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/K", 4);
    model.study("std1").feature("sm").setIndex("pname", "DV", 4);
    model.study("std1").feature("sm").setEntry("lboundselection", "col5", "min_DV");
    model.study("std1").feature("sm").setEntry("uboundselection", "col5", "max_DV");
    model.study("std1").feature("sm").set("surrogatemodel", "dnn");
    model.study("std1").feature("sm").set("layertype", new String[]{"input", "dense", "dense"});
    model.study("std1").feature("sm").set("outfeatures", new int[]{1, 1, 1});
    model.study("std1").feature("sm").set("activation", new String[]{"none", "tanh", "tanh"});
    model.study("std1").feature("sm").set("layerDescription", new String[]{"Input", "Output, Activation=tanh", ""});
    model.study("std1").feature("sm").move("layertype", new int[]{2}, -1);
    model.study("std1").feature("sm").move("outfeatures", new int[]{2}, -1);
    model.study("std1").feature("sm").move("activation", new int[]{2}, -1);
    model.study("std1").feature("sm").move("layerDescription", new int[]{2}, -1);
    model.study("std1").feature("sm").set("layertype", new String[]{"input", "dense", "dense", "dense"});
    model.study("std1").feature("sm").set("outfeatures", new int[]{1, 1, 1, 1});
    model.study("std1").feature("sm").set("activation", new String[]{"none", "tanh", "tanh", "tanh"});
    model.study("std1").feature("sm")
         .set("layerDescription", new String[]{"Input", "Hidden, Output features=1, Activation=tanh", "Output, Activation=tanh", ""});
    model.study("std1").feature("sm").move("layertype", new int[]{3}, -1);
    model.study("std1").feature("sm").move("outfeatures", new int[]{3}, -1);
    model.study("std1").feature("sm").move("activation", new int[]{3}, -1);
    model.study("std1").feature("sm").move("layerDescription", new int[]{3}, -1);
    model.study("std1").feature("sm").set("layertype", new String[]{"input", "dense", "dense", "dense", "dense"});
    model.study("std1").feature("sm").set("outfeatures", new int[]{1, 1, 1, 1, 1});
    model.study("std1").feature("sm").set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh"});
    model.study("std1").feature("sm")
         .set("layerDescription", new String[]{"Input", "Hidden, Output features=1, Activation=tanh", "Hidden, Output features=1, Activation=tanh", "Output, Activation=tanh", ""});
    model.study("std1").feature("sm").move("layertype", new int[]{4}, -1);
    model.study("std1").feature("sm").move("outfeatures", new int[]{4}, -1);
    model.study("std1").feature("sm").move("activation", new int[]{4}, -1);
    model.study("std1").feature("sm").move("layerDescription", new int[]{4}, -1);
    model.study("std1").feature("sm")
         .set("layertype", new String[]{"input", "dense", "dense", "dense", "dense", "dense"});
    model.study("std1").feature("sm").set("outfeatures", new int[]{1, 1, 1, 1, 1, 1});
    model.study("std1").feature("sm")
         .set("activation", new String[]{"none", "tanh", "tanh", "tanh", "tanh", "tanh"});
    model.study("std1").feature("sm")
         .set("layerDescription", new String[]{"Input", "Hidden, Output features=1, Activation=tanh", "Hidden, Output features=1, Activation=tanh", "Hidden, Output features=1, Activation=tanh", "Output, Activation=tanh", ""});
    model.study("std1").feature("sm").move("layertype", new int[]{5}, -1);
    model.study("std1").feature("sm").move("outfeatures", new int[]{5}, -1);
    model.study("std1").feature("sm").move("activation", new int[]{5}, -1);
    model.study("std1").feature("sm").move("layerDescription", new int[]{5}, -1);
    model.study("std1").feature("sm").setIndex("outfeatures", "64", 1);
    model.study("std1").feature("sm").setIndex("outfeatures", "64", 2);
    model.study("std1").feature("sm").setIndex("outfeatures", "32", 3);
    model.study("std1").feature("sm").setIndex("outfeatures", "16", 4);
    model.study("std1").feature("sm").setIndex("funcname", "V", 0);
    model.study("std1").feature("sm").setIndex("funcname", "T", 1);
    model.study("std1").feature("sm").setIndex("funcname", "u", 2);
    model.study("std1").feature("sm").setIndex("funcname", "v", 3);
    model.study("std1").feature("sm").setIndex("funcname", "w", 4);
    model.study("std1").feature("sm").setIndex("funcname", "mises", 5);
    model.study("std1").feature("sm").setEntry("file", "0", "external");
    model.study("std1").feature("sm").setEntry("file", "1", "external");
    model.study("std1").feature("sm").setEntry("file", "2", "external");
    model.study("std1").feature("sm").setEntry("file", "3", "external");
    model.study("std1").feature("sm").setEntry("file", "4", "external");
    model.study("std1").feature("sm").setEntry("file", "5", "external");
    model.study("std1").feature("sm").set("nsolvenonadp", 4000);
    model.study("std1").feature("sm").set("errorhandling", "later");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("pd1").feature("so1").set("psol", "sol2");
    model.batch("pd1").run("compute");

    model.result("pg1").run();

    model.func("dnn1").set("batchsize", 4096);
    model.func("dnn1").set("gputraining", true);
    model.func("dnn1").run();
    model.func("dnn1").importData();
    model.func("dnn1").exportData("thermal_actuator_surrogate_QoI1-6_4000.csv");
    model.func("dnn1").set("filename", "thermal_actuator_surrogate_QoI1-6.csv");

    model.study("std1").feature("sm").set("nsolvenonadp", 1);
    model.study("std1").createAutoSequences("all");

    model.batch("pd1").run("compute");

    model.result("pg1").run();

    model.func("dnn1").set("filename", "thermal_actuator_surrogate_QoI1-6.csv");
    model.func("dnn1").importData();

    model.study("std1").feature("sm").active(false);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", false);
    model.study("std1").feature("stat").setSolveFor("/common/smgs1", false);

    model.component("comp1").geom("geom1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().param().set("DV2", "5[V]");
    model.result().param().descr("DV2", "Voltage");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("expr", "dnn1_V(x,y,z,dw,gap,wv,L,DV2)");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("vol2", "vol1");
    model.result("pg3").run();
    model.result("pg3").feature("vol2").set("expr", "dnn1_T(x,y,z,dw,gap,wv,L,DV2)");
    model.result("pg3").run();
    model.result("pg3").feature("vol2").feature("def1")
         .set("expr", new String[]{"dnn1_u(x,y,z,dw,gap,wv,L,DV2)", "v", "w"});
    model.result("pg3").feature("vol2").feature("def1").setIndex("expr", "dnn1_v(x,y,z,dw,gap,wv,L,DV2)", 1);
    model.result("pg3").feature("vol2").feature("def1").setIndex("expr", "dnn1_w(x,y,z,dw,gap,wv,L,DV2)", 2);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").active(false);
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("unit", "MPa");
    model.result("pg4").feature().duplicate("vol2", "vol1");
    model.result("pg4").run();
    model.result("pg4").feature("vol2").set("expr", "dnn1_mises(x,y,z,dw,gap,wv,L,DV2)");
    model.result("pg4").run();
    model.result("pg4").feature("vol2").feature("def")
         .set("expr", new String[]{"dnn1_u(x,y,z,dw,gap,wv,L,DV2)", "v", "w"});
    model.result("pg4").feature("vol2").feature("def").setIndex("expr", "dnn1_v(x,y,z,dw,gap,wv,L,DV2)", 1);
    model.result("pg4").feature("vol2").feature("def").setIndex("expr", "dnn1_w(x,y,z,dw,gap,wv,L,DV2)", 2);
    model.result("pg4").run();
    model.result("pg4").feature("vol1").active(false);
    model.result().numerical("pev1")
         .setIndex("expr", "sqrteps(real(dnn1_u(x,y,z,dw,gap,wv,L,DV2))^2+real(dnn1_v(x,y,z,dw,gap,wv,L,DV2))^2+real(dnn1_w(x,y,z,dw,gap,wv,L,DV2))^2)", 0);
    model.result().numerical("max1").setIndex("expr", "dnn1_T(x,y,z,dw,gap,wv,L,DV2)", 0);
    model.result("pg3").run();

    model.title("\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b App");

    model
         .description("\u201c\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b\u201dApp \u6f14\u793a\u5982\u4f55\u5229\u7528\u4ee3\u7406\u6a21\u578b\u6765\u52a0\u901f\u591a\u7269\u7406\u573a\u5206\u6790\u7684\u8ba1\u7b97\uff0c\u8fd9\u79cd\u4ee3\u7406\u6a21\u578b\u9002\u7528\u4e8e\u5168\u53c2\u6570\u5316\u7684\u51e0\u4f55\u6a21\u578b\u3002\u8fd9\u662f\u4e00\u79cd\u66f4\u7b80\u5355\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u4f4e\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u8fd1\u4f3c\u6a21\u62df\u66f4\u590d\u6742\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u9ad8\u7684\u6a21\u578b\u7684\u884c\u4e3a\uff1b\u53ef\u4ee5\u5b9e\u73b0\u66f4\u5feb\u7684\u6a21\u578b\u8ba1\u7b97\u901f\u5ea6\uff0c\u4e3a App \u7528\u6237\u5e26\u6765\u4e86\u66f4\u5177\u4ea4\u4e92\u6027\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u540c\u65f6\u4e5f\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u5185\u63a8\u5e7f\u4eff\u771f\u7684\u4f7f\u7528\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u521b\u5efa\u4ee3\u7406\u6a21\u578b\uff0c\u5e76\u5728 App \u4e2d\u7528\u4e8e\u591a\u7269\u7406\u573a\u4eff\u771f\uff0c\u5176\u4e2d\u7684\u51e0\u4f55\u57fa\u4e8e\u53c2\u6570\u5316 CAD \u6a21\u578b\u3002");

    model.label("thermal_actuator_surrogate.mph");

    model.result("pg3").run();

    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").label("\u7535\u52bf");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u573a");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b App \u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u5b8c\u5168\u53c2\u6570\u5316\u51e0\u4f55\u6a21\u578b\u7684\u4ee3\u7406\u6a21\u578b\u52a0\u901f\u591a\u7269\u7406\u573a\u5206\u6790\u7684\u8ba1\u7b97\u3002\u4ee3\u7406\u6a21\u578b\u662f\u4e00\u79cd\u8f83\u7b80\u5355\u7684\u6a21\u578b\uff0c\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u8f83\u4f4e\uff0c\u7528\u4e8e\u8fd1\u4f3c\u8f83\u590d\u6742\u7684\u6a21\u578b\uff08\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u8f83\u9ad8\uff09\u7684\u884c\u4e3a\u3002\u4ee3\u7406\u6a21\u578b\u53ef\u63d0\u4f9b\u66f4\u5feb\u7684\u6a21\u578b\u8bc4\u4f30\uff0c\u4e3a App \u7528\u6237\u63d0\u4f9b\u4ea4\u4e92\u6027\u66f4\u5f3a\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u5e76\u4f7f\u6a21\u62df\u7684\u4f7f\u7528\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u4e2d\u63a8\u5e7f\u3002\u672c\u793a\u4f8b\u8bf4\u660e\u4e86\u5982\u4f55\u521b\u5efa\u4ee3\u7406\u6a21\u578b\uff0c\u5e76\u5728 App \u4e2d\u7528\u4e8e\u591a\u7269\u7406\u573a\u4eff\u771f\uff0c\u5176\u4e2d\u51e0\u4f55\u4f53\u57fa\u4e8e\u53c2\u6570\u5316 CAD \u6a21\u578b\u3002");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec1").set("heading", "Global Definitions");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u6839\u8282\u70b9");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").set("heading", "Parameters");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").label("\u53c2\u6570 1");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec1").feature()
         .create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").label("\u53c2\u6570 2");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").feature()
         .create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("sec2").feature("param1")
         .set("noderef", "par2");
    model.result().report("rpt1").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").set("heading", "Functions");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").feature()
         .create("func1", "Functions");
    model.result().report("rpt1").feature("sec1").feature("sec2").feature("sec1").feature("func1")
         .set("includesettings", false);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u70ed\u6267\u884c\u5668");
    model.result().report("rpt1").feature("sec2").set("heading", "Thermal Actuator");
    model.result().report("rpt1").feature("sec2").feature().create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("heading", "Definitions");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").label("\u9009\u62e9");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").set("heading", "Selections");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").feature()
         .create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").feature("sec1")
         .label("\u9762\u63a5\u89e6");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").feature("sec1")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").feature("sec1").feature()
         .create("sel1", "Selection");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec1").feature("sec1").feature("sel1")
         .set("noderef", "uni1");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2").label("\u5750\u6807\u7cfb");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2")
         .set("heading", "Coordinate Systems");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2").feature()
         .create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2").feature("sec1")
         .label("\u8fb9\u754c\u5750\u6807\u7cfb 1");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2").feature("sec1")
         .set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("sec2").feature("sec1").feature()
         .create("csys1", "CoordinateSystem");
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u51e0\u4f55 1");
    model.result().report("rpt1").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("geom1", "Geometry");

    return model;
  }

  public static Model run3(Model model) {
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec3").set("heading", "Materials");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").label("\u591a\u6676\u7845");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").feature()
         .create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec4").label("\u7535\u6d41");
    model.result().report("rpt1").feature("sec2").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec4").feature().create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec5").label("\u56fa\u4f53\u4f20\u70ed");
    model.result().report("rpt1").feature("sec2").feature("sec5").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec5").feature().create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature("sec5").feature("phys1").set("noderef", "ht");
    model.result().report("rpt1").feature("sec2").feature().create("sec6", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec6").label("\u56fa\u4f53\u529b\u5b66");
    model.result().report("rpt1").feature("sec2").feature("sec6").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec6").feature().create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature("sec6").feature("phys1").set("noderef", "solid");
    model.result().report("rpt1").feature("sec2").feature().create("sec7", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec7").label("\u591a\u7269\u7406\u573a");
    model.result().report("rpt1").feature("sec2").feature("sec7").set("heading", "Multiphysics");
    model.result().report("rpt1").feature("sec2").feature("sec7").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec7").feature("sec1").label("\u7535\u78c1\u70ed 1");
    model.result().report("rpt1").feature("sec2").feature("sec7").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec7").feature("sec1").feature()
         .create("mph1", "Multiphysics");
    model.result().report("rpt1").feature("sec2").feature().create("sec8", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec8").label("\u7f51\u683c 1");
    model.result().report("rpt1").feature("sec2").feature("sec8").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec8").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec2").feature("sec8").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec8").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7814\u7a76 1");
    model.result().report("rpt1").feature("sec3").set("heading", "Study 1");
    model.result().report("rpt1").feature("sec3").feature().create("std1", "Study");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").set("heading", "Results");
    model.result().report("rpt1").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec4").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("param1", "ResultParameter");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec4").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").set("heading", "Datasets");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").feature()
         .create("dset1", "DataSet");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature()
         .create("dset1", "DataSet");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature("dset1")
         .set("noderef", "dset2");
    model.result().report("rpt1").feature("sec4").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec3").label("\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec4").feature("sec3").set("heading", "Derived Values");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec1").label("\u70b9\u8ba1\u7b97 1");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec1").feature()
         .create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec2")
         .label("\u4f53\u6700\u5927\u503c 1");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec2").feature()
         .create("num1", "DerivedValues");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("sec2").feature("num1")
         .set("noderef", "max1");
    model.result().report("rpt1").feature("sec4").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec4").label("\u8868\u683c");
    model.result().report("rpt1").feature("sec4").feature("sec4").set("heading", "Tables");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec1")
         .set("heading", "Design of Experiments");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec1").feature()
         .create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec2").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec4").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec5").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec4").feature("sec5").set("heading", "Plot Groups");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec1").label("\u7535\u52bf (ec)");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec2").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec3").label("\u6e29\u5ea6 (ht)");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec3").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec4").label("\u5e94\u529b (solid)");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("sec4").feature("pg1")
         .set("noderef", "pg4");

    model.title("\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b App");

    model
         .description("\u201c\u70ed\u6267\u884c\u5668\u4ee3\u7406\u6a21\u578b\u201dApp \u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5168\u53c2\u6570\u5316\u51e0\u4f55\u6a21\u578b\u7684\u4ee3\u7406\u6a21\u578b\u6765\u52a0\u901f\u591a\u7269\u7406\u573a\u5206\u6790\u7684\u8ba1\u7b97\u3002\u8fd9\u662f\u4e00\u79cd\u66f4\u7b80\u5355\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u4f4e\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u8fd1\u4f3c\u6a21\u62df\u8f83\u590d\u6742\u6a21\u578b\uff08\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u66f4\u9ad8\uff09\u7684\u884c\u4e3a\uff1b\u53ef\u4ee5\u5b9e\u73b0\u66f4\u5feb\u7684\u6a21\u578b\u8ba1\u7b97\u901f\u5ea6\uff0c\u4e3a App \u7528\u6237\u63d0\u4f9b\u66f4\u5177\u4ea4\u4e92\u6027\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u540c\u65f6\u4e5f\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u5185\u63a8\u5e7f\u4eff\u771f\u7684\u4f7f\u7528\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u521b\u5efa\u4ee3\u7406\u6a21\u578b\u5e76\u5728 App \u4e2d\u7528\u4e8e\u591a\u7269\u7406\u573a\u4eff\u771f\uff0c\u5176\u4e2d\u7684\u51e0\u4f55\u57fa\u4e8e\u53c2\u6570\u5316 CAD \u6a21\u578b\u3002");

    model.label("thermal_actuator_surrogate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
