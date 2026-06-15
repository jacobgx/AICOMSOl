/*
 * packed_bed_latent_heat_storage.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:12 by COMSOL 6.3.0.290. */
public class packed_bed_latent_heat_storage {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("packed_bed_latent_heat_storage_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("dp", "55[mm]");
    model.param().descr("dp", "\u5c01\u88c5 PCM \u7684\u76f4\u5f84");
    model.param().set("por", "0.49");
    model.param().descr("por", "\u5e8a\u5b54\u9699\u7387");
    model.param().set("V_in", "2[l/min]");
    model.param().descr("V_in", "\u6d41\u7387");
    model.param().set("T0", "32[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Qu", "375[W]");
    model.param().descr("Qu", "\u592a\u9633\u80fd\u52a0\u70ed\u529f\u7387");
    model.param().set("rho_av", "(861[kg/m^3]+778[kg/m^3])/2");
    model.param().descr("rho_av", "\u77f3\u8721\u7684\u5e73\u5747\u5bc6\u5ea6");

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
    model.component("comp1").material("mat2").label("\u77f3\u8721\uff0c\u56fa\u4f53");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u77f3\u8721\uff0c\u6db2\u4f53");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u73bb\u7483\u68c9");
    model.component("comp1").material("mat4").selection().set(4);
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set(2);

    model.component("comp1").physics("fp").selection().set(1, 2, 3);
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("fp").feature("porous1").selection().set(2);
    model.component("comp1").physics("fp").feature("porous1").set("flowModelType", "nonDarcian");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("permeabilityModelType", "ergun");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("dp", "dp");
    model.component("comp1").physics("ht").feature("fluid1").selection().set(1, 3);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("porous1", "PorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("porous1").selection().set(2);
    model.component("comp1").physics("ht").feature("porous1").set("PorousMediumType", "LocalThermalNonequilibrium");
    model.component("comp1").physics("ht").feature("porous1").set("coeffType", "SphericalPellets");
    model.component("comp1").physics("ht").feature("porous1").set("d_p", "dp");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .create("phc1", "PhaseChangeMaterial", 2);
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("phc1")
         .set("T_pc12", "60[degC]");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("phc1").set("dT_pc12", "2[K]");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("phc1")
         .set("L_pc12", "213[kJ/kg]");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("phc1")
         .set("MaterialPhase1", "mat2");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").feature("phc1")
         .set("MaterialPhase2", "mat3");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);

    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"0.4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"1850"});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"0.15"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"2384"});
    model.component("comp1").material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"0.025"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"850"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"1250"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "mat1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"rho_av"});

    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("fp").feature("inl1").selection().set(7);
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("fp").feature("inl1").set("V0fdf", "V_in");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").set(7);

    model.component("comp1").physics("fp").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("fp").feature("out1").selection().set(2);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").set(2);

