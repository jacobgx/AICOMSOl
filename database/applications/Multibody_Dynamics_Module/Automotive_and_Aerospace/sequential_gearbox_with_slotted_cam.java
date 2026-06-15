/*
 * sequential_gearbox_with_slotted_cam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:21 by COMSOL 6.3.0.290. */
public class sequential_gearbox_with_slotted_cam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n1i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e00\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param().set("n2i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e8c\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param().set("n3i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e09\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param().set("n4i", "20", "\u9f7f\u6570\uff0c\u7b2c\u56db\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param().set("n1o", "76", "\u9f7f\u6570\uff0c\u7b2c\u4e00\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param().set("n2o", "44", "\u9f7f\u6570\uff0c\u7b2c\u4e8c\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param().set("n3o", "28", "\u9f7f\u6570\uff0c\u7b2c\u4e09\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param().set("n4o", "20", "\u9f7f\u6570\uff0c\u7b2c\u56db\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param().set("gear_ratio1", "n1o/n1i", "\u9f7f\u6bd4\uff0c\u7b2c\u4e00\u9f7f\u8f6e");
    model.param().set("gear_ratio2", "n2o/n2i", "\u9f7f\u6bd4\uff0c\u7b2c\u4e8c\u9f7f\u8f6e");
    model.param().set("gear_ratio3", "n3o/n3i", "\u9f7f\u6bd4\uff0c\u7b2c\u4e09\u9f7f\u8f6e");
    model.param().set("gear_ratio4", "n4o/n4i", "\u9f7f\u6bd4\uff0c\u7b2c\u56db\u9f7f\u8f6e");
    model.param().set("cdy", "10[cm]", "\u8f74\u4e2d\u5fc3\u95f4\u8ddd");
    model.param().set("cdx", "5[cm]", "\u9f7f\u8f6e\u4e2d\u5fc3\u95f4\u8ddd");
    model.param()
         .set("d1i", "2*cdy/(1+gear_ratio1)", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e00\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param()
         .set("d1o", "2*cdy-d1i", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e00\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param()
         .set("d2i", "2*cdy/(1+gear_ratio2)", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e8c\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param()
         .set("d2o", "2*cdy-d2i", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e8c\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param()
         .set("d3i", "2*cdy/(1+gear_ratio3)", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e09\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param()
         .set("d3o", "2*cdy-d3i", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u4e09\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param()
         .set("d4i", "2*cdy/(1+gear_ratio4)", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u56db\u9f7f\u8f6e\uff08\u8f93\u5165\u8f74\uff09");
    model.param()
         .set("d4o", "2*cdy-d4i", "\u8282\u8ddd\u76f4\u5f84\uff0c\u7b2c\u56db\u9f7f\u8f6e\uff08\u8f93\u51fa\u8f74\uff09");
    model.param().set("alpha", "25[deg]", "\u538b\u529b\u89d2");
    model.param().set("omega", "80[rad/s]/3", "\u5f15\u64ce\u6216\u8f93\u5165\u8f74\u901f\u5ea6");
    model.param().set("omega_c", "15[rad/s]/3", "\u51f8\u8f6e\u901f\u5ea6");
    model.param().set("T_ext", "0.1[N*m]", "\u963b\u529b\u8f6c\u77e9");
    model.param().set("tf", "1.33[s]", "\u6700\u540e\u65f6\u95f4");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "sequential_gearbox_with_slotted_cam.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("dm1", "DistanceMeasurement");
    model.component("comp1").geom("geom1").feature("dm1").selection("ent1").set("imp1(6)", 5);
    model.component("comp1").geom("geom1").feature("dm1").selection("ent2").set("imp1(6)", 6);
    model.component("comp1").geom("geom1").run("dm1");
    model.component("comp1").geom("geom1").feature().duplicate("dm2", "dm1");
    model.component("comp1").geom("geom1").feature("dm2").selection("ent1").clear("imp1(6)");
    model.component("comp1").geom("geom1").feature("dm2").selection("ent1").set("imp1(12)", new int[]{6});
    model.component("comp1").geom("geom1").feature("dm2").selection("ent2").clear("imp1(6)");
    model.component("comp1").geom("geom1").feature("dm2").selection("ent2").set("imp1(12)", new int[]{5});
    model.component("comp1").geom("geom1").run("dm2");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("imp1(6)", 2, 5, 6, 9);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").feature().duplicate("cm2", "cm1");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").clear("imp1(6)");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").set("imp1(6)", 10, 13, 14, 17);
    model.component("comp1").geom("geom1").run("cm2");
    model.component("comp1").geom("geom1").feature().duplicate("cm3", "cm2");
    model.component("comp1").geom("geom1").feature("cm3").selection("ent").clear("imp1(6)");
    model.component("comp1").geom("geom1").feature("cm3").selection("ent").set("imp1(12)", 2, 5, 6, 9);
    model.component("comp1").geom("geom1").run("cm3");
    model.component("comp1").geom("geom1").feature().duplicate("cm4", "cm3");
    model.component("comp1").geom("geom1").feature("cm4").selection("ent").clear("imp1(12)");
    model.component("comp1").geom("geom1").feature("cm4").selection("ent").set("imp1(12)", 10, 13, 14, 17);
    model.component("comp1").geom("geom1").run("cm4");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("imp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dm1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("dm2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cm1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cm2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cm3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("cm4");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 261, 321);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").feature().duplicate("sel2", "sel1");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 1, 130);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").feature().duplicate("sel3", "sel2");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 131, 260);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").feature().duplicate("sel4", "sel3");
    model.component("comp1").geom("geom1").feature("sel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").remove("fin", 131, 260);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .add("fin", 264, 265, 271, 272, 273, 274, 275, 276, 279, 282, 283, 284);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").feature().duplicate("sel5", "sel4");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .remove("fin", 264, 265, 271, 272, 273, 274, 275, 276, 279, 282, 283, 284);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .add("fin", 292, 293, 299, 300, 301, 302, 305, 306, 307, 310, 311, 312);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").feature().duplicate("sel6", "sel5");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .add("fin", 264, 265, 266, 267, 268, 269, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 294, 295, 296, 297, 303, 304, 308, 309, 313, 314, 315, 316, 317, 318, 319);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").feature().duplicate("sel7", "sel6");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection")
         .remove("fin", 264, 265, 266, 267, 268, 269, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").add("fin", 1191, 1675);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel6", "sel7"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").feature().duplicate("sel8", "sel7");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(3);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection")
         .set("fin", 6, 12, 13, 16, 22, 23, 24, 27);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").feature().duplicate("sel9", "sel8");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("fin", 8, 11, 18, 21);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").feature().duplicate("unisel2", "unisel1");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 3);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"sel8", "sel9"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").feature().duplicate("sel10", "sel9");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection")
         .set("fin", 5, 6, 14, 16, 22, 26, 27, 28);
    model.component("comp1").geom("geom1").feature("sel10").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel10")
         .set("customcolor", new double[]{0.545098066329956, 0.6941176652908325, 0.8509804010391235});
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").feature().duplicate("sel11", "sel10");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("fin", 4, 29);
    model.component("comp1").geom("geom1").feature("sel11")
         .set("customcolor", new double[]{0.45490196347236633, 0.8588235378265381, 0.8117647171020508});
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").feature().duplicate("sel12", "sel11");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection")
         .set("fin", 7, 8, 9, 12, 13, 15, 17, 18, 19, 23, 24, 25);
    model.component("comp1").geom("geom1").feature("sel12")
         .set("customcolor", new double[]{0.8392156958580017, 0.49803921580314636, 0.47843137383461});
    model.component("comp1").geom("geom1").run("sel12");
    model.component("comp1").geom("geom1").feature().duplicate("sel13", "sel12");
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").set("fin", 1, 2);
    model.component("comp1").geom("geom1").feature("sel13")
         .set("customcolor", new double[]{0.49803921580314636, 0.5098039507865906, 0.5098039507865906});
    model.component("comp1").geom("geom1").run("sel13");
    model.component("comp1").geom("geom1").feature().duplicate("sel14", "sel13");
    model.component("comp1").geom("geom1").feature("sel14").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").feature("sel14")
         .set("customcolor", new double[]{0.8470588326454163, 0.7960784435272217, 0.7960784435272217});
    model.component("comp1").geom("geom1").run("sel14");
    model.component("comp1").geom("geom1").feature().duplicate("sel15", "sel14");
    model.component("comp1").geom("geom1").feature("sel15").selection("selection").set("fin", 10, 11, 20, 21);
    model.component("comp1").geom("geom1").feature("sel15")
         .set("customcolor", new double[]{0.7411764860153198, 0.7411764860153198, 0.6470588445663452});
    model.component("comp1").geom("geom1").run("sel15");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter("fin");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel5");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel6");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel7");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("unisel1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel8");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel9");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("unisel2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel10");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel11");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel12");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel13");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel14");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sel15");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "angVel");
    model.component("comp1").func("pw1").setIndex("pieces", "t0", 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t1", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "omega_c", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t1", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t2", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 0, 1, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t2", 2, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t3", 2, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "omega_c", 2, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t3", 3, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t4", 3, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 0, 3, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t4", 4, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t5", 4, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "omega_c", 4, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t5", 5, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t6", 5, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 0, 5, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t6", 6, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t7", 6, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "omega_c", 6, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t7", 7, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t8", 7, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 0, 7, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t8", 8, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "tf", 8, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "omega_c", 8, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "rad/s");
    model.component("comp1").func("pw1").setIndex("pname", "t0", 0);
    model.component("comp1").func("pw1").setIndex("plist", 0, 0);
    model.component("comp1").func("pw1").setIndex("pname", "t1", 1);
    model.component("comp1").func("pw1").setIndex("plist", 0, 1);
    model.component("comp1").func("pw1").setIndex("plist", 0.04, 1);
    model.component("comp1").func("pw1").setIndex("pname", "t2", 2);
    model.component("comp1").func("pw1").setIndex("plist", 0, 2);
    model.component("comp1").func("pw1").setIndex("plist", 0.1, 2);
    model.component("comp1").func("pw1").setIndex("pname", "t3", 3);
    model.component("comp1").func("pw1").setIndex("plist", 0, 3);
    model.component("comp1").func("pw1").setIndex("plist", 0.155, 3);
    model.component("comp1").func("pw1").setIndex("pname", "t4", 4);
    model.component("comp1").func("pw1").setIndex("plist", 0, 4);
    model.component("comp1").func("pw1").setIndex("plist", 0.285, 4);
    model.component("comp1").func("pw1").setIndex("pname", "t5", 5);
    model.component("comp1").func("pw1").setIndex("plist", 0, 5);
    model.component("comp1").func("pw1").setIndex("plist", 0.37, 5);
    model.component("comp1").func("pw1").setIndex("pname", "t6", 6);
    model.component("comp1").func("pw1").setIndex("plist", 0, 6);
    model.component("comp1").func("pw1").setIndex("plist", 0.7, 6);
    model.component("comp1").func("pw1").setIndex("pname", "t7", 7);
    model.component("comp1").func("pw1").setIndex("plist", 0, 7);
    model.component("comp1").func("pw1").setIndex("plist", 0.895, 7);
    model.component("comp1").func("pw1").setIndex("pname", "t8", 8);
    model.component("comp1").func("pw1").setIndex("plist", 0, 8);
    model.component("comp1").func("pw1").setIndex("plist", 1.3, 8);
    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.04);
    model.component("comp1").func("step1").set("locationdef", "beginning");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(3);
    model.component("comp1").physics("mbd").feature().duplicate("rd2", "rd1");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(1);
    model.component("comp1").physics("mbd").feature().duplicate("rd3", "rd2");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(2);
    model.component("comp1").physics("mbd").feature().duplicate("rd4", "rd3");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(10, 11);
    model.component("comp1").physics("mbd").feature().duplicate("rd5", "rd4");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(20, 21);
    model.component("comp1").physics("mbd").feature().duplicate("rd6", "rd5");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(17, 18, 19);
    model.component("comp1").physics("mbd").feature().duplicate("rd7", "rd6");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(7, 8, 9);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").add("rd5");
    model.nodeGroup("grp1").add("rd6");
    model.nodeGroup("grp1").add("rd7");

    model.component("comp1").physics("mbd").create("spg1", "SpurGear", 3);
    model.component("comp1").physics("mbd").feature("spg1").selection().set(4);
    model.component("comp1").physics("mbd").feature("spg1").set("nt", "n1i");
    model.component("comp1").physics("mbd").feature("spg1").set("dp", "d1i");
    model.component("comp1").physics("mbd").feature("spg1").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("spg1").set("eg", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature("spg1").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature().duplicate("spg2", "spg1");
    model.component("comp1").physics("mbd").feature("spg2").selection().set(5, 6);
    model.component("comp1").physics("mbd").feature("spg2").set("nt", "n1o");
    model.component("comp1").physics("mbd").feature("spg2").set("dp", "d1o");
    model.component("comp1").physics("mbd").feature("spg2").set("xc", new String[]{"0", "cdy", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg3", "spg2");
    model.component("comp1").physics("mbd").feature("spg3").selection().set(14, 16);
    model.component("comp1").physics("mbd").feature("spg3").set("nt", "n4i");
    model.component("comp1").physics("mbd").feature("spg3").set("dp", "d4i");
    model.component("comp1").physics("mbd").feature("spg3").set("xc", new String[]{"cdx", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg4", "spg3");
    model.component("comp1").physics("mbd").feature("spg4").selection().set(12, 13, 15);
    model.component("comp1").physics("mbd").feature("spg4").set("nt", "n4o");
    model.component("comp1").physics("mbd").feature("spg4").set("dp", "d4o");
    model.component("comp1").physics("mbd").feature("spg4").set("xc", new String[]{"cdx", "cdy", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg5", "spg4");
    model.component("comp1").physics("mbd").feature("spg5").selection().set(23, 24, 25);
    model.component("comp1").physics("mbd").feature("spg5").set("nt", "n3i");
    model.component("comp1").physics("mbd").feature("spg5").set("dp", "d3i");
    model.component("comp1").physics("mbd").feature("spg5").set("xc", new String[]{"2*cdx", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg6", "spg5");
    model.component("comp1").physics("mbd").feature("spg6").selection().set(22, 26);
    model.component("comp1").physics("mbd").feature("spg6").set("nt", "n3o");
    model.component("comp1").physics("mbd").feature("spg6").set("dp", "d3o");
    model.component("comp1").physics("mbd").feature("spg6").set("xc", new String[]{"2*cdx", "cdy", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg7", "spg6");
    model.component("comp1").physics("mbd").feature("spg7").selection().set(27, 28);
    model.component("comp1").physics("mbd").feature("spg7").set("nt", "n2i");
    model.component("comp1").physics("mbd").feature("spg7").set("dp", "d2i");
    model.component("comp1").physics("mbd").feature("spg7").set("xc", new String[]{"3*cdx", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("spg8", "spg7");
    model.component("comp1").physics("mbd").feature("spg8").selection().set(29);
    model.component("comp1").physics("mbd").feature("spg8").set("nt", "n2o");
    model.component("comp1").physics("mbd").feature("spg8").set("dp", "d2o");
    model.component("comp1").physics("mbd").feature("spg8").set("xc", new String[]{"3*cdx", "cdy", "0"});

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("spg1");
    model.nodeGroup("grp2").add("spg2");
    model.nodeGroup("grp2").add("spg3");
    model.nodeGroup("grp2").add("spg4");
    model.nodeGroup("grp2").add("spg5");
    model.nodeGroup("grp2").add("spg6");
    model.nodeGroup("grp2").add("spg7");
    model.nodeGroup("grp2").add("spg8");

    model.component("comp1").physics("mbd").create("grp1", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp1").set("Wheel", "spg1");
    model.component("comp1").physics("mbd").feature("grp1").set("Pinion", "spg2");
    model.component("comp1").physics("mbd").feature().duplicate("grp2", "grp1");
    model.component("comp1").physics("mbd").feature("grp2").set("Wheel", "spg3");
    model.component("comp1").physics("mbd").feature("grp2").set("Pinion", "spg4");
    model.component("comp1").physics("mbd").feature().duplicate("grp3", "grp2");
    model.component("comp1").physics("mbd").feature("grp3").set("Wheel", "spg5");
    model.component("comp1").physics("mbd").feature("grp3").set("Pinion", "spg6");
    model.component("comp1").physics("mbd").feature().duplicate("grp4", "grp3");
    model.component("comp1").physics("mbd").feature("grp4").set("Wheel", "spg7");
    model.component("comp1").physics("mbd").feature("grp4").set("Pinion", "spg8");

    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("grp1");
    model.nodeGroup("grp3").add("grp2");
    model.nodeGroup("grp3").add("grp3");
    model.nodeGroup("grp3").add("grp4");

    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "spg2");
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "spg3");
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "spg6");
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "spg7");
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj4");
    model.component("comp1").physics("mbd").feature("hgj5").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj5").feature("cjb1").selection().named("geom1_sel1");
    model.component("comp1").physics("mbd").feature("hgj5").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj5").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj5").feature("pm1").set("omegap", "-angVel(t)");
    model.component("comp1").physics("mbd").feature().duplicate("hgj6", "hgj5");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj6").feature("cjb1").selection().named("geom1_sel2");
    model.component("comp1").physics("mbd").feature("hgj6").feature("pm1").set("omegap", "-omega");
    model.component("comp1").physics("mbd").feature().duplicate("hgj7", "hgj6");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj7").feature("cjb1").selection().named("geom1_sel3");
    model.component("comp1").physics("mbd").feature("hgj7").feature().remove("pm1");
    model.component("comp1").physics("mbd").feature("hgj7").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj7").feature("afm1")
         .set("M", new String[]{"-T_ext*step1(t)", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("hgj8", "hgj7");
    model.component("comp1").physics("mbd").feature("hgj8").set("Source", "spg4");
    model.component("comp1").physics("mbd").feature("hgj8").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("hgj8").set("CenterOfJointType", "CentroidOfSource");
    model.component("comp1").physics("mbd").feature("hgj8").feature().remove("afm1");
    model.component("comp1").physics("mbd").feature().duplicate("hgj9", "hgj8");
    model.component("comp1").physics("mbd").feature("hgj9").set("Source", "spg5");
    model.component("comp1").physics("mbd").feature("hgj9").set("Destination", "rd5");

    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("init1");
    model.nodeGroup("grp4").add("hgj1");
    model.nodeGroup("grp4").add("hgj2");
    model.nodeGroup("grp4").add("hgj3");
    model.nodeGroup("grp4").add("hgj4");
    model.nodeGroup("grp4").add("hgj5");
    model.nodeGroup("grp4").add("hgj6");
    model.nodeGroup("grp4").add("hgj7");
    model.nodeGroup("grp4").add("hgj8");
    model.nodeGroup("grp4").add("hgj9");

    model.component("comp1").physics("mbd").create("fxj1", "FixedJoint", -1);
    model.component("comp1").physics("mbd").feature("fxj1").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("fxj1").set("Destination", "spg1");
    model.component("comp1").physics("mbd").feature().duplicate("fxj2", "fxj1");
    model.component("comp1").physics("mbd").feature("fxj2").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("fxj2").set("Destination", "spg8");
    model.component("comp1").physics("mbd").feature().duplicate("fxj3", "fxj2");
    model.component("comp1").physics("mbd").feature("fxj3").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("fxj3").set("Destination", "spg4");
    model.component("comp1").physics("mbd").feature().duplicate("fxj4", "fxj3");
    model.component("comp1").physics("mbd").feature("fxj4").set("Source", "rd6");
    model.component("comp1").physics("mbd").feature("fxj4").set("Destination", "spg5");

    model.nodeGroup().create("grp5", "Physics", "mbd");
    model.nodeGroup("grp5").placeAfter("init1");
    model.nodeGroup("grp5").add("fxj1");
    model.nodeGroup("grp5").add("fxj2");
    model.nodeGroup("grp5").add("fxj3");
    model.nodeGroup("grp5").add("fxj4");

    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "spg4");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature().duplicate("prj2", "prj1");
    model.component("comp1").physics("mbd").feature("prj2").set("Source", "spg5");
    model.component("comp1").physics("mbd").feature("prj2").set("Destination", "rd2");

    model.nodeGroup().create("grp6", "Physics", "mbd");
    model.nodeGroup("grp6").placeAfter("init1");
    model.nodeGroup("grp6").add("prj1");
    model.nodeGroup("grp6").add("prj2");

    model.component("comp1").physics("mbd").create("clj1", "CylindricalJoint", -1);
    model.component("comp1").physics("mbd").feature("clj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("clj1").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("clj1").feature("cjb1").selection().named("geom1_sel1");
    model.component("comp1").physics("mbd").feature().duplicate("clj2", "clj1");
    model.component("comp1").physics("mbd").feature("clj2").set("Destination", "rd5");

    model.nodeGroup().create("grp7", "Physics", "mbd");
    model.nodeGroup("grp7").placeAfter("init1");
    model.nodeGroup("grp7").add("clj1");
    model.nodeGroup("grp7").add("clj2");

    model.component("comp1").physics("mbd").create("cfc1", "CamFollower", -1);
    model.component("comp1").physics("mbd").feature("cfc1").selection("bndCam").named("geom1_sel4");
    model.component("comp1").physics("mbd").feature("cfc1").set("connPoint", "GeomPoint");
    model.component("comp1").physics("mbd").feature("cfc1").selection("PntFollowerGeom").set(2187);
    model.component("comp1").physics("mbd").feature().duplicate("cfc2", "cfc1");
    model.component("comp1").physics("mbd").feature("cfc2").selection("bndCam").named("geom1_sel5");
    model.component("comp1").physics("mbd").feature("cfc2").selection("PntFollowerGeom").set(3101);

    model.nodeGroup().create("grp8", "Physics", "mbd");
    model.nodeGroup("grp8").placeAfter("init1");
    model.nodeGroup("grp8").add("cfc1");
    model.nodeGroup("grp8").add("cfc2");

    model.component("comp1").physics("mbd").create("rbc1", "RigidBodyContact", -1);
    model.component("comp1").physics("mbd").feature("rbc1").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("rbc1").set("ShapeParaSource", "UserDefined");
    model.component("comp1").physics("mbd").feature("rbc1").set("rs", "geom1.dm1/2");
    model.component("comp1").physics("mbd").feature("rbc1")
         .set("Xs", new String[]{"geom1.cm1.x", "geom1.cm1.y", "geom1.cm1.z"});
    model.component("comp1").physics("mbd").feature("rbc1").set("DestinationShape", "Noncircular");
    model.component("comp1").physics("mbd").feature("rbc1").selection("bndDestinationSelection").set(1157, 1158);
    model.component("comp1").physics("mbd").feature("rbc1").set("fp", 0.01);
    model.component("comp1").physics("mbd").feature().duplicate("rbc2", "rbc1");
    model.component("comp1").physics("mbd").feature("rbc2")
         .set("Xs", new String[]{"geom1.cm2.x", "geom1.cm2.y", "geom1.cm2.z"});
    model.component("comp1").physics("mbd").feature("rbc2").selection("bndDestinationSelection").set(1706, 1710);
    model.component("comp1").physics("mbd").feature().duplicate("rbc3", "rbc2");
    model.component("comp1").physics("mbd").feature("rbc3").set("Source", "rd6");
    model.component("comp1").physics("mbd").feature("rbc3").set("rs", "geom1.dm2/2");
    model.component("comp1").physics("mbd").feature("rbc3")
         .set("Xs", new String[]{"geom1.cm3.x", "geom1.cm3.y", "geom1.cm3.z"});
    model.component("comp1").physics("mbd").feature("rbc3").selection("bndDestinationSelection").set(1639, 1643);
    model.component("comp1").physics("mbd").feature().duplicate("rbc4", "rbc3");
    model.component("comp1").physics("mbd").feature("rbc4")
         .set("Xs", new String[]{"geom1.cm4.x", "geom1.cm4.y", "geom1.cm4.z"});
    model.component("comp1").physics("mbd").feature("rbc4").selection("bndDestinationSelection").set(2247, 2250);

    model.nodeGroup().create("grp9", "Physics", "mbd");
    model.nodeGroup("grp9").placeAfter("init1");
    model.nodeGroup("grp9").add("rbc1");
    model.nodeGroup("grp9").add("rbc2");
    model.nodeGroup("grp9").add("rbc3");
    model.nodeGroup("grp9").add("rbc4");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.08);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").feature("map1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().named("geom1_sel7");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_unisel2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(8, 18);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 30);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 130, 131, 260, 262, 320);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.005,tf)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(1, 2, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 19, 22, 23, 24, 25, 26, 27, 28, 29);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().set(3, 10, 11, 20, 21);
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().remove("arwl1");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "custom");
    model.result("pg3").feature("vol1")
         .set("customcolor", new double[]{0.49803921580314636, 0.5098039507865906, 0.5098039507865906});
    model.result("pg3").feature("vol1").create("sel1", "Selection");
    model.result("pg3").feature("vol1").feature("sel1").selection().named("geom1_sel13");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("vol2", "vol1");
    model.result("pg3").run();
    model.result("pg3").feature("vol2")
         .set("customcolor", new double[]{0.545098066329956, 0.6941176652908325, 0.8509804010391235});
    model.result("pg3").run();
    model.result("pg3").feature("vol2").feature("sel1").selection().named("geom1_sel11");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("vol3", "vol2");
    model.result("pg3").run();
    model.result("pg3").feature("vol3")
         .set("customcolor", new double[]{0.4627451002597809, 0.6823529601097107, 0.3176470696926117});
    model.result("pg3").feature().duplicate("vol4", "vol3");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol2").feature("sel1").selection().named("geom1_sel10");
    model.result("pg3").run();
    model.result("pg3").feature("vol4")
         .set("customcolor", new double[]{0.8392156958580017, 0.49803921580314636, 0.47843137383461});
    model.result("pg3").run();
    model.result("pg3").feature("vol4").feature("sel1").selection().named("geom1_sel12");
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("view", "new");
    model.result("pg3").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);
    model.view("view2").set("locked", true);

    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 32, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 75, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 180, 0);
    model.result("pg3").run();
    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.hgj6.th_t"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.hgj6.th_t", "mbd.hgj7.th_t"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6", "\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("styleconfig", "gsty1");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.spg1.th_tx"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.spg1.th_tx", "mbd.spg3.th_tx"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf"});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"mbd.spg1.th_tx", "mbd.spg3.th_tx", "mbd.spg5.th_tx"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf"});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"mbd.spg1.th_tx", "mbd.spg3.th_tx", "mbd.spg5.th_tx", "mbd.spg7.th_tx"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf", "\u521a\u4f53\u89d2\u901f\u5ea6\uff0cx \u5206\u91cf"});
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("expr", "mbd.spg2.th_tx", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "mbd.spg4.th_tx", 1);
    model.result("pg5").feature("glob2").setIndex("expr", "mbd.spg6.th_tx", 2);
    model.result("pg5").feature("glob2").setIndex("expr", "mbd.spg8.th_tx", 3);
    model.result("pg5").feature("glob2").setIndex("legends", "Gear 1 (output)", 0);
    model.result("pg5").feature("glob2").setIndex("legends", "Gear 4 (output)", 1);
    model.result("pg5").feature("glob2").setIndex("legends", "Gear 3 (output)", 2);
    model.result("pg5").feature("glob2").setIndex("legends", "Gear 2 (output)", 3);
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg5").feature("glob2").set("linestyle", "dashed");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("styleconfig", "gsty1");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(2165, 3079);
    model.result("pg6").feature("ptgr1").set("expr", "u");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg6").feature("ptgr1").setIndex("legends", "Output Pin", 0);
    model.result("pg6").feature("ptgr1").setIndex("legends", "Input Pin", 1);
    model.result("pg6").run();
    model.result("pg6").create("ann1", "Annotation");
    model.result("pg6").feature("ann1").set("posxexpr", 0.07);
    model.result("pg6").feature("ann1").set("posyexpr", -2);
    model.result("pg6").feature("ann1").set("showpoint", false);
    model.result("pg6").feature("ann1").set("anchorpoint", "center");
    model.result("pg6").feature().duplicate("ann2", "ann1");
    model.result("pg6").run();
    model.result("pg6").feature("ann2").set("posxexpr", 0.22);
    model.result("pg6").feature("ann2").set("posyexpr", 2.2);
    model.result("pg6").feature().duplicate("ann3", "ann2");
    model.result("pg6").run();
    model.result("pg6").feature("ann3").set("posxexpr", 0.535);
    model.result("pg6").feature().duplicate("ann4", "ann3");
    model.result("pg6").run();
    model.result("pg6").feature("ann4").set("posxexpr", 1.0975);
    model.result("pg6").feature("ann4").set("posyexpr", "-2.0");
    model.result("pg6").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 200);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result("pg1").run();

    model.title("\u987a\u5e8f\u5f0f\u53d8\u901f\u7bb1\u7684\u6362\u6321");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u987a\u5e8f\u5f0f\u624b\u52a8\u53d8\u901f\u7bb1\u7684\u5efa\u6a21\u8fc7\u7a0b\uff0c\u5176\u4e2d\u901a\u8fc7\u65cb\u8f6c\u9f13\u4f9d\u6b21\u9009\u62e9\u9f7f\u8f6e\u3002\u53d8\u901f\u9f13\u7684\u5468\u56f4\u523b\u6709\u4e24\u4e2a\u7279\u5b9a\u8f6e\u5ed3\u7684\u51f9\u69fd\u3002\u968f\u7740\u9f13\u7684\u65cb\u8f6c\uff0c\u5176\u51f9\u69fd\u4f1a\u5f15\u5bfc\u4e00\u7ec4\u6362\u6321\u53c9\uff0c\u4ece\u800c\u5e2e\u52a9\u556e\u5408\u6240\u9700\u7684\u9f7f\u8f6e\u3002\n\n\u672c\u4f8b\u901a\u8fc7\u77ac\u6001\u591a\u4f53\u5206\u6790\uff0c\u8ba1\u7b97\u5728\u6307\u5b9a\u53d1\u52a8\u673a\u8f6c\u901f\u548c\u5916\u90e8\u8f7d\u8377\u6761\u4ef6\u4e0b\u6240\u6709\u9f7f\u8f6e\u7684\u89d2\u901f\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sequential_gearbox_with_slotted_cam.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
