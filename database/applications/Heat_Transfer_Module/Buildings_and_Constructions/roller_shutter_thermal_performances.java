/*
 * roller_shutter_thermal_performances.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:18 by COMSOL 6.3.0.290. */
public class roller_shutter_thermal_performances {

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

    model.component("comp1").label("\u5377\u5e18\u767e\u53f6\u7a97\u7bb1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("C1", "0.025[W/(m*K)]", "\u5bf9\u6d41\u4f20\u70ed\u7cfb\u6570\u8ba1\u7b97\u7684\u7b2c\u4e00\u4e2a\u5e38\u6570");
    model.param()
         .set("C2", "0.73[W/(m^2*K)]", "\u5bf9\u6d41\u4f20\u70ed\u7cfb\u6570\u8ba1\u7b97\u7684\u7b2c\u4e8c\u4e2a\u5e38\u6570");
    model.param().set("epsilon", "0.90", "\u8868\u9762\u53d1\u5c04\u7387");
    model.param().set("Ti", "20[degC]", "\u5185\u90e8\u6e29\u5ea6");
    model.param().set("Te", "0[degC]", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("Rsi", "0.13[m^2*K/W]", "\u5185\u8868\u9762\u70ed\u963b");
    model.param().set("Rse", "0.04[m^2*K/W]", "\u5916\u8868\u9762\u70ed\u963b");
    model.param().set("lambda_p", "0.035[W/(m*K)]", "\u9694\u70ed\u677f\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("lambda_pvc", "0.17[W/(m*K)]", "PVC \u7684\u5bfc\u70ed\u7cfb\u6570");

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
    model.func().create("an5", "Analytic");
    model.func("an5").set("funcname", "w_resized");
    model.func("an5").set("expr", "sqrt(A*b/d)");
    model.func("an5").set("args", "A, b, d");
    model.func("an5").setIndex("argunit", "m^2", 0);
    model.func("an5").setIndex("argunit", "m", 1);
    model.func("an5").setIndex("argunit", "m", 2);
    model.func("an5").set("fununit", "m");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "roller_shutter_thermal_performances_box.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 46, 50, 126, 129, 131, 133, 135, 169);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input").set("ige1", 120);
    model.component("comp1").geom("geom1").run("igv1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(4, 5, 6, 34);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(7, 8, 9, 35);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(10, 11, 12, 36);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(13, 14, 15, 37);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(16, 17, 18, 38);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(19, 20, 21, 39);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(22, 23, 24, 40);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(25, 26, 27, 41);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp1").selection("sel9").geom(1);
    model.component("comp1").selection("sel9").set(28, 29, 30, 42);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp1").selection("sel10").geom(1);
    model.component("comp1").selection("sel10").set(31, 32, 33, 43);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u4e0d\u901a\u98ce\u7684\u8154 11");
    model.component("comp1").selection("sel11").geom(1);
    model.component("comp1").selection("sel11").set(47, 48, 49, 53);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u4e0d\u901a\u98ce\u7684\u8154 12");
    model.component("comp1").selection("sel12").geom(1);
    model.component("comp1").selection("sel12").set(57, 58, 59, 62);
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u4e0d\u901a\u98ce\u7684\u8154 13");
    model.component("comp1").selection("sel13").geom(1);
    model.component("comp1").selection("sel13").set(66, 67, 68, 70);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u4e0d\u901a\u98ce\u7684\u8154 14");
    model.component("comp1").selection("sel14").geom(1);
    model.component("comp1").selection("sel14").set(74, 75, 76, 81);
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u4e0d\u901a\u98ce\u7684\u8154 15");
    model.component("comp1").selection("sel15").geom(1);
    model.component("comp1").selection("sel15").set(85, 86, 87, 90);
    model.component("comp1").selection().create("sel16", "Explicit");
    model.component("comp1").selection("sel16").label("\u4e0d\u901a\u98ce\u7684\u8154 16");
    model.component("comp1").selection("sel16").geom(1);
    model.component("comp1").selection("sel16").set(94, 95, 96, 98);
    model.component("comp1").selection().create("sel17", "Explicit");
    model.component("comp1").selection("sel17").label("\u4e0d\u901a\u98ce\u7684\u8154 17");
    model.component("comp1").selection("sel17").geom(1);
    model.component("comp1").selection("sel17").set(102, 103, 104, 106);
    model.component("comp1").selection().create("sel18", "Explicit");
    model.component("comp1").selection("sel18").label("\u4e0d\u901a\u98ce\u7684\u8154 18");
    model.component("comp1").selection("sel18").geom(1);
    model.component("comp1").selection("sel18").set(112, 113, 114, 116);
    model.component("comp1").selection().create("sel19", "Explicit");
    model.component("comp1").selection("sel19").label("\u4e0d\u901a\u98ce\u7684\u8154 19");
    model.component("comp1").selection("sel19").geom(1);
    model.component("comp1").selection("sel19").set(120, 121, 122, 125);
    model.component("comp1").selection().create("sel20", "Explicit");
    model.component("comp1").selection("sel20").label("\u4e0d\u901a\u98ce\u7684\u8154 20");
    model.component("comp1").selection("sel20").geom(1);
    model.component("comp1").selection("sel20").set(153, 154, 155, 165);
    model.component("comp1").selection().create("sel21", "Explicit");
    model.component("comp1").selection("sel21").label("\u4e0d\u901a\u98ce\u7684\u8154 21");
    model.component("comp1").selection("sel21").geom(1);
    model.component("comp1").selection("sel21").set(150, 151, 152, 164);
    model.component("comp1").selection().create("sel22", "Explicit");
    model.component("comp1").selection("sel22").label("\u4e0d\u901a\u98ce\u7684\u8154 22");
    model.component("comp1").selection("sel22").geom(1);
    model.component("comp1").selection("sel22").set(147, 148, 149, 163);
    model.component("comp1").selection().create("sel23", "Explicit");
    model.component("comp1").selection("sel23").label("\u4e0d\u901a\u98ce\u7684\u8154 23");
    model.component("comp1").selection("sel23").geom(1);
    model.component("comp1").selection("sel23").set(144, 145, 146, 162);
    model.component("comp1").selection().create("sel24", "Explicit");
    model.component("comp1").selection("sel24").label("\u4e0d\u901a\u98ce\u7684\u8154 24");
    model.component("comp1").selection("sel24").geom(1);
    model.component("comp1").selection("sel24").set(141, 142, 143, 161);
    model.component("comp1").selection().create("sel25", "Explicit");
    model.component("comp1").selection("sel25").label("\u4e0d\u901a\u98ce\u7684\u8154 25");
    model.component("comp1").selection("sel25").geom(1);
    model.component("comp1").selection("sel25").set(138, 139, 140, 160);
    model.component("comp1").selection().create("sel26", "Explicit");
    model.component("comp1").selection("sel26").label("\u4e0d\u901a\u98ce\u7684\u8154 26");
    model.component("comp1").selection("sel26").geom(1);
    model.component("comp1").selection("sel26").set(135, 136, 137, 159);
    model.component("comp1").selection().create("sel27", "Explicit");
    model.component("comp1").selection("sel27").label("\u4e0d\u901a\u98ce\u7684\u8154 27");
    model.component("comp1").selection("sel27").geom(1);
    model.component("comp1").selection("sel27").set(132, 133, 134, 158);
    model.component("comp1").selection().create("sel28", "Explicit");
    model.component("comp1").selection("sel28").label("\u4e0d\u901a\u98ce\u7684\u8154 28");
    model.component("comp1").selection("sel28").geom(1);
    model.component("comp1").selection("sel28").set(129, 130, 131, 157);
    model.component("comp1").selection().create("sel29", "Explicit");
    model.component("comp1").selection("sel29").label("\u4e0d\u901a\u98ce\u7684\u8154 29");
    model.component("comp1").selection("sel29").geom(1);
    model.component("comp1").selection("sel29").set(126, 127, 128, 156);
    model.component("comp1").selection().create("sel30", "Explicit");
    model.component("comp1").selection("sel30").label("\u4e0d\u901a\u98ce\u7684\u8154 30");
    model.component("comp1").selection("sel30").geom(1);
    model.component("comp1").selection("sel30").set(117, 118, 119, 123);
    model.component("comp1").selection().create("sel31", "Explicit");
    model.component("comp1").selection("sel31").label("\u4e0d\u901a\u98ce\u7684\u8154 31");
    model.component("comp1").selection("sel31").geom(1);
    model.component("comp1").selection("sel31").set(107, 108, 109, 115);
    model.component("comp1").selection().create("sel32", "Explicit");
    model.component("comp1").selection("sel32").label("\u4e0d\u901a\u98ce\u7684\u8154 32");
    model.component("comp1").selection("sel32").geom(1);
    model.component("comp1").selection("sel32").set(99, 100, 101, 105);
    model.component("comp1").selection().create("sel33", "Explicit");
    model.component("comp1").selection("sel33").label("\u4e0d\u901a\u98ce\u7684\u8154 33");
    model.component("comp1").selection("sel33").geom(1);
    model.component("comp1").selection("sel33").set(91, 92, 93, 97);
    model.component("comp1").selection().create("sel34", "Explicit");
    model.component("comp1").selection("sel34").label("\u4e0d\u901a\u98ce\u7684\u8154 34");
    model.component("comp1").selection("sel34").geom(1);
    model.component("comp1").selection("sel34").set(82, 83, 84, 89);
    model.component("comp1").selection().create("sel35", "Explicit");
    model.component("comp1").selection("sel35").label("\u4e0d\u901a\u98ce\u7684\u8154 35");
    model.component("comp1").selection("sel35").geom(1);
    model.component("comp1").selection("sel35").set(71, 72, 73, 77);
    model.component("comp1").selection().create("sel36", "Explicit");
    model.component("comp1").selection("sel36").label("\u4e0d\u901a\u98ce\u7684\u8154 36");
    model.component("comp1").selection("sel36").geom(1);
    model.component("comp1").selection("sel36").set(63, 64, 65, 69);
    model.component("comp1").selection().create("sel37", "Explicit");
    model.component("comp1").selection("sel37").label("\u4e0d\u901a\u98ce\u7684\u8154 37");
    model.component("comp1").selection("sel37").geom(1);
    model.component("comp1").selection("sel37").set(54, 55, 56, 61);
    model.component("comp1").selection().create("sel38", "Explicit");
    model.component("comp1").selection("sel38").label("\u5fae\u901a\u98ce\u8154");
    model.component("comp1").selection("sel38").geom(1);
    model.component("comp1").selection("sel38").set(44, 46, 50, 52, 78, 80, 110);
    model.component("comp1").selection().create("sel39", "Explicit");
    model.component("comp1").selection("sel39").label("\u5916\u4fa7");
    model.component("comp1").selection("sel39").geom(1);
    model.component("comp1").selection("sel39").set(1, 2, 51);
    model.component("comp1").selection().create("sel40", "Explicit");
    model.component("comp1").selection("sel40").label("\u5185\u4fa7");
    model.component("comp1").selection("sel40").geom(1);
    model.component("comp1").selection("sel40").set(88, 166);
    model.component("comp1").selection().create("sel41", "Explicit");
    model.component("comp1").selection("sel41").label("\u70ed\u7edd\u7f18");
    model.component("comp1").selection("sel41").geom(1);
    model.component("comp1").selection("sel41").set(3, 60);

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
    model.component("comp1").cpl().create("maxop2", "Maximum");
    model.component("comp1").cpl("maxop2").set("opname", "max_uc2");
    model.component("comp1").cpl("maxop2").selection().set(3);
    model.component("comp1").cpl().create("minop2", "Minimum");
    model.component("comp1").cpl("minop2").set("opname", "min_uc2");
    model.component("comp1").cpl("minop2").selection().set(3);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "ave_uc2");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("sel2");
    model.component("comp1").cpl().create("maxop3", "Maximum");
    model.component("comp1").cpl("maxop3").set("opname", "max_uc3");
    model.component("comp1").cpl("maxop3").selection().set(4);
    model.component("comp1").cpl().create("minop3", "Minimum");
    model.component("comp1").cpl("minop3").set("opname", "min_uc3");
    model.component("comp1").cpl("minop3").selection().set(4);
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").set("opname", "ave_uc3");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop3").selection().named("sel3");
    model.component("comp1").cpl().create("maxop4", "Maximum");
    model.component("comp1").cpl("maxop4").set("opname", "max_uc4");
    model.component("comp1").cpl("maxop4").selection().set(5);
    model.component("comp1").cpl().create("minop4", "Minimum");
    model.component("comp1").cpl("minop4").set("opname", "min_uc4");
    model.component("comp1").cpl("minop4").selection().set(5);
    model.component("comp1").cpl().create("aveop4", "Average");
    model.component("comp1").cpl("aveop4").set("axisym", true);
    model.component("comp1").cpl("aveop4").set("opname", "ave_uc4");
    model.component("comp1").cpl("aveop4").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop4").selection().named("sel4");
    model.component("comp1").cpl().create("maxop5", "Maximum");
    model.component("comp1").cpl("maxop5").set("opname", "max_uc5");
    model.component("comp1").cpl("maxop5").selection().set(6);
    model.component("comp1").cpl().create("minop5", "Minimum");
    model.component("comp1").cpl("minop5").set("opname", "min_uc5");
    model.component("comp1").cpl("minop5").selection().set(6);
    model.component("comp1").cpl().create("aveop5", "Average");
    model.component("comp1").cpl("aveop5").set("axisym", true);
    model.component("comp1").cpl("aveop5").set("opname", "ave_uc5");
    model.component("comp1").cpl("aveop5").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop5").selection().named("sel5");
    model.component("comp1").cpl().create("maxop6", "Maximum");
    model.component("comp1").cpl("maxop6").set("opname", "max_uc6");
    model.component("comp1").cpl("maxop6").selection().set(7);
    model.component("comp1").cpl().create("minop6", "Minimum");
    model.component("comp1").cpl("minop6").set("opname", "min_uc6");
    model.component("comp1").cpl("minop6").selection().set(7);
    model.component("comp1").cpl().create("aveop6", "Average");
    model.component("comp1").cpl("aveop6").set("axisym", true);
    model.component("comp1").cpl("aveop6").set("opname", "ave_uc6");
    model.component("comp1").cpl("aveop6").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop6").selection().named("sel6");
    model.component("comp1").cpl().create("maxop7", "Maximum");
    model.component("comp1").cpl("maxop7").set("opname", "max_uc7");
    model.component("comp1").cpl("maxop7").selection().set(8);
    model.component("comp1").cpl().create("minop7", "Minimum");
    model.component("comp1").cpl("minop7").set("opname", "min_uc7");
    model.component("comp1").cpl("minop7").selection().set(8);
    model.component("comp1").cpl().create("aveop7", "Average");
    model.component("comp1").cpl("aveop7").set("axisym", true);
    model.component("comp1").cpl("aveop7").set("opname", "ave_uc7");
    model.component("comp1").cpl("aveop7").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop7").selection().named("sel7");
    model.component("comp1").cpl().create("maxop8", "Maximum");
    model.component("comp1").cpl("maxop8").set("opname", "max_uc8");
    model.component("comp1").cpl("maxop8").selection().set(9);
    model.component("comp1").cpl().create("minop8", "Minimum");
    model.component("comp1").cpl("minop8").set("opname", "min_uc8");
    model.component("comp1").cpl("minop8").selection().set(9);
    model.component("comp1").cpl().create("aveop8", "Average");
    model.component("comp1").cpl("aveop8").set("axisym", true);
    model.component("comp1").cpl("aveop8").set("opname", "ave_uc8");
    model.component("comp1").cpl("aveop8").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop8").selection().named("sel8");
    model.component("comp1").cpl().create("maxop9", "Maximum");
    model.component("comp1").cpl("maxop9").set("opname", "max_uc9");
    model.component("comp1").cpl("maxop9").selection().set(10);
    model.component("comp1").cpl().create("minop9", "Minimum");
    model.component("comp1").cpl("minop9").set("opname", "min_uc9");
    model.component("comp1").cpl("minop9").selection().set(10);
    model.component("comp1").cpl().create("aveop9", "Average");
    model.component("comp1").cpl("aveop9").set("axisym", true);
    model.component("comp1").cpl("aveop9").set("opname", "ave_uc9");
    model.component("comp1").cpl("aveop9").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop9").selection().named("sel9");
    model.component("comp1").cpl().create("maxop10", "Maximum");
    model.component("comp1").cpl("maxop10").set("opname", "max_uc10");
    model.component("comp1").cpl("maxop10").selection().set(11);
    model.component("comp1").cpl().create("minop10", "Minimum");
    model.component("comp1").cpl("minop10").set("opname", "min_uc10");
    model.component("comp1").cpl("minop10").selection().set(11);
    model.component("comp1").cpl().create("aveop10", "Average");
    model.component("comp1").cpl("aveop10").set("axisym", true);
    model.component("comp1").cpl("aveop10").set("opname", "ave_uc10");
    model.component("comp1").cpl("aveop10").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop10").selection().named("sel10");
    model.component("comp1").cpl().create("maxop11", "Maximum");
    model.component("comp1").cpl("maxop11").set("opname", "max_uc11");
    model.component("comp1").cpl("maxop11").selection().set(13);
    model.component("comp1").cpl().create("minop11", "Minimum");
    model.component("comp1").cpl("minop11").set("opname", "min_uc11");
    model.component("comp1").cpl("minop11").selection().set(13);
    model.component("comp1").cpl().create("aveop11", "Average");
    model.component("comp1").cpl("aveop11").set("axisym", true);
    model.component("comp1").cpl("aveop11").set("opname", "ave_uc11");
    model.component("comp1").cpl("aveop11").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop11").selection().named("sel11");
    model.component("comp1").cpl().create("maxop12", "Maximum");
    model.component("comp1").cpl("maxop12").set("opname", "max_uc12");
    model.component("comp1").cpl("maxop12").selection().set(15);
    model.component("comp1").cpl().create("minop12", "Minimum");
    model.component("comp1").cpl("minop12").set("opname", "min_uc12");
    model.component("comp1").cpl("minop12").selection().set(15);
    model.component("comp1").cpl().create("aveop12", "Average");
    model.component("comp1").cpl("aveop12").set("axisym", true);
    model.component("comp1").cpl("aveop12").set("opname", "ave_uc12");
    model.component("comp1").cpl("aveop12").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop12").selection().named("sel12");
    model.component("comp1").cpl().create("maxop13", "Maximum");
    model.component("comp1").cpl("maxop13").set("opname", "max_uc13");
    model.component("comp1").cpl("maxop13").selection().set(17);
    model.component("comp1").cpl().create("minop13", "Minimum");
    model.component("comp1").cpl("minop13").set("opname", "min_uc13");
    model.component("comp1").cpl("minop13").selection().set(17);
    model.component("comp1").cpl().create("aveop13", "Average");
    model.component("comp1").cpl("aveop13").set("axisym", true);
    model.component("comp1").cpl("aveop13").set("opname", "ave_uc13");
    model.component("comp1").cpl("aveop13").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop13").selection().named("sel13");
    model.component("comp1").cpl().create("maxop14", "Maximum");
    model.component("comp1").cpl("maxop14").set("opname", "max_uc14");
    model.component("comp1").cpl("maxop14").selection().set(19);
    model.component("comp1").cpl().create("minop14", "Minimum");
    model.component("comp1").cpl("minop14").set("opname", "min_uc14");
    model.component("comp1").cpl("minop14").selection().set(19);
    model.component("comp1").cpl().create("aveop14", "Average");
    model.component("comp1").cpl("aveop14").set("axisym", true);
    model.component("comp1").cpl("aveop14").set("opname", "ave_uc14");
    model.component("comp1").cpl("aveop14").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop14").selection().named("sel14");
    model.component("comp1").cpl().create("maxop15", "Maximum");
    model.component("comp1").cpl("maxop15").set("opname", "max_uc15");
    model.component("comp1").cpl("maxop15").selection().set(22);
    model.component("comp1").cpl().create("minop15", "Minimum");
    model.component("comp1").cpl("minop15").set("opname", "min_uc15");
    model.component("comp1").cpl("minop15").selection().set(22);
    model.component("comp1").cpl().create("aveop15", "Average");
    model.component("comp1").cpl("aveop15").set("axisym", true);
    model.component("comp1").cpl("aveop15").set("opname", "ave_uc15");
    model.component("comp1").cpl("aveop15").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop15").selection().named("sel15");
    model.component("comp1").cpl().create("maxop16", "Maximum");
    model.component("comp1").cpl("maxop16").set("opname", "max_uc16");
    model.component("comp1").cpl("maxop16").selection().set(24);
    model.component("comp1").cpl().create("minop16", "Minimum");
    model.component("comp1").cpl("minop16").set("opname", "min_uc16");
    model.component("comp1").cpl("minop16").selection().set(24);
    model.component("comp1").cpl().create("aveop16", "Average");
    model.component("comp1").cpl("aveop16").set("axisym", true);
    model.component("comp1").cpl("aveop16").set("opname", "ave_uc16");
    model.component("comp1").cpl("aveop16").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop16").selection().named("sel16");
    model.component("comp1").cpl().create("maxop17", "Maximum");
    model.component("comp1").cpl("maxop17").set("opname", "max_uc17");
    model.component("comp1").cpl("maxop17").selection().set(26);
    model.component("comp1").cpl().create("minop17", "Minimum");
    model.component("comp1").cpl("minop17").set("opname", "min_uc17");
    model.component("comp1").cpl("minop17").selection().set(26);
    model.component("comp1").cpl().create("aveop17", "Average");
    model.component("comp1").cpl("aveop17").set("axisym", true);
    model.component("comp1").cpl("aveop17").set("opname", "ave_uc17");
    model.component("comp1").cpl("aveop17").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop17").selection().named("sel17");
    model.component("comp1").cpl().create("maxop18", "Maximum");
    model.component("comp1").cpl("maxop18").set("opname", "max_uc18");
    model.component("comp1").cpl("maxop18").selection().set(28);
    model.component("comp1").cpl().create("minop18", "Minimum");
    model.component("comp1").cpl("minop18").set("opname", "min_uc18");
    model.component("comp1").cpl("minop18").selection().set(28);
    model.component("comp1").cpl().create("aveop18", "Average");
    model.component("comp1").cpl("aveop18").set("axisym", true);
    model.component("comp1").cpl("aveop18").set("opname", "ave_uc18");
    model.component("comp1").cpl("aveop18").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop18").selection().named("sel18");
    model.component("comp1").cpl().create("maxop19", "Maximum");
    model.component("comp1").cpl("maxop19").set("opname", "max_uc19");
    model.component("comp1").cpl("maxop19").selection().set(30);
    model.component("comp1").cpl().create("minop19", "Minimum");
    model.component("comp1").cpl("minop19").set("opname", "min_uc19");
    model.component("comp1").cpl("minop19").selection().set(30);
    model.component("comp1").cpl().create("aveop19", "Average");
    model.component("comp1").cpl("aveop19").set("axisym", true);
    model.component("comp1").cpl("aveop19").set("opname", "ave_uc19");
    model.component("comp1").cpl("aveop19").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop19").selection().named("sel19");
    model.component("comp1").cpl().create("maxop20", "Maximum");
    model.component("comp1").cpl("maxop20").set("opname", "max_uc20");
    model.component("comp1").cpl("maxop20").selection().set(40);
    model.component("comp1").cpl().create("minop20", "Minimum");
    model.component("comp1").cpl("minop20").set("opname", "min_uc20");
    model.component("comp1").cpl("minop20").selection().set(40);
    model.component("comp1").cpl().create("aveop20", "Average");
    model.component("comp1").cpl("aveop20").set("axisym", true);
    model.component("comp1").cpl("aveop20").set("opname", "ave_uc20");
    model.component("comp1").cpl("aveop20").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop20").selection().named("sel20");
    model.component("comp1").cpl().create("maxop21", "Maximum");
    model.component("comp1").cpl("maxop21").set("opname", "max_uc21");
    model.component("comp1").cpl("maxop21").selection().set(39);
    model.component("comp1").cpl().create("minop21", "Minimum");
    model.component("comp1").cpl("minop21").set("opname", "min_uc21");
    model.component("comp1").cpl("minop21").selection().set(39);
    model.component("comp1").cpl().create("aveop21", "Average");
    model.component("comp1").cpl("aveop21").set("axisym", true);
    model.component("comp1").cpl("aveop21").set("opname", "ave_uc21");
    model.component("comp1").cpl("aveop21").selection().geom("geom1", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").cpl("aveop21").selection().named("sel21");
    model.component("comp1").cpl().create("maxop22", "Maximum");
    model.component("comp1").cpl("maxop22").set("opname", "max_uc22");
    model.component("comp1").cpl("maxop22").selection().set(38);
    model.component("comp1").cpl().create("minop22", "Minimum");
    model.component("comp1").cpl("minop22").set("opname", "min_uc22");
    model.component("comp1").cpl("minop22").selection().set(38);
    model.component("comp1").cpl().create("aveop22", "Average");
    model.component("comp1").cpl("aveop22").set("axisym", true);
    model.component("comp1").cpl("aveop22").set("opname", "ave_uc22");
    model.component("comp1").cpl("aveop22").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop22").selection().named("sel22");
    model.component("comp1").cpl().create("maxop23", "Maximum");
    model.component("comp1").cpl("maxop23").set("opname", "max_uc23");
    model.component("comp1").cpl("maxop23").selection().set(37);
    model.component("comp1").cpl().create("minop23", "Minimum");
    model.component("comp1").cpl("minop23").set("opname", "min_uc23");
    model.component("comp1").cpl("minop23").selection().set(37);
    model.component("comp1").cpl().create("aveop23", "Average");
    model.component("comp1").cpl("aveop23").set("axisym", true);
    model.component("comp1").cpl("aveop23").set("opname", "ave_uc23");
    model.component("comp1").cpl("aveop23").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop23").selection().named("sel23");
    model.component("comp1").cpl().create("maxop24", "Maximum");
    model.component("comp1").cpl("maxop24").set("opname", "max_uc24");
    model.component("comp1").cpl("maxop24").selection().set(36);
    model.component("comp1").cpl().create("minop24", "Minimum");
    model.component("comp1").cpl("minop24").set("opname", "min_uc24");
    model.component("comp1").cpl("minop24").selection().set(36);
    model.component("comp1").cpl().create("aveop24", "Average");
    model.component("comp1").cpl("aveop24").set("axisym", true);
    model.component("comp1").cpl("aveop24").set("opname", "ave_uc24");
    model.component("comp1").cpl("aveop24").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop24").selection().named("sel24");
    model.component("comp1").cpl().create("maxop25", "Maximum");
    model.component("comp1").cpl("maxop25").set("opname", "max_uc25");
    model.component("comp1").cpl("maxop25").selection().set(35);
    model.component("comp1").cpl().create("minop25", "Minimum");
    model.component("comp1").cpl("minop25").set("opname", "min_uc25");
    model.component("comp1").cpl("minop25").selection().set(35);
    model.component("comp1").cpl().create("aveop25", "Average");
    model.component("comp1").cpl("aveop25").set("axisym", true);
    model.component("comp1").cpl("aveop25").set("opname", "ave_uc25");
    model.component("comp1").cpl("aveop25").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop25").selection().named("sel25");
    model.component("comp1").cpl().create("maxop26", "Maximum");
    model.component("comp1").cpl("maxop26").set("opname", "max_uc26");
    model.component("comp1").cpl("maxop26").selection().set(34);
    model.component("comp1").cpl().create("minop26", "Minimum");
    model.component("comp1").cpl("minop26").set("opname", "min_uc26");
    model.component("comp1").cpl("minop26").selection().set(34);
    model.component("comp1").cpl().create("aveop26", "Average");
    model.component("comp1").cpl("aveop26").set("axisym", true);
    model.component("comp1").cpl("aveop26").set("opname", "ave_uc26");
    model.component("comp1").cpl("aveop26").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop26").selection().named("sel26");
    model.component("comp1").cpl().create("maxop27", "Maximum");
    model.component("comp1").cpl("maxop27").set("opname", "max_uc27");
    model.component("comp1").cpl("maxop27").selection().set(33);
    model.component("comp1").cpl().create("minop27", "Minimum");
    model.component("comp1").cpl("minop27").set("opname", "min_uc27");
    model.component("comp1").cpl("minop27").selection().set(33);
    model.component("comp1").cpl().create("aveop27", "Average");
    model.component("comp1").cpl("aveop27").set("axisym", true);
    model.component("comp1").cpl("aveop27").set("opname", "ave_uc27");
    model.component("comp1").cpl("aveop27").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop27").selection().named("sel27");
    model.component("comp1").cpl().create("maxop28", "Maximum");
    model.component("comp1").cpl("maxop28").set("opname", "max_uc28");
    model.component("comp1").cpl("maxop28").selection().set(32);
    model.component("comp1").cpl().create("minop28", "Minimum");
    model.component("comp1").cpl("minop28").set("opname", "min_uc28");
    model.component("comp1").cpl("minop28").selection().set(32);
    model.component("comp1").cpl().create("aveop28", "Average");
    model.component("comp1").cpl("aveop28").set("axisym", true);
    model.component("comp1").cpl("aveop28").set("opname", "ave_uc28");
    model.component("comp1").cpl("aveop28").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop28").selection().named("sel28");
    model.component("comp1").cpl().create("maxop29", "Maximum");
    model.component("comp1").cpl("maxop29").set("opname", "max_uc29");
    model.component("comp1").cpl("maxop29").selection().set(31);
    model.component("comp1").cpl().create("minop29", "Minimum");
    model.component("comp1").cpl("minop29").set("opname", "min_uc29");
    model.component("comp1").cpl("minop29").selection().set(31);
    model.component("comp1").cpl().create("aveop29", "Average");
    model.component("comp1").cpl("aveop29").set("axisym", true);
    model.component("comp1").cpl("aveop29").set("opname", "ave_uc29");
    model.component("comp1").cpl("aveop29").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop29").selection().named("sel29");
    model.component("comp1").cpl().create("maxop30", "Maximum");
    model.component("comp1").cpl("maxop30").set("opname", "max_uc30");
    model.component("comp1").cpl("maxop30").selection().set(29);
    model.component("comp1").cpl().create("minop30", "Minimum");
    model.component("comp1").cpl("minop30").set("opname", "min_uc30");
    model.component("comp1").cpl("minop30").selection().set(29);
    model.component("comp1").cpl().create("aveop30", "Average");
    model.component("comp1").cpl("aveop30").set("axisym", true);
    model.component("comp1").cpl("aveop30").set("opname", "ave_uc30");
    model.component("comp1").cpl("aveop30").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop30").selection().named("sel30");
    model.component("comp1").cpl().create("maxop31", "Maximum");
    model.component("comp1").cpl("maxop31").set("opname", "max_uc31");
    model.component("comp1").cpl("maxop31").selection().set(27);
    model.component("comp1").cpl().create("minop31", "Minimum");
    model.component("comp1").cpl("minop31").set("opname", "min_uc31");
    model.component("comp1").cpl("minop31").selection().set(27);
    model.component("comp1").cpl().create("aveop31", "Average");
    model.component("comp1").cpl("aveop31").set("axisym", true);
    model.component("comp1").cpl("aveop31").set("opname", "ave_uc31");
    model.component("comp1").cpl("aveop31").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop31").selection().named("sel31");
    model.component("comp1").cpl().create("maxop32", "Maximum");
    model.component("comp1").cpl("maxop32").set("opname", "max_uc32");
    model.component("comp1").cpl("maxop32").selection().set(25);
    model.component("comp1").cpl().create("minop32", "Minimum");
    model.component("comp1").cpl("minop32").set("opname", "min_uc32");
    model.component("comp1").cpl("minop32").selection().set(25);
    model.component("comp1").cpl().create("aveop32", "Average");
    model.component("comp1").cpl("aveop32").set("axisym", true);
    model.component("comp1").cpl("aveop32").set("opname", "ave_uc32");
    model.component("comp1").cpl("aveop32").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop32").selection().named("sel32");
    model.component("comp1").cpl().create("maxop33", "Maximum");
    model.component("comp1").cpl("maxop33").set("opname", "max_uc33");
    model.component("comp1").cpl("maxop33").selection().set(23);
    model.component("comp1").cpl().create("minop33", "Minimum");
    model.component("comp1").cpl("minop33").set("opname", "min_uc33");
    model.component("comp1").cpl("minop33").selection().set(23);
    model.component("comp1").cpl().create("aveop33", "Average");
    model.component("comp1").cpl("aveop33").set("axisym", true);
    model.component("comp1").cpl("aveop33").set("opname", "ave_uc33");
    model.component("comp1").cpl("aveop33").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop33").selection().named("sel33");
    model.component("comp1").cpl().create("maxop34", "Maximum");
    model.component("comp1").cpl("maxop34").set("opname", "max_uc34");
    model.component("comp1").cpl("maxop34").selection().set(21);
    model.component("comp1").cpl().create("minop34", "Minimum");
    model.component("comp1").cpl("minop34").set("opname", "min_uc34");
    model.component("comp1").cpl("minop34").selection().set(21);
    model.component("comp1").cpl().create("aveop34", "Average");
    model.component("comp1").cpl("aveop34").set("axisym", true);
    model.component("comp1").cpl("aveop34").set("opname", "ave_uc34");
    model.component("comp1").cpl("aveop34").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop34").selection().named("sel34");
    model.component("comp1").cpl().create("maxop35", "Maximum");
    model.component("comp1").cpl("maxop35").set("opname", "max_uc35");
    model.component("comp1").cpl("maxop35").selection().set(18);
    model.component("comp1").cpl().create("minop35", "Minimum");
    model.component("comp1").cpl("minop35").set("opname", "min_uc35");
    model.component("comp1").cpl("minop35").selection().set(18);
    model.component("comp1").cpl().create("aveop35", "Average");
    model.component("comp1").cpl("aveop35").set("axisym", true);
    model.component("comp1").cpl("aveop35").set("opname", "ave_uc35");
    model.component("comp1").cpl("aveop35").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop35").selection().named("sel35");
    model.component("comp1").cpl().create("maxop36", "Maximum");
    model.component("comp1").cpl("maxop36").set("opname", "max_uc36");
    model.component("comp1").cpl("maxop36").selection().set(16);
    model.component("comp1").cpl().create("minop36", "Minimum");
    model.component("comp1").cpl("minop36").set("opname", "min_uc36");
    model.component("comp1").cpl("minop36").selection().set(16);
    model.component("comp1").cpl().create("aveop36", "Average");
    model.component("comp1").cpl("aveop36").set("axisym", true);
    model.component("comp1").cpl("aveop36").set("opname", "ave_uc36");
    model.component("comp1").cpl("aveop36").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop36").selection().named("sel36");
    model.component("comp1").cpl().create("maxop37", "Maximum");
    model.component("comp1").cpl("maxop37").set("opname", "max_uc37");
    model.component("comp1").cpl("maxop37").selection().set(14);
    model.component("comp1").cpl().create("minop37", "Minimum");
    model.component("comp1").cpl("minop37").set("opname", "min_uc37");
    model.component("comp1").cpl("minop37").selection().set(14);
    model.component("comp1").cpl().create("aveop37", "Average");
    model.component("comp1").cpl("aveop37").set("axisym", true);
    model.component("comp1").cpl("aveop37").set("opname", "ave_uc37");
    model.component("comp1").cpl("aveop37").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop37").selection().named("sel37");
    model.component("comp1").cpl().create("maxop38", "Maximum");
    model.component("comp1").cpl("maxop38").set("opname", "max_svc");
    model.component("comp1").cpl("maxop38").selection().set(12);
    model.component("comp1").cpl().create("minop38", "Minimum");
    model.component("comp1").cpl("minop38").set("opname", "min_svc");
    model.component("comp1").cpl("minop38").selection().set(12);
    model.component("comp1").cpl().create("aveop38", "Average");
    model.component("comp1").cpl("aveop38").set("axisym", true);
    model.component("comp1").cpl("aveop38").set("opname", "ave_svc");
    model.component("comp1").cpl("aveop38").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop38").selection().named("sel38");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_svc");
    model.component("comp1").cpl("intop1").selection().set(12);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_internal");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("sel40");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("sb_wtot", "198[mm]", "\u767e\u53f6\u7a97\u7bb1\u603b\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("sb_htot", "177[mm]", "\u767e\u53f6\u7a97\u7bb1\u603b\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("b_l", "8[mm]", "PVC \u7bb1\u5185\u90e8\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("b_L1", "15[mm]", "\u4e2d\u95f4 PVC \u7bb1\u7684\u5185\u90e8\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("b_L2", "20[mm]", "\u5927 PVC \u7bb1\u7684\u5185\u90e8\u957f\u5ea6");
    model.component("comp1").variable("var1").set("pvc_t", "1[mm]", "PVC \u539a\u5ea6");
    model.component("comp1").variable("var1")
         .set("p_w1", "100[mm]", "\u9694\u70ed\u677f\u7684\u7b2c\u4e00\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("p_w2", "36[mm]", "\u9694\u70ed\u677f\u7684\u7b2c\u4e8c\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("svc_w", "sb_wtot-(4*pvc_t+2*b_l+p_w2)", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("svc_h", "sb_htot-(2*pvc_t+b_l)", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("abs", "11[mm]", "\u7edd\u70ed\u8fb9\u754c\u504f\u79fb\u91cf");
    model.component("comp1").variable("var1").set("abl", "60[mm]", "\u7edd\u70ed\u8fb9\u754c\u957f\u5ea6");
    model.component("comp1").variable("var1")
         .set("sb_o", "15[mm]", "\u767e\u53f6\u7a97\u7bb1\u7684\u5f00\u53e3\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("A_svc", "int_svc(1)", "\u5fae\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("svc_w2", "w_resized(A_svc,svc_w,svc_h)", "\u5fae\u901a\u98ce\u8154\u7684\u7b49\u6548\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("svc_h2", "h_resized(A_svc,svc_w,svc_h)", "\u5fae\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("R_uc1", "1/(ha_2(b_l,max_uc1(T)-min_uc1(T))+hr(b_l,b_l,ave_uc1(T),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc2", "1/(ha_2(b_l,max_uc2(T)-min_uc2(T))+hr(b_l,b_L1,ave_uc2(T),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc3", "1/(ha_2(b_l,max_uc3(T)-min_uc3(T))+hr(b_l,b_L2,ave_uc3(T),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc4", "1/(ha_2(b_l,max_uc4(T)-min_uc4(T))+hr(b_l,b_L2,ave_uc4(T),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc5", "1/(ha_2(b_l,max_uc5(T)-min_uc5(T))+hr(b_l,b_L2,ave_uc5(T),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc6", "1/(ha_2(b_l,max_uc6(T)-min_uc6(T))+hr(b_l,b_L2,ave_uc6(T),epsilon,epsilon))", "\u7b2c\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc7", "1/(ha_2(b_l,max_uc7(T)-min_uc7(T))+hr(b_l,b_L2,ave_uc7(T),epsilon,epsilon))", "\u7b2c\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc8", "1/(ha_2(b_l,max_uc8(T)-min_uc8(T))+hr(b_l,b_L2,ave_uc8(T),epsilon,epsilon))", "\u7b2c\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc9", "1/(ha_2(b_l,max_uc9(T)-min_uc9(T))+hr(b_l,b_L1,ave_uc9(T),epsilon,epsilon))", "\u7b2c\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc10", "1/(ha_2(b_l,max_uc10(T)-min_uc10(T))+hr(b_l,b_l,ave_uc10(T),epsilon,epsilon))", "\u7b2c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc11", "1/(ha_2(b_L1,max_uc11(T)-min_uc11(T))+hr(b_L1,b_l,ave_uc11(T),epsilon,epsilon))", "\u7b2c\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc12", "1/(ha_2(b_L2,max_uc12(T)-min_uc12(T))+hr(b_L2,b_l,ave_uc12(T),epsilon,epsilon))", "\u7b2c\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc13", "1/(ha_2(b_L2,max_uc13(T)-min_uc13(T))+hr(b_L2,b_l,ave_uc13(T),epsilon,epsilon))", "\u7b2c\u5341\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc14", "1/(ha_2(b_L2,max_uc14(T)-min_uc14(T))+hr(b_L2,b_l,ave_uc14(T),epsilon,epsilon))", "\u7b2c\u5341\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc15", "1/(ha_2(b_L2,max_uc15(T)-min_uc15(T))+hr(b_L2,b_l,ave_uc15(T),epsilon,epsilon))", "\u7b2c\u5341\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc16", "1/(ha_2(b_L2,max_uc16(T)-min_uc16(T))+hr(b_L2,b_l,ave_uc16(T),epsilon,epsilon))", "\u7b2c\u5341\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc17", "1/(ha_2(b_L2,max_uc17(T)-min_uc17(T))+hr(b_L2,b_l,ave_uc17(T),epsilon,epsilon))", "\u7b2c\u5341\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc18", "1/(ha_2(b_L2,max_uc18(T)-min_uc18(T))+hr(b_L2,b_l,ave_uc18(T),epsilon,epsilon))", "\u7b2c\u5341\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc19", "1/(ha_2(b_L1,max_uc19(T)-min_uc19(T))+hr(b_L1,b_l,ave_uc19(T),epsilon,epsilon))", "\u7b2c\u5341\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc20", "1/(ha_2(b_l,max_uc20(T)-min_uc20(T))+hr(b_l,b_l,ave_uc20(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc21", "1/(ha_2(b_l,max_uc21(T)-min_uc21(T))+hr(b_l,b_L1,ave_uc21(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc22", "1/(ha_2(b_l,max_uc22(T)-min_uc22(T))+hr(b_l,b_L2,ave_uc22(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc23", "1/(ha_2(b_l,max_uc23(T)-min_uc23(T))+hr(b_l,b_L2,ave_uc23(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc24", "1/(ha_2(b_l,max_uc24(T)-min_uc24(T))+hr(b_l,b_L2,ave_uc24(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc25", "1/(ha_2(b_l,max_uc25(T)-min_uc25(T))+hr(b_l,b_L2,ave_uc25(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc26", "1/(ha_2(b_l,max_uc26(T)-min_uc26(T))+hr(b_l,b_L2,ave_uc26(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc27", "1/(ha_2(b_l,max_uc27(T)-min_uc27(T))+hr(b_l,b_L2,ave_uc27(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc28", "1/(ha_2(b_l,max_uc28(T)-min_uc28(T))+hr(b_l,b_L1,ave_uc28(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u516b\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc29", "1/(ha_2(b_l,max_uc29(T)-min_uc29(T))+hr(b_l,b_l,ave_uc29(T),epsilon,epsilon))", "\u7b2c\u4e8c\u5341\u4e5d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc30", "1/(ha_2(b_L1,max_uc30(T)-min_uc30(T))+hr(b_L1,b_l,ave_uc30(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc31", "1/(ha_2(b_L2,max_uc31(T)-min_uc31(T))+hr(b_L2,b_l,ave_uc31(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc32", "1/(ha_2(b_L2,max_uc32(T)-min_uc32(T))+hr(b_L2,b_l,ave_uc32(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc33", "1/(ha_2(b_L2,max_uc33(T)-min_uc33(T))+hr(b_L2,b_l,ave_uc33(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc34", "1/(ha_2(b_L2,max_uc34(T)-min_uc34(T))+hr(b_L2,b_l,ave_uc34(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc35", "1/(ha_2(b_L2,max_uc35(T)-min_uc35(T))+hr(b_L2,b_l,ave_uc35(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc36", "1/(ha_2(b_L2,max_uc36(T)-min_uc36(T))+hr(b_L2,b_l,ave_uc36(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u516d\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc37", "1/(ha_2(b_L2,max_uc37(T)-min_uc37(T))+hr(b_L2,b_l,ave_uc37(T),epsilon,epsilon))", "\u7b2c\u4e09\u5341\u4e03\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_svc", "1/(ha_2(svc_w2,max_svc(T)-min_svc(T))+hr(svc_w2,svc_h2,ave_svc(T),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1").set("L2D", "int_internal(ht.ntflux/(Te-Ti))");
    model.component("comp1").variable("var1").descr("L2D", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("PVC");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_pvc"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1390[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"1900[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u9694\u70ed\u677f");
    model.component("comp1").material("mat2").selection().set(20);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp1").material("mat3").selection().set(2);
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp1").material("mat4").selection().set(3);
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc2"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp1").material("mat5").selection().set(4);
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc3"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp1").material("mat6").selection().set(5);
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc4"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp1").material("mat7").selection().set(6);
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc5"});
    model.component("comp1").material("mat7").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").label("\u4e0d\u901a\u98ce\u7684\u8154 6");
    model.component("comp1").material("mat8").selection().set(7);
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc6"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").label("\u4e0d\u901a\u98ce\u7684\u8154 7");
    model.component("comp1").material("mat9").selection().set(8);
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc7"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat10", "Common");
    model.component("comp1").material("mat10").label("\u4e0d\u901a\u98ce\u7684\u8154 8");
    model.component("comp1").material("mat10").selection().set(9);
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc8"});
    model.component("comp1").material("mat10").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat11", "Common");
    model.component("comp1").material("mat11").label("\u4e0d\u901a\u98ce\u7684\u8154 9");
    model.component("comp1").material("mat11").selection().set(10);
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc9"});
    model.component("comp1").material("mat11").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat12", "Common");
    model.component("comp1").material("mat12").label("\u4e0d\u901a\u98ce\u7684\u8154 10");
    model.component("comp1").material("mat12").selection().set(11);
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc10"});
    model.component("comp1").material("mat12").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat13", "Common");
    model.component("comp1").material("mat13").label("\u4e0d\u901a\u98ce\u7684\u8154 11");
    model.component("comp1").material("mat13").selection().set(13);
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L1/R_uc11"});
    model.component("comp1").material("mat13").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat14", "Common");
    model.component("comp1").material("mat14").label("\u4e0d\u901a\u98ce\u7684\u8154 12");
    model.component("comp1").material("mat14").selection().set(15);
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc12"});
    model.component("comp1").material("mat14").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat15", "Common");
    model.component("comp1").material("mat15").label("\u4e0d\u901a\u98ce\u7684\u8154 13");
    model.component("comp1").material("mat15").selection().set(17);
    model.component("comp1").material("mat15").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc13"});
    model.component("comp1").material("mat15").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat15").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat16", "Common");
    model.component("comp1").material("mat16").label("\u4e0d\u901a\u98ce\u7684\u8154 14");
    model.component("comp1").material("mat16").selection().set(19);
    model.component("comp1").material("mat16").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc14"});
    model.component("comp1").material("mat16").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat16").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat17", "Common");
    model.component("comp1").material("mat17").label("\u4e0d\u901a\u98ce\u7684\u8154 15");
    model.component("comp1").material("mat17").selection().set(22);
    model.component("comp1").material("mat17").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc15"});
    model.component("comp1").material("mat17").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat17").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat18", "Common");
    model.component("comp1").material("mat18").label("\u4e0d\u901a\u98ce\u7684\u8154 16");
    model.component("comp1").material("mat18").selection().set(24);
    model.component("comp1").material("mat18").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc16"});
    model.component("comp1").material("mat18").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat18").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat19", "Common");
    model.component("comp1").material("mat19").label("\u4e0d\u901a\u98ce\u7684\u8154 17");
    model.component("comp1").material("mat19").selection().set(26);
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc17"});
    model.component("comp1").material("mat19").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat20", "Common");
    model.component("comp1").material("mat20").label("\u4e0d\u901a\u98ce\u7684\u8154 18");
    model.component("comp1").material("mat20").selection().set(28);
    model.component("comp1").material("mat20").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc18"});
    model.component("comp1").material("mat20").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat20").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat21", "Common");
    model.component("comp1").material("mat21").label("\u4e0d\u901a\u98ce\u7684\u8154 19");
    model.component("comp1").material("mat21").selection().set(30);
    model.component("comp1").material("mat21").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L1/R_uc19"});
    model.component("comp1").material("mat21").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat21").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat22", "Common");
    model.component("comp1").material("mat22").label("\u4e0d\u901a\u98ce\u7684\u8154 20");
    model.component("comp1").material("mat22").selection().set(40);
    model.component("comp1").material("mat22").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc20"});
    model.component("comp1").material("mat22").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat22").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat23", "Common");
    model.component("comp1").material("mat23").label("\u4e0d\u901a\u98ce\u7684\u8154 21");
    model.component("comp1").material("mat23").selection().set(39);
    model.component("comp1").material("mat23").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc21"});
    model.component("comp1").material("mat23").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat23").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat24", "Common");
    model.component("comp1").material("mat24").label("\u4e0d\u901a\u98ce\u7684\u8154 22");
    model.component("comp1").material("mat24").selection().set(38);
    model.component("comp1").material("mat24").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc22"});
    model.component("comp1").material("mat24").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat24").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat25", "Common");
    model.component("comp1").material("mat25").label("\u4e0d\u901a\u98ce\u7684\u8154 23");
    model.component("comp1").material("mat25").selection().set(37);
    model.component("comp1").material("mat25").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc23"});
    model.component("comp1").material("mat25").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat25").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat26", "Common");
    model.component("comp1").material("mat26").label("\u4e0d\u901a\u98ce\u7684\u8154 24");
    model.component("comp1").material("mat26").selection().set(36);
    model.component("comp1").material("mat26").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc24"});
    model.component("comp1").material("mat26").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat26").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat27", "Common");
    model.component("comp1").material("mat27").label("\u4e0d\u901a\u98ce\u7684\u8154 25");
    model.component("comp1").material("mat27").selection().set(35);
    model.component("comp1").material("mat27").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc25"});
    model.component("comp1").material("mat27").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat27").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat28", "Common");
    model.component("comp1").material("mat28").label("\u4e0d\u901a\u98ce\u7684\u8154 26");
    model.component("comp1").material("mat28").selection().set(34);
    model.component("comp1").material("mat28").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc26"});
    model.component("comp1").material("mat28").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat28").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat29", "Common");
    model.component("comp1").material("mat29").label("\u4e0d\u901a\u98ce\u7684\u8154 27");
    model.component("comp1").material("mat29").selection().set(33);
    model.component("comp1").material("mat29").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc27"});
    model.component("comp1").material("mat29").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat29").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat30", "Common");
    model.component("comp1").material("mat30").label("\u4e0d\u901a\u98ce\u7684\u8154 28");
    model.component("comp1").material("mat30").selection().set(32);
    model.component("comp1").material("mat30").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc28"});
    model.component("comp1").material("mat30").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat30").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat31", "Common");
    model.component("comp1").material("mat31").label("\u4e0d\u901a\u98ce\u7684\u8154 29");
    model.component("comp1").material("mat31").selection().set(31);
    model.component("comp1").material("mat31").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_l/R_uc29"});
    model.component("comp1").material("mat31").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat31").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat32", "Common");
    model.component("comp1").material("mat32").label("\u4e0d\u901a\u98ce\u7684\u8154 30");
    model.component("comp1").material("mat32").selection().set(29);
    model.component("comp1").material("mat32").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L1/R_uc30"});
    model.component("comp1").material("mat32").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat32").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat33", "Common");
    model.component("comp1").material("mat33").label("\u4e0d\u901a\u98ce\u7684\u8154 31");
    model.component("comp1").material("mat33").selection().set(27);
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc31"});
    model.component("comp1").material("mat33").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat34", "Common");
    model.component("comp1").material("mat34").label("\u4e0d\u901a\u98ce\u7684\u8154 32");
    model.component("comp1").material("mat34").selection().set(25);
    model.component("comp1").material("mat34").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc32"});
    model.component("comp1").material("mat34").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat34").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat35", "Common");
    model.component("comp1").material("mat35").label("\u4e0d\u901a\u98ce\u7684\u8154 33");
    model.component("comp1").material("mat35").selection().set(23);
    model.component("comp1").material("mat35").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc33"});
    model.component("comp1").material("mat35").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat35").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat36", "Common");
    model.component("comp1").material("mat36").label("\u4e0d\u901a\u98ce\u7684\u8154 34");
    model.component("comp1").material("mat36").selection().set(21);
    model.component("comp1").material("mat36").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc34"});
    model.component("comp1").material("mat36").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat36").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat37", "Common");
    model.component("comp1").material("mat37").label("\u4e0d\u901a\u98ce\u7684\u8154 35");
    model.component("comp1").material("mat37").selection().set(18);
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc35"});
    model.component("comp1").material("mat37").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat38", "Common");
    model.component("comp1").material("mat38").label("\u4e0d\u901a\u98ce\u7684\u8154 36");
    model.component("comp1").material("mat38").selection().set(16);
    model.component("comp1").material("mat38").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc36"});
    model.component("comp1").material("mat38").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat38").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat39", "Common");
    model.component("comp1").material("mat39").label("\u4e0d\u901a\u98ce\u7684\u8154 37");
    model.component("comp1").material("mat39").selection().set(14);
    model.component("comp1").material("mat39").propertyGroup("def")
         .set("thermalconductivity", new String[]{"b_L2/R_uc37"});
    model.component("comp1").material("mat39").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat39").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat40", "Common");
    model.component("comp1").material("mat40").label("\u5fae\u901a\u98ce\u8154");
    model.component("comp1").material("mat40").selection().set(12);
    model.component("comp1").material("mat40").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_w/R_svc"});
    model.component("comp1").material("mat40").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat40").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("PVC \u767e\u53f6\u7a97\u8f6e\u5ed3");

    model.component("comp2").physics().create("ht2", "HeatTransferInSolidsAndFluids", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").geom("geom2").run();
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1")
         .set("filename", "roller_shutter_thermal_performances_profile.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("ige1", "IgnoreEdges");
    model.component("comp2").geom("geom2").feature("ige1").selection("input")
         .set("fin", 4, 6, 12, 13, 18, 19, 23, 24, 29, 31, 35, 39, 43, 48, 49, 54, 55, 60, 61);
    model.component("comp2").geom("geom2").run("ige1");

    model.component("comp2").selection().create("sel42", "Explicit");
    model.component("comp2").selection("sel42").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp2").selection("sel42").geom(1);
    model.component("comp2").selection("sel42").set(4, 5, 6, 7);
    model.component("comp2").selection().create("sel43", "Explicit");
    model.component("comp2").selection("sel43").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp2").selection("sel43").geom(1);
    model.component("comp2").selection("sel43").set(8, 9, 10, 11);
    model.component("comp2").selection().create("sel44", "Explicit");
    model.component("comp2").selection("sel44").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp2").selection("sel44").geom(1);
    model.component("comp2").selection("sel44").set(28, 29, 30, 31);
    model.component("comp2").selection().create("sel45", "Explicit");
    model.component("comp2").selection("sel45").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp2").selection("sel45").geom(1);
    model.component("comp2").selection("sel45").set(32, 33, 34, 35);
    model.component("comp2").selection().create("sel46", "Explicit");
    model.component("comp2").selection("sel46").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp2").selection("sel46").geom(1);
    model.component("comp2").selection("sel46").set(18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.component("comp2").selection().create("sel47", "Explicit");
    model.component("comp2").selection("sel47").label("\u5185\u4fa7");
    model.component("comp2").selection("sel47").geom(1);
    model.component("comp2").selection("sel47").set(3, 17);
    model.component("comp2").selection().create("sel48", "Explicit");
    model.component("comp2").selection("sel48").label("\u5916\u4fa7");
    model.component("comp2").selection("sel48").geom(1);
    model.component("comp2").selection("sel48").set(2, 13);

    model.component("comp2").cpl().create("maxop39", "Maximum");
    model.component("comp2").cpl("maxop39").set("opname", "max_uc1");
    model.component("comp2").cpl("maxop39").selection().set(2);
    model.component("comp2").cpl().create("minop39", "Minimum");
    model.component("comp2").cpl("minop39").set("opname", "min_uc1");
    model.component("comp2").cpl("minop39").selection().set(2);
    model.component("comp2").cpl().create("aveop39", "Average");
    model.component("comp2").cpl("aveop39").set("axisym", true);
    model.component("comp2").cpl("aveop39").set("opname", "ave_uc1");
    model.component("comp2").cpl("aveop39").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop39").selection().named("sel42");
    model.component("comp2").cpl().create("maxop40", "Maximum");
    model.component("comp2").cpl("maxop40").set("opname", "max_uc2");
    model.component("comp2").cpl("maxop40").selection().set(3);
    model.component("comp2").cpl().create("minop40", "Minimum");
    model.component("comp2").cpl("minop40").set("opname", "min_uc2");
    model.component("comp2").cpl("minop40").selection().set(3);
    model.component("comp2").cpl().create("aveop40", "Average");
    model.component("comp2").cpl("aveop40").set("axisym", true);
    model.component("comp2").cpl("aveop40").set("opname", "ave_uc2");
    model.component("comp2").cpl("aveop40").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop40").selection().named("sel43");
    model.component("comp2").cpl().create("maxop41", "Maximum");
    model.component("comp2").cpl("maxop41").set("opname", "max_uc3");
    model.component("comp2").cpl("maxop41").selection().set(6);
    model.component("comp2").cpl().create("minop41", "Minimum");
    model.component("comp2").cpl("minop41").set("opname", "min_uc3");
    model.component("comp2").cpl("minop41").selection().set(6);
    model.component("comp2").cpl().create("aveop41", "Average");
    model.component("comp2").cpl("aveop41").set("axisym", true);
    model.component("comp2").cpl("aveop41").set("opname", "ave_uc3");
    model.component("comp2").cpl("aveop41").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop41").selection().named("sel44");
    model.component("comp2").cpl().create("maxop42", "Maximum");
    model.component("comp2").cpl("maxop42").set("opname", "max_uc4");
    model.component("comp2").cpl("maxop42").selection().set(7);
    model.component("comp2").cpl().create("minop42", "Minimum");
    model.component("comp2").cpl("minop42").set("opname", "min_uc4");
    model.component("comp2").cpl("minop42").selection().set(7);
    model.component("comp2").cpl().create("aveop42", "Average");
    model.component("comp2").cpl("aveop42").set("axisym", true);
    model.component("comp2").cpl("aveop42").set("opname", "ave_uc4");
    model.component("comp2").cpl("aveop42").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop42").selection().named("sel45");
    model.component("comp2").cpl().create("aveop43", "Average");
    model.component("comp2").cpl("aveop43").set("axisym", true);
    model.component("comp2").cpl("aveop43").set("opname", "ave_uc5");
    model.component("comp2").cpl("aveop43").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop43").selection().named("sel46");
    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").set("opname", "int_uc5");
    model.component("comp2").cpl("intop3").selection().set(5);
    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").set("opname", "int_internal");
    model.component("comp2").cpl("intop4").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop4").selection().named("sel47");

    model.component("comp2").variable().create("var2");

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2").set("s_htot", "7[mm]", "\u767e\u53f6\u7a97\u603b\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("s_wtot", "57[mm]", "\u767e\u53f6\u7a97\u603b\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_h", "5[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_w", "12[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc2_h", "5[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc2_w", "12[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc3_h", "5[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc3_w", "12[mm]", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc4_h", "5[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc4_w", "12[mm]", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc5_h", "5[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc5_w", "3[mm]", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2").set("pvc_t", "1[mm]", "PVC \u539a\u5ea6");
    model.component("comp2").variable("var2")
         .set("A_uc5", "int_uc5(1)", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9762\u79ef");
    model.component("comp2").variable("var2")
         .set("uc5_h2", "h_resized(A_uc5,uc5_h,uc5_w)", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u7b49\u6548\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("R_uc1", "1/(ha_2(uc1_h,max_uc1(T2)-min_uc1(T2))+hr(uc1_h,uc1_w,ave_uc1(T2),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc2", "1/(ha_2(uc2_h,max_uc2(T2)-min_uc2(T2))+hr(uc2_h,uc2_w,ave_uc2(T2),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc3", "1/(ha_2(uc3_h,max_uc3(T2)-min_uc3(T2))+hr(uc3_h,uc3_w,ave_uc3(T2),epsilon,epsilon))", "\u7b2c\u4e09\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc4", "1/(ha_2(uc4_h,max_uc4(T2)-min_uc4(T2))+hr(uc4_h,uc4_w,ave_uc4(T2),epsilon,epsilon))", "\u7b2c\u56db\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc5", "1/(ha_1(uc5_h2)+hr(uc5_h,uc5_w,ave_uc5(T2),epsilon,epsilon))", "\u7b2c\u4e94\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("L2D", "int_internal(ht2.ntflux/(Te-Ti))", "\u6846\u67b6\u7684\u70ed\u5bfc\u7387");

    model.component("comp2").material().create("mat41", "Common");
    model.component("comp2").material("mat41").label("PVC");
    model.component("comp2").material("mat41").selection().set(1, 4);
    model.component("comp2").material("mat41").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_pvc"});
    model.component("comp2").material("mat41").propertyGroup("def").set("density", new String[]{"1390[kg/m^3]"});
    model.component("comp2").material("mat41").propertyGroup("def")
         .set("heatcapacity", new String[]{"1900[J/(kg*K)]"});
    model.component("comp2").material().create("mat42", "Common");
    model.component("comp2").material("mat42").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp2").material("mat42").selection().set(2);
    model.component("comp2").material("mat42").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h/R_uc1"});
    model.component("comp2").material("mat42").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat42").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat43", "Common");
    model.component("comp2").material("mat43").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp2").material("mat43").selection().set(3);
    model.component("comp2").material("mat43").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp2").material("mat43").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat43").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat44", "Common");
    model.component("comp2").material("mat44").label("\u4e0d\u901a\u98ce\u7684\u8154 3");
    model.component("comp2").material("mat44").selection().set(6);
    model.component("comp2").material("mat44").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc3_h/R_uc3"});
    model.component("comp2").material("mat44").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat44").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat45", "Common");
    model.component("comp2").material("mat45").label("\u4e0d\u901a\u98ce\u7684\u8154 4");
    model.component("comp2").material("mat45").selection().set(7);
    model.component("comp2").material("mat45").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc4_h/R_uc4"});
    model.component("comp2").material("mat45").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat45").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat46", "Common");
    model.component("comp2").material("mat46").label("\u4e0d\u901a\u98ce\u7684\u8154 5");
    model.component("comp2").material("mat46").selection().set(5);
    model.component("comp2").material("mat46").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc5_h2/R_uc5"});
    model.component("comp2").material("mat46").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat46").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-6");

    model.result().dataset().remove("dset1");
    model.result().dataset().remove("dset2");

    model.title("\u53c2\u6570\u5316\u5377\u5e18\u767e\u53f6\u7a97 - \u9884\u8bbe\u6a21\u578b");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u5377\u5e18\u767e\u53f6\u7a97\u70ed\u6027\u80fd\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("roller_shutter_thermal_performances_preset.mph");

    model.component("comp1").physics("ht").feature("fluid1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", 2);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel39");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "1/Rse");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Te");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().named("sel40");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "1/Rsi");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "Ti");

    model.study("std1").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u70ed\u5c5e\u6027\uff0c\u5377\u5e18\u767e\u53f6\u7a97\u7bb1");
    model.result().numerical("gev1").setIndex("expr", "L2D", 0);
    model.result().numerical("gev1").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev1").setIndex("expr", "L2D/sb_htot", 1);
    model.result().numerical("gev1").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70ed\u5c5e\u6027\uff0c\u5377\u5e18\u767e\u53f6\u7a97\u7bb1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "\u00b0C");
    model.result("pg1").run();

    model.component("comp2").physics("ht2").feature("fluid1").selection().set(2, 3, 5, 6, 7);
    model.component("comp2").physics("ht2").prop("ShapeProperty").set("order_temperature", 2);
    model.component("comp2").physics("ht2").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht2").feature("hf1").selection().named("sel48");
    model.component("comp2").physics("ht2").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf1").set("h", "1/Rse");
    model.component("comp2").physics("ht2").feature("hf1").set("Text", "Te");
    model.component("comp2").physics("ht2").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht2").feature("hf2").selection().named("sel47");
    model.component("comp2").physics("ht2").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf2").set("h", "1/Rsi");
    model.component("comp2").physics("ht2").feature("hf2").set("Text", "Ti");

    model.study("std2").createAutoSequences("all");

    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol1");
    model.result().dataset("dset3").set("comp", "none");
    model.result().dataset("dset3").set("geom", "geom1");
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").set("solution", "sol1");
    model.result().dataset("dset4").set("geom", "geom2");
    model.result().dataset("dset4").set("comp", "comp2");
    model.result().dataset().move("dset3", 2);
    model.result().dataset("dset3").tag("dset3");
    model.result().dataset().move("dset4", 3);
    model.result().dataset("dset4").tag("dset4");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht2)");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u70ed\u5c5e\u6027\uff0cPVC \u767e\u53f6\u7a97\u8f6e\u5ed3");
    model.result().numerical("gev2").set("data", "dset4");
    model.result().numerical("gev2").setIndex("expr", "L2D", 0);
    model.result().numerical("gev2").setIndex("descr", "\u622a\u9762\u7684\u70ed\u5bfc\u7387 (L2D)", 0);
    model.result().numerical("gev2").setIndex("expr", "L2D/s_wtot", 1);
    model.result().numerical("gev2").setIndex("descr", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387 (Uf)", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u70ed\u5c5e\u6027\uff0cPVC \u767e\u53f6\u7a97\u8f6e\u5ed3");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "\u00b0C");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5377\u5e18\u767e\u53f6\u7a97\u70ed\u6027\u80fd");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u53ef\u4ee5\u91cd\u73b0\u6709\u5173\u5377\u5e18\u767e\u53f6\u7a97\u7684 ISO 10077-2:2012 \u6807\u51c6\u7684\u4e24\u4e2a\u6d4b\u8bd5\u6848\u4f8b\u3002\u6bcf\u4e2a\u767e\u53f6\u7a97\u7684\u70ed\u6027\u80fd\u901a\u8fc7\u70ed\u4f20\u5bfc\u7387\u548c\u70ed\u900f\u5c04\u7387\u6765\u8ba1\u7b97\u3002\u6700\u540e\uff0c\u5c06\u7ed3\u679c\u4e0e\u7ed9\u5b9a\u7684\u9a8c\u8bc1\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("roller_shutter_thermal_performances.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
