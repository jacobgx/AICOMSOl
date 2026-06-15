/*
 * electronic_enclosure_cooling_geom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:27 by COMSOL 6.3.0.290. */
public class electronic_enclosure_cooling_geom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");

    model.param().set("OR", "0.4");
    model.param().descr("OR", "\u683c\u6805\u7684\u5f00\u53e3\u7387");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "electronic_enclosure_cooling.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(1, 2, 4, 41, 776);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5851\u6599");
    model.component("comp1").selection("sel2").set(6, 7, 10, 11, 14, 20, 21, 22, 25, 26, 30, 37, 38, 41, 42, 45, 46);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7fc5\u7247");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 292, 322, 378, 441, 495, 529, 636, 651, 666, 677);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5c01\u88c5\u8fb9");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(1, 2, 4, 41, 776);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u94dd\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u53d8\u538b\u5668\u58f3");
    model.component("comp1").selection("sel5").set(3, 8, 9, 18, 23, 24, 34, 39, 40);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5c0f\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("sel6").set(12, 27);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u5927\u578b\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("sel7").set(43);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u53d8\u538b\u5668\u7ebf\u5708");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel6", "sel7"});
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u7535\u611f\u5668");
    model.component("comp1").selection("sel8").set(5, 19, 33);
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u94a2\u5236\u96f6\u4ef6");
    model.component("comp1").selection("uni3").set("input", new String[]{"sel5", "sel8"});
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u5927\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel9").set(17, 48);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u4e2d\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel10").set(4, 15, 29, 35, 44, 50);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u5c0f\u578b\u7535\u5bb9\u5668");
    model.component("comp1").selection("sel11").set(2, 13, 31, 36);
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u7535\u5bb9\u5668");
    model.component("comp1").selection("uni4").set("input", new String[]{"sel9", "sel10", "sel11"});
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u6676\u4f53\u7ba1\u7845\u82af\u7247");
    model.component("comp1").selection("sel12").set(16, 28, 32, 47, 49);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u94dc\u5c42");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel8"});
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u683c\u6805");
    model.component("comp1").selection("sel13").geom(2);
    model.component("comp1").selection("sel13").set(777);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u98ce\u6247");
    model.component("comp1").selection("sel14").geom(2);
    model.component("comp1").selection("sel14").set(87);
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u7535\u8def\u677f");
    model.component("comp1").selection("sel15").geom(2);
    model.component("comp1").selection("sel15").set(3);
    model.component("comp1").selection("sel15").set("groupcontang", true);
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("\u4f20\u5bfc\u5c42");
    model.component("comp1").selection("uni5").set("entitydim", 2);
    model.component("comp1").selection("uni5").set("input", new String[]{"uni1", "adj1", "sel15"});
    model.component("comp1").selection().create("sel16", "Explicit");
    model.component("comp1").selection("sel16").label("\u7535\u7ebf\u7ec4\u8868\u9762");
    model.component("comp1").selection("sel16").geom(2);
    model.component("comp1").selection("sel16")
         .set(538, 539, 556, 557, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774);
    model.component("comp1").selection().create("sel17", "Explicit");
    model.component("comp1").selection("sel17").label("\u5c0f\u578b\u7535\u7ebf\u8868\u9762");
    model.component("comp1").selection("sel17").geom(2);
    model.component("comp1").selection("sel17").set(82, 83, 84, 85);
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u5143\u4ef6\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel2", "uni2", "uni3", "uni4", "sel12"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6362\u70ed\u8868\u9762");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel3", "adj2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel15"});
    model.component("comp1").selection().create("sel18", "Explicit");
    model.component("comp1").selection("sel18").label("\u5f2f\u66f2\u533a\u57df");
    model.component("comp1").selection("sel18").geom(2);
    model.component("comp1").selection("sel18")
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 86, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135);
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("\u58c1");
    model.component("comp1").selection("uni6").set("entitydim", 2);
    model.component("comp1").selection("uni6").set("input", new String[]{"uni5", "sel16", "sel17", "dif1", "sel18"});

    model.title("\u53c2\u6570\u5316\u7684\u7535\u5668\u673a\u7bb1\u51e0\u4f55");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f mph \u6587\u4ef6\uff0c\u5305\u542b\u201c\u542b\u98ce\u6247\u548c\u683c\u6805\u7684\u5c01\u95ed\u7a7a\u95f4\u7684\u5f3a\u5236\u5bf9\u6d41\u6563\u70ed\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("electronic_enclosure_cooling_geom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
