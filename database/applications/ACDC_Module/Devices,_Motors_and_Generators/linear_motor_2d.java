/*
 * linear_motor_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class linear_motor_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().label("\u53c2\u6570 - \u4e3b\u53c2\u6570");
    model.param().set("Np", "10");
    model.param().descr("Np", "\u8fd0\u52a8\u90e8\u5206/\u8f6c\u5b50\u4e0a\u7684\u6781\u6570");
    model.param().set("Ns", "12");
    model.param().descr("Ns", "\u5b9a\u5b50\u69fd\u6570");
    model.param().set("v_lin", "0.5[m/s]");
    model.param().descr("v_lin", "\u7ebf\u6027\u8fd0\u52a8\u901f\u5ea6");
    model.param().set("I_ph", "2.5[A]");
    model.param().descr("I_ph", "\u76f8\u7535\u6d41\u5747\u65b9\u6839");
    model.param().set("Nturn", "120");
    model.param().descr("Nturn", "\u6bcf\u4e2a\u7ebf\u5708\u7684\u531d\u6570");
    model.param().set("r_inc", "25[mm]");
    model.param()
         .descr("r_inc", "\u5207\u53e3/\u8ba1\u7b97\u5e73\u9762\u534a\u5f84\uff08\u8f74\u5411\u78c1\u901a\u7535\u673a\uff09");
    model.param().set("s_rot", "v_lin/(2*pi*r_inc)");
    model.param().descr("s_rot", "\u8f6c\u901f\uff08\u8f74\u5411\u78c1\u901a\u7535\u673a\uff09");
    model.param().set("f_el", "s_rot*Np/2");
    model.param().descr("f_el", "\u7535\u9891\u7387");
    model.param().set("ang_el_init", "0[deg]");
    model.param()
         .descr("ang_el_init", "\u521d\u59cb\u7535\u6d41\u76f8\u4f4d\u89d2\uff08\u6700\u5927\u529b/\u626d\u77e9\uff09");
    model.param().set("L_mag", "20[mm]");
    model.param().descr("L_mag", "\u78c1\u6027\u6750\u6599\u7684\u5e73\u9762\u957f\u5ea6/\u5f84\u5411\u8303\u56f4");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53d8\u91cf\u522b\u540d");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u51e0\u4f55");
    model.param("par2").set("arc_inc", "r_inc*2*pi[rad]");
    model.param("par2").descr("arc_inc", "\u5207\u53e3\u5e73\u9762\u5f27\u957f");
    model.param("par2").set("th_air", "5[mm]");
    model.param("par2").descr("th_air", "\u5916\u90e8\u7a7a\u6c14\u539a\u5ea6");
    model.param("par2").set("th_rbyoke", "5[mm]");
    model.param("par2").descr("th_rbyoke", "\u8f6c\u5b50\u80cc\u8f6d\u539a\u5ea6");
    model.param("par2").set("th_mag", "3[mm]");
    model.param("par2").descr("th_mag", "\u78c1\u4f53\u539a\u5ea6");
    model.param("par2").set("th_airgap", "1[mm]");
    model.param("par2").descr("th_airgap", "\u6c14\u9699\u539a\u5ea6");
    model.param("par2").set("th_stooth", "19[mm]");
    model.param("par2").descr("th_stooth", "\u5b9a\u5b50\u9f7f\u539a");
    model.param("par2").set("th_coil", "18[mm]");
    model.param("par2").descr("th_coil", "\u7ebf\u5708\u539a\u5ea6");
    model.param("par2").set("th_sbyoke", "5[mm]");
    model.param("par2").descr("th_sbyoke", "\u5b9a\u5b50\u80cc\u8f6d\u539a\u5ea6");
    model.param("par2").set("ang_mag", "2*pi[rad]/Np*0.8");
    model.param("par2").descr("ang_mag", "\u78c1\u4f53\u503e\u89d2");
    model.param("par2").set("arc_mag", "r_inc*ang_mag");
    model.param("par2").descr("arc_mag", "\u78c1\u4f53\u5f27\u957f");
    model.param("par2").set("arc_pp", "arc_inc/Np");
    model.param("par2").descr("arc_pp", "\u6781\u8ddd\u5f27\u957f");
    model.param("par2").set("w_slot", "7.5[mm]");
    model.param("par2").descr("w_slot", "\u7f1d\u9699\u5bbd\u5ea6");
    model.param("par2").set("ang_slot", "2*asin(w_slot/2/r_inc)");
    model.param("par2").descr("ang_slot", "\u69fd\u503e\u89d2");
    model.param("par2").set("arc_slot", "r_inc*ang_slot");
    model.param("par2").descr("arc_slot", "\u69fd\u5f27\u957f");
    model.param("par2").set("arc_stooth", "arc_inc/Ns-arc_slot");
    model.param("par2").descr("arc_stooth", "\u5b9a\u5b50\u9f7f\u5f27\u957f");
    model.param("par2").set("A_wire", "th_coil*w_slot/2/Nturn*0.55");
    model.param("par2")
         .descr("A_wire", "\u5bfc\u7ebf\u6a2a\u622a\u9762\u79ef\uff0c\u5305\u62ec 0.55 \u7684\u69fd\u586b\u5145\u56e0\u5b50");

    model.variable().create("var1");
    model.variable("var1").set("iA", "I_ph*cos(2*pi*f_el*t+ang_el_init)");
    model.variable("var1").descr("iA", "A \u76f8\u7535\u6d41");
    model.variable("var1").set("iB", "I_ph*cos(2*pi*f_el*t+ang_el_init-2*pi/3)");
    model.variable("var1").descr("iB", "B \u76f8\u7535\u6d41");
    model.variable("var1").set("iC", "I_ph*cos(2*pi*f_el*t+ang_el_init-4*pi/3)");
    model.variable("var1").descr("iC", "C \u76f8\u7535\u6d41");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"arc_mag", "th_mag"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"(arc_pp-arc_mag)/2", "0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("pos", "-th_mag", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "arc_pp");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u5411\u4e0a\u7684\u78c1\u4f53");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"Np/2", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"arc_pp*2", "0"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").label("\u5411\u4e0b\u7684\u78c1\u4f53");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"Np/2", "1"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"arc_pp*2", "0"});
    model.component("comp1").geom("geom1").feature("arr2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr2").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6240\u6709\u78c1\u4f53");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"arr1", "arr2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u8f6c\u5b50\u80cc\u8f6d");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"arc_inc", "th_rbyoke"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-th_rbyoke-th_mag"});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u8f6c\u5b50\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("r3")
         .set("size", new String[]{"arc_inc", "th_mag+th_rbyoke+th_air+th_airgap/2"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-th_mag-th_rbyoke-th_air"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u8f85\u52a9\u626d\u77e9\u8ba1\u7b97\u57df");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"arc_inc", "th_airgap/4"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "th_airgap/4"});
    model.component("comp1").geom("geom1").feature("r4").set("selresult", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u8f6c\u5b50\u9009\u62e9");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"arr1", "arr2", "r2", "r3", "r4"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").label("\u5b9a\u5b50\u9f7f");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"arc_stooth", "th_stooth"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"arc_slot/2", "th_airgap"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").label("\u5b9a\u5b50\u9f7f\u9635\u5217");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("r5");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new String[]{"Ns", "1"});
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"arc_stooth+arc_slot", "0"});
    model.component("comp1").geom("geom1").feature("arr3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr3").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").label("\u5b9a\u5b50\u80cc\u8f6d");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"arc_inc", "th_sbyoke"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "th_airgap+th_stooth"});
    model.component("comp1").geom("geom1").feature("r6").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r6").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u5b9a\u5b50\u8f6d\u9009\u62e9");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"arr3", "r6"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").label("\u5b9a\u5b50\u8f6d");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("unisel3");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").label("\u7ebf\u5708\u817f");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"arc_slot/2", "th_coil"});
    model.component("comp1").geom("geom1").feature("r7")
         .set("pos", new String[]{"0", "th_airgap+th_stooth-th_coil"});
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("arr4", "Array");
    model.component("comp1").geom("geom1").feature("arr4").label("\u5de6\u7ebf\u5708\u817f");
    model.component("comp1").geom("geom1").feature("arr4").selection("input").set("r7");
    model.component("comp1").geom("geom1").feature("arr4").set("fullsize", new String[]{"Ns", "1"});
    model.component("comp1").geom("geom1").feature("arr4").set("displ", new String[]{"arc_stooth+arc_slot", "0"});
    model.component("comp1").geom("geom1").feature("arr4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr4").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("arr4");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").label("\u53f3\u7ebf\u5708\u817f");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").named("arr4");
    model.component("comp1").geom("geom1").feature("mov2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov2").set("displx", "arc_slot/2+arc_stooth");
    model.component("comp1").geom("geom1").feature("mov2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("mov2").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u7ebf\u5708\u57df");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"arr4", "mov2"});
    model.component("comp1").geom("geom1").feature("unisel4").set("selshow", "dom");
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").label("\u5b9a\u5b50\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("r8")
         .set("size", new String[]{"arc_inc", "th_airgap/2+th_stooth+th_sbyoke+th_air"});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"0", "th_airgap/2"});
    model.component("comp1").geom("geom1").feature("r8").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r8").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u5b9a\u5b50\u9009\u62e9");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel5")
         .set("input", new String[]{"unisel3", "arr4", "mov2", "r8"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").label("\u5b9a\u5b50");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").named("unisel5");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u78c1\u94a2\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_r2_dom", "geom1_unisel3_dom"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u6c14\u9699\u79ef\u5206");
    model.component("comp1").cpl("intop1").selection().named("geom1_r4_dom");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("F_phi", "mf.Bx*mf.By/mu0_const");
    model.component("comp1").variable("var2").descr("F_phi", "\u526a\u5207\u5e94\u529b\u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("Torque", "intop1(F_phi*r_inc)/(th_airgap/4)*L_mag");
    model.component("comp1").variable("var2")
         .descr("Torque", "\u626d\u77e9\uff08\u8f74\u5411\u78c1\u901a\u7535\u673a\uff09");
    model.component("comp1").variable("var2").set("Force", "intop1(F_phi)/(th_airgap/4)*L_mag");
    model.component("comp1").variable("var2")
         .descr("Force", "\u7ebf\u6027\u529b\uff08\u76f4\u7ebf\u7535\u673a\uff09");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u626d\u77e9");
    model.component("comp1").probe("var1").set("expr", "Torque");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").label("\u526a\u529b");
    model.component("comp1").probe("var2").set("expr", "Force");
    model.component("comp1").probe("var2").set("window", "window1");
    model.component("comp1").probe("var2").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat1").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat1").set("family", "iron");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat2").label("N40M (Sintered NdFeB)");
    model.component("comp1").material("mat2").set("family", "chrome");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", "1.28[T]");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat2").selection().named("geom1_unisel1");
    model.component("comp1").material("mat3").selection().named("geom1_unisel4_dom");

    model.component("comp1").view("view1").set("showmaterial", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").label("\u8fd0\u52a8\u90e8\u5206/\u8f6c\u5b50");
    model.component("comp1").common("pres1").selection().named("geom1_unisel2_dom");
    model.component("comp1").common("pres1").set("prescribedDeformation", new String[]{"v_lin*t", "0", "0"});

    model.component("comp1").physics("mf").prop("d").set("d", "L_mag");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("pp1", "PeriodicPair", 1);
    model.component("comp1").physics("mf").feature("pp1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("mf").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("mf").feature("pc1").label("\u5468\u671f\u6027\u6761\u4ef6 - \u5b9a\u5b50");
    model.component("comp1").physics("mf").feature("pc1").selection().set(64, 66, 68, 70, 190, 191, 192, 193);
    model.component("comp1").physics("mf").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("mf").feature("pc2")
         .label("\u5468\u671f\u6027\u6761\u4ef6 - \u8fd0\u52a8\u90e8\u5206/\u8f6c\u5b50");
    model.component("comp1").physics("mf").feature("pc2").selection().set(1, 3, 5, 7, 60, 61, 62, 63);
    model.component("comp1").physics("mf").create("lc1", "LaminatedCore", 2);
    model.component("comp1").physics("mf").feature("lc1").label("\u78c1\u94a2");
    model.component("comp1").physics("mf").feature("lc1").selection().named("uni1");
    model.component("comp1").physics("mf").feature("lc1").set("LaminationModel", "OutOfPlane");
    model.component("comp1").physics("mf").feature("lc1").set("StackingFactor", 1);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").label("A \u76f8\u7ed5\u7ec4");
    model.component("comp1").physics("mf").feature("coil1").selection().set(16, 19, 28, 29, 30, 31, 40, 41);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "iA");
    model.component("comp1").physics("mf").feature("coil1").set("N", "Nturn");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "A_wire");
    model.component("comp1").physics("mf").feature("coil1").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("rcd1").selection().set(19, 29, 30, 40);
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").label("B \u76f8\u7ed5\u7ec4");
    model.component("comp1").physics("mf").feature("coil2").selection().set(20, 21, 22, 23, 32, 33, 34, 35);
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "iB");
    model.component("comp1").physics("mf").feature("coil2").feature("rcd1").selection().set(21, 22, 32, 35);
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").label("C \u76f8\u7ed5\u7ec4");
    model.component("comp1").physics("mf").feature("coil3").selection().set(24, 25, 26, 27, 36, 37, 38, 39);
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "iC");
    model.component("comp1").physics("mf").feature("coil3").feature("rcd1").selection().set(24, 27, 37, 38);
    model.component("comp1").physics("mf").create("mag1", "Magnet", 2);
    model.component("comp1").physics("mf").feature("mag1").selection().named("geom1_unisel1");
    model.component("comp1").physics("mf").feature("mag1").set("PatternType", "LinearPattern");
    model.component("comp1").physics("mf").feature("mag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("mf").feature("mag1").feature("north1").selection().set(12);
    model.component("comp1").physics("mf").feature("mag1").feature("south1").selection().set(11);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1")
         .label("\u5927\u5c0f 1 - \u5468\u671f\u6027\u8fb9\u754c");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection()
         .set(3, 5, 7, 64, 66, 68, 70);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").label("\u5927\u5c0f 2 - \u78c1\u94a2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "2[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").label("\u5927\u5c0f 3 - \u6c14\u9699");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().set(15);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "0.5[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").label("\u7814\u7a76 1 - \u7535\u6d41\u76f8\u4f4d\u89d2\u626b\u63cf");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "A_wire", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat").setIndex("pname", "A_wire", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat").setIndex("pname", "ang_el_init", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,10,360)", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.03);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("solutionparams", "parent");
    model.result("pg3").feature("con1").set("expr", "mf.Az");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("number", 10);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("color", "custom");
    model.result("pg3").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg3").feature("con1").set("resolution", "fine");
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").feature("con1").set("inheritplot", "surf1");
    model.result("pg3").feature("con1").feature().create("filt1", "Filter");
    model.result("pg3").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 37, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg3").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();

    model.param().set("ang_el_init", "90[deg]");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").label("\u7814\u7a76 2 - \u77ac\u6001");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,1/f_el/144,1/f_el)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 145, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.03);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("solutionparams", "parent");
    model.result("pg5").feature("con1").set("expr", "mf.Az");
    model.result("pg5").feature("con1").set("titletype", "none");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("coloring", "uniform");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result("pg5").feature("con1").set("color", "custom");
    model.result("pg5").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg5").feature("con1").set("resolution", "fine");
    model.result("pg5").feature("con1").set("inheritcolor", false);
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result("pg5").feature("con1").set("inheritplot", "surf1");
    model.result("pg5").feature("con1").feature().create("filt1", "Filter");
    model.result("pg5").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 145, 0);
    model.result("pg6").label("\u52a8\u7f51\u683c 1");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").set("colortable", "PrismDark");
    model.result("pg5").feature("surf2").set("colorlegend", false);
    model.result("pg5").feature("surf2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("trn1").set("move", new String[]{"-arc_inc", "0"});
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf3").feature("trn1").set("move", new String[]{"arc_inc", "0"});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u7ed3\u679c\u6c47\u603b");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("av1", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av1").label("\u8868\u9762\u5e73\u5747\u503c - \u6c14\u9699");
    model.result().evaluationGroup("eg1").feature("av1").selection().named("geom1_r4_dom");
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "F_phi", 0);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u6c14\u9699\u526a\u5207\u5e94\u529b", 0);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "F_phi*arc_inc*L_mag", 1);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("descr", "\u7ebf\u6027\u529b", 1);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "F_phi*arc_inc*L_mag*r_inc", 2);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("unit", "N*m", 2);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u626d\u77e9\uff08\u8f74\u5411\u78c1\u901a\u7535\u673a\uff09", 2);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "F_phi*arc_inc*L_mag*v_lin", 3);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u76f4\u7ebf\u8fd0\u52a8\u529f\u7387", 3);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("expr", "F_phi*arc_inc*L_mag*r_inc*s_rot*2*pi[rad]", 4);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u65cb\u8f6c\u8fd0\u52a8\u529f\u7387", 4);
    model.result().evaluationGroup("eg1").feature("av1").set("dataseries", "average");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u5168\u5c40\u8ba1\u7b97 - \u7ed5\u7ec4");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mf.PCoil_1*3", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u7535\u8f93\u5165\u529f\u7387", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mf.ICoil_1^2*mf.RCoil_1*3", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u7ed5\u7ec4\u7535\u963b\u635f\u8017", 1);
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseries", "average");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").label("\u8868\u9762\u79ef\u5206 - \u78c1\u4f53");
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_unisel1");
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"mf.Qrh"});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("descr", new String[]{"\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535"});
    model.result().evaluationGroup("eg1").feature("int1").set("unit", new String[]{"W/m"});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "mf.Qrh*L_mag", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u78c1\u81f4\u635f\u8017", 0);
    model.result().evaluationGroup("eg1").feature("int1").set("dataseries", "average");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7ebf\u6027\u529b");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"Force"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u7ebf\u6027\u529b\uff08\u76f4\u7ebf\u7535\u673a\uff09"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u626d\u77e9\u8c10\u6ce2");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("xaxisdata", 1);
    model.result("pg8").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg8").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg8").feature("tblp1").set("transform", "fourier");
    model.result("pg8").feature("tblp1").set("fouriershow", "spectrum");
    model.result("pg8").feature("tblp1").set("preprocx", "linear");
    model.result("pg8").feature("tblp1").set("scalingx", "f_el");
    model.result("pg8").feature("tblp1").set("linestyle", "dashed");
    model.result("pg8").feature("tblp1").set("linemarker", "square");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", -1);
    model.result("pg8").set("xmax", 25);
    model.result("pg8").set("ymin", -1);
    model.result("pg8").set("ymax", 8);
    model.result("pg8").set("manualgrid", true);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u5468\u671f\u5bf9\u4e0a\u7684\u78c1\u901a\u91cf");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("showlegends", false);
    model.result("pg9").stepLast(0);
    model.result("pg9").run();
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").feature("surf1").set("coloring", "uniform");
    model.result("pg9").feature("surf1").set("color", "gray");
    model.result("pg9").run();
    model.result("pg9").create("arwl1", "ArrowLine");
    model.result("pg9").feature("arwl1").set("scaleactive", true);
    model.result("pg9").feature("arwl1").set("scale", 4);
    model.result("pg9").feature("arwl1").set("color", "green");
    model.result("pg9").feature("arwl1").create("sel1", "Selection");
    model.result("pg9").feature("arwl1").feature("sel1").selection().set(9);
    model.result("pg9").run();
    model.result("pg9").feature("arwl1").create("filt1", "Filter");
    model.result("pg9").run();
    model.result("pg9").feature("arwl1").feature("filt1").set("expr", "x<arc_inc");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("arwl2", "arwl1");
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").set("color", "red");
    model.result("pg9").run();
    model.result("pg9").feature("arwl2").feature("filt1").set("expr", "x>arc_inc");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("arwl3", "arwl2");
    model.result("pg9").run();
    model.result("pg9").feature("arwl3").set("color", "blue");
    model.result("pg9").run();
    model.result("pg9").feature("arwl3").feature("sel1").selection().set(65);
    model.result("pg9").run();
    model.result("pg9").feature("arwl3").feature("filt1").set("expr", "x<arc_inc/5");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg9").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg5").run();

    model.title("\u76f4\u7ebf\u7535\u673a\u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u76f4\u7ebf\u7535\u673a\u5728\u8bb8\u591a\u9700\u8981\u5e73\u79fb\u8fd0\u52a8\u7684\u5e94\u7528\u9886\u57df\u4e2d\u4f7f\u7528\uff0c\u5176\u5178\u578b\u7684\u8bbe\u8ba1\u7279\u70b9\u662f\u7cbe\u5ea6\u9ad8\u6216\u52a0\u901f\u5ea6\u5feb\u3002\u5728\u8be5\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u7814\u7a76\u9759\u6b62\u96f6\u4ef6\u91c7\u7528\u4e09\u76f8\u7ed5\u7ec4\uff0c\u8fd0\u52a8\u96f6\u4ef6\u91c7\u7528\u6c38\u78c1\u4f53\u7684\u540c\u6b65\u7535\u673a\uff1b\u5229\u7528\u52a8\u7f51\u683c\u548c\u5468\u671f\u6027\u6761\u4ef6\u5bf9\u6574\u4e2a\u7535\u673a\u7684\u5468\u671f\u90e8\u5206\u8fdb\u884c\u5efa\u6a21\u3002\u6b64\u5916\uff0c\u8fd8\u9610\u91ca\u4e86\u5982\u4f55\u5c06\u8be5\u6a21\u578b\u7528\u4f5c\u8f74\u5411\u78c1\u901a\u7535\u673a\uff08\u56de\u8f6c\u8fd0\u52a8\uff09\u7684\u4e8c\u7ef4\u8868\u793a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("linear_motor_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
