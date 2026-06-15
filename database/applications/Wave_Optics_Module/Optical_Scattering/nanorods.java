/*
 * nanorods.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:41 by COMSOL 6.3.0.290. */
public class nanorods {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

    model.param().set("lda0", "750[nm]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("f", "c_const/lda0");
    model.param().descr("f", "\u9891\u7387");
    model.param().set("w0", "lda0");
    model.param().descr("w0", "\u5149\u6591\u534a\u5f84");
    model.param().set("z0", "pi*w0^2/lda0");
    model.param().descr("z0", "\u745e\u5229\u8303\u56f4");
    model.param().set("k", "2*pi/lda0");
    model.param().descr("k", "\u4f20\u64ad\u5e38\u6570");
    model.param().set("E0", "1[V/m]");
    model.param().descr("E0", "\u7535\u573a\u5927\u5c0f");
    model.param().set("r_NP", "20[nm]");
    model.param().descr("r_NP", "\u7eb3\u7c73\u68d2\u534a\u5f84");
    model.param().set("N_NP", "40");
    model.param().descr("N_NP", "\u7eb3\u7c73\u68d2\u6570\u91cf");
    model.param().set("dx_NP", "150[nm]");
    model.param().descr("dx_NP", "\u7eb3\u7c73\u68d2\u95f4\u8ddd");
    model.param().set("omega_p", "sqrt(21)*2*pi*f");
    model.param().descr("omega_p", "\u7eb3\u7c73\u68d2\u6750\u6599\u7684\u7b49\u79bb\u5b50\u4f53\u9891\u7387");
    model.param().set("w_air_left", "5*w0");
    model.param().descr("w_air_left", "x < 0 \u90e8\u5206\u7a7a\u6c14\u57df\u5bbd\u5ea6");
    model.param().set("w_air_right", "max(5*w0,1.2*(N_NP-1)*dx_NP)");
    model.param().descr("w_air_right", "x > 0 \u90e8\u5206\u7a7a\u6c14\u57df\u5bbd\u5ea6");
    model.param().set("w_air", "w_air_left+w_air_right");
    model.param().descr("w_air", "\u7a7a\u6c14\u57df\u5bbd\u5ea6");
    model.param().set("h_air", "4*lda0");
    model.param().descr("h_air", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("d_PML", "lda0");
    model.param().descr("d_PML", "PML \u57df\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_air+2*d_PML", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "h_air+2*d_PML", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-w_air_left-d_PML", "0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("pos", "-h_air/2-d_PML", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d_PML", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_NP");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u7eb3\u7c73\u68d2");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "N_NP");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"dx_NP", "0"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view2").axis().set("xmin", "-0.5e-6");
    model.component("comp1").view("view2").axis().set("xmax", "0.5e-6");
    model.component("comp1").view("view2").axis().set("ymin", "-0.5e-6");
    model.component("comp1").view("view2").axis().set("ymax", "0.5e-6");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4, 6, 7, 8, 9);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("ewfd").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("WaveType", "GaussianBeam");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("GaussianBeamType", "PlaneWaves");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("WaveVectorDistributionType", "userdef");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("WaveVectorCount", 51);
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("ktMax", "1.5*ewfd.k0");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("BeamOrientation", "Yaxis");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("w0", "w0");
    model.component("comp1").physics("ewfd").prop("BackgroundField").set("kUserDefined", "-ewfd.k0");
    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("wee2", "WaveEquationElectric", 2);
    model.component("comp1").physics("ewfd").feature("wee2").selection().named("geom1_arr1_dom");
    model.component("comp1").physics("ewfd").feature("wee2")
         .set("DisplacementFieldModel", "DrudeLorentzDispersionModel");
    model.component("comp1").physics("ewfd").feature("wee2").set("omegap", "omega_p");
    model.component("comp1").physics("ewfd").feature("wee2").setIndex("f", 1, 0, 0);

    model.study("std1").feature("freq").set("plist", "f");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a\uff0c\u80cc\u666f (ewfd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ewfd.normEbi");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "inplane");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "-w_air_left", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "w_air_right", 1, 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "cln1");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7edd\u5bf9\u503c\uff1a\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "abs(ewfd.Ex)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "Total", 0);
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "abs(ewfd.Ebx)");
    model.result("pg3").feature("lngr2").setIndex("legends", "Background", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", "6E-6");
    model.result("pg3").set("ymin", 0);
    model.result("pg3").set("ymax", "5e-4");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u7eb3\u7c73\u68d2");

    model
         .description("\u5728\u4e00\u7ec4\u5bc6\u96c6\u7684\u7ec6\u68d2\u4e2d\u5165\u5c04\u9ad8\u65af\u7535\u78c1\u6ce2\u3002\u68d2\u95f4\u8ddd\u548c\u68d2\u76f4\u5f84\u8fdc\u5c0f\u4e8e\u6ce2\u957f\u3002\u5728\u8fd9\u79cd\u60c5\u51b5\u4e0b\uff0c\u68d2\u9635\u5217\u65e0\u6cd5\u8d77\u5230\u884d\u5c04\u5149\u6805\u7684\u4f5c\u7528\uff0c\u800c\u662f\u53d8\u6210\u4e86\u4e00\u4e2a\u8fde\u7eed\u7684\u91d1\u5c5e\u8584\u7247\uff0c\u5149\u6cbf\u7740\u5782\u76f4\u4e8e\u68d2\u7684\u65b9\u5411\u6781\u5316\uff0c\u5bf9\u8fd9\u79cd\u7535\u78c1\u6ce2\uff0c\u68d2\u9635\u5217\u51e0\u4e4e\u900f\u660e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("nanorods.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
