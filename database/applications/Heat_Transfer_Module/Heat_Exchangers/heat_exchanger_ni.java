/*
 * heat_exchanger_ni.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:22 by COMSOL 6.3.0.290. */
public class heat_exchanger_ni {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Heat_Exchangers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("R", "50[um]");
    model.param().descr("R", "\u901a\u9053\u534a\u5f84");
    model.param().set("v_mean", "50[mm/s]");
    model.param().descr("v_mean", "\u5e73\u5747\u901f\u5ea6");
    model.param().set("T_hot", "330[K]");
    model.param().descr("T_hot", "\u6e29\u5ea6\uff0c\u70ed\u901a\u9053");
    model.param().set("T_cold", "300[K]");
    model.param().descr("T_cold", "\u6e29\u5ea6\uff0c\u51b7\u901a\u9053");
    model.param().set("T_mean", "(T_hot+T_cold)/2");
    model.param().descr("T_mean", "\u5e73\u5747\u6e29\u5ea6");
    model.param().set("rho_mean_w", "1000[kg/m^3]");
    model.param().descr("rho_mean_w", "\u6d41\u4f53\u5e73\u5747\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 400, 300});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 400);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "2*R"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "2*R");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", "2*R");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("blk1", "copy1", "cyl1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "blk1*(cyl1+copy1)+blk1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u56fa\u4f53");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u70ed\u901a\u9053");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u51b7\u901a\u9053");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u901a\u9053");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u901a\u9053\u58c1");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1", "uni1"});
    model.component("comp1").selection("adj1").set("exterior", false);
    model.component("comp1").selection("adj1").set("interior", true);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("uni1");
    model.component("comp1").variable("var1").set("rho_w", "rho_mean_w*(1-(T-T_mean)/T_mean)");
    model.component("comp1").variable("var1").descr("rho_w", "\u6d41\u4f53\u5bc6\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
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
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("uni1");

    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rho_w");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").selection().named("sel1");
    model.component("comp1").physics("spf").selection().named("uni1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(5);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_mean");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().set(15);
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("Uavfdf", "v_mean");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(11, 14);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(4, 17);
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(5);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_hot");
    model.component("comp1").physics("ht").create("ifl2", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl2").selection().set(15);
    model.component("comp1").physics("ht").feature("ifl2").set("Tustr", "T_cold");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(11, 14);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "10[um]");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(6, 8, 12, 13);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
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
    model.result("pg4").feature("surf1").feature("sel1").selection().set(6, 8, 12, 13);
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
    model.result("pg4").feature("vol1").feature("sel1").selection().set(1);
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
    model.result("pg4").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("solutionparams", "parent");
    model.result("pg5").feature("iso1").set("expr", "T");
    model.result("pg5").feature("iso1").set("number", 10);
    model.result("pg5").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("iso1").set("smooth", "internal");
    model.result("pg5").feature("iso1").set("showsolutionparams", "on");
    model.result("pg5").feature("iso1").set("data", "parent");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").run();
    model.result("pg5").label("\u7b49\u6e29\u9762\u548c\u4f20\u5bfc\u70ed\u901a\u91cf\u6d41\u7ebf");
    model.result("pg5").run();
    model.result("pg5").feature("iso1").set("levelmethod", "levels");
    model.result("pg5").feature("iso1").set("levels", "range(301,2,329)");
    model.result("pg5").run();
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"ht.dfluxx", "ht.dfluxy", "ht.dfluxz"});
    model.result("pg5").feature("str1").set("descr", "\u4f20\u5bfc\u70ed\u901a\u91cf");
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("linetype", "tube");
    model.result("pg5").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u975e\u7b49\u6e29 MEMS \u6362\u70ed\u5668");

    model
         .description("\u672c\u4f8b\u91c7\u7528\u4e0e\u9644\u5e26\u7684\u201c\u7b49\u6e29 MEMS \u6362\u70ed\u5668\u201d\u6a21\u578b\u76f8\u540c\u7684\u6362\u70ed\u5668\u57fa\u672c\u5355\u5143\u51e0\u4f55\u3002\u4e0d\u8fc7\uff0c\u4e0e\u540e\u8005\u4e0d\u540c\u7684\u662f\uff0c\u672c\u4f8b\u4f7f\u7528\u201c\u975e\u7b49\u6e29\u6d41\u52a8\u201d\u63a5\u53e3\u5c06\u5bf9\u6d41\u548c\u4f20\u5bfc\u4f20\u70ed\u8026\u5408\u5230\u7ba1\u4e2d\u7684\u5f31\u53ef\u538b\u7f29\u7eb3\u7ef4-\u65af\u6258\u514b\u65af\u65b9\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("heat_exchanger_ni.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
