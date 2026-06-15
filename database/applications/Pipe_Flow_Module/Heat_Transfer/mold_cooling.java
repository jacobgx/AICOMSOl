/*
 * mold_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:58 by COMSOL 6.3.0.290. */
public class mold_cooling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("nipfl", "NonisothermalPipeFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/nipfl", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("T_init_mold", "473.15[K]");
    model.param().descr("T_init_mold", "\u521d\u59cb\u6e29\u5ea6\uff0c\u6a21\u5177");
    model.param().set("T_coolant", "288.15[K]");
    model.param().descr("T_coolant", "\u7a33\u6001\u5165\u53e3\u6e29\u5ea6\uff0c\u51b7\u5374\u5242");
    model.param().set("Qw", "10[l/min]");
    model.param().descr("Qw", "\u51b7\u5374\u5242\u6d41\u7387");
    model.param().set("e", "0.046[mm]");
    model.param().descr("e", "\u8868\u9762\u7c97\u7cd9\u5ea6");

    model.func().create("step1", "Step");
    model.func("step1").set("location", 2.5);
    model.func("step1").set("from", 1);
    model.func("step1").set("to", 0);
    model.func("step1").set("smooth", 5);

    model.variable().create("var1");
    model.variable("var1").set("T_inlet", "T_coolant+(T_init_mold-T_coolant)*step1(t[1/s])");
    model.variable("var1").descr("T_inlet", "\u5761\u964d\u7684\u5165\u53e3\u6e29\u5ea6\uff0c\u51b7\u5374\u5242");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "mold_cooling_top.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u65b9\u5411\u76d8");
    model.component("comp1").geom("geom1").feature("imp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").insertFile("mold_cooling_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u51b7\u5374\u6d41\u9053");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel2");

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
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("geom1_csel2_edg");
    model.component("comp1").material().create("sw1", "Switch");
    model.component("comp1").material("sw1").selection().set(1);
    model.component("comp1").material("sw1").feature().create("mat1", "Common");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup()
         .create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("sw1").feature("mat1").label("Aluminum");
    model.component("comp1").material("sw1").feature("mat1").set("family", "aluminum");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu")
         .label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("sw1").feature().create("mat2", "Common");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat2").label("Steel AISI 4340");
    model.component("comp1").material("sw1").feature("mat2").set("family", "steel");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu")
         .label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u805a\u6c28\u916f");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"0.32"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1250"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"1540"});

    model.component("comp1").physics("nipfl").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("nipfl").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("nipfl").feature("pipe1").set("innerd", "1[cm]");
    model.component("comp1").physics("nipfl").feature("pipe1").set("roughness", 13);
    model.component("comp1").physics("nipfl").feature("pipe1").set("e", "e");
    model.component("comp1").physics("nipfl").feature("init1").set("u", 0.1);
    model.component("comp1").physics("nipfl").feature("init1").set("T", "T_init_mold");
    model.component("comp1").physics("nipfl").feature("temp1").set("Tin", "T_inlet");
    model.component("comp1").physics("nipfl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("nipfl").feature("inl1").selection().set(3, 4);
    model.component("comp1").physics("nipfl").feature("inl1").set("spec", 1);
    model.component("comp1").physics("nipfl").feature("inl1").set("qv0", "Qw");
    model.component("comp1").physics("nipfl").create("hofl1", "HeatOutflow", 0);
    model.component("comp1").physics("nipfl").feature("hofl1").selection().set(269, 270);
    model.component("comp1").physics("nipfl").create("wht1", "WallHeatTransfer", 1);
    model.component("comp1").physics("nipfl").feature("wht1").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("nipfl").feature("wht1").create("intfilm1", "InternalFilmResistance", 1);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_init_mold");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 2);

    model.component("comp1").multiphysics().create("pwhtc1", "PipeWallHeatTransfer", 1);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel2_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.003);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.55);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,2,28) range(30,30,600)");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").selection().named("geom1_csel1_dom");
    model.component("comp1").probe("dom1").set("expr", "T2");
    model.component("comp1").probe("dom1").set("descr", "\u6e29\u5ea6");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe("dom1").set("descr", "\u65b9\u5411\u76d8\u5e73\u5747\u6e29\u5ea6");

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("param").setIndex("pname", "T_init_mold", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T_init_mold", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T_coolant", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "K", 1);
    model.study("std1").feature("param").setIndex("pname", "T_coolant", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "K", 1);
    model.study("std1").feature("param").setIndex("pname", "Qw", 0);
    model.study("std1").feature("param").setIndex("plistarr", "20 10", 0);
    model.study("std1").feature("param").setIndex("punit", "l/min", 0);
    model.study("std1").feature("param").setIndex("pname", "e", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0.46 0.046", 1);
    model.study("std1").feature("param").setIndex("punit", "mm", 1);
    model.study("std1").feature("param").set("useaccumtable", true);
    model.study("std1").feature("param").set("keepsol", "last");
    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("damp", "1");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.component("comp1").probe("dom1").genResult("none");

    model.batch("pm1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T2");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").run();
    model.result("pg2").label("\u65b9\u5411\u76d8\u548c\u51b7\u5374\u6d41\u9053\u7684\u6e29\u5ea6");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg2").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "0.5*nipfl.dh");
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("line1").set("inheritplot", "vol1");
    model.result("pg2").feature("line1").set("inheritrange", false);
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 35, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 1, 2);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T2");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").run();
    model.result("pg3").label("\u6a21\u5177\u6e29\u5ea6");
    model.result("pg3").setIndex("looplevel", 19, 0);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6e29\u5ea6 (K)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (pwhtc1)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 35, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").setIndex("looplevel", 1, 2);
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "pwhtc1.radiusExt");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature().create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "T2");
    model.result("pg4").feature("slc1").set("smooth", "internal");
    model.result("pg4").feature("slc1").set("showsolutionparams", "on");
    model.result("pg4").feature("slc1").set("data", "parent");
    model.result("pg4").feature("slc1").set("inheritplot", "line1");
    model.result("pg4").label("\u6e29\u5ea6 (pwhtc1)");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6e29\u5ea6 (K)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "T2");
    model.result("pg4").feature("surf2").set("inheritplot", "slc1");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(3, 5);
    model.result("pg4").run();
    model.result().table("tbl1").set("format", "filled");
    model.result().table().duplicate("tbl3", "tbl1");
    model.result().table("tbl3").set("param", 2);
    model.result().table().duplicate("tbl4", "tbl1");
    model.result().table("tbl4").set("param", 3);
    model.result().table().duplicate("tbl5", "tbl1");
    model.result().table("tbl5").set("param", 4);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("table", "tbl1");
    model.result("pg1").feature("tblp1").set("plotcolumninput", "all");
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u94dd\uff0cQw=20\uff0ce=0.46", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u94dd\uff0cQw=20\uff0ce=0.046", 1);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("table", "tbl3");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u94dd\uff0cQw=10\uff0ce=0.46", 0);
    model.result("pg1").feature("tblp2").setIndex("legends", "\u94dd\uff0cQw=10\uff0ce=0.046", 1);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("tblp3", "tblp1");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp3").set("table", "tbl4");
    model.result("pg1").feature("tblp3").setIndex("legends", "\u94a2\uff0cQw=20\uff0ce=0.46", 0);
    model.result("pg1").feature("tblp3").setIndex("legends", "\u94a2\uff0cQw=20\uff0ce=0.046", 1);
    model.result("pg1").feature().duplicate("tblp4", "tblp3");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp4").set("table", "tbl5");
    model.result("pg1").feature("tblp4").setIndex("legends", "\u94a2\uff0cQw=10\uff0ce=0.46", 0);
    model.result("pg1").feature("tblp4").setIndex("legends", "\u94a2\uff0cQw=10\uff0ce=0.046", 1);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").label("\u65b9\u5411\u76d8\u5e73\u5747\u6e29\u5ea6");
    model.result("pg4").run();

    model.title("\u6ce8\u5851\u6a21\u5177\u51b7\u5374");

    model
         .description("\u7531\u4e8e\u6750\u6599\u7684\u7ed3\u6784\u5b8c\u6574\u6027\u4e0e\u51b7\u5374\u5386\u53f2\u76f8\u5173\uff0c\u56e0\u6b64\u4ed4\u7ec6\u63a7\u5236\u6ce8\u5851\u6a21\u5177\u7684\u51b7\u5374\u5bf9\u4e8e\u5851\u6599\u6a21\u5177\u5b9e\u73b0\u6240\u9700\u7684\u51b7\u5374\u5386\u53f2\u975e\u5e38\u91cd\u8981\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7ba1\u9053\u6d41\u6a21\u5757\u201d\u6a21\u62df\u6c7d\u8f66\u65b9\u5411\u76d8\u7684\u6ce8\u5851\u805a\u6c28\u916f\u90e8\u4ef6\u7684\u51b7\u5374\u8fc7\u7a0b\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("mold_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
