/*
 * windows_thermal_performances.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:20 by COMSOL 6.3.0.290. */
public class windows_thermal_performances {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").label("\u7b2c\u4e00\u79cd\u7a97\u6237");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("C1", "0.025[W/(m*K)]", "\u5bf9\u6d41\u4f20\u70ed\u7cfb\u6570\u8ba1\u7b97\u7684\u7b2c\u4e00\u4e2a\u5e38\u6570");
    model.param()
         .set("C2", "0.73[W/(m^2*K)]", "\u5bf9\u6d41\u4f20\u70ed\u7cfb\u6570\u8ba1\u7b97\u7684\u7b2c\u4e8c\u4e2a\u5e38\u6570");
    model.param().set("epsilon", "0.90", "\u8868\u9762\u53d1\u5c04\u7387");
    model.param().set("Ti", "20[degC]", "\u5185\u90e8\u6e29\u5ea6");
    model.param().set("Te", "0[degC]", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("Rsi_n", "0.13[m^2*K/W]", "\u5185\u8868\u9762\u70ed\u963b\uff08\u5e38\u89c4\uff09");
    model.param().set("Rsi_p", "0.20[m^2*K/W]", "\u5185\u8868\u9762\u70ed\u963b\uff08\u53d7\u4fdd\u62a4\uff09");
    model.param().set("Rse", "0.04[m^2*K/W]", "\u5916\u8868\u9762\u70ed\u963b");
    model.param().set("lambda_p", "0.035[W/(m*K)]", "\u9694\u70ed\u677f\u7684\u5bfc\u70ed\u7cfb\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "ha_1");
    model.func("an1").set("expr", "C1[(m*K)/W]/d");
    model.func("an1").set("args", "d");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "W/(m^2*K)");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "ha_2");
    model.func("an2").set("expr", "max(C1[(m*K)/W]/d,C2[(m^2*K)/W]*delta^(1/3))");
    model.func("an2").set("args", "d, delta");
    model.func("an2").setIndex("argunit", "m", 0);
    model.func("an2").setIndex("argunit", "K", 1);
    model.func("an2").set("fununit", "W/(m^2*K)");
    model.func().create("an3", "Analytic");
    model.func("an3").set("funcname", "hr");
    model.func("an3").set("expr", "4*sigma_const[(K^4*m^2)/W]*Tm^3/(1/e1+1/e2-1)*(1+sqrt(1+(d/b)^2)-d/b)/2");
    model.func("an3").set("args", "d, b, Tm, e1, e2");
    model.func("an3").setIndex("argunit", "m", 0);
    model.func("an3").setIndex("argunit", "m", 1);
    model.func("an3").setIndex("argunit", "K", 2);
    model.func("an3").setIndex("argunit", 1, 3);
    model.func("an3").setIndex("argunit", 1, 4);
    model.func("an3").set("fununit", "W/(m^2*K)");
    model.func().create("an4", "Analytic");
    model.func("an4").set("funcname", "h_resized");
    model.func("an4").set("expr", "sqrt(A*d/b)");
    model.func("an4").set("args", "A, d, b");
    model.func("an4").setIndex("argunit", "m^2", 0);
    model.func("an4").setIndex("argunit", "m", 1);
    model.func("an4").setIndex("argunit", "m", 2);
    model.func("an4").set("fununit", "m");
    model.func("an4").setIndex("plotargs", 0.1, 2, 1);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "windows_thermal_performances_first_window.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 66, 104, 116, 144, 152, 175, 179);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("ige1", 30, 53, 74, 75, 81, 146, 151);
    model.component("comp1").geom("geom1").run("igv1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(3, 4, 6, 8, 9, 10, 11, 14);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(17, 18, 19, 51);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(21, 22, 24, 25, 26, 27, 28, 30, 31, 32, 33, 41);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(61, 62, 63, 64, 65, 69, 72, 73, 80, 82, 96);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(105, 106, 107, 132);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(137, 138, 139, 159);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7")
         .set(128, 129, 131, 133, 134, 135, 136, 140, 141, 142, 143, 144, 147, 149);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(109, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 123);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp1").selection("sel9").geom(1);
    model.component("comp1").selection("sel9").set(97, 98, 99, 126);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp1").selection("sel10").geom(1);
    model.component("comp1").selection("sel10")
         .set(47, 48, 50, 54, 55, 56, 57, 67, 70, 71, 74, 75, 76, 77, 78, 79, 83, 88, 89, 92, 93, 94, 95, 102);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp1").selection("sel11").geom(1);
    model.component("comp1").selection("sel11").set(84, 87, 90);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u5916\u4fa7");
    model.component("comp1").selection("sel12").geom(1);
    model.component("comp1").selection("sel12").set(2, 85, 91, 160, 161, 162);
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp1").selection("sel13").geom(1);
    model.component("comp1").selection("sel13").set(7, 38, 166);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp1").selection("sel14").geom(1);
    model.component("comp1").selection("sel14").set(29, 34, 36, 163, 164, 165);
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u5185\u4fa7");
    model.component("comp1").selection("sel15").geom(1);
    model.component("comp1").selection("sel15").set(7, 29, 34, 36, 38, 163, 164, 165, 166);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").set("opname", "max_uc1");
    model.component("comp1").cpl("maxop1").selection().set(2);
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").set("opname", "min_uc1");
    model.component("comp1").cpl("minop1").selection().set(2);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "ave_uc1");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_uc1");
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl().create("maxop2", "Maximum");
    model.component("comp1").cpl("maxop2").set("opname", "max_uc2");
    model.component("comp1").cpl("maxop2").selection().set(5);
    model.component("comp1").cpl().create("minop2", "Minimum");
    model.component("comp1").cpl("minop2").set("opname", "min_uc2");
    model.component("comp1").cpl("minop2").selection().set(5);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "ave_uc2");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("sel2");
    model.component("comp1").cpl().create("maxop3", "Maximum");
    model.component("comp1").cpl("maxop3").set("opname", "max_uc3");
    model.component("comp1").cpl("maxop3").selection().set(6);
    model.component("comp1").cpl().create("minop3", "Minimum");
    model.component("comp1").cpl("minop3").set("opname", "min_uc3");
    model.component("comp1").cpl("minop3").selection().set(6);
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").set("opname", "ave_uc3");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop3").selection().named("sel3");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_uc3");
    model.component("comp1").cpl("intop2").selection().set(6);
    model.component("comp1").cpl().create("maxop4", "Maximum");
    model.component("comp1").cpl("maxop4").set("opname", "max_uc4");
    model.component("comp1").cpl("maxop4").selection().set(12);
    model.component("comp1").cpl().create("minop4", "Minimum");
    model.component("comp1").cpl("minop4").set("opname", "min_uc4");
    model.component("comp1").cpl("minop4").selection().set(12);
    model.component("comp1").cpl().create("aveop4", "Average");
    model.component("comp1").cpl("aveop4").set("axisym", true);
    model.component("comp1").cpl("aveop4").set("opname", "ave_uc4");
    model.component("comp1").cpl("aveop4").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop4").selection().named("sel4");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "int_uc4");
    model.component("comp1").cpl("intop3").selection().set(12);
    model.component("comp1").cpl().create("maxop5", "Maximum");
    model.component("comp1").cpl("maxop5").set("opname", "max_uc5");
    model.component("comp1").cpl("maxop5").selection().set(17);
    model.component("comp1").cpl().create("minop5", "Minimum");
    model.component("comp1").cpl("minop5").set("opname", "min_uc5");
    model.component("comp1").cpl("minop5").selection().set(17);
    model.component("comp1").cpl().create("aveop5", "Average");
    model.component("comp1").cpl("aveop5").set("axisym", true);
    model.component("comp1").cpl("aveop5").set("opname", "ave_uc5");
    model.component("comp1").cpl("aveop5").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop5").selection().named("sel5");
    model.component("comp1").cpl().create("maxop6", "Maximum");
    model.component("comp1").cpl("maxop6").set("opname", "max_uc6");
    model.component("comp1").cpl("maxop6").selection().set(21);
    model.component("comp1").cpl().create("minop6", "Minimum");
    model.component("comp1").cpl("minop6").set("opname", "min_uc6");
    model.component("comp1").cpl("minop6").selection().set(21);
    model.component("comp1").cpl().create("aveop6", "Average");
    model.component("comp1").cpl("aveop6").set("axisym", true);
    model.component("comp1").cpl("aveop6").set("opname", "ave_uc6");
    model.component("comp1").cpl("aveop6").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop6").selection().named("sel6");
    model.component("comp1").cpl().create("maxop7", "Maximum");
    model.component("comp1").cpl("maxop7").set("opname", "max_uc7");
    model.component("comp1").cpl("maxop7").selection().set(20);
    model.component("comp1").cpl().create("minop7", "Minimum");
    model.component("comp1").cpl("minop7").set("opname", "min_uc7");
    model.component("comp1").cpl("minop7").selection().set(20);
    model.component("comp1").cpl().create("aveop7", "Average");
    model.component("comp1").cpl("aveop7").set("axisym", true);
    model.component("comp1").cpl("aveop7").set("opname", "ave_uc7");
    model.component("comp1").cpl("aveop7").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop7").selection().named("sel7");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "int_uc7");
    model.component("comp1").cpl("intop4").selection().set(20);
    model.component("comp1").cpl().create("maxop8", "Maximum");
    model.component("comp1").cpl("maxop8").set("opname", "max_uc8");
    model.component("comp1").cpl("maxop8").selection().set(18);
    model.component("comp1").cpl().create("minop8", "Minimum");
    model.component("comp1").cpl("minop8").set("opname", "min_uc8");
    model.component("comp1").cpl("minop8").selection().set(18);
    model.component("comp1").cpl().create("aveop8", "Average");
    model.component("comp1").cpl("aveop8").set("axisym", true);
    model.component("comp1").cpl("aveop8").set("opname", "ave_uc8");
    model.component("comp1").cpl("aveop8").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop8").selection().named("sel8");
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").set("opname", "int_uc8");
    model.component("comp1").cpl("intop5").selection().set(18);
    model.component("comp1").cpl().create("maxop9", "Maximum");
    model.component("comp1").cpl("maxop9").set("opname", "max_uc9");
    model.component("comp1").cpl("maxop9").selection().set(15);
    model.component("comp1").cpl().create("minop9", "Minimum");
    model.component("comp1").cpl("minop9").set("opname", "min_uc9");
    model.component("comp1").cpl("minop9").selection().set(15);
    model.component("comp1").cpl().create("aveop9", "Average");
    model.component("comp1").cpl("aveop9").set("axisym", true);
    model.component("comp1").cpl("aveop9").set("opname", "ave_uc9");
    model.component("comp1").cpl("aveop9").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop9").selection().named("sel9");
    model.component("comp1").cpl().create("maxop10", "Maximum");
    model.component("comp1").cpl("maxop10").set("opname", "max_uc10");
    model.component("comp1").cpl("maxop10").selection().set(10);
    model.component("comp1").cpl().create("minop10", "Minimum");
    model.component("comp1").cpl("minop10").set("opname", "min_uc10");
    model.component("comp1").cpl("minop10").selection().set(10);
    model.component("comp1").cpl().create("aveop10", "Average");
    model.component("comp1").cpl("aveop10").set("axisym", true);
    model.component("comp1").cpl("aveop10").set("opname", "ave_uc10");
    model.component("comp1").cpl("aveop10").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop10").selection().named("sel10");
    model.component("comp1").cpl().create("intop6", "Integration");
    model.component("comp1").cpl("intop6").set("axisym", true);
    model.component("comp1").cpl("intop6").set("opname", "int_uc10");
    model.component("comp1").cpl("intop6").selection().set(10);
    model.component("comp1").cpl().create("maxop11", "Maximum");
    model.component("comp1").cpl("maxop11").set("opname", "max_svc");
    model.component("comp1").cpl("maxop11").selection().set(14);
    model.component("comp1").cpl().create("minop11", "Minimum");
    model.component("comp1").cpl("minop11").set("opname", "min_svc");
    model.component("comp1").cpl("minop11").selection().set(14);
    model.component("comp1").cpl().create("aveop11", "Average");
    model.component("comp1").cpl("aveop11").set("axisym", true);
    model.component("comp1").cpl("aveop11").set("opname", "ave_svc");
    model.component("comp1").cpl("aveop11").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop11").selection().named("sel11");
    model.component("comp1").cpl().create("intop7", "Integration");
    model.component("comp1").cpl("intop7").set("axisym", true);
    model.component("comp1").cpl("intop7").set("opname", "int_internal");
    model.component("comp1").cpl("intop7").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop7").selection().named("sel15");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("f_htot", "63[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_hl1", "53[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_hl2", "10[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_hr1", "10[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_hr2", "28[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u539a\u5ea6\uff09");
    model.component("comp1").variable("var1")
         .set("f_hr3", "25[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("f_wtot", "110[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_wt1", "40[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_wt2", "70[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_wb1", "65[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_wb2", "5[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("f_wb3", "40[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e09\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1").set("at", "2[mm]", "\u94dd\u5c42\u539a\u5ea6");
    model.component("comp1").variable("var1")
         .set("aw1", "4[mm]", "\u8f83\u5c0f\u94dd\u5236\u6954\u5b50\u7684\u957f\u5ea6");
    model.component("comp1").variable("var1").set("aw2", "5[mm]", "\u94dd\u5236\u6954\u5b50\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("aw3", "6[mm]", "\u8f83\u957f\u94dd\u5236\u6954\u5b50\u7684\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc1_w", "9[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc1_h", "49[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc2_w", "35[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc2_h", "24[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc3_w", "31[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc3_h", "23[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc4_w", "24[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc4_h", "29[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc5_w", "16[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc5_h", "27[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc6_w", "18[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc6_h", "15[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc7_w", "14[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc7_h", "42[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc8_w", "12[mm]", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc8_h", "22[mm]", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc9_w", "16[mm]", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc9_h", "6[mm]", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc10_w", "28[mm]", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc10_h", "30[mm]", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("svc_w", "5[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1").set("svc_h", "8[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("pp", "15[mm]", "\u9762\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp1").variable("var1").set("pvl", "190[mm]", "\u9762\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Rp", "f_hr2/lambda_p", "\u9762\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("Up_n", "1/(Rsi_n+Rse+Rp)", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp1").variable("var1")
         .set("Up_p", "1/(Rsi_p+Rse+Rp)", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp1").variable("var1")
         .set("Up", "(f_hr3*Up_p+(pvl-f_hr3)*Up_n)/pvl", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp1").variable("var1")
         .set("R_uc1", "1/(ha_2(uc1_h2,max_uc1(T)-min_uc1(T))+hr(uc1_h,uc1_w,ave_uc1(T),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc2", "1/(ha_2(uc2_h,max_uc2(T)-min_uc2(T))+hr(uc2_h,uc2_w,ave_uc2(T),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc3", "1/(ha_2(uc3_h2,max_uc3(T)-min_uc3(T))+hr(uc3_h,uc3_w,ave_uc3(T),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc4", "1/(ha_2(uc4_h2,max_uc4(T)-min_uc4(T))+hr(uc4_h,uc4_w,ave_uc4(T),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc5", "1/(ha_2(uc5_h,max_uc5(T)-min_uc5(T))+hr(uc5_h,uc5_w,ave_uc5(T),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc6", "1/(ha_2(uc6_h,max_uc6(T)-min_uc6(T))+hr(uc6_h,uc6_w,ave_uc6(T),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc7", "1/(ha_2(uc7_h2,max_uc7(T)-min_uc7(T))+hr(uc7_h,uc7_w,ave_uc7(T),epsilon,epsilon))", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc8", "1/(ha_2(uc8_h2,max_uc8(T)-min_uc8(T))+hr(uc8_h,uc8_w,ave_uc8(T),epsilon,epsilon))", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc9", "1/(ha_2(uc9_h,max_uc9(T)-min_uc9(T))+hr(uc9_h,uc9_w,ave_uc9(T),epsilon,epsilon))", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc10", "1/(ha_2(uc10_h2,max_uc10(T)-min_uc10(T))+hr(uc10_h,uc10_w,ave_uc10(T),epsilon,epsilon))", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_svc", "1/(ha_2(svc_h,max_svc(T)-min_svc(T))+hr(svc_h,svc_w,ave_svc(T),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("A_uc1", "int_uc1(1)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("A_uc3", "int_uc3(1)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("A_uc4", "int_uc4(1)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("A_uc7", "int_uc7(1)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("A_uc8", "int_uc8(1)", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("A_uc10", "int_uc10(1)", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("uc1_h2", "h_resized(A_uc1,uc1_h,uc1_w)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc3_h2", "h_resized(A_uc3,uc3_h,uc3_w)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc4_h2", "h_resized(A_uc4,uc4_h,uc4_w)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc7_h2", "h_resized(A_uc7,uc7_h,uc7_w)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc8_h2", "h_resized(A_uc8,uc8_h,uc8_w)", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc10_h2", "h_resized(A_uc10,uc10_h,uc10_w)", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94dd");
    model.component("comp1").material("mat1").selection().set(1, 3, 8, 13);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"160[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2800[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("EPDM");
    model.component("comp1").material("mat2").selection().set(7, 11, 23, 24);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u805a\u9170\u80fa");
    model.component("comp1").material("mat3").selection().set(4, 9, 16, 19);
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.30[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1450[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u9694\u70ed\u677f");
    model.component("comp1").material("mat4").selection().set(22);
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp1").material("mat5").selection().set(2);
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h2/R_uc1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat6", "Common");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat6").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp1").material("mat6").selection().set(5);
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp1").material("mat7").selection().set(6);
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h2/R_uc3"});
    model.component("comp1").material("mat7").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp1").material("mat8").selection().set(12);
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h2/R_uc4"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp1").material("mat9").selection().set(17);
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h/R_uc5"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat10", "Common");
    model.component("comp1").material("mat10").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp1").material("mat10").selection().set(21);
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc6_h/R_uc6"});
    model.component("comp1").material("mat10").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat11", "Common");
    model.component("comp1").material("mat11").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp1").material("mat11").selection().set(20);
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc7_h2/R_uc7"});
    model.component("comp1").material("mat11").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat12", "Common");
    model.component("comp1").material("mat12").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp1").material("mat12").selection().set(18);
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc8_h2/R_uc8"});
    model.component("comp1").material("mat12").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat13", "Common");
    model.component("comp1").material("mat13").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp1").material("mat13").selection().set(15);
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc9_h/R_uc9"});
    model.component("comp1").material("mat13").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat14", "Common");
    model.component("comp1").material("mat14").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp1").material("mat14").selection().set(10);
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc10_h2/R_uc10"});
    model.component("comp1").material("mat14").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat15", "Common");
    model.component("comp1").material("mat15").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp1").material("mat15").selection().set(14);
    model.component("comp1").material("mat15").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_h/R_svc"});
    model.component("comp1").material("mat15").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat15").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("\u7b2c\u4e8c\u79cd\u7a97\u6237");

    model.component("comp2").physics().create("ht2", "HeatTransferInSolidsAndFluids", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").geom("geom2").run();
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1")
         .set("filename", "windows_thermal_performances_second_window.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("ige1", "IgnoreEdges");
    model.component("comp2").geom("geom2").feature("ige1").selection("input").set("fin", 8, 24, 48, 77);
    model.component("comp2").geom("geom2").run("ige1");
    model.component("comp2").geom("geom2").create("igv1", "IgnoreVertices");
    model.component("comp2").geom("geom2").feature("igv1").selection("input")
         .set("ige1", 9, 14, 18, 21, 22, 31, 32, 38, 48, 52, 55);
    model.component("comp2").geom("geom2").run("igv1");

    model.component("comp2").selection().create("sel16", "Explicit");
    model.component("comp2").selection("sel16").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp2").selection("sel16").geom(1);
    model.component("comp2").selection("sel16").set(3, 4, 6, 20);
    model.component("comp2").selection().create("sel17", "Explicit");
    model.component("comp2").selection("sel17").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp2").selection("sel17").geom(1);
    model.component("comp2").selection("sel17").set(26, 27, 28, 32);
    model.component("comp2").selection().create("sel18", "Explicit");
    model.component("comp2").selection("sel18").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp2").selection("sel18").geom(1);
    model.component("comp2").selection("sel18").set(29, 30, 31, 39, 40, 51);
    model.component("comp2").selection().create("sel19", "Explicit");
    model.component("comp2").selection("sel19").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp2").selection("sel19").geom(1);
    model.component("comp2").selection("sel19").set(48, 49, 50, 52, 53, 55);
    model.component("comp2").selection().create("sel20", "Explicit");
    model.component("comp2").selection("sel20").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp2").selection("sel20").geom(1);
    model.component("comp2").selection("sel20").set(36, 37, 38, 41, 43, 45);
    model.component("comp2").selection().create("sel21", "Explicit");
    model.component("comp2").selection("sel21").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp2").selection("sel21").geom(1);
    model.component("comp2").selection("sel21").set(14, 15, 16, 17, 18, 19, 22, 23, 24, 25, 58);
    model.component("comp2").selection().create("sel22", "Explicit");
    model.component("comp2").selection("sel22").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp2").selection("sel22").geom(1);
    model.component("comp2").selection("sel22").set(7, 13, 64);
    model.component("comp2").selection().create("sel23", "Explicit");
    model.component("comp2").selection("sel23").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp2").selection("sel23").geom(1);
    model.component("comp2").selection("sel23").set(8, 9, 11, 61, 62, 63);
    model.component("comp2").selection().create("sel24", "Explicit");
    model.component("comp2").selection("sel24").label("\u5916\u4fa7");
    model.component("comp2").selection("sel24").geom(1);
    model.component("comp2").selection("sel24").set(2, 33, 34, 35, 54, 56);
    model.component("comp2").selection().create("sel25", "Explicit");
    model.component("comp2").selection("sel25").label("\u5185\u4fa7");
    model.component("comp2").selection("sel25").geom(1);
    model.component("comp2").selection("sel25").set(7, 8, 9, 11, 13, 61, 62, 63, 64);

    model.component("comp2").cpl().create("maxop12", "Maximum");
    model.component("comp2").cpl("maxop12").set("opname", "max_uc1");
    model.component("comp2").cpl("maxop12").selection().set(2);
    model.component("comp2").cpl().create("minop12", "Minimum");
    model.component("comp2").cpl("minop12").set("opname", "min_uc1");
    model.component("comp2").cpl("minop12").selection().set(2);
    model.component("comp2").cpl().create("aveop12", "Average");
    model.component("comp2").cpl("aveop12").set("axisym", true);
    model.component("comp2").cpl("aveop12").set("opname", "ave_uc1");
    model.component("comp2").cpl("aveop12").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop12").selection().named("sel16");
    model.component("comp2").cpl().create("maxop13", "Maximum");
    model.component("comp2").cpl("maxop13").set("opname", "max_uc2");
    model.component("comp2").cpl("maxop13").selection().set(8);
    model.component("comp2").cpl().create("minop13", "Minimum");
    model.component("comp2").cpl("minop13").set("opname", "min_uc2");
    model.component("comp2").cpl("minop13").selection().set(8);
    model.component("comp2").cpl().create("aveop13", "Average");
    model.component("comp2").cpl("aveop13").set("axisym", true);
    model.component("comp2").cpl("aveop13").set("opname", "ave_uc2");
    model.component("comp2").cpl("aveop13").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop13").selection().named("sel17");
    model.component("comp2").cpl().create("maxop14", "Maximum");
    model.component("comp2").cpl("maxop14").set("opname", "max_uc3");
    model.component("comp2").cpl("maxop14").selection().set(9);
    model.component("comp2").cpl().create("minop14", "Minimum");
    model.component("comp2").cpl("minop14").set("opname", "min_uc3");
    model.component("comp2").cpl("minop14").selection().set(9);
    model.component("comp2").cpl().create("aveop14", "Average");
    model.component("comp2").cpl("aveop14").set("axisym", true);
    model.component("comp2").cpl("aveop14").set("opname", "ave_uc3");
    model.component("comp2").cpl("aveop14").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop14").selection().named("sel18");
    model.component("comp2").cpl().create("intop8", "Integration");
    model.component("comp2").cpl("intop8").set("axisym", true);
    model.component("comp2").cpl("intop8").set("opname", "int_uc3");
    model.component("comp2").cpl("intop8").selection().set(9);
    model.component("comp2").cpl().create("maxop15", "Maximum");
    model.component("comp2").cpl("maxop15").set("opname", "max_uc4");
    model.component("comp2").cpl("maxop15").selection().set(14);
    model.component("comp2").cpl().create("minop15", "Minimum");
    model.component("comp2").cpl("minop15").set("opname", "min_uc4");
    model.component("comp2").cpl("minop15").selection().set(14);
    model.component("comp2").cpl().create("aveop15", "Average");
    model.component("comp2").cpl("aveop15").set("axisym", true);
    model.component("comp2").cpl("aveop15").set("opname", "ave_uc4");
    model.component("comp2").cpl("aveop15").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop15").selection().named("sel19");
    model.component("comp2").cpl().create("intop9", "Integration");
    model.component("comp2").cpl("intop9").set("axisym", true);
    model.component("comp2").cpl("intop9").set("opname", "int_uc4");
    model.component("comp2").cpl("intop9").selection().set(14);
    model.component("comp2").cpl().create("maxop16", "Maximum");
    model.component("comp2").cpl("maxop16").set("opname", "max_uc5");
    model.component("comp2").cpl("maxop16").selection().set(10);
    model.component("comp2").cpl().create("minop16", "Minimum");
    model.component("comp2").cpl("minop16").set("opname", "min_uc5");
    model.component("comp2").cpl("minop16").selection().set(10);
    model.component("comp2").cpl().create("aveop16", "Average");
    model.component("comp2").cpl("aveop16").set("axisym", true);
    model.component("comp2").cpl("aveop16").set("opname", "ave_uc5");
    model.component("comp2").cpl("aveop16").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop16").selection().named("sel20");
    model.component("comp2").cpl().create("maxop17", "Maximum");
    model.component("comp2").cpl("maxop17").set("opname", "max_uc6");
    model.component("comp2").cpl("maxop17").selection().set(6);
    model.component("comp2").cpl().create("minop17", "Minimum");
    model.component("comp2").cpl("minop17").set("opname", "min_uc6");
    model.component("comp2").cpl("minop17").selection().set(6);
    model.component("comp2").cpl().create("aveop17", "Average");
    model.component("comp2").cpl("aveop17").set("axisym", true);
    model.component("comp2").cpl("aveop17").set("opname", "ave_uc6");
    model.component("comp2").cpl("aveop17").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop17").selection().named("sel21");
    model.component("comp2").cpl().create("intop10", "Integration");
    model.component("comp2").cpl("intop10").set("axisym", true);
    model.component("comp2").cpl("intop10").set("opname", "int_uc6");
    model.component("comp2").cpl("intop10").selection().set(6);
    model.component("comp2").cpl().create("intop11", "Integration");
    model.component("comp2").cpl("intop11").set("axisym", true);
    model.component("comp2").cpl("intop11").set("opname", "int_internal");
    model.component("comp2").cpl("intop11").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop11").selection().named("sel25");

    model.component("comp2").variable().create("var2");

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2").set("f_htot", "119[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_hl1", "100[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_hl2", "19[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_hr1", "26[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_hr2", "14[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_hr3", "58[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u539a\u5ea6\uff09");
    model.component("comp2").variable("var2")
         .set("f_hr4", "19[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("f_wtot", "110[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_wb1", "90[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_wb2", "20[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_wt1", "47[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("f_wt2", "63[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_h", "92[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_w", "76[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_h", "54[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_w", "63[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_h2", "12[mm]", "\u9694\u70ed\u677f\u4e0b\u65b9\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("at", "2[mm]", "\u94dd\u5c42\u539a\u5ea6");
    model.component("comp2").variable("var2").set("et", "3[mm]", "EPDM \u539a\u5ea6");
    model.component("comp2").variable("var2")
         .set("e1_w", "12[mm]", "\u7b2c\u4e00\u4e2a EPDM \u5757\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_h", "6[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_w", "76[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc2_h", "22[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc2_w", "10[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc3_h", "27[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc3_w", "30[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc4_h", "15[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc4_w", "13[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc5_h", "26[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc5_w", "5[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc6_h", "77[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc6_w", "51[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("cc_w1", "17[mm]", "\u4e2d\u5fc3\u8154\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("cc_w2", "4[mm]", "\u4e2d\u5fc3\u8154\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("cc_h", "6[mm]", "\u4e2d\u5fc3\u8154\u7684\u9ad8\u5ea6\uff08\u6728\u5757 2 \u4e0e\u94dd\u5757\u4e4b\u95f4\uff09");
    model.component("comp2").variable("var2").set("pp", "15[mm]", "\u9694\u70ed\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp2").variable("var2").set("pvl", "190[mm]", "\u9694\u70ed\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp2").variable("var2")
         .set("Rp", "f_hr3/lambda_p", "\u9694\u70ed\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("Up_n", "1/(Rsi_n+Rse+Rp)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp2").variable("var2")
         .set("Up_p", "1/(Rsi_p+Rse+Rp)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp2").variable("var2")
         .set("Up", "(f_hr4*Up_p+(pvl-f_hr4)*Up_n)/pvl", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp2").variable("var2")
         .set("A_uc3", "int_uc3(1)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp2").variable("var2")
         .set("A_uc4", "int_uc4(1)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp2").variable("var2")
         .set("A_uc6", "int_uc6(1)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp2").variable("var2")
         .set("uc3_h2", "h_resized(A_uc3,uc3_h,uc3_w)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc4_h2", "h_resized(A_uc4,uc4_h,uc4_w)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc6_h2", "h_resized(A_uc6,uc6_h,uc6_w)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("R_uc1", "1/(ha_2(uc1_h,max_uc1(T2)-min_uc1(T2))+hr(uc1_h,uc1_w,ave_uc1(T2),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc2", "1/(ha_2(uc2_h,max_uc2(T2)-min_uc2(T2))+hr(uc2_h,uc2_w,ave_uc2(T2),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc3", "1/(ha_2(uc3_h2,max_uc3(T2)-min_uc3(T2))+hr(uc3_h,uc3_w,ave_uc3(T2),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc4", "1/(ha_2(uc4_h2,max_uc4(T2)-min_uc4(T2))+hr(uc4_h,uc4_w,ave_uc4(T2),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc5", "1/(ha_2(uc5_h,max_uc5(T2)-min_uc5(T2))+hr(uc5_h,uc5_w,ave_uc5(T2),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc6", "1/(ha_2(uc6_h2,max_uc6(T2)-min_uc6(T2))+hr(uc6_h,uc6_w,ave_uc6(T2),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");

    model.component("comp2").material().create("mat16", "Common");
    model.component("comp2").material("mat16").label("\u94dd");
    model.component("comp2").material("mat16").selection().set(1, 7);
    model.component("comp2").material("mat16").propertyGroup("def")
         .set("thermalconductivity", new String[]{"160[W/(m*K)]"});
    model.component("comp2").material("mat16").propertyGroup("def").set("density", new String[]{"2800[kg/m^3]"});
    model.component("comp2").material("mat16").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp2").material().create("mat17", "Common");
    model.component("comp2").material("mat17").label("EPDM");
    model.component("comp2").material("mat17").selection().set(4, 11, 13);
    model.component("comp2").material("mat17").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp2").material("mat17").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp2").material("mat17").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp2").material().create("mat18", "Common");
    model.component("comp2").material("mat18").label("\u9694\u70ed\u677f");
    model.component("comp2").material("mat18").selection().set(12);
    model.component("comp2").material("mat18").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp2").material("mat18").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp2").material("mat18").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp2").material().create("mat19", "Common");
    model.component("comp2").material("mat19").label("\u8f6f\u6728");
    model.component("comp2").material("mat19").selection().set(3, 5);
    model.component("comp2").material("mat19").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.13[W/(m*K)]"});
    model.component("comp2").material("mat19").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp2").material("mat19").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp2").material().create("mat20", "Common");
    model.component("comp2").material("mat20").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp2").material("mat20").selection().set(2);
    model.component("comp2").material("mat20").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h/R_uc1"});
    model.component("comp2").material("mat20").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat20").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat21", "Common");
    model.component("comp2").material("mat21").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp2").material("mat21").selection().set(8);
    model.component("comp2").material("mat21").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp2").material("mat21").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat21").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat22", "Common");
    model.component("comp2").material("mat22").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp2").material("mat22").selection().set(9);
    model.component("comp2").material("mat22").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h2/R_uc3"});
    model.component("comp2").material("mat22").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat22").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat23", "Common");
    model.component("comp2").material("mat23").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp2").material("mat23").selection().set(14);
    model.component("comp2").material("mat23").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h2/R_uc4"});
    model.component("comp2").material("mat23").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat23").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat24", "Common");
    model.component("comp2").material("mat24").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp2").material("mat24").selection().set(10);
    model.component("comp2").material("mat24").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h/R_uc5"});
    model.component("comp2").material("mat24").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat24").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat25", "Common");
    model.component("comp2").material("mat25").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp2").material("mat25").selection().set(6);
    model.component("comp2").material("mat25").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc6_h2/R_uc6"});
    model.component("comp2").material("mat25").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat25").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-6");

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 2);

    model.component("comp3").mesh().create("mesh3");

    model.component("comp3").label("\u7b2c\u4e09\u79cd\u7a97\u6237");

    model.component("comp3").physics().create("ht3", "HeatTransferInSolidsAndFluids", "geom3");

    model.study("std1").feature("stat").setSolveFor("/physics/ht3", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht3", false);

    model.component("comp3").geom("geom3").run();
    model.component("comp3").geom("geom3").create("imp1", "Import");
    model.component("comp3").geom("geom3").feature("imp1")
         .set("filename", "windows_thermal_performances_third_window.mphbin");
    model.component("comp3").geom("geom3").feature("imp1").importData();
    model.component("comp3").geom("geom3").run("fin");
    model.component("comp3").geom("geom3").create("ige1", "IgnoreEdges");
    model.component("comp3").geom("geom3").feature("ige1").selection("input")
         .set("fin", 51, 76, 86, 94, 96, 98, 100, 102, 106, 107, 108, 110, 112, 143);
    model.component("comp3").geom("geom3").run("ige1");
    model.component("comp3").geom("geom3").create("igv1", "IgnoreVertices");
    model.component("comp3").geom("geom3").feature("igv1").selection("input")
         .set("ige1", 15, 16, 22, 23, 40, 60, 64, 67, 73, 76, 84, 85, 106, 107, 112, 113);
    model.component("comp3").geom("geom3").run("igv1");

    model.component("comp3").selection().create("sel26", "Explicit");
    model.component("comp3").selection("sel26").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp3").selection("sel26").geom(1);
    model.component("comp3").selection("sel26").set(3, 4, 6, 8);
    model.component("comp3").selection().create("sel27", "Explicit");
    model.component("comp3").selection("sel27").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp3").selection("sel27").geom(1);
    model.component("comp3").selection("sel27").set(15, 16, 17, 36);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp3").selection().create("sel28", "Explicit");
    model.component("comp3").selection("sel28").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp3").selection("sel28").geom(1);
    model.component("comp3").selection("sel28").set(12, 13, 14, 35);
    model.component("comp3").selection().create("sel29", "Explicit");
    model.component("comp3").selection("sel29").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp3").selection("sel29").geom(1);
    model.component("comp3").selection("sel29").set(32, 33, 34, 56);
    model.component("comp3").selection().create("sel30", "Explicit");
    model.component("comp3").selection("sel30").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp3").selection("sel30").geom(1);
    model.component("comp3").selection("sel30").set(43, 44, 45, 46, 50, 53, 55, 67);
    model.component("comp3").selection().create("sel31", "Explicit");
    model.component("comp3").selection("sel31").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp3").selection("sel31").geom(1);
    model.component("comp3").selection("sel31").set(37, 38, 39, 48, 51, 52, 60, 61, 65, 66);
    model.component("comp3").selection().create("sel32", "Explicit");
    model.component("comp3").selection("sel32").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp3").selection("sel32").geom(1);
    model.component("comp3").selection("sel32").set(57, 58, 59, 72, 73, 85);
    model.component("comp3").selection().create("sel33", "Explicit");
    model.component("comp3").selection("sel33").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp3").selection("sel33").geom(1);
    model.component("comp3").selection("sel33").set(69, 70, 71, 84);
    model.component("comp3").selection().create("sel34", "Explicit");
    model.component("comp3").selection("sel34").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp3").selection("sel34").geom(1);
    model.component("comp3").selection("sel34").set(92, 93, 94, 103);
    model.component("comp3").selection().create("sel35", "Explicit");
    model.component("comp3").selection("sel35").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp3").selection("sel35").geom(1);
    model.component("comp3").selection("sel35").set(89, 90, 91, 95, 97, 99);
    model.component("comp3").selection().create("sel36", "Explicit");
    model.component("comp3").selection("sel36").label("\u4e0d\u901a\u98ce\u7684\u8154 11");
    model.component("comp3").selection("sel36").geom(1);
    model.component("comp3").selection("sel36").set(86, 87, 88, 102);
    model.component("comp3").selection().create("sel37", "Explicit");
    model.component("comp3").selection("sel37").label("\u4e0d\u901a\u98ce\u7684\u8154 12");
    model.component("comp3").selection("sel37").geom(1);
    model.component("comp3").selection("sel37").set(9, 10, 11, 68);
    model.component("comp3").selection().create("sel38", "Explicit");
    model.component("comp3").selection("sel38").label("\u5916\u4fa7");
    model.component("comp3").selection("sel38").geom(1);
    model.component("comp3").selection("sel38").set(2, 77, 78, 79, 104, 105, 106);
    model.component("comp3").selection().create("sel39", "Explicit");
    model.component("comp3").selection("sel39").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp3").selection("sel39").geom(1);
    model.component("comp3").selection("sel39").set(18, 25, 27, 107, 108, 109);
    model.component("comp3").selection().create("sel40", "Explicit");
    model.component("comp3").selection("sel40").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp3").selection("sel40").geom(1);
    model.component("comp3").selection("sel40").set(7, 29, 110);
    model.component("comp3").selection().create("sel41", "Explicit");
    model.component("comp3").selection("sel41").label("\u5185\u4fa7");
    model.component("comp3").selection("sel41").geom(1);
    model.component("comp3").selection("sel41").set(7, 18, 25, 27, 29, 107, 108, 109, 110);

    model.component("comp3").cpl().create("maxop18", "Maximum");
    model.component("comp3").cpl("maxop18").set("opname", "max_uc1");
    model.component("comp3").cpl("maxop18").selection().set(2);
    model.component("comp3").cpl().create("minop18", "Minimum");
    model.component("comp3").cpl("minop18").set("opname", "min_uc1");
    model.component("comp3").cpl("minop18").selection().set(2);
    model.component("comp3").cpl().create("aveop18", "Average");
    model.component("comp3").cpl("aveop18").set("axisym", true);
    model.component("comp3").cpl("aveop18").set("opname", "ave_uc1");
    model.component("comp3").cpl("aveop18").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop18").selection().named("sel26");
    model.component("comp3").cpl().create("maxop19", "Maximum");
    model.component("comp3").cpl("maxop19").set("opname", "max_uc2");
    model.component("comp3").cpl("maxop19").selection().set(5);
    model.component("comp3").cpl().create("minop19", "Minimum");
    model.component("comp3").cpl("minop19").set("opname", "min_uc2");
    model.component("comp3").cpl("minop19").selection().set(5);
    model.component("comp3").cpl().create("aveop19", "Average");
    model.component("comp3").cpl("aveop19").set("axisym", true);
    model.component("comp3").cpl("aveop19").set("opname", "ave_uc2");
    model.component("comp3").cpl("aveop19").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop19").selection().named("sel27");
    model.component("comp3").cpl().create("maxop20", "Maximum");
    model.component("comp3").cpl("maxop20").set("opname", "max_uc3");
    model.component("comp3").cpl("maxop20").selection().set(4);
    model.component("comp3").cpl().create("minop20", "Minimum");
    model.component("comp3").cpl("minop20").set("opname", "min_uc3");
    model.component("comp3").cpl("minop20").selection().set(4);
    model.component("comp3").cpl().create("aveop20", "Average");
    model.component("comp3").cpl("aveop20").set("axisym", true);
    model.component("comp3").cpl("aveop20").set("opname", "ave_uc3");
    model.component("comp3").cpl("aveop20").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop20").selection().named("sel28");
    model.component("comp3").cpl().create("intop12", "Integration");
    model.component("comp3").cpl("intop12").set("axisym", true);
    model.component("comp3").cpl("intop12").set("opname", "int_uc3");
    model.component("comp3").cpl("intop12").selection().set(4);
    model.component("comp3").cpl().create("maxop21", "Maximum");
    model.component("comp3").cpl("maxop21").set("opname", "max_uc4");
    model.component("comp3").cpl("maxop21").selection().set(9);
    model.component("comp3").cpl().create("minop21", "Minimum");
    model.component("comp3").cpl("minop21").set("opname", "min_uc4");
    model.component("comp3").cpl("minop21").selection().set(9);
    model.component("comp3").cpl().create("aveop21", "Average");
    model.component("comp3").cpl("aveop21").set("axisym", true);
    model.component("comp3").cpl("aveop21").set("opname", "ave_uc4");
    model.component("comp3").cpl("aveop21").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop21").selection().named("sel29");
    model.component("comp3").cpl().create("maxop22", "Maximum");
    model.component("comp3").cpl("maxop22").set("opname", "max_uc5");
    model.component("comp3").cpl("maxop22").selection().set(12);
    model.component("comp3").cpl().create("minop22", "Minimum");
    model.component("comp3").cpl("minop22").set("opname", "min_uc5");
    model.component("comp3").cpl("minop22").selection().set(12);
    model.component("comp3").cpl().create("aveop22", "Average");
    model.component("comp3").cpl("aveop22").set("axisym", true);
    model.component("comp3").cpl("aveop22").set("opname", "ave_uc5");
    model.component("comp3").cpl("aveop22").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop22").selection().named("sel30");
    model.component("comp3").cpl().create("intop13", "Integration");
    model.component("comp3").cpl("intop13").set("axisym", true);
    model.component("comp3").cpl("intop13").set("opname", "int_uc5");
    model.component("comp3").cpl("intop13").selection().set(12);
    model.component("comp3").cpl().create("maxop23", "Maximum");
    model.component("comp3").cpl("maxop23").set("opname", "max_uc6");
    model.component("comp3").cpl("maxop23").selection().set(10);
    model.component("comp3").cpl().create("minop23", "Minimum");
    model.component("comp3").cpl("minop23").set("opname", "min_uc6");
    model.component("comp3").cpl("minop23").selection().set(10);
    model.component("comp3").cpl().create("aveop23", "Average");
    model.component("comp3").cpl("aveop23").set("axisym", true);
    model.component("comp3").cpl("aveop23").set("opname", "ave_uc6");
    model.component("comp3").cpl("aveop23").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop23").selection().named("sel31");
    model.component("comp3").cpl().create("intop14", "Integration");
    model.component("comp3").cpl("intop14").set("axisym", true);
    model.component("comp3").cpl("intop14").set("opname", "int_uc6");
    model.component("comp3").cpl("intop14").selection().set(10);
    model.component("comp3").cpl().create("maxop24", "Maximum");
    model.component("comp3").cpl("maxop24").set("opname", "max_uc7");
    model.component("comp3").cpl("maxop24").selection().set(13);
    model.component("comp3").cpl().create("minop24", "Minimum");
    model.component("comp3").cpl("minop24").set("opname", "min_uc7");
    model.component("comp3").cpl("minop24").selection().set(13);
    model.component("comp3").cpl().create("aveop24", "Average");
    model.component("comp3").cpl("aveop24").set("axisym", true);
    model.component("comp3").cpl("aveop24").set("opname", "ave_uc7");
    model.component("comp3").cpl("aveop24").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop24").selection().named("sel32");
    model.component("comp3").cpl().create("intop15", "Integration");
    model.component("comp3").cpl("intop15").set("axisym", true);
    model.component("comp3").cpl("intop15").set("opname", "int_uc7");
    model.component("comp3").cpl("intop15").selection().set(13);
    model.component("comp3").cpl().create("maxop25", "Maximum");
    model.component("comp3").cpl("maxop25").set("opname", "max_uc8");
    model.component("comp3").cpl("maxop25").selection().set(15);
    model.component("comp3").cpl().create("minop25", "Minimum");
    model.component("comp3").cpl("minop25").set("opname", "min_uc8");
    model.component("comp3").cpl("minop25").selection().set(15);
    model.component("comp3").cpl().create("aveop25", "Average");
    model.component("comp3").cpl("aveop25").set("axisym", true);
    model.component("comp3").cpl("aveop25").set("opname", "ave_uc8");
    model.component("comp3").cpl("aveop25").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop25").selection().named("sel33");
    model.component("comp3").cpl().create("maxop26", "Maximum");
    model.component("comp3").cpl("maxop26").set("opname", "max_uc9");
    model.component("comp3").cpl("maxop26").selection().set(18);
    model.component("comp3").cpl().create("minop26", "Minimum");
    model.component("comp3").cpl("minop26").set("opname", "min_uc9");
    model.component("comp3").cpl("minop26").selection().set(18);
    model.component("comp3").cpl().create("aveop26", "Average");
    model.component("comp3").cpl("aveop26").set("axisym", true);
    model.component("comp3").cpl("aveop26").set("opname", "ave_uc9");
    model.component("comp3").cpl("aveop26").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop26").selection().named("sel34");
    model.component("comp3").cpl().create("maxop27", "Maximum");
    model.component("comp3").cpl("maxop27").set("opname", "max_uc10");
    model.component("comp3").cpl("maxop27").selection().set(17);
    model.component("comp3").cpl().create("minop27", "Minimum");
    model.component("comp3").cpl("minop27").set("opname", "min_uc10");
    model.component("comp3").cpl("minop27").selection().set(17);
    model.component("comp3").cpl().create("aveop27", "Average");
    model.component("comp3").cpl("aveop27").set("axisym", true);
    model.component("comp3").cpl("aveop27").set("opname", "ave_uc10");
    model.component("comp3").cpl("aveop27").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop27").selection().named("sel35");
    model.component("comp3").cpl().create("maxop28", "Maximum");
    model.component("comp3").cpl("maxop28").set("opname", "max_uc11");
    model.component("comp3").cpl("maxop28").selection().set(16);
    model.component("comp3").cpl().create("minop28", "Minimum");
    model.component("comp3").cpl("minop28").set("opname", "min_uc11");
    model.component("comp3").cpl("minop28").selection().set(16);
    model.component("comp3").cpl().create("aveop28", "Average");
    model.component("comp3").cpl("aveop28").set("axisym", true);
    model.component("comp3").cpl("aveop28").set("opname", "ave_uc11");
    model.component("comp3").cpl("aveop28").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop28").selection().named("sel36");
    model.component("comp3").cpl().create("maxop29", "Maximum");
    model.component("comp3").cpl("maxop29").set("opname", "max_uc12");
    model.component("comp3").cpl("maxop29").selection().set(3);
    model.component("comp3").cpl().create("minop29", "Minimum");
    model.component("comp3").cpl("minop29").set("opname", "min_uc12");
    model.component("comp3").cpl("minop29").selection().set(3);
    model.component("comp3").cpl().create("aveop29", "Average");
    model.component("comp3").cpl("aveop29").set("axisym", true);
    model.component("comp3").cpl("aveop29").set("opname", "ave_uc12");
    model.component("comp3").cpl("aveop29").selection().geom("geom3", 1);
    model.component("comp3").cpl("aveop29").selection().named("sel37");
    model.component("comp3").cpl().create("intop16", "Integration");
    model.component("comp3").cpl("intop16").set("axisym", true);
    model.component("comp3").cpl("intop16").set("opname", "int_internal");
    model.component("comp3").cpl("intop16").selection().geom("geom3", 1);
    model.component("comp3").cpl("intop16").selection().named("sel41");

    model.component("comp3").variable().create("var3");

//    To import content from file, use:
//    model.component("comp3").variable("var3").loadFile("FILENAME");
    model.component("comp3").variable("var3").set("f_htot", "83[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_hl1", "66[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_hl2", "17[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_hr1", "11[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_hr2", "19[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_hr3", "28[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u539a\u5ea6\uff09");
    model.component("comp3").variable("var3")
         .set("f_hr4", "25[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3").set("f_wtot", "110[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_wb1", "68[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_wb2", "42[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_wt1", "28[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("f_wt2", "82[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc1_h", "60[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc1_w", "8[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc2_h", "11[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc2_w", "22[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc3_h", "39[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc3_w", "22[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc4_h", "8[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc4_w", "15[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc5_h", "20[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc5_w", "26[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc6_h", "39[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc6_w", "26[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc7_h", "53[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc7_w", "34[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc8_h", "10[mm]", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc8_w", "17[mm]", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc9_h", "14[mm]", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc9_w", "22[mm]", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc10_h", "36[mm]", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc10_w", "10[mm]", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc11_h", "10[mm]", "\u7b2c\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc11_w", "22[mm]", "\u7b2c\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc12_h", "4[mm]", "\u7b2c\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc12_w", "54[mm]", "\u7b2c\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp3").variable("var3").set("epdm_t1", "3[mm]", "\u7b2c\u4e00\u4e2a epdm \u539a\u5ea6");
    model.component("comp3").variable("var3").set("epdm_t2", "5[mm]", "\u7b2c\u4e8c\u4e2a epdm \u539a\u5ea6");
    model.component("comp3").variable("var3").set("epdm_w1", "8[mm]", "epdm \u7684\u7b2c\u4e00\u5bbd\u5ea6");
    model.component("comp3").variable("var3").set("epdm_w2", "10[mm]", "epdm \u7684\u7b2c\u4e8c\u5bbd\u5ea6");
    model.component("comp3").variable("var3").set("s_t", "2[mm]", "\u94a2\u539a\u5ea6");
    model.component("comp3").variable("var3").set("sp1", "1[mm]", "PVC \u4e0e\u94a2\u4e4b\u95f4\u7684\u7a7a\u95f4");
    model.component("comp3").variable("var3").set("sp2", "6[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp3").variable("var3").set("sp3", "16[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp3").variable("var3").set("pvc_t1", "2[mm]", "\u7b2c\u4e00\u4e2a pvc \u539a\u5ea6");
    model.component("comp3").variable("var3").set("pvc_t2", "3[mm]", "\u7b2c\u4e8c\u4e2a pvc \u539a\u5ea6");
    model.component("comp3").variable("var3").set("pvc_w", "4[mm]", "PVC \u6954\u5b50\u957f\u5ea6");
    model.component("comp3").variable("var3").set("pp", "15[mm]", "\u9762\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp3").variable("var3").set("pvl", "190[mm]", "\u9762\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp3").variable("var3")
         .set("Rp", "f_hr3/lambda_p", "\u9762\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("Up_n", "1/(Rp+Rse+Rsi_n)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp3").variable("var3")
         .set("Up_p", "1/(Rp+Rse+Rsi_p)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp3").variable("var3")
         .set("Up", "(Up_p*f_hr4+Up_n*(pvl-f_hr4))/pvl", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp3").variable("var3")
         .set("A_uc3", "int_uc3(1)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp3").variable("var3")
         .set("A_uc5", "int_uc5(1)", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp3").variable("var3")
         .set("A_uc6", "int_uc6(1)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp3").variable("var3")
         .set("A_uc7", "int_uc7(1)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp3").variable("var3")
         .set("uc3_h2", "h_resized(A_uc3,uc3_h,uc3_w)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc5_h2", "h_resized(A_uc5,uc5_h,uc5_w)", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc6_h2", "h_resized(A_uc6,uc6_h,uc6_w)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("uc7_h2", "h_resized(A_uc7,uc7_h,uc7_w)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp3").variable("var3")
         .set("R_uc1", "1/(ha_2(uc1_h,max_uc1(T3)-min_uc1(T3))+hr(uc1_h,uc1_w,ave_uc1(T3),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc2", "1/(ha_2(uc2_h,max_uc2(T3)-min_uc2(T3))+hr(uc2_h,uc2_w,ave_uc2(T3),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc3", "1/(ha_2(uc3_h2,max_uc3(T3)-min_uc3(T3))+hr(uc3_h,uc3_w,ave_uc3(T3),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc4", "1/(ha_2(uc4_h,max_uc4(T3)-min_uc4(T3))+hr(uc4_h,uc4_w,ave_uc4(T3),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc5", "1/(ha_2(uc5_h2,max_uc5(T3)-min_uc5(T3))+hr(uc5_h,uc5_w,ave_uc5(T3),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc6", "1/(ha_2(uc6_h2,max_uc6(T3)-min_uc6(T3))+hr(uc6_h,uc6_w,ave_uc6(T3),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc7", "1/(ha_2(uc7_h2,max_uc7(T3)-min_uc7(T3))+hr(uc7_h,uc7_w,ave_uc7(T3),epsilon,epsilon))", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc8", "1/(ha_2(uc8_h,max_uc8(T3)-min_uc8(T3))+hr(uc8_h,uc8_w,ave_uc8(T3),epsilon,epsilon))", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc9", "1/(ha_2(uc9_h,max_uc9(T3)-min_uc9(T3))+hr(uc9_h,uc9_w,ave_uc9(T3),epsilon,epsilon))", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc10", "1/(ha_2(uc10_h,max_uc10(T3)-min_uc10(T3))+hr(uc10_h,uc10_w,ave_uc10(T3),epsilon,epsilon))", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc11", "1/(ha_2(uc11_h,max_uc11(T3)-min_uc11(T3))+hr(uc11_h,uc11_w,ave_uc11(T3),epsilon,epsilon))", "\u7b2c\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp3").variable("var3")
         .set("R_uc12", "1/(ha_2(uc12_h,max_uc12(T3)-min_uc12(T3))+hr(uc12_h,uc12_w,ave_uc12(T3),epsilon,epsilon))", "\u7b2c\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");

    model.component("comp3").material().create("mat26", "Common");
    model.component("comp3").material("mat26").label("\u94a2");
    model.component("comp3").material("mat26").selection().set(6, 14);
    model.component("comp3").material("mat26").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]"});
    model.component("comp3").material("mat26").propertyGroup("def").set("density", new String[]{"7800[kg/m^3]"});
    model.component("comp3").material("mat26").propertyGroup("def")
         .set("heatcapacity", new String[]{"450[J/(kg*K)]"});
    model.component("comp3").material().create("mat27", "Common");
    model.component("comp3").material("mat27").label("EPDM");
    model.component("comp3").material("mat27").selection().set(7, 11, 19, 21);
    model.component("comp3").material("mat27").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp3").material("mat27").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp3").material("mat27").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp3").material().create("mat28", "Common");
    model.component("comp3").material("mat28").label("PVC");
    model.component("comp3").material("mat28").selection().set(1, 8);
    model.component("comp3").material("mat28").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.17[W/(m*K)]"});
    model.component("comp3").material("mat28").propertyGroup("def").set("density", new String[]{"1390[kg/m^3]"});
    model.component("comp3").material("mat28").propertyGroup("def")
         .set("heatcapacity", new String[]{"1900[J/(kg*K)]"});
    model.component("comp3").material().create("mat29", "Common");
    model.component("comp3").material("mat29").label("\u9694\u70ed\u677f");
    model.component("comp3").material("mat29").selection().set(20);
    model.component("comp3").material("mat29").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp3").material("mat29").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp3").material("mat29").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp3").material().create("mat30", "Common");
    model.component("comp3").material("mat30").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp3").material("mat30").selection().set(2);
    model.component("comp3").material("mat30").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h/R_uc1"});
    model.component("comp3").material("mat30").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat30").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat31", "Common");
    model.component("comp3").material("mat31").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp3").material("mat31").selection().set(5);
    model.component("comp3").material("mat31").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp3").material("mat31").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat31").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat32", "Common");
    model.component("comp3").material("mat32").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp3").material("mat32").selection().set(4);
    model.component("comp3").material("mat32").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h2/R_uc3"});
    model.component("comp3").material("mat32").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat32").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat33", "Common");
    model.component("comp3").material("mat33").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp3").material("mat33").selection().set(9);
    model.component("comp3").material("mat33").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h/R_uc4"});
    model.component("comp3").material("mat33").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat33").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat34", "Common");
    model.component("comp3").material("mat34").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp3").material("mat34").selection().set(12);
    model.component("comp3").material("mat34").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h2/R_uc5"});
    model.component("comp3").material("mat34").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat34").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat35", "Common");
    model.component("comp3").material("mat35").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp3").material("mat35").selection().set(10);
    model.component("comp3").material("mat35").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc6_h2/R_uc6"});
    model.component("comp3").material("mat35").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat35").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat36", "Common");
    model.component("comp3").material("mat36").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp3").material("mat36").selection().set(13);
    model.component("comp3").material("mat36").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc7_h2/R_uc7"});
    model.component("comp3").material("mat36").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat36").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp3").material().create("mat37", "Common");
    model.component("comp3").material("mat37").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp3").material("mat37").selection().set(15);
    model.component("comp3").material("mat37").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc8_h/R_uc8"});
    model.component("comp3").material("mat37").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat37").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat38", "Common");
    model.component("comp3").material("mat38").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp3").material("mat38").selection().set(18);
    model.component("comp3").material("mat38").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc9_h/R_uc9"});
    model.component("comp3").material("mat38").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat38").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat39", "Common");
    model.component("comp3").material("mat39").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp3").material("mat39").selection().set(17);
    model.component("comp3").material("mat39").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc10_h/R_uc10"});
    model.component("comp3").material("mat39").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat39").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat40", "Common");
    model.component("comp3").material("mat40").label("\u4e0d\u901a\u98ce\u7684\u8154 11");
    model.component("comp3").material("mat40").selection().set(16);
    model.component("comp3").material("mat40").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc11_h/R_uc11"});
    model.component("comp3").material("mat40").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat40").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp3").material().create("mat41", "Common");
    model.component("comp3").material("mat41").label("\u4e0d\u901a\u98ce\u7684\u8154 12");
    model.component("comp3").material("mat41").selection().set(3);
    model.component("comp3").material("mat41").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc12_h/R_uc12"});
    model.component("comp3").material("mat41").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp3").material("mat41").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht3", true);
    model.study("std3").showAutoSequences("all");
    model.study("std3").feature("stat").set("usestol", true);
    model.study("std3").feature("stat").set("stol", "1e-6");

    model.component().create("comp4", true);

    model.component("comp4").geom().create("geom4", 2);

    model.component("comp4").mesh().create("mesh4");

    model.component("comp4").label("\u7b2c\u56db\u79cd\u7a97\u6237");

    model.component("comp4").physics().create("ht4", "HeatTransferInSolidsAndFluids", "geom4");

    model.study("std1").feature("stat").setSolveFor("/physics/ht4", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht4", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht4", false);

    model.component("comp4").geom("geom4").run();
    model.component("comp4").geom("geom4").create("imp1", "Import");
    model.component("comp4").geom("geom4").feature("imp1")
         .set("filename", "windows_thermal_performances_fourth_window.mphbin");
    model.component("comp4").geom("geom4").feature("imp1").importData();
    model.component("comp4").geom("geom4").run("fin");
    model.component("comp4").geom("geom4").create("ige1", "IgnoreEdges");
    model.component("comp4").geom("geom4").feature("ige1").selection("input")
         .set("fin", 9, 12, 22, 25, 27, 33, 35, 42, 53, 60, 100, 101);
    model.component("comp4").geom("geom4").run("ige1");
    model.component("comp4").geom("geom4").create("igv1", "IgnoreVertices");
    model.component("comp4").geom("geom4").feature("igv1").selection("input")
         .set("ige1", 7, 14, 17, 21, 29, 41, 48, 57, 62, 66, 72, 74, 77);
    model.component("comp4").geom("geom4").run("igv1");

    model.component("comp4").selection().create("sel42", "Explicit");
    model.component("comp4").selection("sel42").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp4").selection("sel42").geom(1);
    model.component("comp4").selection("sel42").set(3, 4, 5, 11, 12, 14, 15, 17, 18);
    model.component("comp4").selection().create("sel43", "Explicit");
    model.component("comp4").selection("sel43").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp4").selection("sel43").geom(1);
    model.component("comp4").selection("sel43").set(40, 41, 43, 50);
    model.component("comp4").selection().create("sel44", "Explicit");
    model.component("comp4").selection("sel44").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp4").selection("sel44").geom(1);
    model.component("comp4").selection("sel44").set(76, 77, 78, 79, 80, 82, 84);
    model.component("comp4").selection().create("sel45", "Explicit");
    model.component("comp4").selection("sel45").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 1");
    model.component("comp4").selection("sel45").geom(1);
    model.component("comp4").selection("sel45").set(44, 45, 53);
    model.component("comp4").selection().create("sel46", "Explicit");
    model.component("comp4").selection("sel46").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 2");
    model.component("comp4").selection("sel46").geom(1);
    model.component("comp4").selection("sel46").set(25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 37);
    model.component("comp4").selection().create("sel47", "Explicit");
    model.component("comp4").selection("sel47").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 3");
    model.component("comp4").selection("sel47").geom(1);
    model.component("comp4").selection("sel47").set(58, 59, 60, 63, 64, 66, 67, 68, 69, 71, 73);
    model.component("comp4").selection().create("sel48", "Explicit");
    model.component("comp4").selection("sel48").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 4");
    model.component("comp4").selection("sel48").geom(1);
    model.component("comp4").selection("sel48").set(9, 10, 16);
    model.component("comp4").selection().create("sel49", "Explicit");
    model.component("comp4").selection("sel49").label("\u5916\u4fa7");
    model.component("comp4").selection("sel49").geom(1);
    model.component("comp4").selection("sel49").set(2, 6, 7, 8, 22, 23, 24, 70, 72, 74, 90, 91, 92);
    model.component("comp4").selection().create("sel50", "Explicit");
    model.component("comp4").selection("sel50").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp4").selection("sel50").geom(1);
    model.component("comp4").selection("sel50").set(46, 47, 55, 87, 88, 93, 94, 95);
    model.component("comp4").selection().create("sel51", "Explicit");
    model.component("comp4").selection("sel51").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp4").selection("sel51").geom(1);
    model.component("comp4").selection("sel51").set(21, 75, 89, 96);
    model.component("comp4").selection().create("sel52", "Explicit");
    model.component("comp4").selection("sel52").label("\u5185\u4fa7");
    model.component("comp4").selection("sel52").geom(1);
    model.component("comp4").selection("sel52").set(21, 46, 47, 55, 75, 87, 88, 89, 93, 94, 95, 96);

    model.component("comp4").cpl().create("maxop30", "Maximum");
    model.component("comp4").cpl("maxop30").set("opname", "max_uc1");
    model.component("comp4").cpl("maxop30").selection().set(2);
    model.component("comp4").cpl().create("minop30", "Minimum");
    model.component("comp4").cpl("minop30").set("opname", "min_uc1");
    model.component("comp4").cpl("minop30").selection().set(2);
    model.component("comp4").cpl().create("aveop30", "Average");
    model.component("comp4").cpl("aveop30").set("axisym", true);
    model.component("comp4").cpl("aveop30").set("opname", "ave_uc1");
    model.component("comp4").cpl("aveop30").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop30").selection().named("sel42");
    model.component("comp4").cpl().create("intop17", "Integration");
    model.component("comp4").cpl("intop17").set("axisym", true);
    model.component("comp4").cpl("intop17").set("opname", "int_uc1");
    model.component("comp4").cpl("intop17").selection().set(2);
    model.component("comp4").cpl().create("aveop31", "Average");
    model.component("comp4").cpl("aveop31").set("axisym", true);
    model.component("comp4").cpl("aveop31").set("opname", "ave_uc2");
    model.component("comp4").cpl("aveop31").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop31").selection().named("sel43");
    model.component("comp4").cpl().create("maxop31", "Maximum");
    model.component("comp4").cpl("maxop31").set("opname", "max_uc3");
    model.component("comp4").cpl("maxop31").selection().set(15);
    model.component("comp4").cpl().create("minop31", "Minimum");
    model.component("comp4").cpl("minop31").set("opname", "min_uc3");
    model.component("comp4").cpl("minop31").selection().set(15);
    model.component("comp4").cpl().create("aveop32", "Average");
    model.component("comp4").cpl("aveop32").set("axisym", true);
    model.component("comp4").cpl("aveop32").set("opname", "ave_uc3");
    model.component("comp4").cpl("aveop32").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop32").selection().named("sel44");
    model.component("comp4").cpl().create("aveop33", "Average");
    model.component("comp4").cpl("aveop33").set("axisym", true);
    model.component("comp4").cpl("aveop33").set("opname", "ave_svc1");
    model.component("comp4").cpl("aveop33").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop33").selection().named("sel45");
    model.component("comp4").cpl().create("maxop32", "Maximum");
    model.component("comp4").cpl("maxop32").set("opname", "max_svc2");
    model.component("comp4").cpl("maxop32").selection().set(7);
    model.component("comp4").cpl().create("minop32", "Minimum");
    model.component("comp4").cpl("minop32").set("opname", "min_svc2");
    model.component("comp4").cpl("minop32").selection().set(7);
    model.component("comp4").cpl().create("aveop34", "Average");
    model.component("comp4").cpl("aveop34").set("axisym", true);
    model.component("comp4").cpl("aveop34").set("opname", "ave_svc2");
    model.component("comp4").cpl("aveop34").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop34").selection().named("sel46");
    model.component("comp4").cpl().create("intop18", "Integration");
    model.component("comp4").cpl("intop18").set("axisym", true);
    model.component("comp4").cpl("intop18").set("opname", "int_svc2");
    model.component("comp4").cpl("intop18").selection().set(7);
    model.component("comp4").cpl().create("maxop33", "Maximum");
    model.component("comp4").cpl("maxop33").set("opname", "max_svc3");
    model.component("comp4").cpl("maxop33").selection().set(13);
    model.component("comp4").cpl().create("minop33", "Minimum");
    model.component("comp4").cpl("minop33").set("opname", "min_svc3");
    model.component("comp4").cpl("minop33").selection().set(13);
    model.component("comp4").cpl().create("aveop35", "Average");
    model.component("comp4").cpl("aveop35").set("axisym", true);
    model.component("comp4").cpl("aveop35").set("opname", "ave_svc3");
    model.component("comp4").cpl("aveop35").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop35").selection().named("sel47");
    model.component("comp4").cpl().create("intop19", "Integration");
    model.component("comp4").cpl("intop19").set("axisym", true);
    model.component("comp4").cpl("intop19").set("opname", "int_svc3");
    model.component("comp4").cpl("intop19").selection().set(13);
    model.component("comp4").cpl().create("aveop36", "Average");
    model.component("comp4").cpl("aveop36").set("axisym", true);
    model.component("comp4").cpl("aveop36").set("opname", "ave_svc4");
    model.component("comp4").cpl("aveop36").selection().geom("geom4", 1);
    model.component("comp4").cpl("aveop36").selection().named("sel48");
    model.component("comp4").cpl().create("intop20", "Integration");
    model.component("comp4").cpl("intop20").set("axisym", true);
    model.component("comp4").cpl("intop20").set("opname", "int_internal");
    model.component("comp4").cpl("intop20").selection().geom("geom4", 1);
    model.component("comp4").cpl("intop20").selection().named("sel52");

    model.component("comp4").variable().create("var4");

//    To import content from file, use:
//    model.component("comp4").variable("var4").loadFile("FILENAME");
    model.component("comp4").variable("var4").set("f_htot", "145[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl1", "12[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl2", "3[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl3", "63[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl4", "10[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl5", "8[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e94\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hl6", "49[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u516d\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hr1", "6[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hr2", "3[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hr3", "24[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u539a\u5ea6\uff09");
    model.component("comp4").variable("var4")
         .set("f_hr4", "56[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hr5", "7[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e94\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_hr6", "49[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u516d\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wtot", "89[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6\uff08\u7528\u4e8e\u8ba1\u7b97\uff09");
    model.component("comp4").variable("var4")
         .set("f_wb1", "10[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wb2", "20[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wb3", "54[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e09\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wb4", "5[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u56db\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wb5", "19[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e94\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wt1", "19[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wt2", "40[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wt3", "3[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e09\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wt4", "33[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u56db\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("f_wT4", "13[mm]", "\u6846\u67b6\u7b2c\u4e94\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc1_h", "80[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc1_w", "19[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc2_h", "20[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc2_w", "3[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc3_h", "28[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("uc3_w", "5[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc1_h", "7[mm]", "\u7b2c\u4e00\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc1_w", "3[mm]", "\u7b2c\u4e00\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc2_h", "63[mm]", "\u7b2c\u4e8c\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc2_w", "26[mm]", "\u7b2c\u4e8c\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc3_h", "15[mm]", "\u7b2c\u4e09\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc3_w", "18[mm]", "\u7b2c\u4e09\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc4_h", "10[mm]", "\u7b2c\u56db\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc4_w", "3[mm]", "\u7b2c\u56db\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4").set("pp", "13[mm]", "\u9762\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp4").variable("var4").set("pvl", "190[mm]", "\u9762\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp4").variable("var4")
         .set("epdm_t1", "2[mm]", "\u7b2c\u4e00\u4e2a EPDM \u5757\u7684\u539a\u5ea6");
    model.component("comp4").variable("var4")
         .set("epdm_t2", "3[mm]", "\u7b2c\u4e8c\u4e2a EPDM \u5757\u7684\u539a\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb1_h", "130[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb1_w", "40[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u7684\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb2_h", "95[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb2_w1", "15[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u7b2c\u4e00\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb2_w2", "34[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u7b2c\u4e8c\u5bbd\u5ea6");
    model.component("comp4").variable("var4")
         .set("wb2_w3", "52[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u7684\u7b2c\u4e09\u5bbd\u5ea6");
    model.component("comp4").variable("var4").set("a_t", "1[mm]", "\u94dd\u5c42\u539a\u5ea6");
    model.component("comp4").variable("var4")
         .set("a_h", "48[mm]", "\u6846\u67b6\u5de6\u4fa7\u94dd\u5757\u7684\u9ad8\u5ea6");
    model.component("comp4").variable("var4").set("sp1", "2[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp4").variable("var4").set("sp2", "3[mm]", "\u6709\u7528\u539a\u5ea6");
    model.component("comp4").variable("var4").set("sp3", "5[mm]", "\u6709\u7528\u539a\u5ea6");
    model.component("comp4").variable("var4").set("sp4", "6[mm]", "\u6709\u7528\u539a\u5ea6");
    model.component("comp4").variable("var4").set("sp5", "8[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp4").variable("var4")
         .set("A_uc1", "int_uc1(1)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp4").variable("var4")
         .set("A_svc2", "int_svc2(1)", "\u7b2c\u4e8c\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp4").variable("var4")
         .set("A_svc3", "int_svc3(1)", "\u7b2c\u4e09\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp4").variable("var4")
         .set("uc1_h2", "h_resized(A_uc1,uc1_h,uc1_w)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc2_h2", "h_resized(A_svc2,svc2_h,svc2_w)", "\u7b2c\u4e8c\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("svc3_h2", "h_resized(A_svc3,svc3_h,svc3_w)", "\u7b2c\u4e09\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp4").variable("var4")
         .set("R_uc1", "1/(ha_2(uc1_h2,max_uc1(T4)-min_uc1(T4))+hr(uc1_h,uc1_w,ave_uc1(T4),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_uc2", "1/(ha_1(uc2_h)+hr(uc2_h,uc2_w,ave_uc2(T4),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_uc3", "1/(ha_2(uc3_h,max_uc3(T4)-min_uc3(T4))+hr(uc3_h,uc3_w,ave_uc3(T4),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_svc1", "1/(ha_1(svc1_h)+hr(svc1_h,svc1_w,ave_svc1(T4),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_svc2", "1/(ha_2(svc2_h2,max_svc2(T4)-min_svc2(T4))+hr(svc2_h,svc2_w,ave_svc2(T4),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_svc3", "1/(ha_2(svc3_h2,max_svc3(T4)-min_svc3(T4))+hr(svc3_h,svc3_w,ave_svc3(T4),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("R_svc4", "1/(ha_1(svc4_h)+hr(svc4_h,svc4_w,ave_svc4(T4),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("Rp", "f_hr3/lambda_p", "\u9762\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp4").variable("var4")
         .set("Up_n", "1/(Rsi_n+Rse+Rp)", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp4").variable("var4")
         .set("Up_p", "1/(Rsi_p+Rse+Rp)", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp4").variable("var4")
         .set("Up", "(30[mm]*Up_p+(pvl-30[mm])*Up_n)/pvl", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387");

    model.component("comp4").material().create("mat42", "Common");
    model.component("comp4").material("mat42").label("\u8f6f\u6728");
    model.component("comp4").material("mat42").selection().set(5, 9);
    model.component("comp4").material("mat42").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.13[W/(m*K)]"});
    model.component("comp4").material("mat42").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp4").material("mat42").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp4").material().create("mat43", "Common");
    model.component("comp4").material("mat43").label("EPDM");
    model.component("comp4").material("mat43").selection().set(8, 11, 16, 18);
    model.component("comp4").material("mat43").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp4").material("mat43").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp4").material("mat43").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp4").material().create("mat44", "Common");
    model.component("comp4").material("mat44").label("\u9694\u70ed\u677f");
    model.component("comp4").material("mat44").selection().set(17);
    model.component("comp4").material("mat44").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp4").material("mat44").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp4").material("mat44").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp4").material().create("mat45", "Common");
    model.component("comp4").material("mat45").label("\u94dd");
    model.component("comp4").material("mat45").selection().set(1, 3, 6, 14);
    model.component("comp4").material("mat45").propertyGroup("def")
         .set("thermalconductivity", new String[]{"160[W/(m*K)]"});
    model.component("comp4").material("mat45").propertyGroup("def").set("density", new String[]{"2800[kg/m^3]"});
    model.component("comp4").material("mat45").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp4").material().create("mat46", "Common");
    model.component("comp4").material("mat46").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp4").material("mat46").selection().set(2);
    model.component("comp4").material("mat46").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h2/R_uc1"});
    model.component("comp4").material("mat46").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat46").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat47", "Common");
    model.component("comp4").material("mat47").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp4").material("mat47").selection().set(10);
    model.component("comp4").material("mat47").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp4").material("mat47").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat47").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat48", "Common");
    model.component("comp4").material("mat48").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp4").material("mat48").selection().set(15);
    model.component("comp4").material("mat48").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h/R_uc3"});
    model.component("comp4").material("mat48").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat48").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat49", "Common");
    model.component("comp4").material("mat49").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 1");
    model.component("comp4").material("mat49").selection().set(12);
    model.component("comp4").material("mat49").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc1_h/R_svc1"});
    model.component("comp4").material("mat49").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat49").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat50", "Common");
    model.component("comp4").material("mat50").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 2");
    model.component("comp4").material("mat50").selection().set(7);
    model.component("comp4").material("mat50").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc2_h2/R_svc2"});
    model.component("comp4").material("mat50").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat50").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat51", "Common");
    model.component("comp4").material("mat51").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 3");
    model.component("comp4").material("mat51").selection().set(13);
    model.component("comp4").material("mat51").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc3_h2/R_svc3"});
    model.component("comp4").material("mat51").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat51").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp4").material().create("mat52", "Common");
    model.component("comp4").material("mat52").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154 4");
    model.component("comp4").material("mat52").selection().set(4);
    model.component("comp4").material("mat52").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc4_h/R_svc4"});
    model.component("comp4").material("mat52").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp4").material("mat52").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht3", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht4", true);
    model.study("std4").showAutoSequences("all");
    model.study("std4").feature("stat").set("usestol", true);
    model.study("std4").feature("stat").set("stol", "1e-6");

    model.component().create("comp5", true);

    model.component("comp5").geom().create("geom5", 2);

    model.component("comp5").mesh().create("mesh5");

    model.component("comp5").label("\u7b2c\u4e94\u79cd\u7a97\u6237");

    model.component("comp5").physics().create("ht5", "HeatTransferInSolidsAndFluids", "geom5");

    model.study("std1").feature("stat").setSolveFor("/physics/ht5", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht5", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht5", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht5", false);

    model.component("comp5").geom("geom5").run();
    model.component("comp5").geom("geom5").create("imp1", "Import");
    model.component("comp5").geom("geom5").feature("imp1")
         .set("filename", "windows_thermal_performances_fifth_window.mphbin");
    model.component("comp5").geom("geom5").feature("imp1").importData();
    model.component("comp5").geom("geom5").run("fin");
    model.component("comp5").geom("geom5").create("ige1", "IgnoreEdges");

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp5").geom("geom5").feature("ige1").set("ignorevtx", false);
    model.component("comp5").geom("geom5").feature("ige1").selection("input")
         .set("fin", 15, 25, 27, 57, 59, 63, 89, 154);
    model.component("comp5").geom("geom5").run("ige1");
    model.component("comp5").geom("geom5").create("igv1", "IgnoreVertices");
    model.component("comp5").geom("geom5").feature("igv1").selection("input")
         .set("ige1", 3, 11, 16, 19, 49, 56, 60, 76, 100, 103, 123, 126);
    model.component("comp5").geom("geom5").run("igv1");

    model.component("comp5").selection().create("sel53", "Explicit");
    model.component("comp5").selection("sel53").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp5").selection("sel53").geom(1);
    model.component("comp5").selection("sel53").set(3, 4, 6, 8, 9, 10, 11, 12, 13, 14, 17, 30);
    model.component("comp5").selection().create("sel54", "Explicit");
    model.component("comp5").selection("sel54").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp5").selection("sel54").geom(1);
    model.component("comp5").selection("sel54").set(23, 24, 26, 27, 29, 31, 32, 33, 37, 38, 44, 45, 46, 47, 53);
    model.component("comp5").selection().create("sel55", "Explicit");
    model.component("comp5").selection("sel55").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp5").selection("sel55").geom(1);
    model.component("comp5").selection("sel55")
         .set(34, 35, 36, 61, 62, 63, 66, 67, 68, 69, 70, 71, 72, 73, 80, 84, 101, 102, 106, 107, 108, 109, 110, 111, 114, 117, 123);
    model.component("comp5").selection().create("sel56", "Explicit");
    model.component("comp5").selection("sel56").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp5").selection("sel56").geom(1);
    model.component("comp5").selection("sel56").set(119, 120, 122, 124, 125, 126, 127, 128);
    model.component("comp5").selection().create("sel57", "Explicit");
    model.component("comp5").selection("sel57").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp5").selection("sel57").geom(1);
    model.component("comp5").selection("sel57").set(94, 95, 96);
    model.component("comp5").selection().create("sel58", "Explicit");
    model.component("comp5").selection("sel58").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp5").selection("sel58").geom(1);
    model.component("comp5").selection("sel58").set(90, 91, 92);
    model.component("comp5").selection().create("sel59", "Explicit");
    model.component("comp5").selection("sel59").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp5").selection("sel59").geom(1);
    model.component("comp5").selection("sel59").set(58, 60, 64, 65, 74);
    model.component("comp5").selection().create("sel60", "Explicit");
    model.component("comp5").selection("sel60").label("\u5916\u4fa7");
    model.component("comp5").selection("sel60").geom(1);
    model.component("comp5").selection("sel60")
         .set(2, 20, 21, 22, 39, 40, 41, 49, 50, 56, 57, 59, 75, 86, 88, 97, 98, 99, 100, 134, 135, 136);
    model.component("comp5").selection().create("sel61", "Explicit");
    model.component("comp5").selection("sel61").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp5").selection("sel61").geom(1);
    model.component("comp5").selection("sel61").set(7, 103, 104, 105, 140);
    model.component("comp5").selection().create("sel62", "Explicit");
    model.component("comp5").selection("sel62").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp5").selection("sel62").geom(1);
    model.component("comp5").selection("sel62").set(137, 138, 139);
    model.component("comp5").selection().create("sel63", "Explicit");
    model.component("comp5").selection("sel63").label("\u5185\u4fa7");
    model.component("comp5").selection("sel63").geom(1);
    model.component("comp5").selection("sel63").set(7, 103, 104, 105, 137, 138, 139, 140);

    model.component("comp5").cpl().create("maxop34", "Maximum");
    model.component("comp5").cpl("maxop34").set("opname", "max_uc1");
    model.component("comp5").cpl("maxop34").selection().set(2);
    model.component("comp5").cpl().create("minop34", "Minimum");
    model.component("comp5").cpl("minop34").set("opname", "min_uc1");
    model.component("comp5").cpl("minop34").selection().set(2);
    model.component("comp5").cpl().create("aveop37", "Average");
    model.component("comp5").cpl("aveop37").set("axisym", true);
    model.component("comp5").cpl("aveop37").set("opname", "ave_uc1");
    model.component("comp5").cpl("aveop37").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop37").selection().named("sel53");
    model.component("comp5").cpl().create("intop21", "Integration");
    model.component("comp5").cpl("intop21").set("axisym", true);
    model.component("comp5").cpl("intop21").set("opname", "int_uc1");
    model.component("comp5").cpl("intop21").selection().set(2);
    model.component("comp5").cpl().create("maxop35", "Maximum");
    model.component("comp5").cpl("maxop35").set("opname", "max_uc2");
    model.component("comp5").cpl("maxop35").selection().set(5);
    model.component("comp5").cpl().create("minop35", "Minimum");
    model.component("comp5").cpl("minop35").set("opname", "min_uc2");
    model.component("comp5").cpl("minop35").selection().set(5);
    model.component("comp5").cpl().create("aveop38", "Average");
    model.component("comp5").cpl("aveop38").set("axisym", true);
    model.component("comp5").cpl("aveop38").set("opname", "ave_uc2");
    model.component("comp5").cpl("aveop38").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop38").selection().named("sel54");
    model.component("comp5").cpl().create("intop22", "Integration");
    model.component("comp5").cpl("intop22").set("axisym", true);
    model.component("comp5").cpl("intop22").set("opname", "int_uc2");
    model.component("comp5").cpl("intop22").selection().set(5);
    model.component("comp5").cpl().create("maxop36", "Maximum");
    model.component("comp5").cpl("maxop36").set("opname", "max_uc3");
    model.component("comp5").cpl("maxop36").selection().set(6);
    model.component("comp5").cpl().create("minop36", "Minimum");
    model.component("comp5").cpl("minop36").set("opname", "min_uc3");
    model.component("comp5").cpl("minop36").selection().set(6);
    model.component("comp5").cpl().create("aveop39", "Average");
    model.component("comp5").cpl("aveop39").set("axisym", true);
    model.component("comp5").cpl("aveop39").set("opname", "ave_uc3");
    model.component("comp5").cpl("aveop39").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop39").selection().named("sel55");
    model.component("comp5").cpl().create("intop23", "Integration");
    model.component("comp5").cpl("intop23").set("axisym", true);
    model.component("comp5").cpl("intop23").set("opname", "int_uc3");
    model.component("comp5").cpl("intop23").selection().set(6);
    model.component("comp5").cpl().create("aveop40", "Average");
    model.component("comp5").cpl("aveop40").set("axisym", true);
    model.component("comp5").cpl("aveop40").set("opname", "ave_uc4");
    model.component("comp5").cpl("aveop40").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop40").selection().named("sel56");
    model.component("comp5").cpl().create("intop24", "Integration");
    model.component("comp5").cpl("intop24").set("axisym", true);
    model.component("comp5").cpl("intop24").set("opname", "int_uc4");
    model.component("comp5").cpl("intop24").selection().set(17);
    model.component("comp5").cpl().create("aveop41", "Average");
    model.component("comp5").cpl("aveop41").set("axisym", true);
    model.component("comp5").cpl("aveop41").set("opname", "ave_uc5");
    model.component("comp5").cpl("aveop41").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop41").selection().named("sel57");
    model.component("comp5").cpl().create("aveop42", "Average");
    model.component("comp5").cpl("aveop42").set("axisym", true);
    model.component("comp5").cpl("aveop42").set("opname", "ave_uc6");
    model.component("comp5").cpl("aveop42").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop42").selection().named("sel58");
    model.component("comp5").cpl().create("aveop43", "Average");
    model.component("comp5").cpl("aveop43").set("axisym", true);
    model.component("comp5").cpl("aveop43").set("opname", "ave_svc");
    model.component("comp5").cpl("aveop43").selection().geom("geom5", 1);
    model.component("comp5").cpl("aveop43").selection().named("sel59");
    model.component("comp5").cpl().create("intop25", "Integration");
    model.component("comp5").cpl("intop25").set("axisym", true);
    model.component("comp5").cpl("intop25").set("opname", "int_svc");
    model.component("comp5").cpl("intop25").selection().set(8);
    model.component("comp5").cpl().create("intop26", "Integration");
    model.component("comp5").cpl("intop26").set("axisym", true);
    model.component("comp5").cpl("intop26").set("opname", "int_internal");
    model.component("comp5").cpl("intop26").selection().geom("geom5", 1);
    model.component("comp5").cpl("intop26").selection().named("sel63");

    model.component("comp5").variable().create("var5");

//    To import content from file, use:
//    model.component("comp5").variable("var5").loadFile("FILENAME");
    model.component("comp5").variable("var5").set("f_htot", "85[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hl1", "46[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hl2", "6[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hl3", "33[mm]", "\u6846\u67b6\u5de6\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hr1", "48[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hr2", "4[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hr3", "25[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u9ad8\u5ea6\uff09");
    model.component("comp5").variable("var5")
         .set("f_hr4", "4[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_hr5", "4[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e94\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5").set("f_wtot", "95[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wt1", "43[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wt2", "52[mm]", "\u6846\u67b6\u53f3\u4e0a\u89d2\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wb1", "7[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wb2", "15[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wb3", "21[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e09\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("f_wb4", "52[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u56db\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc1_h", "81[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc1_w", "13[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc2_h", "45[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc2_w", "14[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc3_h", "33[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc3_w", "58[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc4_h", "29[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc4_w", "4[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc5_h", "2[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc5_w", "2[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc6_h", "2[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc6_w", "2[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5").set("svc_h", "6[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5").set("svc_w", "6[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5").set("wvc_h", "15[mm]", "\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5").set("wvc_w", "17[mm]", "\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("pu1_h", "10[mm]", "\u7b2c\u4e00\u4e2a\u805a\u6c28\u916f (pu) \u5757\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("pu1_w", "8[mm]", "\u7b2c\u4e00\u4e2a\u805a\u6c28\u916f (pu) \u5757\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("pu2_h", "13[mm]", "\u7b2c\u4e8c\u4e2a\u805a\u6c28\u916f (pu) \u5757\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("pu2_w", "9[mm]", "\u7b2c\u4e8c\u4e2a\u805a\u6c28\u916f (pu) \u5757\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5").set("pws_h", "4[mm]", "\u5bc6\u5c01\u6761\u5757\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5").set("pws_w", "8[mm]", "\u5bc6\u5c01\u6761\u5757\u7684\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("p_h1", "11[mm]", "\u805a\u9170\u80fa\u5757\u7684\u7b2c\u4e00\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("p_h2", "33[mm]", "\u805a\u9170\u80fa\u5757\u7684\u7b2c\u4e8c\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("p_w1", "2[mm]", "\u805a\u9170\u80fa\u5757\u7684\u7b2c\u4e00\u5bbd\u5ea6");
    model.component("comp5").variable("var5")
         .set("p_w2", "3[mm]", "\u805a\u9170\u80fa\u5757\u7684\u7b2c\u4e8c\u5bbd\u5ea6");
    model.component("comp5").variable("var5").set("pp", "15[mm]", "\u9694\u70ed\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp5").variable("var5").set("pvl", "190[mm]", "\u9762\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp5").variable("var5").set("a_t", "2[mm]", "\u94dd\u5c42\u539a\u5ea6");
    model.component("comp5").variable("var5")
         .set("a_w1", "4[mm]", "\u94dd\u5236\u6954\u5b50\u7684\u7b2c\u4e00\u4e2a\u957f\u5ea6");
    model.component("comp5").variable("var5")
         .set("a_w2", "12[mm]", "\u94dd\u5236\u6954\u5b50\u7684\u7b2c\u4e8c\u4e2a\u957f\u5ea6");
    model.component("comp5").variable("var5").set("epdm_w", "3[mm]", "EPDM \u5757\u7684\u5bbd\u5ea6");
    model.component("comp5").variable("var5").set("sp1", "3[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp5").variable("var5").set("sp2", "4[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp5").variable("var5").set("sp3", "5[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp5").variable("var5").set("sp4", "7[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp5").variable("var5")
         .set("A_uc1", "int_uc1(1)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp5").variable("var5")
         .set("A_uc2", "int_uc2(1)", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp5").variable("var5")
         .set("A_uc3", "int_uc3(1)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp5").variable("var5")
         .set("A_uc4", "int_uc4(1)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp5").variable("var5")
         .set("A_svc", "int_svc(1)", "\u5fae\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp5").variable("var5")
         .set("uc1_h2", "h_resized(A_uc1,uc1_h,uc1_w)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc2_h2", "h_resized(A_uc2,uc2_h,uc2_w)", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc3_h2", "h_resized(A_uc3,uc3_h,uc3_w)", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("uc4_h2", "h_resized(A_uc4,uc4_h,uc4_w)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("svc_h2", "h_resized(A_svc,svc_h,svc_w)", "\u5fae\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp5").variable("var5")
         .set("R_uc1", "1/(ha_2(uc1_h2,max_uc1(T5)-min_uc1(T5))+hr(uc1_h,uc1_w,ave_uc1(T5),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_uc2", "1/(ha_2(uc2_h2,max_uc2(T5)-min_uc2(T5))+hr(uc2_h,uc2_w,ave_uc2(T5),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_uc3", "1/(ha_2(uc3_h2,max_uc3(T5)-min_uc3(T5))+hr(uc3_h,uc3_w,ave_uc3(T5),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_uc4", "1/(ha_1(uc4_h2)+hr(uc4_h,uc4_w,ave_uc4(T5),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_uc5", "1/(ha_1(uc5_h)+hr(uc5_h,uc5_w,ave_uc5(T5),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_uc6", "1/(ha_1(uc6_h)+hr(uc6_h,uc6_w,ave_uc6(T5),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("R_svc", "1/(ha_1(svc_h2)+hr(svc_h,svc_w,ave_svc(T5),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("Rp", "f_hr3/lambda_p", "\u9762\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp5").variable("var5")
         .set("Up_n", "1/(Rse+Rp+Rsi_n)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp5").variable("var5")
         .set("Up_p", "1/(Rse+Rp+Rsi_p)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp5").variable("var5")
         .set("Up", "(Up_p*f_hr4+Up_n*(pvl-f_hr4))/pvl", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387");

    model.component("comp5").material().create("mat53", "Common");
    model.component("comp5").material("mat53").label("\u94dd");
    model.component("comp5").material("mat53").selection().set(1, 3, 9, 10);
    model.component("comp5").material("mat53").propertyGroup("def")
         .set("thermalconductivity", new String[]{"160[W/(m*K)]"});
    model.component("comp5").material("mat53").propertyGroup("def").set("density", new String[]{"2800[kg/m^3]"});
    model.component("comp5").material("mat53").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp5").material().create("mat54", "Common");
    model.component("comp5").material("mat54").label("EPDM");
    model.component("comp5").material("mat54").selection().set(18);
    model.component("comp5").material("mat54").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp5").material("mat54").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp5").material("mat54").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp5").material().create("mat55", "Common");
    model.component("comp5").material("mat55").label("\u9694\u70ed\u677f");
    model.component("comp5").material("mat55").selection().set(19);
    model.component("comp5").material("mat55").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp5").material("mat55").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp5").material("mat55").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp5").material().create("mat56", "Common");
    model.component("comp5").material("mat56").label("\u5bc6\u5c01\u6761\uff08\u805a\u916f\u9a6c\u6d77\u6bdb\uff09");
    model.component("comp5").material("mat56").selection().set(11, 12, 13);
    model.component("comp5").material("mat56").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.14[W/(m*K)]"});
    model.component("comp5").material("mat56").propertyGroup("def").set("density", new String[]{"1380[kg/m^3]"});
    model.component("comp5").material("mat56").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp5").material().create("mat57", "Common");
    model.component("comp5").material("mat57").label("\u805a\u9170\u80fa");
    model.component("comp5").material("mat57").selection().set(7);
    model.component("comp5").material("mat57").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp5").material("mat57").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp5").material("mat57").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp5").material().create("mat58", "Common");
    model.component("comp5").material("mat58").label("\u786c\u805a\u6c28\u916f");
    model.component("comp5").material("mat58").selection().set(4, 16);
    model.component("comp5").material("mat58").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp5").material("mat58").propertyGroup("def").set("density", new String[]{"1200[kg/m^3]"});
    model.component("comp5").material("mat58").propertyGroup("def")
         .set("heatcapacity", new String[]{"1800[J/(kg*K)]"});
    model.component("comp5").material().create("mat59", "Common");
    model.component("comp5").material("mat59").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp5").material("mat59").selection().set(2);
    model.component("comp5").material("mat59").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h2/R_uc1"});
    model.component("comp5").material("mat59").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat59").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat60", "Common");
    model.component("comp5").material("mat60").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp5").material("mat60").selection().set(5);
    model.component("comp5").material("mat60").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h2/R_uc2"});
    model.component("comp5").material("mat60").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat60").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat61", "Common");
    model.component("comp5").material("mat61").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp5").material("mat61").selection().set(6);
    model.component("comp5").material("mat61").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h2/R_uc3"});
    model.component("comp5").material("mat61").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat61").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat62", "Common");
    model.component("comp5").material("mat62").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp5").material("mat62").selection().set(17);
    model.component("comp5").material("mat62").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h2/R_uc4"});
    model.component("comp5").material("mat62").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat62").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat63", "Common");
    model.component("comp5").material("mat63").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp5").material("mat63").selection().set(15);
    model.component("comp5").material("mat63").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h/R_uc5"});
    model.component("comp5").material("mat63").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat63").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat64", "Common");
    model.component("comp5").material("mat64").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp5").material("mat64").selection().set(14);
    model.component("comp5").material("mat64").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc6_h/R_uc6"});
    model.component("comp5").material("mat64").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat64").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp5").material().create("mat65", "Common");
    model.component("comp5").material("mat65").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp5").material("mat65").selection().set(8);
    model.component("comp5").material("mat65").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_h2/R_svc"});
    model.component("comp5").material("mat65").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp5").material("mat65").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht3", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht4", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht5", true);
    model.study("std5").showAutoSequences("all");
    model.study("std5").feature("stat").set("usestol", true);
    model.study("std5").feature("stat").set("stol", "1e-6");

    model.component().create("comp6", true);

    model.component("comp6").geom().create("geom6", 2);

    model.component("comp6").mesh().create("mesh6");

    model.component("comp6").label("\u7b2c\u516d\u79cd\u7a97\u6237");

    model.component("comp6").physics().create("ht6", "HeatTransferInSolidsAndFluids", "geom6");

    model.study("std1").feature("stat").setSolveFor("/physics/ht6", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht6", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht6", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht6", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht6", false);

    model.component("comp6").geom("geom6").run();
    model.component("comp6").geom("geom6").create("imp1", "Import");
    model.component("comp6").geom("geom6").feature("imp1")
         .set("filename", "windows_thermal_performances_sixth_window.mphbin");
    model.component("comp6").geom("geom6").feature("imp1").importData();
    model.component("comp6").geom("geom6").run("fin");
    model.component("comp6").geom("geom6").create("ige1", "IgnoreEdges");
    model.component("comp6").geom("geom6").feature("ige1").set("ignorevtx", false);
    model.component("comp6").geom("geom6").feature("ige1").selection("input").set("fin", 78);
    model.component("comp6").geom("geom6").run("ige1");
    model.component("comp6").geom("geom6").create("igv1", "IgnoreVertices");
    model.component("comp6").geom("geom6").feature("igv1").selection("input")
         .set("ige1", 11, 13, 20, 21, 25, 29, 45, 48, 52, 54, 57, 64, 67);
    model.component("comp6").geom("geom6").run("igv1");

    model.component("comp6").selection().create("sel64", "Explicit");
    model.component("comp6").selection("sel64").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp6").selection("sel64").geom(1);
    model.component("comp6").selection("sel64").set(13, 14, 15, 23, 24, 41);
    model.component("comp6").selection().create("sel65", "Explicit");
    model.component("comp6").selection("sel65").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp6").selection("sel65").geom(1);
    model.component("comp6").selection("sel65").set(26, 27, 28, 33, 35, 39);
    model.component("comp6").selection().create("sel66", "Explicit");
    model.component("comp6").selection("sel66").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp6").selection("sel66").geom(1);
    model.component("comp6").selection("sel66").set(51, 52, 53, 62);
    model.component("comp6").selection().create("sel67", "Explicit");
    model.component("comp6").selection("sel67").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp6").selection("sel67").geom(1);
    model.component("comp6").selection("sel67").set(10, 11, 12, 31, 32, 38);
    model.component("comp6").selection().create("sel68", "Explicit");
    model.component("comp6").selection("sel68").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp6").selection("sel68").geom(1);
    model.component("comp6").selection("sel68").set(44, 45, 47, 54, 56, 58);
    model.component("comp6").selection().create("sel69", "Explicit");
    model.component("comp6").selection("sel69").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp6").selection("sel69").geom(1);
    model.component("comp6").selection("sel69").set(7, 8, 9, 21, 25);
    model.component("comp6").selection().create("sel70", "Explicit");
    model.component("comp6").selection("sel70").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp6").selection("sel70").geom(1);
    model.component("comp6").selection("sel70").set(18, 19, 20, 22, 29, 30, 36, 37, 42);
    model.component("comp6").selection().create("sel71", "Explicit");
    model.component("comp6").selection("sel71").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp6").selection("sel71").geom(1);

    return model;
  }

  public static Model run6(Model model) {
    model.component("comp6").selection("sel71").set(4, 6, 16);
    model.component("comp6").selection().create("sel72", "Explicit");
    model.component("comp6").selection("sel72").label("\u5916\u4fa7");
    model.component("comp6").selection("sel72").geom(1);
    model.component("comp6").selection("sel72").set(2, 5, 17, 43, 63, 64, 65);
    model.component("comp6").selection().create("sel73", "Explicit");
    model.component("comp6").selection("sel73").label("\u5185\u4fa7\uff08\u5e73\u76f4\u533a\u57df\uff09");
    model.component("comp6").selection("sel73").geom(1);
    model.component("comp6").selection("sel73").set(3, 61, 69);
    model.component("comp6").selection().create("sel74", "Explicit");
    model.component("comp6").selection("sel74").label("\u5185\u4fa7\uff08\u62d0\u89d2\u533a\u57df\uff09");
    model.component("comp6").selection("sel74").geom(1);
    model.component("comp6").selection("sel74").set(49, 50, 66, 67, 68);
    model.component("comp6").selection().create("sel75", "Explicit");
    model.component("comp6").selection("sel75").label("\u5185\u4fa7");
    model.component("comp6").selection("sel75").geom(1);
    model.component("comp6").selection("sel75").set(3, 49, 50, 61, 66, 67, 68, 69);

    model.component("comp6").cpl().create("maxop37", "Maximum");
    model.component("comp6").cpl("maxop37").set("opname", "max_uc1");
    model.component("comp6").cpl("maxop37").selection().set(5);
    model.component("comp6").cpl().create("minop37", "Minimum");
    model.component("comp6").cpl("minop37").set("opname", "min_uc1");
    model.component("comp6").cpl("minop37").selection().set(5);
    model.component("comp6").cpl().create("aveop44", "Average");
    model.component("comp6").cpl("aveop44").set("axisym", true);
    model.component("comp6").cpl("aveop44").set("opname", "ave_uc1");
    model.component("comp6").cpl("aveop44").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop44").selection().named("sel64");
    model.component("comp6").cpl().create("intop27", "Integration");
    model.component("comp6").cpl("intop27").set("axisym", true);
    model.component("comp6").cpl("intop27").set("opname", "int_uc1");
    model.component("comp6").cpl("intop27").selection().set(5);
    model.component("comp6").cpl().create("maxop38", "Maximum");
    model.component("comp6").cpl("maxop38").set("opname", "max_uc2");
    model.component("comp6").cpl("maxop38").selection().set(7);
    model.component("comp6").cpl().create("minop38", "Minimum");
    model.component("comp6").cpl("minop38").set("opname", "min_uc2");
    model.component("comp6").cpl("minop38").selection().set(7);
    model.component("comp6").cpl().create("aveop45", "Average");
    model.component("comp6").cpl("aveop45").set("axisym", true);
    model.component("comp6").cpl("aveop45").set("opname", "ave_uc2");
    model.component("comp6").cpl("aveop45").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop45").selection().named("sel65");
    model.component("comp6").cpl().create("intop28", "Integration");
    model.component("comp6").cpl("intop28").set("axisym", true);
    model.component("comp6").cpl("intop28").set("opname", "int_uc2");
    model.component("comp6").cpl("intop28").selection().set(7);
    model.component("comp6").cpl().create("maxop39", "Maximum");
    model.component("comp6").cpl("maxop39").set("opname", "max_uc3");
    model.component("comp6").cpl("maxop39").selection().set(10);
    model.component("comp6").cpl().create("minop39", "Minimum");
    model.component("comp6").cpl("minop39").set("opname", "min_uc3");
    model.component("comp6").cpl("minop39").selection().set(10);
    model.component("comp6").cpl().create("aveop46", "Average");
    model.component("comp6").cpl("aveop46").set("axisym", true);
    model.component("comp6").cpl("aveop46").set("opname", "ave_uc3");
    model.component("comp6").cpl("aveop46").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop46").selection().named("sel66");
    model.component("comp6").cpl().create("maxop40", "Maximum");
    model.component("comp6").cpl("maxop40").set("opname", "max_uc4");
    model.component("comp6").cpl("maxop40").selection().set(4);
    model.component("comp6").cpl().create("minop40", "Minimum");
    model.component("comp6").cpl("minop40").set("opname", "min_uc4");
    model.component("comp6").cpl("minop40").selection().set(4);
    model.component("comp6").cpl().create("aveop47", "Average");
    model.component("comp6").cpl("aveop47").set("axisym", true);
    model.component("comp6").cpl("aveop47").set("opname", "ave_uc4");
    model.component("comp6").cpl("aveop47").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop47").selection().named("sel67");
    model.component("comp6").cpl().create("intop29", "Integration");
    model.component("comp6").cpl("intop29").set("axisym", true);
    model.component("comp6").cpl("intop29").set("opname", "int_uc4");
    model.component("comp6").cpl("intop29").selection().set(4);
    model.component("comp6").cpl().create("maxop41", "Maximum");
    model.component("comp6").cpl("maxop41").set("opname", "max_uc5");
    model.component("comp6").cpl("maxop41").selection().set(9);
    model.component("comp6").cpl().create("minop41", "Minimum");
    model.component("comp6").cpl("minop41").set("opname", "min_uc5");
    model.component("comp6").cpl("minop41").selection().set(9);
    model.component("comp6").cpl().create("aveop48", "Average");
    model.component("comp6").cpl("aveop48").set("axisym", true);
    model.component("comp6").cpl("aveop48").set("opname", "ave_uc5");
    model.component("comp6").cpl("aveop48").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop48").selection().named("sel68");
    model.component("comp6").cpl().create("maxop42", "Maximum");
    model.component("comp6").cpl("maxop42").set("opname", "max_uc6");
    model.component("comp6").cpl("maxop42").selection().set(3);
    model.component("comp6").cpl().create("minop42", "Minimum");
    model.component("comp6").cpl("minop42").set("opname", "min_uc6");
    model.component("comp6").cpl("minop42").selection().set(3);
    model.component("comp6").cpl().create("aveop49", "Average");
    model.component("comp6").cpl("aveop49").set("axisym", true);
    model.component("comp6").cpl("aveop49").set("opname", "ave_uc6");
    model.component("comp6").cpl("aveop49").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop49").selection().named("sel69");
    model.component("comp6").cpl().create("intop30", "Integration");
    model.component("comp6").cpl("intop30").set("axisym", true);
    model.component("comp6").cpl("intop30").set("opname", "int_uc6");
    model.component("comp6").cpl("intop30").selection().set(3);
    model.component("comp6").cpl().create("maxop43", "Maximum");
    model.component("comp6").cpl("maxop43").set("opname", "max_uc7");
    model.component("comp6").cpl("maxop43").selection().set(6);
    model.component("comp6").cpl().create("minop43", "Minimum");
    model.component("comp6").cpl("minop43").set("opname", "min_uc7");
    model.component("comp6").cpl("minop43").selection().set(6);
    model.component("comp6").cpl().create("aveop50", "Average");
    model.component("comp6").cpl("aveop50").set("axisym", true);
    model.component("comp6").cpl("aveop50").set("opname", "ave_uc7");
    model.component("comp6").cpl("aveop50").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop50").selection().named("sel70");
    model.component("comp6").cpl().create("intop31", "Integration");
    model.component("comp6").cpl("intop31").set("axisym", true);
    model.component("comp6").cpl("intop31").set("opname", "int_uc7");
    model.component("comp6").cpl("intop31").selection().set(6);
    model.component("comp6").cpl().create("aveop51", "Average");
    model.component("comp6").cpl("aveop51").set("axisym", true);
    model.component("comp6").cpl("aveop51").set("opname", "ave_svc");
    model.component("comp6").cpl("aveop51").selection().geom("geom6", 1);
    model.component("comp6").cpl("aveop51").selection().named("sel71");
    model.component("comp6").cpl().create("intop32", "Integration");
    model.component("comp6").cpl("intop32").set("axisym", true);
    model.component("comp6").cpl("intop32").set("opname", "int_internal");
    model.component("comp6").cpl("intop32").selection().geom("geom6", 1);
    model.component("comp6").cpl("intop32").selection().named("sel75");

    model.component("comp6").variable().create("var6");

//    To import content from file, use:
//    model.component("comp6").variable("var6").loadFile("FILENAME");
    model.component("comp6").variable("var6").set("f_htot", "99[mm]", "\u6846\u67b6\u603b\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_hr1", "29[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e00\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_hr2", "6[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e8c\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_hr3", "24[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e09\u90e8\u5206\u7684\u9ad8\u5ea6\uff08\u9694\u70ed\u677f\u539a\u5ea6\uff09");
    model.component("comp6").variable("var6")
         .set("f_hr4", "28[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u56db\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_hr5", "12[mm]", "\u6846\u67b6\u53f3\u4fa7\u7b2c\u4e94\u90e8\u5206\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6").set("f_wtot", "48[mm]", "\u6846\u67b6\u603b\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_wb1", "31[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_wb2", "17[mm]", "\u6846\u67b6\u5e95\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_wt1", "31[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e00\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("f_wt2", "17[mm]", "\u6846\u67b6\u9876\u90e8\u7b2c\u4e8c\u90e8\u5206\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc1_h", "31[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc1_w", "25[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc2_h", "9[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc2_w", "10[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc3_h", "19[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc3_w", "12[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc4_h", "19[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc4_w", "25[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc5_h", "30[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc5_w", "5[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc6_h", "35[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc6_w", "15[mm]", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc7_h", "37[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc7_w", "36[mm]", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6").set("svc_h", "8[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp6").variable("var6").set("svc_w", "3[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp6").variable("var6").set("pvc_t", "3[mm]", "PVC \u539a\u5ea6");
    model.component("comp6").variable("var6").set("epdm_t", "3[mm]", "EPDM \u539a\u5ea6");
    model.component("comp6").variable("var6")
         .set("m_t1", "2[mm]", "\u672a\u77e5\u6750\u6599\u7684\u7b2c\u4e00\u539a\u5ea6");
    model.component("comp6").variable("var6")
         .set("m_t2", "3[mm]", "\u672a\u77e5\u6750\u6599\u7684\u7b2c\u4e8c\u539a\u5ea6");
    model.component("comp6").variable("var6")
         .set("m_t3", "6[mm]", "\u672a\u77e5\u6750\u6599\u7684\u7b2c\u4e09\u539a\u5ea6");
    model.component("comp6").variable("var6").set("pvl", "190[mm]", "\u9762\u677f\u53ef\u89c1\u957f\u5ea6");
    model.component("comp6").variable("var6").set("pp", "12[mm]", "\u9762\u677f\u8d2f\u7a7f\u6df1\u5ea6");
    model.component("comp6").variable("var6").set("sp1", "2[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp6").variable("var6").set("sp2", "5[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp6").variable("var6").set("sp3", "6[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp6").variable("var6").set("sp4", "7[mm]", "\u6709\u7528\u7a7a\u95f4");
    model.component("comp6").variable("var6")
         .set("A_uc1", "int_uc1(1)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp6").variable("var6")
         .set("A_uc2", "int_uc2(1)", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp6").variable("var6")
         .set("A_uc4", "int_uc4(1)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp6").variable("var6")
         .set("A_uc6", "int_uc6(1)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp6").variable("var6")
         .set("A_uc7", "int_uc7(1)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp6").variable("var6")
         .set("uc1_h2", "h_resized(A_uc1,uc1_h,uc1_w)", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc2_h2", "h_resized(A_uc2,uc2_h,uc2_w)", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc4_h2", "h_resized(A_uc4,uc4_h,uc4_w)", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc6_h2", "h_resized(A_uc6,uc6_h,uc6_w)", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("uc7_h2", "h_resized(A_uc7,uc7_h,uc7_w)", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp6").variable("var6")
         .set("R_uc1", "1/(ha_2(uc1_h2,max_uc1(T6)-min_uc1(T6))+hr(uc1_h,uc1_w,ave_uc1(T6),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc2", "1/(ha_2(uc2_h2,max_uc2(T6)-min_uc2(T6))+hr(uc2_h,uc2_w,ave_uc2(T6),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc3", "1/(ha_2(uc3_h,max_uc3(T6)-min_uc3(T6))+hr(uc3_h,uc3_w,ave_uc3(T6),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc4", "1/(ha_2(uc4_h2,max_uc4(T6)-min_uc4(T6))+hr(uc4_h,uc4_w,ave_uc4(T6),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc5", "1/(ha_2(uc5_h,max_uc5(T6)-min_uc5(T6))+hr(uc5_h,uc5_w,ave_uc5(T6),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc6", "1/(ha_2(uc6_h2,max_uc6(T6)-min_uc6(T6))+hr(uc6_h,uc6_w,ave_uc6(T6),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_uc7", "1/(ha_2(uc7_h2,max_uc7(T6)-min_uc7(T6))+hr(uc7_h,uc7_w,ave_uc7(T6),epsilon,epsilon))", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("R_svc", "1/(ha_1(svc_h)+hr(svc_h,svc_w,ave_svc(T6),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("Rp", "f_hr3/lambda_p", "\u9694\u70ed\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp6").variable("var6")
         .set("Up_n", "1/(Rsi_n+Rp+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp6").variable("var6")
         .set("Up_p", "1/(Rsi_p+Rp+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp6").variable("var6")
         .set("Up", "(Up_p*f_hr4+Up_n*(pvl-f_hr4))/pvl", "\u9762\u677f\u7684\u70ed\u900f\u5c04\u7387");

    model.component("comp6").material().create("mat66", "Common");
    model.component("comp6").material("mat66").label("PVC");
    model.component("comp6").material("mat66").selection().set(1);
    model.component("comp6").material("mat66").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.17[W/(m*K)]"});
    model.component("comp6").material("mat66").propertyGroup("def").set("density", new String[]{"1390[kg/m^3]"});
    model.component("comp6").material("mat66").propertyGroup("def")
         .set("heatcapacity", new String[]{"1900[J/(kg*K)]"});
    model.component("comp6").material().create("mat67", "Common");
    model.component("comp6").material("mat67").label("EPDM");
    model.component("comp6").material("mat67").selection().set(11, 13);
    model.component("comp6").material("mat67").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp6").material("mat67").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp6").material("mat67").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp6").material().create("mat68", "Common");
    model.component("comp6").material("mat68").label("\u9694\u70ed\u677f");
    model.component("comp6").material("mat68").selection().set(12);
    model.component("comp6").material("mat68").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp6").material("mat68").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp6").material("mat68").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp6").material().create("mat69", "Common");
    model.component("comp6").material("mat69").label("\u805a\u9170\u80fa");
    model.component("comp6").material("mat69").selection().set(8);
    model.component("comp6").material("mat69").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp6").material("mat69").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp6").material("mat69").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp6").material().create("mat70", "Common");
    model.component("comp6").material("mat70").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp6").material("mat70").selection().set(5);
    model.component("comp6").material("mat70").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h2/R_uc1"});
    model.component("comp6").material("mat70").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat70").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat71", "Common");
    model.component("comp6").material("mat71").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp6").material("mat71").selection().set(7);
    model.component("comp6").material("mat71").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h2/R_uc2"});
    model.component("comp6").material("mat71").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat71").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat72", "Common");
    model.component("comp6").material("mat72").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp6").material("mat72").selection().set(10);
    model.component("comp6").material("mat72").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h/R_uc3"});
    model.component("comp6").material("mat72").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat72").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat73", "Common");
    model.component("comp6").material("mat73").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp6").material("mat73").selection().set(4);
    model.component("comp6").material("mat73").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h2/R_uc4"});
    model.component("comp6").material("mat73").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat73").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat74", "Common");
    model.component("comp6").material("mat74").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp6").material("mat74").selection().set(9);
    model.component("comp6").material("mat74").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h/R_uc5"});
    model.component("comp6").material("mat74").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat74").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat75", "Common");
    model.component("comp6").material("mat75").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp6").material("mat75").selection().set(3);
    model.component("comp6").material("mat75").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc6_h2/R_uc6"});
    model.component("comp6").material("mat75").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat75").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat76", "Common");
    model.component("comp6").material("mat76").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp6").material("mat76").selection().set(6);
    model.component("comp6").material("mat76").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc7_h2/R_uc7"});
    model.component("comp6").material("mat76").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat76").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp6").material().create("mat77", "Common");
    model.component("comp6").material("mat77").label("\u8f7b\u5fae\u901a\u98ce\u7684\u8154");
    model.component("comp6").material("mat77").selection().set(2);
    model.component("comp6").material("mat77").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_h/R_svc"});
    model.component("comp6").material("mat77").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp6").material("mat77").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht3", false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht4", false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht5", false);
    model.study("std6").feature("stat").setSolveFor("/physics/ht6", true);
    model.study("std6").showAutoSequences("all");
    model.study("std6").feature("stat").set("usestol", true);
    model.study("std6").feature("stat").set("stol", "1e-6");

    model.result().dataset().remove("dset1");
    model.result().dataset().remove("dset2");
    model.result().dataset().remove("dset3");
    model.result().dataset().remove("dset4");
    model.result().dataset().remove("dset5");
    model.result().dataset().remove("dset6");
    model.result().dataset().remove("dset7");
    model.result().dataset().remove("dset8");
    model.result().dataset().remove("dset9");
    model.result().dataset().remove("dset10");
    model.result().dataset().remove("dset11");
    model.result().dataset().remove("dset12");
    model.result().dataset().remove("dset13");
    model.result().dataset().remove("dset14");
    model.result().dataset().remove("dset15");
    model.result().dataset().remove("dset16");
    model.result().dataset().remove("dset17");
    model.result().dataset().remove("dset18");
    model.result().dataset().remove("dset19");
    model.result().dataset().remove("dset20");

    model.title("\u53c2\u6570\u5316\u7a97\u6237 - \u9884\u8bbe\u6a21\u578b");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u7a97\u6237\u70ed\u6027\u80fd\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("windows_thermal_performances_preset.mph");

    model.component("comp1").variable("var1").set("L2D", "int_internal(ht.ntflux/(Te-Ti))");
    model.component("comp1").variable("var1").descr("L2D", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp1").physics("ht").feature("fluid1").selection()
         .set(2, 5, 6, 10, 12, 14, 15, 17, 18, 20, 21);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel12");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "1/Rse");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Te");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().named("sel13");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "Ti");
    model.component("comp1").physics("ht").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf3").selection().named("sel14");
    model.component("comp1").physics("ht").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp1").physics("ht").feature("hf3").set("Text", "Ti");

    model.study("std1").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 1");
    model.result().numerical("gev1").setIndex("expr", "L2D", 0);
    model.result().numerical("gev1").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev1").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev1").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp2").variable("var2")
         .set("L2D", "int_internal(ht2.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp2").physics("ht2").feature("fluid1").selection().set(2, 6, 8, 9, 10, 14);
    model.component("comp2").physics("ht2").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht2").feature("hf1").selection().named("sel24");
    model.component("comp2").physics("ht2").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf1").set("h", "1/Rse");
    model.component("comp2").physics("ht2").feature("hf1").set("Text", "Te");
    model.component("comp2").physics("ht2").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht2").feature("hf2").selection().named("sel22");
    model.component("comp2").physics("ht2").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp2").physics("ht2").feature("hf2").set("Text", "Ti");
    model.component("comp2").physics("ht2").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht2").feature("hf3").selection().named("sel23");
    model.component("comp2").physics("ht2").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp2").physics("ht2").feature("hf3").set("Text", "Ti");

    model.study("std2").createAutoSequences("all");

    model.result().dataset().create("dset7", "Solution");
    model.result().dataset("dset7").set("solution", "sol1");
    model.result().dataset("dset7").set("comp", "none");
    model.result().dataset("dset7").set("geom", "geom1");
    model.result().dataset().create("dset8", "Solution");
    model.result().dataset("dset8").set("solution", "sol1");
    model.result().dataset("dset8").set("geom", "geom2");
    model.result().dataset().create("dset9", "Solution");
    model.result().dataset("dset9").set("solution", "sol1");

    return model;
  }

  public static Model run7(Model model) {
    model.result().dataset("dset9").set("geom", "geom3");
    model.result().dataset().create("dset10", "Solution");
    model.result().dataset("dset10").set("solution", "sol1");
    model.result().dataset("dset10").set("geom", "geom4");
    model.result().dataset().create("dset11", "Solution");
    model.result().dataset("dset11").set("solution", "sol1");
    model.result().dataset("dset11").set("geom", "geom5");
    model.result().dataset().create("dset12", "Solution");
    model.result().dataset("dset12").set("solution", "sol1");
    model.result().dataset("dset12").set("geom", "geom6");
    model.result().dataset("dset12").set("comp", "comp6");
    model.result().dataset().move("dset7", 6);
    model.result().dataset("dset7").tag("dset7");
    model.result().dataset().move("dset8", 7);
    model.result().dataset("dset8").tag("dset8");
    model.result().dataset().move("dset9", 8);
    model.result().dataset("dset9").tag("dset9");
    model.result().dataset().move("dset10", 9);
    model.result().dataset("dset10").tag("dset10");
    model.result().dataset().move("dset11", 10);
    model.result().dataset("dset11").tag("dset11");
    model.result().dataset().move("dset12", 11);
    model.result().dataset("dset12").tag("dset12");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht2)");
    model.result("pg2").set("data", "dset8");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 2");
    model.result().numerical("gev2").set("data", "dset8");
    model.result().numerical("gev2").setIndex("expr", "L2D", 0);
    model.result().numerical("gev2").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev2").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev2").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp3").variable("var3")
         .set("L2D", "int_internal(ht3.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp3").physics("ht3").feature("fluid1").selection()
         .set(2, 3, 4, 5, 9, 10, 12, 13, 15, 16, 17, 18);
    model.component("comp3").physics("ht3").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp3").physics("ht3").feature("hf1").selection().named("sel38");
    model.component("comp3").physics("ht3").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp3").physics("ht3").feature("hf1").set("h", "1/Rse");
    model.component("comp3").physics("ht3").feature("hf1").set("Text", "Te");
    model.component("comp3").physics("ht3").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp3").physics("ht3").feature("hf2").selection().named("sel40");
    model.component("comp3").physics("ht3").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp3").physics("ht3").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp3").physics("ht3").feature("hf2").set("Text", "Ti");
    model.component("comp3").physics("ht3").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp3").physics("ht3").feature("hf3").selection().named("sel39");
    model.component("comp3").physics("ht3").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp3").physics("ht3").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp3").physics("ht3").feature("hf3").set("Text", "Ti");

    model.component("comp3").mesh("mesh3").automatic(false);
    model.component("comp3").mesh("mesh3").feature("size").set("hnarrow", 2);

    model.study("std3").createAutoSequences("all");

    model.result().dataset().create("dset13", "Solution");
    model.result().dataset("dset13").set("solution", "sol2");
    model.result().dataset("dset13").set("comp", "none");
    model.result().dataset("dset13").set("geom", "geom1");
    model.result().dataset().create("dset14", "Solution");
    model.result().dataset("dset14").set("solution", "sol2");
    model.result().dataset("dset14").set("geom", "geom2");
    model.result().dataset().create("dset15", "Solution");
    model.result().dataset("dset15").set("solution", "sol2");
    model.result().dataset("dset15").set("geom", "geom3");
    model.result().dataset().create("dset16", "Solution");
    model.result().dataset("dset16").set("solution", "sol2");
    model.result().dataset("dset16").set("geom", "geom4");
    model.result().dataset().create("dset17", "Solution");
    model.result().dataset("dset17").set("solution", "sol2");
    model.result().dataset("dset17").set("geom", "geom5");
    model.result().dataset().create("dset18", "Solution");
    model.result().dataset("dset18").set("solution", "sol2");
    model.result().dataset("dset18").set("geom", "geom6");
    model.result().dataset("dset18").set("comp", "comp6");
    model.result().dataset().move("dset13", 12);
    model.result().dataset("dset13").tag("dset13");
    model.result().dataset().move("dset14", 13);
    model.result().dataset("dset14").tag("dset14");
    model.result().dataset().move("dset15", 14);
    model.result().dataset("dset15").tag("dset15");
    model.result().dataset().move("dset16", 15);
    model.result().dataset("dset16").tag("dset16");
    model.result().dataset().move("dset17", 16);
    model.result().dataset("dset17").tag("dset17");
    model.result().dataset().move("dset18", 17);
    model.result().dataset("dset18").tag("dset18");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht3)");
    model.result("pg3").set("data", "dset15");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 3");
    model.result().numerical("gev3").set("data", "dset15");
    model.result().numerical("gev3").setIndex("expr", "L2D", 0);
    model.result().numerical("gev3").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev3").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev3").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp4").variable("var4")
         .set("L2D", "int_internal(ht4.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp4").physics("ht4").prop("ShapeProperty").set("order_temperature", 2);
    model.component("comp4").physics("ht4").feature("fluid1").selection().set(2, 4, 7, 10, 12, 13, 15);
    model.component("comp4").physics("ht4").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp4").physics("ht4").feature("hf1").selection().named("sel49");
    model.component("comp4").physics("ht4").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp4").physics("ht4").feature("hf1").set("h", "1/Rse");
    model.component("comp4").physics("ht4").feature("hf1").set("Text", "Te");
    model.component("comp4").physics("ht4").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp4").physics("ht4").feature("hf2").selection().named("sel51");
    model.component("comp4").physics("ht4").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp4").physics("ht4").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp4").physics("ht4").feature("hf2").set("Text", "Ti");
    model.component("comp4").physics("ht4").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp4").physics("ht4").feature("hf3").selection().named("sel50");
    model.component("comp4").physics("ht4").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp4").physics("ht4").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp4").physics("ht4").feature("hf3").set("Text", "Ti");

    model.component("comp4").mesh("mesh4").automatic(false);
    model.component("comp4").mesh("mesh4").feature("size").set("hnarrow", 2);

    model.study("std4").createAutoSequences("all");

    model.result().dataset().create("dset19", "Solution");
    model.result().dataset("dset19").set("solution", "sol3");
    model.result().dataset("dset19").set("comp", "none");
    model.result().dataset("dset19").set("geom", "geom1");
    model.result().dataset().create("dset20", "Solution");
    model.result().dataset("dset20").set("solution", "sol3");
    model.result().dataset("dset20").set("geom", "geom2");
    model.result().dataset().create("dset21", "Solution");
    model.result().dataset("dset21").set("solution", "sol3");
    model.result().dataset("dset21").set("geom", "geom3");
    model.result().dataset().create("dset22", "Solution");
    model.result().dataset("dset22").set("solution", "sol3");
    model.result().dataset("dset22").set("geom", "geom4");
    model.result().dataset().create("dset23", "Solution");
    model.result().dataset("dset23").set("solution", "sol3");
    model.result().dataset("dset23").set("geom", "geom5");
    model.result().dataset().create("dset24", "Solution");
    model.result().dataset("dset24").set("solution", "sol3");
    model.result().dataset("dset24").set("geom", "geom6");
    model.result().dataset("dset24").set("comp", "comp6");
    model.result().dataset().move("dset19", 18);
    model.result().dataset("dset19").tag("dset19");
    model.result().dataset().move("dset20", 19);
    model.result().dataset("dset20").tag("dset20");
    model.result().dataset().move("dset21", 20);
    model.result().dataset("dset21").tag("dset21");
    model.result().dataset().move("dset22", 21);
    model.result().dataset("dset22").tag("dset22");
    model.result().dataset().move("dset23", 22);
    model.result().dataset("dset23").tag("dset23");
    model.result().dataset().move("dset24", 23);
    model.result().dataset("dset24").tag("dset24");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht4)");
    model.result("pg4").set("data", "dset22");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 4");
    model.result().numerical("gev4").set("data", "dset22");
    model.result().numerical("gev4").setIndex("expr", "L2D", 0);
    model.result().numerical("gev4").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev4").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev4").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 4");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp5").variable("var5")
         .set("L2D", "int_internal(ht5.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp5").physics("ht5").feature("fluid1").selection().set(2, 5, 6, 8, 14, 15, 17);
    model.component("comp5").physics("ht5").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp5").physics("ht5").feature("hf1").selection().named("sel60");
    model.component("comp5").physics("ht5").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp5").physics("ht5").feature("hf1").set("h", "1/Rse");
    model.component("comp5").physics("ht5").feature("hf1").set("Text", "Te");
    model.component("comp5").physics("ht5").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp5").physics("ht5").feature("hf2").selection().named("sel61");
    model.component("comp5").physics("ht5").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp5").physics("ht5").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp5").physics("ht5").feature("hf2").set("Text", "Ti");
    model.component("comp5").physics("ht5").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp5").physics("ht5").feature("hf3").selection().named("sel62");
    model.component("comp5").physics("ht5").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp5").physics("ht5").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp5").physics("ht5").feature("hf3").set("Text", "Ti");

    model.study("std5").createAutoSequences("all");

    model.result().dataset().create("dset25", "Solution");
    model.result().dataset("dset25").set("solution", "sol4");
    model.result().dataset("dset25").set("comp", "none");
    model.result().dataset("dset25").set("geom", "geom1");
    model.result().dataset().create("dset26", "Solution");
    model.result().dataset("dset26").set("solution", "sol4");
    model.result().dataset("dset26").set("geom", "geom2");
    model.result().dataset().create("dset27", "Solution");
    model.result().dataset("dset27").set("solution", "sol4");
    model.result().dataset("dset27").set("geom", "geom3");
    model.result().dataset().create("dset28", "Solution");
    model.result().dataset("dset28").set("solution", "sol4");
    model.result().dataset("dset28").set("geom", "geom4");
    model.result().dataset().create("dset29", "Solution");
    model.result().dataset("dset29").set("solution", "sol4");
    model.result().dataset("dset29").set("geom", "geom5");
    model.result().dataset().create("dset30", "Solution");
    model.result().dataset("dset30").set("solution", "sol4");
    model.result().dataset("dset30").set("geom", "geom6");
    model.result().dataset("dset30").set("comp", "comp6");
    model.result().dataset().move("dset25", 24);
    model.result().dataset("dset25").tag("dset25");
    model.result().dataset().move("dset26", 25);
    model.result().dataset("dset26").tag("dset26");
    model.result().dataset().move("dset27", 26);
    model.result().dataset("dset27").tag("dset27");
    model.result().dataset().move("dset28", 27);
    model.result().dataset("dset28").tag("dset28");
    model.result().dataset().move("dset29", 28);
    model.result().dataset("dset29").tag("dset29");
    model.result().dataset().move("dset30", 29);
    model.result().dataset("dset30").tag("dset30");

    model.sol("sol4").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (ht5)");
    model.result("pg5").set("data", "dset29");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 5");
    model.result().numerical("gev5").set("data", "dset29");
    model.result().numerical("gev5").setIndex("expr", "L2D", 0);
    model.result().numerical("gev5").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev5").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev5").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 5");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp6").variable("var6")
         .set("L2D", "int_internal(ht6.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp6").physics("ht6").feature("fluid1").selection().set(2, 3, 4, 5, 6, 7, 9, 10);
    model.component("comp6").physics("ht6").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp6").physics("ht6").feature("hf1").selection().named("sel72");
    model.component("comp6").physics("ht6").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp6").physics("ht6").feature("hf1").set("h", "1/Rse");
    model.component("comp6").physics("ht6").feature("hf1").set("Text", "Te");
    model.component("comp6").physics("ht6").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp6").physics("ht6").feature("hf2").selection().named("sel73");
    model.component("comp6").physics("ht6").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp6").physics("ht6").feature("hf2").set("h", "1/Rsi_n");
    model.component("comp6").physics("ht6").feature("hf2").set("Text", "Ti");
    model.component("comp6").physics("ht6").create("hf3", "HeatFluxBoundary", 1);
    model.component("comp6").physics("ht6").feature("hf3").selection().named("sel74");
    model.component("comp6").physics("ht6").feature("hf3").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp6").physics("ht6").feature("hf3").set("h", "1/Rsi_p");
    model.component("comp6").physics("ht6").feature("hf3").set("Text", "Ti");

    model.study("std6").createAutoSequences("all");

    model.result().dataset().create("dset31", "Solution");
    model.result().dataset("dset31").set("solution", "sol5");
    model.result().dataset("dset31").set("comp", "none");
    model.result().dataset("dset31").set("geom", "geom1");
    model.result().dataset().create("dset32", "Solution");
    model.result().dataset("dset32").set("solution", "sol5");
    model.result().dataset("dset32").set("geom", "geom2");
    model.result().dataset().create("dset33", "Solution");
    model.result().dataset("dset33").set("solution", "sol5");
    model.result().dataset("dset33").set("geom", "geom3");
    model.result().dataset().create("dset34", "Solution");
    model.result().dataset("dset34").set("solution", "sol5");
    model.result().dataset("dset34").set("geom", "geom4");
    model.result().dataset().create("dset35", "Solution");
    model.result().dataset("dset35").set("solution", "sol5");
    model.result().dataset("dset35").set("geom", "geom5");
    model.result().dataset().create("dset36", "Solution");
    model.result().dataset("dset36").set("solution", "sol5");
    model.result().dataset("dset36").set("geom", "geom6");
    model.result().dataset("dset36").set("comp", "comp6");
    model.result().dataset().move("dset31", 30);
    model.result().dataset("dset31").tag("dset31");
    model.result().dataset().move("dset32", 31);
    model.result().dataset("dset32").tag("dset32");
    model.result().dataset().move("dset33", 32);
    model.result().dataset("dset33").tag("dset33");
    model.result().dataset().move("dset34", 33);
    model.result().dataset("dset34").tag("dset34");
    model.result().dataset().move("dset35", 34);
    model.result().dataset("dset35").tag("dset35");
    model.result().dataset().move("dset36", 35);
    model.result().dataset("dset36").tag("dset36");

    model.sol("sol5").runAll();

    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u6e29\u5ea6 (ht6)");
    model.result("pg6").set("data", "dset36");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").run();
    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 6");
    model.result().numerical("gev6").set("data", "dset36");
    model.result().numerical("gev6").setIndex("expr", "L2D", 0);
    model.result().numerical("gev6").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev6").setIndex("expr", "(L2D-Up*pvl)/f_wtot", 1);
    model.result().numerical("gev6").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D) 6");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").setResult();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();

    model.title("\u7a97\u6237\u70ed\u6027\u80fd");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u53ef\u4ee5\u91cd\u73b0 ISO 10077-2:2012 \u6807\u51c6\u6709\u5173\u7a97\u6237\u7684\u516d\u79cd\u60c5\u51b5\u3002\u901a\u8fc7\u8ba1\u7b97\u6bcf\u4e2a\u7a97\u6237\u7684\u70ed\u5bfc\u7387\u548c\u70ed\u900f\u5c04\u7387\u5f97\u5230\u76f8\u5173\u7a97\u6237\u7684\u70ed\u6027\u80fd\u3002\u6700\u540e\u7684\u7ed3\u679c\u4e0e\u7ed9\u5b9a\u7684\u6570\u636e\u8fdb\u884c\u4e86\u9a8c\u8bc1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("windows_thermal_performances.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    run7(model);
  }

}
