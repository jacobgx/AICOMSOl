/*
 * tonpilz_transducer_prestressed.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class tonpilz_transducer_prestressed {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("bolt", "BoltPretension");
    model.study("std1").feature("bolt").set("solnum", "auto");
    model.study("std1").feature("bolt").set("notsolnum", "auto");
    model.study("std1").feature("bolt").set("outputmap", new String[]{});
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").setSolveFor("/physics/pabe", true);
    model.study("std1").feature("bolt").setSolveFor("/physics/solid", true);
    model.study("std1").feature("bolt").setSolveFor("/physics/es", true);
    model.study("std1").feature("bolt").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho0", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("c0", "1480[m/s]", "\u6c34\u4e2d\u7684\u58f0\u901f");
    model.param().set("Zeval", "-10[m]", "\u65b9\u5411\u6027\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("Vrms", "1[V]", "\u5747\u65b9\u6839\u9a71\u52a8\u7535\u538b");
    model.param().set("V0", "sqrt(2)*Vrms", "\u96f6\u5230\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("f0min", "30[kHz]", "\u6700\u5c0f\u5de5\u4f5c\u9891\u7387");
    model.param().set("f0max", "110[kHz]", "\u6700\u5927\u5de5\u4f5c\u9891\u7387");
    model.param().set("f0step", "1[kHz]", "\u9891\u7387\u6b65\u8fdb");
    model.param().set("F_pre", "3.1[kN]", "\u87ba\u6813\u9884\u5e94\u529b");
    model.param().set("w_depth", "500[m]", "\u6c34\u6df1");
    model.param().set("eta_struct", "0.01", "\u7ed3\u6784\u4ef6\u7684\u963b\u5c3c\u6bd4");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"6[mm]", "10[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"2[mm]", "15[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"2[mm]", "8[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"4[mm]", "7[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("layer", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("layer", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("layer", 2, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-5[mm]", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "5[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "2[mm]", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "5[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "2[mm]", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "7[mm]", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "9[mm]", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "7[mm]", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "20[mm]", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-3[mm]", 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "-20[mm]", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "-3[mm]", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "-5[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "20[mm]", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "-3[mm]", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("pol1", "qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 1, 2, 8);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"20[mm]", "20[mm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "60[mm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "30[mm]", 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "rev1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u94dd");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("int1", 1, 2);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u94a2\u5236\u96f6\u4ef6");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("int1", 3);
    model.component("comp1").geom("geom1").feature("sel2").set("selkeep", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u94a2");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("+Z \u5411\u6781\u5316\u7684\u538b\u7535");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("int1", 4, 6);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("-Z \u5411\u6781\u5316\u7684\u538b\u7535");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("int1", 5, 7);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("int1", 22, 23, 30, 31, 36, 37, 63, 67, 70, 90, 94, 97);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u7535\u538b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .set("int1", 26, 27, 34, 35, 65, 69, 92, 96);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u6d78\u6ca1\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection")
         .set("int1", 1, 2, 3, 8, 10, 56, 81, 111, 113);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u5f39\u7c27\u57fa\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("int1", 14, 15, 59, 104);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u538b\u7535\u57df");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("int1", 4, 5, 6, 7);
    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Bolts\\simple_bolt_no_thread.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hdia", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hthic", "2[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ndia", "4[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "blen", "20[mm]");
    model.component("comp1").geom("geom1").feature("pi1").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new int[]{0, 0, 25});
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel5", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6392\u9664 BEM \u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel7"});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u6e90\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection")
         .set("fin", 144, 145, 148, 153, 155, 156, 166, 176, 186, 189, 215, 219, 221, 225, 226, 229);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").label("\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection")
         .set("fin", 50, 51, 52, 53, 54, 55, 62, 66, 91, 92, 102, 107, 126, 129, 130, 133);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("ap1").manualSelection(true);
    model.component("comp1").pair("ap1").source().named("geom1_sel10");
    model.component("comp1").pair("ap1").destination().named("geom1_sel11");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel7");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(36, 37, 84, 116);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().set(6);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("c33 \u56e0\u5b50");
    model.component("comp1").func("int1").set("funcname", "c33_factor");
    model.component("comp1").func("int1").setIndex("table", 30, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0.95, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 50, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 90, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 1.02, 2, 1);
    model.component("comp1").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").func("int1").setIndex("argunit", "MPa", 0);
    model.component("comp1").func("int1").setIndex("fununit", 1, 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u635f\u8017\u56e0\u5b50");
    model.component("comp1").func("int2").set("funcname", "loss_factor");
    model.component("comp1").func("int2").setIndex("table", 30, 0, 0);
    model.component("comp1").func("int2").setIndex("table", 1.25, 0, 1);
    model.component("comp1").func("int2").setIndex("table", 50, 1, 0);
    model.component("comp1").func("int2").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int2").setIndex("table", 90, 2, 0);
    model.component("comp1").func("int2").setIndex("table", 0.7, 2, 1);
    model.component("comp1").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").func("int2").setIndex("argunit", "MPa", 0);
    model.component("comp1").func("int2").setIndex("fununit", 1, 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").label("d33 \u56e0\u5b50");
    model.component("comp1").func("int3").set("funcname", "d33_factor");
    model.component("comp1").func("int3").setIndex("table", 30, 0, 0);
    model.component("comp1").func("int3").setIndex("table", 0.96, 0, 1);
    model.component("comp1").func("int3").setIndex("table", 50, 1, 0);
    model.component("comp1").func("int3").setIndex("table", 1, 1, 1);
    model.component("comp1").func("int3").setIndex("table", 90, 2, 0);
    model.component("comp1").func("int3").setIndex("table", 1.01, 2, 1);
    model.component("comp1").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").func("int3").setIndex("argunit", "MPa", 0);
    model.component("comp1").func("int3").setIndex("fununit", 1, 0);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Zaco", "intop1(p)/intop1(pabe.iomega*(w+eps))/(rho0*c0)", "\u6bd4\u58f0\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("pext_1", "at3_spatial(0,0,-1[m],pabe.p_t,'minc')", "1 m \u5904\u7684\u58f0\u538b");
    model.component("comp1").variable("var1")
         .set("prms", "sqrt(0.5*pext_1*conj(pext_1))", "1 m \u5904\u7684\u5747\u65b9\u6839\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("TVR", "20*log10(prms/Vrms/1[uPa/V])", "\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR)");
    model.component("comp1").variable("var1")
         .set("pext_Zeval", "at3_spatial(0,0,Zeval,pabe.p_t,'minc')", "Zeval \u5904\u7684\u58f0\u538b");
    model.component("comp1").variable("var1")
         .set("Ifront", "0.5*pext_Zeval*conj(pext_Zeval)/(rho0*c0)", "Zeval \u5904\u7684\u8f74\u4e0a\u5f3a\u5ea6");
    model.component("comp1").variable("var1")
         .set("Ptot", "-intop1(pabe.I_bndx*pabe.nx+pabe.I_bndy*pabe.ny+pabe.I_bndz*pabe.nz)", "\u603b\u8f90\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Iave", "Ptot/(4*pi*Zeval^2)", "Zeval \u5904\u7684\u5355\u6781\u6e90\u5e73\u5747\u5f3a\u5ea6");
    model.component("comp1").variable("var1").set("Di", "Ifront/Iave", "\u5f3a\u5ea6\u65b9\u5411\u6027");
    model.component("comp1").variable("var1")
         .set("DI", "10*log10(Di)", "Tonpilz \u578b\u6362\u80fd\u5668\u7684\u6307\u5411\u6027\u6307\u6570");
    model.component("comp1").variable("var1").set("k0", "2*pi*freq/c0", "\u6ce2\u6570");
    model.component("comp1").variable("var1")
         .set("pzt_stress", "F_pre/intop2(1)", "PZT \u5904\u7684\u540d\u4e49\u538b\u5e94\u529b");
    model.component("comp1").variable("var1")
         .set("a", "sqrt(intop3(1)/pi)", "\u7b49\u6548\u5e26\u51f8\u7f18\u6d3b\u585e\u7684\u534a\u5f84");
    model.component("comp1").variable("var1")
         .set("DI_fl_pist", "10*log10((k0*a)^2/(1-2*besselj(1,2*k0*a)/(2*k0*a)))", "\u5e26\u51f8\u7f18\u6d3b\u585e\u7684\u6307\u5411\u6027\u6307\u6570");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Steel AISI 4340");
    model.component("comp1").material("mat3").set("family", "steel");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat4").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat4").label("Lead Zirconate Titanate (PZT-4)");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.9);
    model.component("comp1").material("mat4").set("roughness", 0.1);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.23e-011[1/Pa]", "-4.05e-012[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.05e-012[1/Pa]", "1.23e-011[1/Pa]", "-5.31e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.31e-012[1/Pa]", "-5.31e-012[1/Pa]", "1.55e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.9e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.27e-011[1/Pa]"});
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "-1.23e-010[C/N]", "0[C/N]", "0[C/N]", "2.89e-010[C/N]", "0[C/N]", 
         "4.96e-010[C/N]", "0[C/N]", "4.96e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat4").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"1475", "0", "0", "0", "1475", "0", "0", "0", "1300"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.38999e+011[Pa]", "7.78366e+010[Pa]", "7.42836e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "7.78366e+010[Pa]", "1.38999e+011[Pa]", "7.42836e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "7.42836e+010[Pa]", "7.42836e+010[Pa]", "1.15412e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "3.0581e+010[Pa]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "15.0804[C/m^2]", "0[C/m^2]", 
         "12.7179[C/m^2]", "0[C/m^2]", "12.7179[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"762.5", "0", "0", "0", "762.5", "0", "0", "0", "663.2"});
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();
    model.component("comp1").material("mat2").selection().named("geom1_sel1");
    model.component("comp1").material("mat3").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat4").selection().named("geom1_sel9");

    model.component("comp1").physics("pabe").selection().set();
    model.component("comp1").physics("pabe").selection().allVoids();
    model.component("comp1").physics("pabe").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("pabe").prop("Symmetry").setIndex("sym3", "even", 0);
    model.component("comp1").physics("pabe").create("eb1", "ExcludedBoundary", 2);
    model.component("comp1").physics("pabe").feature("eb1").selection().named("geom1_comsel1");
    model.component("comp1").physics("pabe").feature("bpam1").set("FluidModel", "OceanAttenuation");
    model.component("comp1").physics("pabe").feature("bpam1").set("minput_depth", "w_depth");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("geom1_sel3");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("solid").feature().duplicate("pzm2", "pzm1");
    model.component("comp1").physics("solid").feature("pzm2").selection().named("geom1_sel4");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "pi", "0"});

    model.component("comp1").physics("solid").feature("pzm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").create("spf1", "SpringFoundation2", 2);
    model.component("comp1").physics("solid").feature("spf1").selection().named("geom1_sel8");
    model.component("comp1").physics("solid").feature("spf1").set("SpringType", "kTot");
    model.component("comp1").physics("solid").feature("spf1")
         .set("kTot", new String[]{"10000[N/m]", "0", "0", "0", "10000[N/m]", "0", "0", "0", "10000[N/m]"});
    model.component("comp1").physics("solid").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("solid").feature("pblt1").set("F_pre", "F_pre");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").selection().named("geom1_pi1_sel5");
    model.component("comp1").physics("solid").create("cont1", "Continuity", 2);
    model.component("comp1").physics("solid").feature("cont1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f1", "f0min");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta1", "eta_struct");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f2", "f0max");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta2", "eta_struct");
    model.component("comp1").physics("es").selection().named("geom1_sel9");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("geom1_sel5");
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_sel6");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "linper(V0)");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().named("geom1_sel7");

    model.component("comp1").material("mat4")
         .label("\u9506\u949b\u9178\u94c5 (PZT-4) \uff08\u542b\u672c\u6784\u6a21\u578b\uff09");
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.38999e+011[Pa]", "7.78366e+010[Pa]", "1.38999e+011[Pa]", "7.42836e+010[Pa]", "7.42836e+010[Pa]", "1.15412e+011[Pa]*c33_factor(pzt_stress)", "0[Pa]", "0[Pa]", "0[Pa]", "2.5641e+010[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.5641e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "3.0581e+010[Pa]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-5.20279[C/m^2]", "0[C/m^2]", "0[C/m^2]", "15.0804[C/m^2]*d33_factor(pzt_stress)", "0[C/m^2]", 
         "12.7179[C/m^2]", "0[C/m^2]", "12.7179[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat4").propertyGroup("StressCharge")
         .set("eta_cE", new String[]{"0.01*loss_factor(pzt_stress)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3, 8, 22, 23, 70, 77, 97, 109);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "6[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_sel9");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_sel5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("punit", "kHz");
    model.study("std1").feature("frlin").set("plist", "1 2 5 10 20 range(f0min,f0step,f0max)");
    model.study("std1").feature("frlin").set("geometricNonlinearity", true);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "rho0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg/m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "rho0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg/m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "F_pre", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1.9 4.4", 0);
    model.study("std1").feature("param").setIndex("punit", "kN", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset3");
    model.result().dataset("grid1").set("parmin1", "-50[mm]");
    model.result().dataset("grid1").set("parmax1", 0);
    model.result().dataset("grid1").set("parmin2", "-50[mm]");
    model.result().dataset("grid1").set("parmax2", "50[mm]");
    model.result().dataset("grid1").set("parmax3", "-150[mm]");
    model.result().dataset("grid1").set("res1", 40);
    model.result().dataset("grid1").set("res2", 80);
    model.result().dataset("grid1").set("res3", 120);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u9884\u7d27\u529b\u4ea7\u751f\u7684\u9759\u6001\u5e94\u529b");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "solid.sz");
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").feature("vol1").set("evalmethod", "linpoint");
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormin", -100);
    model.result("pg1").feature("vol1").set("rangecolormax", 400);
    model.result("pg1").feature("vol1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("filt1").set("expr", "x>-0.01[mm]");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def1").set("evalmethod", "linpoint");
    model.result("pg1").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def1").set("scale", 120);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "0");
    model.result("pg1").feature("line1").set("evalmethod", "linpoint");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "vol1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature().copy("def1", "pg1/vol1/def1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature().copy("filt1", "pg1/vol1/filt1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u9884\u7d27\u529b\u4ea7\u751f\u7684\u9759\u6001\u53d8\u5f62");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("expr", "w");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.015);
    model.result("pg2").feature("vol1").set("rangecolormax", 0.025);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b");
    model.result("pg3").set("data", "grid1");
    model.result("pg3").setIndex("looplevel", 19, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", 0);
    model.result("pg3").feature("mslc1").set("colortable", "Wave");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "dset3");
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").feature("line1").set("expr", "0");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "dset3");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg3").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\u7ea7");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "pabe.Lp");
    model.result("pg4").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg4").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "pabe.Lp_bnd");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u538b");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").selection().geom("geom1", 3);
    model.result("pg5").selection().named("geom1_sel9");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "V");
    model.result("pg5").feature("vol1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("filt1").set("expr", "x>-0.01[mm]");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result().duplicate("pg6", "pg2");
    model.result("pg6").run();
    model.result("pg6").label("\u52a8\u6001\u53d8\u5f62");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("evalmethod", "harmonic");
    model.result("pg6").feature("vol1").set("rangecoloractive", false);
    model.result("pg6").run();
    model.result("pg6").feature("vol1").feature("def1").set("evalmethod", "harmonic");
    model.result("pg6").feature("vol1").feature("def1").set("scaleactive", false);
    model.result("pg6").run();
    model.result("pg6").feature("line1").feature("def1").set("evalmethod", "harmonic");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 18, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 19, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 86, 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6bd4\u58f0\u963b\u6297");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevelinput", "manual", 1);
    model.result("pg7").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f (kHz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "Z/(\\rho c)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "abs(Zaco)", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "real(Zaco)", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "imag(Zaco)", 2);
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "\u7edd\u5bf9\u503c", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "\u5b9e\u90e8", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "\u865a\u90e8", 2);
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "freq");
    model.result("pg7").feature("glob1").set("xdataunit", "kHz");
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature("glob1").set("linestyle", "cycle");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR)");
    model.result("pg8").setIndex("looplevelinput", "all", 1);
    model.result("pg8").set("ylabel", "TVR\uff08dB \u76f8\u5bf9 1\\mu Pa/V\uff09");
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{"TVR"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR)"});
    model.result("pg8").feature("glob1").set("unit", new String[]{""});
    model.result("pg8").feature("glob1").set("legendmethod", "automatic");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result().duplicate("pg9", "pg7");
    model.result("pg9").run();
    model.result("pg9").label("\u6307\u5411\u6027\u6307\u6570 (DI)");
    model.result("pg9").set("ylabel", "DI (dB)");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("expr", new String[]{"DI"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"Tonpilz \u578b\u6362\u80fd\u5668\u7684\u6307\u5411\u6027\u6307\u6570"});
    model.result("pg9").feature("glob1").set("unit", new String[]{""});
    model.result("pg9").feature("glob1").set("expr", new String[]{"DI", "DI_fl_pist"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"Tonpilz \u578b\u6362\u80fd\u5668\u7684\u6307\u5411\u6027\u6307\u6570", "\u5e26\u51f8\u7f18\u6d3b\u585e\u7684\u6307\u5411\u6027\u6307\u6570"});
    model.result("pg9").feature("glob1").set("legendmethod", "automatic");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u603b\u8f90\u5c04\u529f\u7387");
    model.result("pg10").setIndex("looplevelinput", "all", 1);
    model.result("pg10").set("ylabel", "Ptot (mW)");
    model.result("pg10").set("legendpos", "upperright");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").set("expr", new String[]{"Ptot"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"\u603b\u8f90\u5c04\u529f\u7387"});
    model.result("pg10").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg10").feature("glob1").setIndex("unit", "mW", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7535\u963b\u6297");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("twoyaxes", true);
    model.result("pg11").set("ylog", true);
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "abs(1/es.Y11)", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "|Z<sub>el</sub>|", 0);
    model.result("pg11").feature("glob1").set("linewidth", 1);
    model.result("pg11").feature().duplicate("glob2", "glob1");
    model.result("pg11").run();
    model.result("pg11").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg11").feature("glob2").setIndex("expr", "arg(1/es.Y11)", 0);
    model.result("pg11").feature("glob2").setIndex("unit", "deg", 0);
    model.result("pg11").feature("glob2").setIndex("descr", "Angle", 0);
    model.result("pg11").feature("glob2").set("linestyle", "dotted");
    model.result("pg11").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg11").run();
    model.result().dataset().create("grid2", "Grid1D");
    model.result().dataset("grid2").set("source", "data");
    model.result().dataset("grid2").set("parmin1", 30);
    model.result().dataset("grid2").set("parmax1", 90);
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u5c5e\u6027\u53d8\u5316");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u56e0\u5b50 (1)");
    model.result("pg12").set("legendpos", "lowerright");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("data", "grid2");
    model.result("pg12").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg12").feature("lngr1").set("expr", "c33_factor(x[1/mm][MPa])");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "x[1/mm][MPa]");
    model.result("pg12").feature("lngr1").set("xdatadescractive", true);
    model.result("pg12").feature("lngr1").set("xdatadescr", "\u9884\u7d27\u529b");
    model.result("pg12").feature("lngr1").set("xdataunit", "MPa");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("autosolution", false);
    model.result("pg12").feature("lngr1").set("autodescr", true);
    model.result("pg12").feature("lngr1").set("linewidth", 1);
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("lngr2", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("expr", "loss_factor(x[1/mm][MPa])");
    model.result("pg12").feature().duplicate("lngr3", "lngr2");
    model.result("pg12").run();
    model.result("pg12").feature("lngr3").set("expr", "d33_factor(x[1/mm][MPa])");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("ann1", "Annotation");
    model.result("pg12").feature("ann1").set("showpoint", false);
    model.result("pg12").feature("ann1").set("posxexpr", 30);
    model.result("pg12").run();
    model.result("pg6").run();
    model.result().duplicate("pg13", "pg6");
    model.result("pg13").run();
    model.result("pg13").label("\u7f29\u7565\u56fe");
    model.result("pg13").run();
    model.result("pg13").feature("vol1").set("colortable", "ThermalDark");
    model.result("pg13").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg13").run();
    model.result("pg13").create("mslc1", "Multislice");
    model.result("pg13").feature("mslc1").set("data", "grid1");
    model.result("pg13").feature("mslc1").set("solutionparams", "parent");
    model.result("pg13").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg13").feature("mslc1").set("xcoord", 0);
    model.result("pg13").feature("mslc1").set("colortable", "Wave");
    model.result("pg13").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").set("titletype", "none");
    model.result("pg14").create("vol1", "Volume");
    model.result("pg14").feature("vol1").set("expr", "0");
    model.result("pg14").feature("vol1").set("coloring", "uniform");
    model.result("pg14").feature("vol1").set("color", "gray");
    model.result("pg14").feature("vol1").set("resolution", "extrafine");
    model.result("pg14").feature("vol1").create("sel1", "Selection");
    model.result("pg14").feature("vol1").feature("sel1").selection().set(3, 8, 9, 10, 17, 24, 25);
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("vol2", "vol1");
    model.result("pg14").run();
    model.result("pg14").feature("vol2").set("color", "green");
    model.result("pg14").run();
    model.result("pg14").feature("vol2").feature("sel1").selection().set(4, 5, 6, 7);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("vol3", "vol1");
    model.result("pg14").run();
    model.result("pg14").feature("vol3").set("color", "custom");
    model.result("pg14").feature("vol3")
         .set("customcolor", new double[]{0.32549020648002625, 0.3294117748737335, 0.41960784792900085});
    model.result("pg14").run();
    model.result("pg14").feature("vol3").feature("sel1").selection().set(1, 2);
    model.result("pg14").run();
    model.result("pg14").create("line1", "Line");
    model.result("pg14").feature("line1").set("expr", "0");
    model.result("pg14").feature("line1").set("coloring", "uniform");
    model.result("pg14").feature("line1").set("color", "black");
    model.result("pg14").feature("line1").set("resolution", "extrafine");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().remove("pg14");
    model.result("pg13").run();

    model.title("\u9884\u5e94\u529b\u87ba\u6813 Tonpilz \u578b\u538b\u7535\u6362\u80fd\u5668");

    model
         .description("Tonpilz \u6362\u80fd\u5668\u7528\u4e8e\u9891\u7387\u76f8\u5bf9\u8f83\u4f4e\u7684\u5927\u529f\u7387\u58f0\u53d1\u5c04\uff0c\u8fd9\u662f\u58f0\u5450\u5e94\u7528\u4e2d\u5e38\u7528\u7684\u4e00\u79cd\u6362\u80fd\u5668\u914d\u7f6e\u3002\u6362\u80fd\u5668\u7531\u524d\u8d28\u91cf\u5757\u3001\u540e\u8d28\u91cf\u5757\u4ee5\u53ca\u5806\u53e0\u5728\u4e24\u8005\u4e4b\u95f4\u7684\u538b\u7535\u9676\u74f7\u73af\u7ec4\u6210\uff0c\u8fd9\u4e9b\u73af\u901a\u8fc7\u4e2d\u5fc3\u87ba\u6813\u8fde\u63a5\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5728\u87ba\u6813\u4e2d\u52a0\u5165\u9884\u7d27\u529b\u7684\u5f71\u54cd\uff0c\u4ee5\u53ca\u5982\u4f55\u6dfb\u52a0\u672c\u6784\u6a21\u578b\u6765\u63cf\u8ff0\u538b\u7535\u6750\u6599\u7684\u5f71\u54cd\uff1b\u5176\u4e2d\u4ece\u201c\u96f6\u4ef6\u5e93\u201d\u5bfc\u5165\u87ba\u6813\u51e0\u4f55\u3002\u9891\u7387\u54cd\u5e94\u663e\u793a\u4e86\u7ed3\u6784\u548c\u58f0\u5b66\u6548\u5e94\uff0c\u4f8b\u5982\u53d8\u5f62\u3001\u5e94\u529b\u3001\u8f90\u5c04\u529f\u7387\u3001\u58f0\u538b\u7ea7\u3001\u53d1\u5c04\u7535\u538b\u54cd\u5e94 (TVR) \u66f2\u7ebf\u548c\u58f0\u675f\u7684\u6307\u5411\u6027\u6307\u6570 (DI) \u7b49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("tonpilz_transducer_prestressed.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
