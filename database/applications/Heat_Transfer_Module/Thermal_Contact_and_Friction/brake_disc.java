/*
 * brake_disc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:29 by COMSOL 6.3.0.290. */
public class brake_disc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("mu", "0.3", "\u6469\u64e6\u7cfb\u6570");
    model.param().set("v0", "25[m/s]", "\u521d\u59cb\u8f66\u901f");
    model.param().set("a0", "-10[m/s^2]", "\u8f66\u8f86\u521d\u59cb\u52a0\u901f\u5ea6");
    model.param().set("r_wheel", "0.25[m]", "\u8f66\u8f6e\u534a\u5f84");
    model.param().set("m_car", "1800[kg]", "\u8f66\u8f86\u8d28\u91cf");
    model.param().set("t_brake_start", "2[s]", "\u5236\u52a8\u65f6\u95f4\uff08\u5f00\u59cb\uff09");
    model.param().set("t_brake_end", "4[s]", "\u5236\u52a8\u65f6\u95f4\uff08\u7ed3\u675f\uff09");
    model.param().set("T_air", "300[K]", "\u6e29\u5ea6\uff0c\u7a7a\u6c14");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.14);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.013);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.08);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.01);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0, 0, 0.013});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.013);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.135, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.02, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.135, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.05, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.13, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.04, 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.105, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("w", 2.5, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.04, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.105, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.03, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.08, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.035, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.09, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.09, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb3", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.09, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.035, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.09, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.03, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.08, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.04, 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.105, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb4", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.04, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.05, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.105, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.13, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.02, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.135, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.135, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("w", 2.5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("cb1", "cb2", "cb3", "cb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.0065, 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5239\u8f66\u76d8\u9762");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 2, 4, 5, 6, 8, 13, 14, 15, 18);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5239\u8f66\u7247\u9762");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(9, 10, 12, 16, 17);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u63a5\u89e6\u9762");
    model.component("comp1").selection("sel3").geom(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel3").set(11);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5916\u8868\u9762");
    model.component("comp1").selection("sel4").all();
    model.component("comp1").selection("sel4").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel4").all();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel3");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("sel4");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "v");
    model.component("comp1").func("pw1").set("arg", "t");
    model.component("comp1").func("pw1").set("smooth", "contd2");
    model.component("comp1").func("pw1").set("zonelengthtype", "absolute");
    model.component("comp1").func("pw1").set("smoothzone", 0.2);
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t_brake_start[1/s]", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "v0[s/m]", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t_brake_start[1/s]", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t_brake_end[1/s]", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "v0[s/m]+a0*(t[s]-t_brake_start)[s/m]", 1, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t_brake_end[1/s]", 2, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 12, 2, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "v0[s/m]+a0*(t_brake_end-t_brake_start)[s/m]", 2, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "m/s");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "a");
    model.component("comp1").func("an1").set("expr", "d(v(t),t)");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);
    model.component("comp1").func("an1").set("fununit", "m/s^2");
    model.component("comp1").func("an1").setIndex("plotargs", 10, 0, 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5239\u8f66\u76d8");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"82[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7870[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"449[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5239\u8f66\u7247");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"8.7[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2000[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"935[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("solidtrm1", "SolidWithTranslationalMotion", 3);
    model.component("comp1").physics("ht").feature("solidtrm1").selection().all();
    model.component("comp1").physics("ht").feature("solidtrm1").feature("trm1").selection().set(1, 2);
    model.component("comp1").physics("ht").feature("solidtrm1").feature("trm1")
         .set("u", new String[]{"-y*v(t)/r_wheel", "x*v(t)/r_wheel", "0"});
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("HeatTransferCoefficientType", "ExtForcedConvection");
    model.component("comp1").physics("ht").feature("hf1").set("Lpl", "0.14[m]");
    model.component("comp1").physics("ht").feature("hf1").set("U", "v(t)");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_air");
    model.component("comp1").physics("ht").create("tc1", "ThermalContact", 2);
    model.component("comp1").physics("ht").feature("tc1").selection().set(11);
    model.component("comp1").physics("ht").feature("tc1").set("Tn", "ht.tc1.Qb/(mu*v(t))");
    model.component("comp1").physics("ht").feature("tc1").set("Hmic", "800[MPa]");
    model.component("comp1").physics("ht").feature("tc1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("tc1").set("Pb", "-m_car*v(t)*a(t)/8");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_air");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.28);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "T_air");
    model.component("comp1").physics("ht").create("sar2", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar2").selection().named("sel2");
    model.component("comp1").physics("ht").feature("sar2").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar2").set("epsilon_rad", 0.8);
    model.component("comp1").physics("ht").feature("sar2").set("Tamb", "T_air");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(3);
    model.component("comp1").physics("ht").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("ht").feature("ge1").set("DependentVariableQuantity", "energy");
    model.component("comp1").physics("ht").feature("ge1").set("SourceTermQuantity", "power");
    model.component("comp1").physics("ht").feature("ge1").setIndex("name", "W_prod", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("equation", "W_prodt-intop1(ht.tc1.Qb)", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("description", "\u4ea7\u70ed", 0, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("name", "W_diss", 1, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ht").feature("ge1")
         .setIndex("equation", "W_disst+(intop2(ht.q0+ht.rflux))", 1, 0);
    model.component("comp1").physics("ht").feature("ge1").setIndex("description", "\u6563\u70ed", 1, 0);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 7, 11);

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time")
         .set("tlist", "range(0,0.5,1.5) range(1.55,0.05,3) range(3.2,0.2,5) range(6,1,12)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 38, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8017\u6563\u548c\u4ea7\u751f\u7684\u70ed\u91cf");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "log10(W_prod+1)");
    model.result("pg2").feature("ptgr1").set("linecolor", "blue");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "log10(W_prod+1)\uff0c\u4ea7\u70ed", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "log10(W_diss+1)");
    model.result("pg2").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr2").setIndex("legends", "log10(W_diss+1)\uff0c\u6563\u70ed", 0);
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0.0129, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", -0.047, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.1316, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.0129, 1, 2);
    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset("par1").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("par1")
         .setIndex("looplevel", new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}, 0);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\u66f2\u7ebf vs. \u65f6\u95f4");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg4").feature().create("slit1", "SurfaceSlit");
    model.result("pg4").feature("slit1").set("solutionparams", "parent");
    model.result("pg4").feature("slit1").set("updescractive", true);
    model.result("pg4").feature("slit1").set("upexpr", "ht.Tu");
    model.result("pg4").feature("slit1").set("updescr", "\u4e0a\u4fa7\u6e29\u5ea6");
    model.result("pg4").feature("slit1").set("downdescractive", true);
    model.result("pg4").feature("slit1").set("downexpr", "ht.Td");
    model.result("pg4").feature("slit1").set("downdescr", "\u4e0b\u4fa7\u6e29\u5ea6");
    model.result("pg4").feature("slit1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("slit1").set("smooth", "internal");
    model.result("pg4").feature("slit1").set("showsolutionparams", "on");
    model.result("pg4").feature("slit1").set("data", "parent");
    model.result("pg4").feature("slit1").feature().create("sel1", "Selection");
    model.result("pg4").feature("slit1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("slit1").feature("sel1").selection().set(11);
    model.result("pg4").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 38, 0);
    model.result("pg1").run();

    model.title("\u76d8\u5f0f\u5236\u52a8\u5668\u7684\u53d1\u70ed");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5728\u7a81\u7136\u5236\u52a8\u7684\u60c5\u51b5\u4e0b\uff0c\u76d8\u5f0f\u5236\u52a8\u5668\u4e0a\u6e29\u5ea6\u573a\u7684\u77ac\u6001\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("brake_disc.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
