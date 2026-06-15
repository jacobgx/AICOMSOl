/*
 * window_and_glazing_thermal_performances_preset.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:20 by COMSOL 6.3.0.290. */
public class window_and_glazing_thermal_performances_preset {

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

    model.component("comp1").label("\u4f7f\u7528\u9694\u70ed\u677f\u7684\u7a97\u6237");

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
    model.func("an1").set("funcname", "ha");
    model.func("an1").set("expr", "max(C1[(m*K)/W]/d,C2[(m^2*K)/W]*delta^(1/3))");
    model.func("an1").set("args", "d, delta");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").setIndex("argunit", "K", 1);
    model.func("an1").set("fununit", "W/(m^2*K)");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "hr");
    model.func("an2").set("expr", "4*sigma_const[(K^4*m^2)/W]*Tm^3/(1/e1+1/e2-1)*(1+sqrt(1+(d/b)^2)-d/b)/2");
    model.func("an2").set("args", "d, b, Tm, e1, e2");
    model.func("an2").setIndex("argunit", "m", 0);
    model.func("an2").setIndex("argunit", "m", 1);
    model.func("an2").setIndex("argunit", "K", 2);
    model.func("an2").setIndex("argunit", 1, 3);
    model.func("an2").setIndex("argunit", 1, 4);
    model.func("an2").set("fununit", "W/(m^2*K)");

    model.component("comp1").geom("geom1")
         .insertFile("window_and_glazing_thermal_performances_insulation_panel_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel4");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").set("opname", "max_uc1");
    model.component("comp1").cpl("maxop1").selection().named("geom1_r4_dom");
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").set("opname", "min_uc1");
    model.component("comp1").cpl("minop1").selection().named("geom1_r4_dom");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "ave_uc1");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_adjsel1");
    model.component("comp1").cpl().create("maxop2", "Maximum");
    model.component("comp1").cpl("maxop2").set("opname", "max_uc2");
    model.component("comp1").cpl("maxop2").selection().named("geom1_r8_dom");
    model.component("comp1").cpl().create("minop2", "Minimum");
    model.component("comp1").cpl("minop2").set("opname", "min_uc2");
    model.component("comp1").cpl("minop2").selection().named("geom1_r8_dom");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "ave_uc2");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("geom1_adjsel2");
    model.component("comp1").cpl().create("maxop3", "Maximum");
    model.component("comp1").cpl("maxop3").set("opname", "max_svc");
    model.component("comp1").cpl("maxop3").selection().named("geom1_r6_dom");
    model.component("comp1").cpl().create("minop3", "Minimum");
    model.component("comp1").cpl("minop3").set("opname", "min_svc");
    model.component("comp1").cpl("minop3").selection().named("geom1_r6_dom");
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").set("opname", "ave_svc");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop3").selection().named("geom1_sel1");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_internal");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("geom1_unisel1");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("b1", "5[mm]", "\u95f4\u9699");
    model.component("comp1").variable("var1").set("b2", "15[mm]", "\u91cd\u53e0");
    model.component("comp1").variable("var1").set("bp", "190[mm]", "\u677f\u7684\u53ef\u89c1\u957f\u5ea6");
    model.component("comp1").variable("var1").set("dp", "28[mm]", "\u9694\u70ed\u677f\u539a\u5ea6");
    model.component("comp1").variable("var1").set("bf", "110[mm]", "\u6846\u67b6\u5bbd\u5ea6");
    model.component("comp1").variable("var1").set("df", "83[mm]", "\u6846\u67b6\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb1_wb", "63[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u5e95\u90e8\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb1_wt", "42[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u9876\u90e8\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb1_hl", "66[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u5de6\u4fa7\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb1_hr", "15[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u53f3\u4fa7\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc1_w", "6[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc1_h", "54[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1").set("ib_h", "3[mm]", "\u9694\u70ed\u5757\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("ib1_w", "16[mm]", "\u7b2c\u4e00\u4e2a\u9694\u70ed\u5757\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("ib_w", "15[mm]", "\u5176\u4ed6\u9694\u70ed\u5757\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1").set("svc_w", "5[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp1").variable("var1").set("svc_h", "18[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb2_wb", "42[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u5e95\u90e8\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb2_wt", "84[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u9876\u90e8\u5bbd\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb2_hr", "34[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u53f3\u4fa7\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("wb2_hl", "14[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u5de6\u4fa7\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("uc2_h", "34[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp1").variable("var1")
         .set("Rp", "dp/lambda_p", "\u9694\u70ed\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("Up_n", "1/(Rp+Rsi_n+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp1").variable("var1")
         .set("Up_p", "1/(Rp+Rsi_p+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u963b\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp1").variable("var1")
         .set("Up", "(Up_p*30[mm]+Up_n*(bp-30[mm]))/bp", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp1").variable("var1")
         .set("R_uc1", "1/(ha(uc1_h,max_uc1(T)-min_uc1(T))+hr(uc1_h,uc1_w,ave_uc1(T),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_uc2", "1/(ha(uc2_h,max_uc2(T)-min_uc2(T))+hr(uc2_h,b1,ave_uc2(T),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp1").variable("var1")
         .set("R_svc", "1/(ha(svc_h,max_svc(T)-min_svc(T))+hr(svc_h,svc_w,ave_svc(T),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u8f6f\u6728");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.13[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("EPDM");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u9694\u70ed\u677f");
    model.component("comp1").material("mat3").selection().named("geom1_r10_dom");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_p"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"50[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"1030[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp1").material("mat4").selection().named("geom1_r4_dom");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h/R_uc1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp1").material("mat5").selection().named("geom1_r8_dom");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("\u5fae\u901a\u98ce\u8154");
    model.component("comp1").material("mat6").selection().named("geom1_r6_dom");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_h/R_svc"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("\u4f7f\u7528\u73bb\u7483\u7684\u7a97\u6237");

    model.component("comp2").geom("geom2")
         .insertFile("window_and_glazing_thermal_performances_glazing_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").run("sel8");

    model.component("comp2").physics().create("ht2", "HeatTransferInSolidsAndFluids", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").cpl().create("maxop4", "Maximum");
    model.component("comp2").cpl("maxop4").set("opname", "max_uc1");
    model.component("comp2").cpl("maxop4").selection().named("geom2_r4_dom");
    model.component("comp2").cpl().create("minop4", "Minimum");
    model.component("comp2").cpl("minop4").set("opname", "min_uc1");
    model.component("comp2").cpl("minop4").selection().named("geom2_r4_dom");
    model.component("comp2").cpl().create("aveop4", "Average");
    model.component("comp2").cpl("aveop4").set("axisym", true);
    model.component("comp2").cpl("aveop4").set("opname", "ave_uc1");
    model.component("comp2").cpl("aveop4").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop4").selection().named("geom2_adjsel1");
    model.component("comp2").cpl().create("maxop5", "Maximum");
    model.component("comp2").cpl("maxop5").set("opname", "max_uc2");
    model.component("comp2").cpl("maxop5").selection().named("geom2_r8_dom");
    model.component("comp2").cpl().create("minop5", "Minimum");
    model.component("comp2").cpl("minop5").set("opname", "min_uc2");
    model.component("comp2").cpl("minop5").selection().named("geom2_r8_dom");
    model.component("comp2").cpl().create("aveop5", "Average");
    model.component("comp2").cpl("aveop5").set("axisym", true);
    model.component("comp2").cpl("aveop5").set("opname", "ave_uc2");
    model.component("comp2").cpl("aveop5").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop5").selection().named("geom2_adjsel2");
    model.component("comp2").cpl().create("maxop6", "Maximum");
    model.component("comp2").cpl("maxop6").set("opname", "max_svc");
    model.component("comp2").cpl("maxop6").selection().named("geom2_r6_dom");
    model.component("comp2").cpl().create("minop6", "Minimum");
    model.component("comp2").cpl("minop6").set("opname", "min_svc");
    model.component("comp2").cpl("minop6").selection().named("geom2_r6_dom");
    model.component("comp2").cpl().create("aveop6", "Average");
    model.component("comp2").cpl("aveop6").set("axisym", true);
    model.component("comp2").cpl("aveop6").set("opname", "ave_svc");
    model.component("comp2").cpl("aveop6").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop6").selection().named("geom2_sel1");
    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").set("opname", "int_internal");
    model.component("comp2").cpl("intop2").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop2").selection().named("geom2_unisel1");

    model.component("comp2").variable().create("var2");

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2").set("b1", "5[mm]", "\u95f4\u9699");
    model.component("comp2").variable("var2").set("b2", "15[mm]", "\u91cd\u53e0");
    model.component("comp2").variable("var2").set("bg", "190[mm]", "\u73bb\u7483\u7684\u53ef\u89c1\u957f\u5ea6");
    model.component("comp2").variable("var2").set("dg", "28[mm]", "\u73bb\u7483\u539a\u5ea6");
    model.component("comp2").variable("var2").set("bf", "110[mm]", "\u6846\u67b6\u5bbd\u5ea6");
    model.component("comp2").variable("var2").set("df", "83[mm]", "\u6846\u67b6\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_wb", "63[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u5e95\u90e8\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_wt", "42[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u9876\u90e8\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_hl", "66[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u5de6\u4fa7\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb1_hr", "15[mm]", "\u7b2c\u4e00\u4e2a\u6728\u5757\u53f3\u4fa7\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_w", "6[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc1_h", "54[mm]", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("ib_h", "3[mm]", "\u9694\u70ed\u5757\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("ib1_w", "16[mm]", "\u7b2c\u4e00\u4e2a\u9694\u70ed\u5757\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("ib_w", "15[mm]", "\u5176\u4ed6\u9694\u70ed\u5757\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2").set("svc_w", "5[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u5bbd\u5ea6");
    model.component("comp2").variable("var2").set("svc_h", "18[mm]", "\u5fae\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_wb", "42[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u5e95\u90e8\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_wt", "84[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u9876\u90e8\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_hr", "34[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u53f3\u4fa7\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("wb2_hl", "14[mm]", "\u7b2c\u4e8c\u4e2a\u6728\u5757\u5de6\u4fa7\u9ad8\u5ea6");
    model.component("comp2").variable("var2")
         .set("uc2_h", "34[mm]", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("a_t", "0.5[mm]", "\u94dd\u5c42\u539a\u5ea6");
    model.component("comp2").variable("var2").set("p_w", "3[mm]", "\u591a\u786b\u5316\u7269\u5757\u5bbd\u5ea6");
    model.component("comp2").variable("var2").set("g_t", "4[mm]", "\u73bb\u7483\u539a\u5ea6");
    model.component("comp2").variable("var2").set("sg_h", "19[mm]", "\u7845\u80f6\u5757\u9ad8\u5ea6");
    model.component("comp2").variable("var2").set("sg_w", "9[mm]", "\u7845\u80f6\u5757\u5bbd\u5ea6");
    model.component("comp2").variable("var2")
         .set("Rp", "dg/lambda_p", "\u9694\u70ed\u677f\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("Up_n", "1/(Rp+Rsi_n+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387\uff08\u5e38\u89c4\uff09");
    model.component("comp2").variable("var2")
         .set("Up_p", "1/(Rp+Rsi_p+Rse)", "\u9694\u70ed\u677f\u7684\u70ed\u963b\uff08\u53d7\u4fdd\u62a4\uff09");
    model.component("comp2").variable("var2")
         .set("Up", "(Up_p*30[mm]+Up_n*(bg-30[mm]))/bg", "\u9694\u70ed\u677f\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp2").variable("var2")
         .set("Ug", "1.3[W/(m^2*K)]", "\u73bb\u7483\u4e2d\u5fc3\u533a\u57df\u7684\u70ed\u900f\u5c04\u7387");
    model.component("comp2").variable("var2")
         .set("Uf", "1.375[W/(m^2*K)]", "\u6846\u67b6\u7684\u70ed\u900f\u5c04\u7387\uff08\u7b2c\u4e00\u4e2a\u6a21\u578b\u7684 c.f \u7ed3\u679c\uff09");
    model.component("comp2").variable("var2")
         .set("R_uc1", "1/(ha(uc1_h,max_uc1(T2)-min_uc1(T2))+hr(uc1_h,uc1_w,ave_uc1(T2),epsilon,epsilon))", "\u7b2c\u4e00\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_uc2", "1/(ha(uc2_h,max_uc2(T2)-min_uc2(T2))+hr(uc2_h,b1,ave_uc2(T2),epsilon,epsilon))", "\u7b2c\u4e8c\u4e2a\u4e0d\u901a\u98ce\u8154\u7684\u70ed\u963b");
    model.component("comp2").variable("var2")
         .set("R_svc", "1/(ha(svc_h,max_svc(T2)-min_svc(T2))+hr(svc_h,svc_w,ave_svc(T2),epsilon,epsilon))", "\u5fae\u901a\u98ce\u8154\u7684\u70ed\u963b");

    model.component("comp2").material().create("mat7", "Common");
    model.component("comp2").material("mat7").label("\u8f6f\u6728");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.13[W/(m*K)]"});
    model.component("comp2").material("mat7").propertyGroup("def").set("density", new String[]{"500[kg/m^3]"});
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp2").material().create("mat8", "Common");
    model.component("comp2").material("mat8").label("EPDM");
    model.component("comp2").material("mat8").selection().named("geom2_csel1_dom");
    model.component("comp2").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp2").material("mat8").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp2").material("mat8").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp2").material().create("mat9", "Common");
    model.component("comp2").material("mat9").label("\u73bb\u7483");
    model.component("comp2").material("mat9").selection().named("geom2_sel5");
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.00[W/(m*K)]"});
    model.component("comp2").material("mat9").propertyGroup("def").set("density", new String[]{"2500[kg/m^3]"});
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("heatcapacity", new String[]{"750[J/(kg*K)]"});
    model.component("comp2").material().create("mat10", "Common");
    model.component("comp2").material("mat10").label("\u94dd");
    model.component("comp2").material("mat10").selection().named("geom2_sel7");
    model.component("comp2").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"160[W/(m*K)]"});
    model.component("comp2").material("mat10").propertyGroup("def").set("density", new String[]{"2800[kg/m^3]"});
    model.component("comp2").material("mat10").propertyGroup("def")
         .set("heatcapacity", new String[]{"880[J/(kg*K)]"});
    model.component("comp2").material().create("mat11", "Common");
    model.component("comp2").material("mat11").label("\u591a\u786b\u5316\u7269");
    model.component("comp2").material("mat11").selection().named("geom2_sel8");
    model.component("comp2").material("mat11").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.40[W/(m*K)]"});
    model.component("comp2").material("mat11").propertyGroup("def").set("density", new String[]{"1700[kg/m^3]"});
    model.component("comp2").material("mat11").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp2").material().create("mat12", "Common");
    model.component("comp2").material("mat12").label("\u7845\u80f6");
    model.component("comp2").material("mat12").selection().named("geom2_r12_dom");
    model.component("comp2").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.13[W/(m*K)]"});
    model.component("comp2").material("mat12").propertyGroup("def").set("density", new String[]{"720[kg/m^3]"});
    model.component("comp2").material("mat12").propertyGroup("def")
         .set("heatcapacity", new String[]{"1000[J/(kg*K)]"});
    model.component("comp2").material().create("mat13", "Common");
    model.component("comp2").material("mat13").label("\u805a\u9170\u80fa");
    model.component("comp2").material("mat13").selection().named("geom2_r13_dom");
    model.component("comp2").material("mat13").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp2").material("mat13").propertyGroup("def").set("density", new String[]{"1150[kg/m^3]"});
    model.component("comp2").material("mat13").propertyGroup("def")
         .set("heatcapacity", new String[]{"1600[J/(kg*K)]"});
    model.component("comp2").material().create("mat14", "Common");
    model.component("comp2").material("mat14").label("\u586b\u5145\u6c14\u4f53");
    model.component("comp2").material("mat14").selection().named("geom2_sel6");
    model.component("comp2").material("mat14").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.034[W/(m*K)]"});
    model.component("comp2").material("mat14").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat14").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat15", "Common");
    model.component("comp2").material("mat15").label("\u4e0d\u901a\u98ce\u7684\u8154 1");
    model.component("comp2").material("mat15").selection().named("geom2_r4_dom");
    model.component("comp2").material("mat15").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc1_h/R_uc1"});
    model.component("comp2").material("mat15").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat15").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat16", "Common");
    model.component("comp2").material("mat16").label("\u4e0d\u901a\u98ce\u7684\u8154 2");
    model.component("comp2").material("mat16").selection().named("geom2_r8_dom");
    model.component("comp2").material("mat16").propertyGroup("def")
         .set("thermalconductivity", new String[]{"uc2_h/R_uc2"});
    model.component("comp2").material("mat16").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat16").propertyGroup("def")
         .set("heatcapacity", new String[]{"1008[J/(kg*K)]"});
    model.component("comp2").material().create("mat17", "Common");
    model.component("comp2").material("mat17").label("\u5fae\u901a\u98ce\u8154");
    model.component("comp2").material("mat17").selection().named("geom2_r6_dom");
    model.component("comp2").material("mat17").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2*svc_h/R_svc"});
    model.component("comp2").material("mat17").propertyGroup("def").set("density", new String[]{"1.23[kg/m^3]"});
    model.component("comp2").material("mat17").propertyGroup("def")
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

    model.title("\u53c2\u6570\u5316\u7a97\u6237\u548c\u73bb\u7483 - \u9884\u8bbe\u6a21\u578b");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u73bb\u7483\u5bf9\u7a97\u6237\u70ed\u6027\u80fd\u7684\u5f71\u54cd\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("window_and_glazing_thermal_performances_preset.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
