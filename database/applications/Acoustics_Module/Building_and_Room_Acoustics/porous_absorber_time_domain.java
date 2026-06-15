/*
 * porous_absorber_time_domain.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class porous_absorber_time_domain {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("L0", "1.5[m]");
    model.param().descr("L0", "Computational domain height");
    model.param().set("w0", "5[cm]");
    model.param().descr("w0", "Porous layer width");
    model.param().set("xs", "-1[m]");
    model.param().descr("xs", "Source location, x-coordinate");
    model.param().set("xr", "1[m]");
    model.param().descr("xr", "Receiver location, x-coordinate");
    model.param().set("ys", "0.5[m]");
    model.param().descr("ys", "Source and receiver location, y-coordinate");
    model.param().set("B", "0.045[m]");
    model.param().descr("B", "Pulse width");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "p_init");
    model.func("an1").set("expr", "(1 - ((x - xs)^2 + (y - ys)^2)/B^2)*exp(-((x - xs)^2 + (y - ys)^2)/B^2/2)");
    model.func("an1").set("args", "x, y");
    model.func().create("pff1", "PartialFraction");
    model.func("pff1").set("funcname", "beta_porous");
    model.func("pff1").set("filename", "porous_absorber_time_domain_compressibility.txt");
    model.func("pff1").run();
    model.func().duplicate("pff2", "pff1");
    model.func("pff2").set("funcname", "rho_porous");
    model.func("pff2").set("filename", "porous_absorber_time_domain_density.txt");
    model.func("pff2").run();
    model.func().duplicate("pff3", "pff2");
    model.func("pff3").set("funcname", "Y_5cm");
    model.func("pff3").set("filename", "porous_absorber_time_domain_admittance.txt");
    model.func("pff3").run();
    model.func().duplicate("pff4", "pff3");
    model.func("pff4").set("funcname", "Y_15cm");
    model.func("pff4").setEntry("columnType", "col4", "real");
    model.func("pff4").setEntry("columnType", "col5", "imag");
    model.func("pff4").run();

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"3*L0", "L0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-1.5*L0", "0"});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L0/5", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"3*L0", "w0"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-1.5*L0", "-w0"});
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "L0/5", 0);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "xs", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "ys", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "xr", 0);
    model.component("comp1").geom("geom1").run("pt2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(2, 3, 5, 6, 8, 9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(1, 4, 7);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(1, 2, 3, 6, 7, 8, 9);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1", "sel2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(4, 11, 18);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(1, 3, 5, 7, 14, 21, 22, 23, 24);

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

    model.component("comp1").physics().create("pate", "PressureAcousticsTimeExplicit", "geom1");
    model.component("comp1").physics("pate").feature("init1").set("p", "p_init(x, y)");
    model.component("comp1").physics("pate").create("pom1", "TransientPoroacousticsModel", 2);
    model.component("comp1").physics("pate").feature("pom1").selection().named("sel2");
    model.component("comp1").physics("pate").feature("pom1").set("CompressibilityApproximantSource", "Function");
    model.component("comp1").physics("pate").feature("pom1")
         .set("CompressibilityApproximantFunctionReference", "pff1");
    model.component("comp1").physics("pate").feature("pom1").set("beta_inf", "7.163920377003123E-6");
    model.component("comp1").physics("pate").feature("pom1")
         .set("beta_R", new String[]{"0.003951422100388825", "6.869447667628804E-4", "0.0017583186110390597"});
    model.component("comp1").physics("pate").feature("pom1")
         .set("beta_xi", new String[]{"-13292.982617324331", "-3597.429942550003", "-830.0269343818281"});
    model.component("comp1").physics("pate").feature("pom1").set("beta_Q", new String[]{});
    model.component("comp1").physics("pate").feature("pom1").set("beta_zeta", new String[]{});
    model.component("comp1").physics("pate").feature("pom1").set("DensityApproximantSource", "Function");
    model.component("comp1").physics("pate").feature("pom1").set("DensityApproximantFunctionReference", "pff2");
    model.component("comp1").physics("pate").feature("pom1").set("rho_inf", "1.3422845046738547");
    model.component("comp1").physics("pate").feature("pom1")
         .set("rho_R", new String[]{"1326.911518068681", "246.51641311321762", "716.2056380466704"});
    model.component("comp1").physics("pate").feature("pom1")
         .set("rho_xi", new String[]{"-5597.444876260025", "-1127.463168726241", "-3.9433555349490784E-4"});
    model.component("comp1").physics("pate").feature("pom1").set("rho_Q", new String[]{});
    model.component("comp1").physics("pate").feature("pom1").set("rho_zeta", new String[]{});
    model.component("comp1").physics("pate").create("md1", "MaterialDiscontinuity", 1);
    model.component("comp1").physics("pate").feature("md1").selection().named("sel4");
    model.component("comp1").physics("pate").create("imp1", "Impedance", 1);
    model.component("comp1").physics("pate").feature("imp1").selection().named("sel5");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().named("sel3");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "343[m/s]/4000[Hz]/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pate", true);
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0, 1[ms], 5[ms]) range(5.025[ms], 0.025[ms], 10[ms])");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "w0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "5[cm] 15[cm]", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.component("comp1").physics().create("pate2", "PressureAcousticsTimeExplicit", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/pate2", true);

    model.component("comp1").physics("pate2").selection().set();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("pate2").feature("init1").set("p2", "p_init(x, y)");
    model.component("comp1").physics("pate2").selection().named("sel1");
    model.component("comp1").physics("pate2").create("imp1", "Impedance", 1);
    model.component("comp1").physics("pate2").feature("imp1").selection().named("sel5");
    model.component("comp1").physics("pate2").create("imp2", "Impedance", 1);
    model.component("comp1").physics("pate2").feature("imp2").selection().named("sel4");
    model.component("comp1").physics("pate2").feature("imp2").set("ImpedanceModel", "RationalApproximation");
    model.component("comp1").physics("pate2").feature("imp2").set("ApproximantSource", "Function");
    model.component("comp1").physics("pate2").feature("imp2").set("ApproximantFunctionReference", "pff3");
    model.component("comp1").physics("pate2").feature("imp2").set("Y_inf", "0.0016558010951927872");
    model.component("comp1").physics("pate2").feature("imp2").set("R", new String[]{"-0.3419867589385943"});
    model.component("comp1").physics("pate2").feature("imp2").set("xi", new String[]{"-899.2977787798595"});
    model.component("comp1").physics("pate2").feature("imp2")
         .set("Q", new String[]{"5.6627512282248045+14.43740720664713*i", "4.495045659987196+0.6418934415255355*i", "4.147624892875908+1.1270068592396467*i"});
    model.component("comp1").physics("pate2").feature("imp2")
         .set("zeta", new String[]{"-2809.0150089623+7804.5270752497245*i", "-692.6119600101805+4390.336723610788*i", "-401.6125135398694+1290.7825635385047*i"});
    model.component("comp1").physics("pate2").create("imp3", "Impedance", 1);
    model.component("comp1").physics("pate2").feature("imp3").selection().named("sel4");
    model.component("comp1").physics("pate2").feature("imp3").set("ImpedanceModel", "RationalApproximation");
    model.component("comp1").physics("pate2").feature("imp3").set("ApproximantSource", "Function");
    model.component("comp1").physics("pate2").feature("imp3").set("ApproximantFunctionReference", "pff4");
    model.component("comp1").physics("pate2").feature("imp3").set("Y_inf", "0.002314667178200755");
    model.component("comp1").physics("pate2").feature("imp3").set("R", new String[]{"-5.04419576372458"});
    model.component("comp1").physics("pate2").feature("imp3").set("xi", new String[]{"-3033.9746369676213"});
    model.component("comp1").physics("pate2").feature("imp3")
         .set("Q", new String[]{"-0.3896649250000104+0.9108445136525672*i", "0.8823743662088483+0.9388007936662108*i", "-0.30218005189517644-0.09739397818851839*i", "1.1922046229470886+0.394987212460588*i", "1.3372915333558664+0.33301422634362865*i", "1.112646928091139+0.8869854989546313*i"});
    model.component("comp1").physics("pate2").feature("imp3")
         .set("zeta", new String[]{"-642.7643054665252+4063.385131804076*i", "-600.0879153916163+3242.5082934800507*i", "-505.9555973079798+4827.358222721193*i", "-494.6564897652551+2290.9766385627186*i", "-396.7959320594626+1292.9575526663477*i", "-229.93586833692075+323.64198246499166*i"});

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/pate", true);
    model.study("std2").feature("time").setSolveFor("/physics/pate2", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tlist", "range(0, 1[ms], 5[ms]) range(5.025[ms], 0.025[ms], 10[ms])");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/physics/pate", false);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"pate2/imp3"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/pate", true);
    model.study("std3").feature("time").setSolveFor("/physics/pate2", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").set("tlist", "range(0, 1[ms], 5[ms]) range(5.025[ms], 0.025[ms], 10[ms])");
    model.study("std3").feature("time").setSolveFor("/physics/pate", false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study("std1").feature("time").setSolveFor("/physics/pate2", false);

    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("dif1");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("dif1");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().named("dif1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").setIndex("looplevel", 26, 0);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "pate2.p_t");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "dset2");
    model.result("pg5").feature("ptgr1").setIndex("looplevelinput", "first", 1);
    model.result("pg5").feature("ptgr1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").feature("ptgr1").setIndex("looplevelindices", "range(6, 1, 206)", 0);
    model.result("pg5").feature("ptgr1").selection().set(10);
    model.result("pg5").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "Extended Reacting", 0);
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").set("data", "dset3");
    model.result("pg5").feature("ptgr2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").feature("ptgr2").setIndex("looplevelindices", "range(6, 1, 206)", 0);
    model.result("pg5").feature("ptgr2").selection().set(10);
    model.result("pg5").feature("ptgr2").set("expr", "comp1.pate2.p_t");
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "Local Reacting", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").setIndex("looplevelinput", "last", 1);
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("data", "dset4");
    model.result("pg6").run();
    model.result("pg2").run();

    model
         .title("\u91c7\u7528\u5c40\u90e8\u548c\u6269\u5c55\u53cd\u5e94\u8fd1\u4f3c\u7684\u591a\u5b54\u5438\u58f0\u4f53\u65f6\u57df\u5efa\u6a21");

    model
         .description("\u672c\u6559\u7a0b\u901a\u8fc7\u65f6\u57df\u5206\u6790\uff0c\u7814\u7a76\u5f53\u5b58\u5728\u591a\u5b54\u5438\u58f0\u4f53\u65f6\uff0c\u58f0\u97f3\u7684\u4f20\u64ad\u60c5\u51b5\u3002\u5176\u4e2d\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5c40\u90e8\u548c\u6269\u5c55\u53cd\u5e94\u8fd1\u4f3c\u6765\u6a21\u62df\u591a\u5b54\u5438\u58f0\u4f53\uff0c\u5e76\u6bd4\u8f83\u4e86\u8fd9\u4e24\u79cd\u65b9\u6cd5\u5728\u4e0d\u540c\u539a\u5ea6\u5438\u58f0\u4f53\u4e0a\u7684\u6548\u679c\u3002\u5438\u58f0\u4f53\u7684\u8017\u6563\u548c\u5171\u632f\u884c\u4e3a\u4e0e\u9891\u7387\u76f8\u5173\u3002\u901a\u8fc7\u4f7f\u7528\u201c\u90e8\u5206\u5206\u5f0f\u62df\u5408\u201d\u529f\u80fd\uff0c\u53ef\u4ee5\u5728\u65f6\u57df\u4eff\u771f\u4e2d\u8003\u8651\u9891\u7387\u4f9d\u8d56\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("porous_absorber_time_domain.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
