/*
 * nonisothermal_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:15 by COMSOL 6.3.0.290. */
public class nonisothermal_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("ftplistmethod", "manual");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").feature("frrot").setSolveFor("/physics/ht", true);
    model.study("std1").feature("frrot").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.101);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "c2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", 0.01);
    model.component("comp1").geom("geom1").feature("c3").set("pos", new double[]{0.05, 0.05});
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", 0.05);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c4");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0, -0.04});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0, 0.04});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{-0.04, 0});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new double[]{0.04, 0});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("c4", "ls1", "ls2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").common("rot1").selection().set(3);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "-20[rpm]");

    model.component("comp1").physics("spf").selection().set(2, 3);
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 1);
    model.component("comp1").physics("spf").feature("iwbc1").selection().set(17, 18, 19, 20);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(5);
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").selection().set(1);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(13, 14, 15, 16);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "60[degC]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(1, 2, 7, 12);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("HeatTransferCoefficientType", "ExtNaturalConvection");
    model.component("comp1").physics("ht").feature("hf1").set("Lwall", 0.2);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrow", 0.1);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "nitf1.T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(2, 3);
    model.result("pg4").feature().create("surf2", "Surface");
    model.result("pg4").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("solutionparams", "parent");
    model.result("pg4").feature("surf2").set("expr", "nitf1.T");
    model.result("pg4").feature("surf2").set("smooth", "internal");
    model.result("pg4").feature("surf2").set("showsolutionparams", "on");
    model.result("pg4").feature("surf2").set("data", "parent");
    model.result("pg4").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg4").feature("arws1").set("xnumber", 30);
    model.result("pg4").feature("arws1").set("ynumber", 30);
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg4").feature("arws1").feature().create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.015);
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.015);
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "-0.05[m]", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "0.065[m]", 1);
    model.component("comp1").probe("pdom1").feature().duplicate("ppb2", "ppb1");
    model.component("comp1").probe("pdom1").feature("ppb2").set("expr", "T");
    model.component("comp1").probe("pdom1").feature("ppb2").set("window", "new");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.5,40)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol2").feature("t1").set("maxstepbdf", 0.05);

    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (spf) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 81, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (spf) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 81, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 81, 0);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("expr", "T");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 81, 0);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "nitf1.T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg10").feature("surf1").feature("sel1").selection().set(2, 3);
    model.result("pg10").feature().create("surf2", "Surface");
    model.result("pg10").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg10").feature("surf2").set("showsolutionparams", "on");
    model.result("pg10").feature("surf2").set("solutionparams", "parent");
    model.result("pg10").feature("surf2").set("expr", "nitf1.T");
    model.result("pg10").feature("surf2").set("smooth", "internal");
    model.result("pg10").feature("surf2").set("showsolutionparams", "on");
    model.result("pg10").feature("surf2").set("data", "parent");
    model.result("pg10").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg10").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg10").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg10").feature("surf2").set("inheritplot", "surf1");
    model.result("pg10").feature().create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg10").feature("arws1").set("showsolutionparams", "on");
    model.result("pg10").feature("arws1").set("solutionparams", "parent");
    model.result("pg10").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg10").feature("arws1").set("xnumber", 30);
    model.result("pg10").feature("arws1").set("ynumber", 30);
    model.result("pg10").feature("arws1").set("arrowtype", "cone");
    model.result("pg10").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("arws1").set("showsolutionparams", "on");
    model.result("pg10").feature("arws1").set("data", "parent");
    model.result("pg10").feature("arws1").feature().create("col1", "Color");
    model.result("pg10").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg10").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg10").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg7").run();
    model.result("pg5").set("window", "window1");
    model.result("pg5").run();
    model.result("pg6").set("window", "window2");
    model.result("pg6").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg6").run();
    model.result("pg9").run();
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("surf1").set("colortable", "Wave");
    model.result("pg9").run();
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("udist", 0.015);
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 21, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 41, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 61, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 81, 0);
    model.result("pg9").run();
    model.result().export().create("anim1", "Animation");
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
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg9");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.25);
    model.result().export("anim1").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 31, 0);
    model.result("pg9").run();

    model.title("\u6405\u62cc\u5668\u4e2d\u7684\u975e\u7b49\u6e29\u6d41\u52a8 - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u4e3a\u7b80\u5316\u6405\u62cc\u5668\u4e2d\u7684\u6e29\u5ea6\u5206\u5e03\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("nonisothermal_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