    model.component("comp1").physics("fp").feature("out1").selection().named("sel2");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel2");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u70ed\u901a\u91cf\u8fb9\u754c");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").add(14, 15, 16, 17, 21, 24, 25, 28, 29);

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 5);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("deltaT", "Qu/V_in/aveop1(ht.Cp)/aveop1(ht.rho)");
    model.component("comp1").variable("var1").descr("deltaT", "\u6e29\u5ea6\u4e0a\u5347");
    model.component("comp1").variable("var1").set("T_in", "aveop1(T)+deltaT");
    model.component("comp1").variable("var1").descr("T_in", "\u5165\u53e3\u6e29\u5ea6");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/fp", false);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.25,3.75) range(4,5[min],9) range(9.25,0.25,24)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-4");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").selection().set(2);

    model.component("comp1").variable("var1").set("T_min", "minop1(ht.porous.pm.T)");
    model.component("comp1").variable("var1").descr("T_min", "\u6700\u4f4e\u6e29\u5ea6");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.T_min > 70[degC]", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "T");
    model.result("pg1").feature("con1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg1").run();
    model.result("pg1").label("\u7b49\u6e29\u7ebf & \u901f\u5ea6\u6d41\u7ebf");
    model.result("pg1").run();
    model.result("pg1").feature("con1").set("contourtype", "filled");
    model.result("pg1").feature("con1").set("number", 15);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 10);
    model.result("pg1").feature("str1").selection().named("sel1");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "p");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "GrayScale");
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "reverse");
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", "0.05 0.47/2 0.42");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("expr", "ht.porous.pm.T", 0);
    model.result().numerical("pev1").setIndex("descr", "\u77f3\u8721\u6e29\u5ea6", 0);
    model.result().numerical("pev1").setIndex("expr", "ht.porous.fluid.T", 1);
    model.result().numerical("pev1").setIndex("descr", "\u6c34\u6e29", 1);
    model.result().numerical("pev1").setIndex("expr", "T", 2);
    model.result().numerical("pev1").setIndex("descr", "\u591a\u5b54\u4ecb\u8d28\u6e29\u5ea6", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "table");
    model.result("pg2").feature("tblp1").set("table", "tbl1");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{2, 3, 4});
    model.result("pg2").feature("tblp1").set("linestyle", "dotted");
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("plotcolumns", new int[]{5, 6, 7});
    model.result("pg2").feature("tblp2").set("linestyle", "dashed");
    model.result("pg2").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg2").feature().duplicate("tblp3", "tblp2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("plotcolumns", new int[]{8, 9, 10});
    model.result("pg2").feature("tblp2").set("linestyle", "solid");
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\u6f14\u53d8");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 3.5);
    model.result("pg2").set("xmax", 9.5);
    model.result("pg2").set("ymin", 328);
    model.result("pg2").set("ymax", 344);
    model.result("pg2").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond4/pcond1/pg2|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond4/pcond1/pg1|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond4/pcond1/pg3|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond6/pcond1/pg4|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond6/pcond1/pg2|ht", "ht/HT_PhysicsInterfaces/icom10/pdef1/pcond6/pcond6/pcond1/pg5|ht"});
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u591a\u5b54\u57fa\u4f53 (ht) 1");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "ht.porous.pm.T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u591a\u5b54\u57fa\u4f53 (ht) 1");
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendstitle", true);
    model.result("pg3").set("looplevel", new int[]{41});
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "ht.porous.fluid.T");
    model.result("pg3").feature("con1").set("number", 10);
    model.result("pg3").feature("con1").set("contourtype", "tubes");
    model.result("pg3").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("con1").set("tuberadiusscale", 0.001);
    model.result("pg3").feature("con1").set("contourlabels", true);
    model.result("pg3").feature("con1").set("colortable", "GrayScale");
    model.result("pg3").feature("con1").set("legendtype", "filled");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "ht.theta2");
    model.result("pg4").feature("vol1").set("descr", "\u76f8\u6307\u793a\u5668\uff0c\u76f8 2");
    model.result("pg4").feature("vol1").set("colortable", "Cividis");
    model.result("pg4").feature("vol1").set("rangecoloractive", true);
    model.result("pg4").feature("vol1").set("rangecolormax", 1);
    model.result("pg4").feature("vol1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("tran1").set("transparency", 0.1);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(4);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminum");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.06);
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowcountactive", true);
    model.result("pg4").feature("str1").set("arrowcount", 120);
    model.result("pg4").feature("str1").set("color", "white");
    model.result("pg4").run();
    model.result("pg4").label("\u6db2\u76f8");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6db2\u76f8\u9971\u548c\u5ea6 (1) \u548c\u901f\u5ea6\u6d41\u7ebf");
    model.result("pg4").set("paramindicator", "Time = eval(t,h) h");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("looplevel", new int[]{53});
    model.result("pg4").run();

    model.view("view4").set("showgrid", false);
    model.view("view4").set("showaxisorientation", false);

    model.result("pg4").set("looplevel", new int[]{17});
    model.result("pg4").set("showlegends", false);
    model.result("pg4").set("looplevel", new int[]{41});
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{65});
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{81});
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{53});
    model.result("pg4").set("showlegends", true);
    model.result("pg4").run();

    model.title("\u586b\u5145\u5e8a\u6f5c\u70ed\u50a8\u5b58");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u586b\u5145\u5e8a\u6f5c\u70ed\u50a8\u7f50\u3002\u6f5c\u70ed\u50a8\u5b58\u7cfb\u7edf\u5229\u7528\u76f8\u53d8\u6750\u6599\u50a8\u5b58\u80fd\u91cf - \u901a\u5e38\u662f\u592a\u9633\u80fd\u96c6\u70ed\u5668\u63d0\u4f9b\u7684\u80fd\u91cf\u3002\u8fd9\u79cd\u6784\u578b\u9700\u8981\u8003\u8651\u76f8\u53d8\u6750\u6599\u77f3\u8721\u4e0e\u5145\u6db2\u6c34\u4e4b\u95f4\u7684\u5c40\u90e8\u70ed\u975e\u5e73\u8861\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("packed_bed_latent_heat_storage.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
