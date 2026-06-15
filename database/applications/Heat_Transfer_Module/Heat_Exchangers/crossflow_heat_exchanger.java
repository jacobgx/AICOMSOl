/*
 * crossflow_heat_exchanger.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:21 by COMSOL 6.3.0.290. */
public class crossflow_heat_exchanger {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Heat_Exchangers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
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
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("solnum", "auto");
    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("outputmap", new String[]{});
    model.study("std1").feature("stat2").set("ngenAUX", "1");
    model.study("std1").feature("stat2").set("goalngenAUX", "1");
    model.study("std1").feature("stat2").set("ngenAUX", "1");
    model.study("std1").feature("stat2").set("goalngenAUX", "1");
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);

    model.param().set("T_cold", "300[K]");
    model.param().descr("T_cold", "\u6e29\u5ea6\uff0c\u51b7\u6d41");
    model.param().set("T_hot", "330[K]");
    model.param().descr("T_hot", "\u6e29\u5ea6\uff0c\u70ed\u6d41");
    model.param().set("u_avg", "50[mm/s]");
    model.param().descr("u_avg", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{800, 800, 60});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{800, 100, 40});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 200, 0});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 5);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 120, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u901a\u9053");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("arr1", "blk1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new int[]{0, 0, 60});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("rot1").set("ax3", new int[]{1, 1, 0});
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u4e0a\u65b9\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(41, 48, 55, 62, 69);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4e0b\u65b9\u5165\u53e3");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(8, 14, 20, 26, 32);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u4e0a\u65b9\u51fa\u53e3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(44, 51, 58, 65, 72);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u4e0b\u65b9\u51fa\u53e3");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(77, 78, 79, 80, 81);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection("sel5")
         .add(3, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 43, 46, 50, 53, 57, 60, 64, 67, 71, 74);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u51fa\u53e3");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel3", "sel4"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u4e0d\u9508\u94a2");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"15[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800[J/(kg*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"420[kg/m^3]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "(T_cold+T_hot)/2");
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_hot");
    model.component("comp1").physics("ht").create("ifl2", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl2").selection().named("sel2");
    model.component("comp1").physics("ht").feature("ifl2").set("Tustr", "T_cold");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel3");
    model.component("comp1").physics("ht").create("ofl2", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl2").selection().named("sel4");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("sel5");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_avg");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().named("sel2");
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("Uavfdf", "u_avg");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel3");
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out2").selection().named("sel4");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel5");

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u4e0a\u901a\u9053\u58c1\u5904\u7684\u5e73\u5747\u503c");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection()
         .set(40, 42, 45, 47, 49, 52, 54, 56, 59, 61, 63, 66, 68, 70, 73);

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
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(9, 11, 12, 15, 17, 18, 21, 23, 24, 27, 29, 30, 33, 35, 36, 40, 42, 45, 47, 49, 52, 54, 56, 59, 61, 63, 66, 68, 70, 73);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u6e29");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(9, 11, 12, 15, 17, 18, 21, 23, 24, 27, 29, 30, 33, 35, 36, 40, 42, 45, 47, 49, 52, 54, 56, 59, 61, 63, 66, 68, 70, 73);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "nitf1.T");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg4").feature("vol1").feature("sel1").selection().set(1, 2);
    model.result("pg4").feature("vol1").set("inheritplot", "surf1");
    model.result("pg4").feature().create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("solutionparams", "parent");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg4").feature("arwv1").set("xnumber", 30);
    model.result("pg4").feature("arwv1").set("ynumber", 30);
    model.result("pg4").feature("arwv1").set("znumber", 30);
    model.result("pg4").feature("arwv1").set("arrowtype", "cone");
    model.result("pg4").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("data", "parent");
    model.result("pg4").feature("arwv1").feature().create("col1", "Color");
    model.result("pg4").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg4").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("solutionparams", "parent");
    model.result("pg5").feature("iso1").set("number", 10);
    model.result("pg5").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("iso1").set("smooth", "internal");
    model.result("pg5").feature("iso1").set("showsolutionparams", "on");
    model.result("pg5").feature("iso1").set("data", "parent");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "surf1");
    model.result("pg5").feature("surf1").set("inheritplot", "iso1");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("slc1");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg2").feature("surf1").set("unit", "mm/s");
    model.result("pg2").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u51fa\u53e3");
    model.result().dataset("surf2").selection().named("uni1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u51fa\u53e3\u6e29\u5ea6");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "surf2");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev1").set("expr", new String[]{"ht.ofl1.Tave"});
    model.result().numerical("gev1").set("descr", new String[]{"\u52a0\u6743\u5e73\u5747\u6e29\u5ea6"});
    model.result().numerical("gev1").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("gev1")
         .setIndex("descr", "\u52a0\u6743\u5e73\u5747\u6e29\u5ea6\uff08\u4e0a\u65b9\u51fa\u53e3\uff09", 0);
    model.result().numerical("gev1").setIndex("expr", "ht.ofl2.Tave", 1);
    model.result().numerical("gev1")
         .setIndex("descr", "\u52a0\u6743\u5e73\u5747\u6e29\u5ea6\uff08\u4e0b\u65b9\u51fa\u53e3\uff09", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("max1", "MaxSurface");
    model.result().numerical("max1").selection().all();
    model.result().numerical("max1").set("expr", new String[]{"p"});
    model.result().numerical("max1").set("descr", new String[]{"\u538b\u529b"});
    model.result().numerical("max1").set("unit", new String[]{"Pa"});
    model.result().numerical("max1").label("\u6700\u5927\u538b\u964d");
    model.result().numerical("max1").setIndex("descr", "\u6700\u5927\u538b\u964d", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u6700\u5927\u538b\u964d");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "aveop1(ht.ntflux)/(T_hot-T_cold)", 0);
    model.result().numerical("gev2").setIndex("descr", "\u4f20\u70ed\u7cfb\u6570", 0);
    model.result().numerical("gev2").label("\u4f20\u70ed\u7cfb\u6570");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u4f20\u70ed\u7cfb\u6570");
    model.result().numerical("gev2").set("table", "tbl3");
    model.result().numerical("gev2").setResult();
    model.result("pg5").run();

    model.title("\u9519\u6d41\u5f0f\u6362\u70ed\u5668");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u4e00\u4e2a\u7528\u4e0d\u9508\u94a2\u5236\u6210\u7684\u5fae\u578b\u9519\u6d41\u5f0f\u6362\u70ed\u5668\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u548c\u4f20\u70ed\uff0c\u8fd9\u6837\u7684\u6362\u70ed\u5668\u5e38\u7528\u4e8e\u82af\u7247\u5b9e\u9a8c\u5ba4\u8bbe\u5907\u548c\u71c3\u6599\u7535\u6c60\u5fae\u53cd\u5e94\u5668\u3002\u8be5\u6a21\u578b\u5206\u6790\u901a\u8fc7\u5bf9\u6d41\u548c\u4f20\u5bfc\u8fdb\u884c\u7684\u4f20\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("crossflow_heat_exchanger.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
