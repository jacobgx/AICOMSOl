/*
 * acoustic_streaming_microchannel_cross_section.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class acoustic_streaming_microchannel_cross_section {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", new String[]{"Viscothermal"});
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance");
    model.component("comp1").physics("acpr").feature("tvb1").selection().all();
    model.component("comp1").physics("acpr").feature("tvb1").set("MechanicalCondition", new String[]{"NoSlip"});
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.component("comp1").multiphysics().create("asdc1", "AcousticStreamingDomainCoupling", 2);
    model.component("comp1").multiphysics("asdc1").set("Source_physics", "acpr");
    model.component("comp1").multiphysics("asdc1").set("Destination_physics", "spf");
    model.component("comp1").multiphysics("asdc1").selection().all();
    model.component("comp1").multiphysics().create("asbc1", "AcousticStreamingBoundaryCoupling", 1);
    model.component("comp1").multiphysics("asbc1").set("Source_physics", "acpr");
    model.component("comp1").multiphysics("asbc1").set("Destination_physics", "spf");
    model.component("comp1").multiphysics("asbc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/spf", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", true);
    model.study("std1").feature("freq").setSolveFor("/physics/fpt", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asdc1", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asbc1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "1.9652[MHz]", "\u7814\u7a76\u9891\u7387");
    model.param().set("T0", "25[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("p0", "1[atm]", "\u73af\u5883\u538b\u529b");
    model.param().set("omega0", "2*pi*f0", "\u7814\u7a76\u89d2\u9891\u7387");
    model.param().set("W", "380[um]", "\u901a\u9053\u6a2a\u622a\u9762\u5bbd\u5ea6");
    model.param().set("H", "160[um]", "\u901a\u9053\u6a2a\u622a\u9762\u9ad8\u5ea6");
    model.param().set("d0", "0.1[nm]", "\u58c1\u4f4d\u79fb");
    model.param().set("a", "3[um]", "\u7c92\u5b50\u534a\u5f84");
    model.param().set("rho_p", "1.05e3[kg/m^3]", "\u7c92\u5b50\u5bc6\u5ea6");
    model.param()
         .set("cp_p", "2.4e3[m/s]", "\u7c92\u5b50\u7684\u538b\u529b\u6ce2\u901f\uff08\u7eb5\u5411/\u538b\u7f29\uff09");
    model.param().set("cs_p", "1.15e3[m/s]", "\u7c92\u5b50\u7684\u526a\u5207\u6ce2\u901f\uff08\u6a2a\u5411\uff09");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("tvb1").set("MechanicalCondition", "Velocity");
    model.component("comp1").physics("acpr").feature("tvb1").set("vel", new String[]{"d0*acpr.iomega", "0", "0"});
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Tref", "T0");
    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("fp1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("minput_temperature", "T0");
    model.component("comp1").physics("spf").feature("prpc1").selection().set(2);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 2, 4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input_src", "root.comp1.acpr.tvb1.Q_tot");
    model.component("comp1").physics("ht").feature("bhs1").selection().set(3);
    model.component("comp1").physics("fpt").prop("Formulation")
         .setIndex("Formulation", "NewtonianIgnoreInertialTerms", 0);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.asdc1.u_lagx");
    model.component("comp1").physics("fpt").feature("df1").set("minput_temperature", "T0");
    model.component("comp1").physics("fpt").feature("df1").selection().set(1);
    model.component("comp1").physics("fpt").create("acof1", "AcoustophoreticForce", 2);
    model.component("comp1").physics("fpt").feature("acof1").selection().set(1);
    model.component("comp1").physics("fpt").feature("acof1").set("minput_temperature", "T0");
    model.component("comp1").physics("fpt").feature("acof1").set("pA_src", "root.comp1.p");
    model.component("comp1").physics("fpt").feature("acof1").set("uaco_src", "root.comp1.acpr.vx");
    model.component("comp1").physics("fpt").feature("acof1").set("cp", "cp_p");
    model.component("comp1").physics("fpt").feature("acof1").set("cs", "cs_p");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "rho_p");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "2*a");
    model.component("comp1").physics("fpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("fpt").feature("relg1")
         .setIndex("x0", "range(10[\u00b5m],(W-10[\u00b5m]-(10[\u00b5m]))/9,W-10[\u00b5m])", 0);
    model.component("comp1").physics("fpt").feature("relg1")
         .setIndex("x0", "range(10[\u00b5m],(H-10[\u00b5m]-(10[\u00b5m]))/7,H-10[\u00b5m])", 1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u58f0\u573a");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/asdc1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/asbc1", true);
    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("time").setSolveFor("/physics/spf", true);
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/asdc1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/asbc1", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u7a33\u6001\u573a");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std3").label("\u7814\u7a76 3\uff1a\u9897\u7c92\u8ddf\u8e2a");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "f0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "1/s", 0);
    model.study("std3").feature("param").setIndex("pname", "f0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "1/s", 0);
    model.study("std3").feature("param").setIndex("pname", "a", 0);
    model.study("std3").feature("param").setIndex("plistarr", "0.4 3", 0);
    model.study("std3").feature("param").setIndex("punit", "um", 0);
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", "1e-3");
    model.study("std3").feature("time").set("tlist", "range(0,0.02,3)");
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/ht", false);
    model.study("std3").feature("time").setSolveFor("/multiphysics/asdc1", false);
    model.study("std3").feature("time").setSolveFor("/multiphysics/asbc1", false);
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "acpr.p_t*i");
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u62c9\u683c\u6717\u65e5\u7a33\u6001\u901f\u5ea6 (asdc1)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "asdc1.U_lag");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"asdc1.u_lagx", "asdc1.u_lagy"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("color", "white");
    model.result("pg2").feature("str1").set("smooth", "internal");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg2").label("\u62c9\u683c\u6717\u65e5\u7a33\u6001\u901f\u5ea6 (asdc1)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "\u00b5m/s");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").run();
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "T-T0");
    model.result("pg3").feature("surf1").set("unit", "mK");
    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "T-T0");
    model.result("pg3").feature("con1").set("number", 8);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("color", "white");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").run();

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol4");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 151, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg4").run();
    model.result("pg4").label("\u9897\u7c92\u8f68\u8ff9 - \u5927 (fpt)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("showlegendstitle", true);
    model.result("pg4").setIndex("looplevel", 6, 0);
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("linetype", "line");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("unit", "mm/s");
    model.result("pg4").feature("traj1").feature("col1").set("colortable", "Rainbow");
    model.result("pg4").feature("traj1").feature("col1").set("colorscalemode", "linear");
    model.result("pg4").feature("traj1").feature("col1").set("legendtitle", "\u901f\u5ea6");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u9897\u7c92\u8f68\u8ff9 - \u5c0f (fpt)");
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").setIndex("looplevel", 151, 0);
    model.result("pg5").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
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
    model.result().export("anim1").label("\u9897\u7c92\u8f68\u8ff9");
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").setIndex("singlelooplevel", 2, 1);
    model.result("pg2").run();

    model.title("\u5fae\u901a\u9053\u6a2a\u622a\u9762\u4e2d\u7684\u58f0\u6d41");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u5fae\u6d41\u4f53\u901a\u9053\u6a2a\u622a\u9762\u4e2d\u7ec6\u80de\u7684\u58f0\u5b66\u5904\u7406\uff0c\u5176\u4e2d\u7ed3\u5408\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df\u201d\u63a5\u53e3\u548c\u201c\u70ed\u9ecf\u6027\u8fb9\u754c\u5c42\u963b\u6297\u201d\u8fb9\u754c\u6761\u4ef6\u6765\u6a21\u62df\u5fae\u6d41\u4f53\u901a\u9053\u4e2d\u7684\u58f0\u573a\uff0c\u4ee5\u5305\u542b\u9ecf\u6027\u8fb9\u754c\u5c42\u7684\u5f71\u54cd\u3002\u7136\u540e\uff0c\u4f7f\u7528\u201c\u58f0\u6d41\uff0c\u57df\u8026\u5408\u201d\u548c\u201c\u58f0\u6d41\uff0c\u8fb9\u754c\u8026\u5408\u201d\u591a\u7269\u7406\u573a\u8026\u5408\u6765\u8ba1\u7b97\u7531\u58f0\u573a\u5f15\u8d77\u7684\u7a33\u5b9a\u6d41\u4f53\u6d41\u52a8\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u6d41\u4f53\u6d41\u52a8\u9897\u7c92\u8ddf\u8e2a\u201d\u63a5\u53e3\u5bf9\u6a2a\u622a\u9762\u4e0a\u7684\u9897\u7c92\u8f68\u8ff9\u8fdb\u884c\u5efa\u6a21\u3002\u9897\u7c92\u8f68\u8ff9\u7531\u9ecf\u6027\u66f3\u529b\uff08\u6765\u81ea\u58f0\u6d41\uff09\u548c\u58f0\u8f90\u5c04\u529b\u4e4b\u95f4\u7684\u5e73\u8861\u63a7\u5236\uff0c\u6a21\u578b\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528 COMSOL Multiphysics \u6765\u5305\u542b\u548c\u6a21\u62df\u8fd9\u4e24\u79cd\u529b\u3002\u6700\u540e\uff0c\u8fd8\u6f14\u793a\u4e86\u5982\u4f55\u6a21\u62df\u7531\u58f0\u573a\u4e2d\u7684\u9ecf\u6ede\u635f\u8017\u5f15\u8d77\u7684\u53d1\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("acoustic_streaming_microchannel_cross_section.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
