/*
 * radial_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:15 by COMSOL 6.3.0.290. */
public class radial_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").insertFile("radial_pump_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("mfr", "13.2[kg/s]", "\u8d28\u91cf\u6d41\u7387");
    model.param("par2").set("rpm", "600[rpm]", "\u8f6c\u901f");
    model.param("par2").set("omega", "rpm*2*pi[rad]", "\u89d2\u901f\u5ea6");

    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(26);
    model.component("comp1").selection("sel1").set("color", "custom");
    model.component("comp1").selection("sel1").set("customcolor", new double[]{0, 0, 1});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(16, 33);
    model.component("comp1").selection("sel2").set("color", "custom");
    model.component("comp1").selection("sel2").set("customcolor", new double[]{0, 1, 0});
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(25);
    model.component("comp1").selection("sel3").set("color", "custom");
    model.component("comp1").selection("sel3").set("customcolor", new double[]{1, 0, 0});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(10);
    model.component("comp1").selection("sel4").set("color", "custom");
    model.component("comp1").selection("sel4").set("customcolor", new double[]{1, 0, 0});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(1, 9, 23, 27, 28, 36);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set(2);

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "avein");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "aveout");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("sel2");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().named("sel6");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "-omega");

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

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("PseudoTimeSetting", "On");
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "mfr/material.def.rho/(pi*R_in^2)");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");
    model.component("comp1").physics("spf").create("mp1", "MixingPlane", 2);
    model.component("comp1").physics("spf").feature("mp1").selection().named("sel3");
    model.component("comp1").physics("spf").feature("mp1").selection("item.mixingEdge").set(42);
    model.component("comp1").physics("spf").feature("mp1").set("item.er", new int[]{0, 1, 0});
    model.component("comp1").physics("spf").create("mp2", "MixingPlane", 2);
    model.component("comp1").physics("spf").feature("mp2").selection().named("sel4");
    model.component("comp1").physics("spf").feature("mp2").selection("item.mixingEdge").set(14);
    model.component("comp1").physics("spf").feature("mp2").set("item.er", new int[]{0, 1, 0});
    model.component("comp1").physics("spf").feature("mp2").set("item.flowDirection", "radial");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 2);
    model.component("comp1").physics("spf").feature("pfc1").selection().named("sel5");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("showselection", false);

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").feature().duplicate("frrot1", "frrot");
    model.study("std1").feature("frrot").set("useadvanceddisable", true);
    model.study("std1").feature("frrot").set("disabledphysics", new String[]{"spf/mp1", "spf/mp2"});
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendstitle", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("legendpos", "alternating");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg1").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(2, 3, 11);
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "p");
    model.result("pg1").feature("slc1").set("quickxnumber", 3);
    model.result("pg1").feature("slc1").set("colortable", "Garnet");
    model.result("pg1").run();
    model.result("pg1").create("slc2", "Slice");
    model.result("pg1").feature("slc2").set("expr", "p");
    model.result("pg1").feature("slc2").set("quickxnumber", 1);
    model.result("pg1").feature("slc2").set("titletype", "none");
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").feature("slc2").create("sel1", "Selection");
    model.result("pg1").feature("slc2").feature("sel1").selection().set(3);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("slc3", "slc2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc3").feature("sel1").selection().set(5);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "p");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "slc1");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(19, 20, 22, 29);
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"up(u)", "up(v)", "up(w)"});
    model.result("pg1").feature("arws1").set("titletype", "none");
    model.result("pg1").feature("arws1").set("arrowcount", 20);
    model.result("pg1").feature("arws1").set("arrowbase", "head");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "1e-3");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(10);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws2", "arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws2").set("expr", new String[]{"down(u)", "up(v)", "up(w)"});
    model.result("pg1").feature("arws2").setIndex("expr", "down(v)", 1);
    model.result("pg1").feature("arws2").setIndex("expr", "down(w)", 2);
    model.result("pg1").feature("arws2").set("arrowbase", "tail");
    model.result("pg1").feature("arws2").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws3", "arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws3").set("scale", "5e-3");
    model.result("pg1").run();
    model.result("pg1").feature("arws3").feature("sel1").selection().set(25);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arws4", "arws3");
    model.result("pg1").run();
    model.result("pg1").feature("arws4").set("expr", new String[]{"down(u)", "up(v)", "up(w)"});
    model.result("pg1").feature("arws4").setIndex("expr", "down(v)", 1);
    model.result("pg1").feature("arws4").setIndex("expr", "down(w)", 2);
    model.result("pg1").feature("arws4").set("arrowbase", "tail");
    model.result("pg1").feature("arws4").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().named("sel1");
    model.result().numerical("int1").setIndex("expr", "spf.rho*(u*nx+v*ny+w*nz)", 0);
    model.result().numerical("int1").setIndex("descr", "Inlet Mass Flux", 0);
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").selection().named("sel2");
    model.result().numerical("int2").setIndex("expr", "spf.rho*(u*nx+v*ny+w*nz)", 0);
    model.result().numerical("int2").setIndex("descr", "Outlet Mass Flux", 0);
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").selection().named("sel3");
    model.result().numerical("int3").setIndex("expr", "spf.rho*(up(u)*nx+up(v)*ny+up(w)*nz)", 0);
    model.result().numerical("int3").setIndex("descr", "Upside", 0);
    model.result().numerical("int3").setIndex("expr", "spf.rho*(down(u)*nx+down(v)*ny+down(w)*nz)", 1);
    model.result().numerical("int3").setIndex("descr", "Downside", 1);
    model.result().numerical().duplicate("int4", "int3");
    model.result().numerical("int4").selection().named("sel4");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .setIndex("expr", "(aveout(p/spf.rho)-avein(p/spf.rho))/g_const+(aveout(spf.U)^2-avein(spf.U)^2)/2/g_const", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8868\u9762\u79ef\u5206 3");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u8868\u9762\u79ef\u5206 4");
    model.result().numerical("int4").set("table", "tbl4");
    model.result().numerical("int4").setResult();
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl5");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();

    model.title("\u4f7f\u7528\u6df7\u5408\u5e73\u9762\u5206\u6790\u5f84\u5411\u6cf5");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u6df7\u5408\u5e73\u9762\u65b9\u6cd5\u548c\u51bb\u7ed3\u8f6c\u5b50\u7814\u7a76\uff0c\u8ba1\u7b97\u5f84\u5411\u6cf5\u6a21\u578b\u4e2d\u7684\u5e73\u5747\u6d41\u573a\u3002\u8fd9\u4e2a\u89e3\u8fd1\u4f3c\u6a21\u62df\u4e86\u7531\u8f6c\u5b50\u53f6\u7247\u7684\u5404\u79cd\u53ef\u80fd\u4f4d\u7f6e\u800c\u4ea7\u751f\u7684\u6574\u4f53\u6d41\u52a8\uff0c\u4ece\u800c\u907f\u514d\u4e86\u8fdb\u884c\u9ad8\u6210\u672c\u77ac\u6001\u4eff\u771f\u7684\u9700\u6c42\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("radial_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
