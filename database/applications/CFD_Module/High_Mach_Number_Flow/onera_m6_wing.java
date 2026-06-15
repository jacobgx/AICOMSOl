/*
 * onera_m6_wing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class onera_m6_wing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\High_Mach_Number_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").insertFile("onera_m6_wing_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igf3");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", true);

    model.param().create("par2");
    model.param("par2").set("M_INF", "0.84");
    model.param("par2").descr("M_INF", "Far-field Mach Number");
    model.param("par2").set("M_STEP", "0.25");
    model.param("par2").descr("M_STEP", "Multiplication factor for Mach Number");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "Ideal gas");
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
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
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
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "(def.gamma+1)/2");
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
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material("mat1").set("family", "air");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(21, 22, 23, 24, 41, 42);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(11, 12, 44, 46);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(14, 15, 49, 51);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(17, 18, 54, 56);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(20, 21, 59, 61);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(23, 24, 64, 66);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(1);
    model.component("comp1").selection("sel9").set(26, 27, 69, 71);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").geom(1);
    model.component("comp1").selection("sel10").set(29, 30, 74, 76);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection()
         .set(8, 9, 11, 12, 14, 15, 17, 18, 20, 21, 23, 24, 26, 27, 29, 30);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection()
         .set(39, 41, 44, 46, 49, 51, 54, 56, 59, 61, 64, 66, 69, 71, 74, 76, 80, 82, 83);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "4e-3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", "4e-3");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().set(39, 40);
    model.component("comp1").mesh("mesh1").feature("conv1").set("splitmethod", "center");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(19, 20, 21, 22, 23, 24, 41, 42);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "4e-3");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "1e-3");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1e-3");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 2);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 3);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 4);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 5);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics().create("hmnf", "HighMachNumberFlowTurbulentSpalartAllmaras", "geom1");
    model.component("comp1").physics("hmnf").feature("init1")
         .set("u_init", new String[]{"M_INF*sqrt(hmnf.gamma*hmnf.Rs*293.15[K])*M_STEP", "0", "0"});
    model.component("comp1").physics("hmnf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("hmnf").feature("sym1").selection().set(2);
    model.component("comp1").physics("hmnf").create("hminl1", "HighMachNumberFlowInlet", 2);
    model.component("comp1").physics("hmnf").feature("hminl1").selection().set(1);
    model.component("comp1").physics("hmnf").feature("hminl1").set("T0stat", "293.15[K]");
    model.component("comp1").physics("hmnf").feature("hminl1").set("Ma0", "M_INF*M_STEP");
    model.component("comp1").physics("hmnf").create("hmout1", "HighMachNumberFlowOutlet", 2);
    model.component("comp1").physics("hmnf").feature("hmout1").selection().set(3, 4);

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/hmnf", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/hmnf", true);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "ALPHA", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "ALPHA", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "M_STEP", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.25 0.5 0.75 1", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").setGenPlots(false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("wdi").set("notlistsolnum", 1);
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("listsolnum", 1);
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "wdi");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "wdi");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.001);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u58c1\u8ddd\u79bb (hmnf)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u58c1\u8ddd\u79bb (hmnf)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "stat");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "stat");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").create("p1", "Parametric");
    model.sol("sol1").feature("s2").feature().remove("pDef");
    model.sol("sol1").feature("s2").feature("p1").set("control", "stat");
    model.sol("sol1").feature("s2").set("control", "stat");
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("se1", "Segregated");
    model.sol("sol1").feature("s2").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s2").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s2").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_T", "comp1_p", "comp1_u", "comp1_hmnf_TWall_d", "comp1_hmnf_TWall_u"});
    model.sol("sol1").feature("s2").feature("se1").feature("ss1").set("subdamp", 0.5);
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (hmnf)");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("seconditer", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s2").feature("se1").feature("ss1").label("\u6d41\u52a8\u53d8\u91cf u\u3001p\u3001T");
    model.sol("sol1").feature("s2").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_nutilde"});
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subdamp", 0.35);
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subiter", 3);
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subtermconst", "itertol");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("subntolfact", 1);
    model.sol("sol1").feature("s2").create("i2", "Iterative");
    model.sol("sol1").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (hmnf)");
    model.sol("sol1").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.6);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.4);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.6);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.4);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s2").feature("se1").feature("ss2").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol1").feature("s2").feature("se1").set("maxsegiter", 400);
    model.sol("sol1").feature("s2").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("s2").feature("se1").feature("ll1")
         .set("lowerlimit", "comp1.nutilde 0 comp1.p 1 comp1.T 1 ");
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (hmnf)");
    model.sol("sol1").feature("s2").create("d2", "Direct");
    model.sol("sol1").feature("s2").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (hmnf)");
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");
    model.sol("sol1").feature("v2").set("notlistsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notlistsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("control", "stat");
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("solvertype", "solnum");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("listsolnum", new String[]{"1"});
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("control", "stat");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("int1").selection().named("uni1");
    model.result().numerical("int1")
         .setIndex("expr", "(hmnf.pA-1[atm])/(0.5*hmnf.gamma*M_INF^2*1[atm])*nx/1.5668[m^2]", 0);
    model.result().numerical("int1").setIndex("descr", "Drag coefficient", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2")
         .setIndex("expr", "(hmnf.pA-1[atm])/(0.5*hmnf.gamma*M_INF^2*1[atm])*nz/1.5668[m^2]", 0);
    model.result().numerical("int2").setIndex("descr", "Lift coefficient", 0);
    model.result().numerical("int2").set("table", "tbl1");
    model.result().numerical("int2").appendResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl1").importData("onera_m6_wing_Cp_YbyB_1_up.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl2").importData("onera_m6_wing_Cp_YbyB_1_down.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl3").importData("onera_m6_wing_Cp_YbyB_2_up.txt");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl4").importData("onera_m6_wing_Cp_YbyB_2_down.txt");
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl5").importData("onera_m6_wing_Cp_YbyB_3_up.txt");
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl6").importData("onera_m6_wing_Cp_YbyB_3_down.txt");
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl7").importData("onera_m6_wing_Cp_YbyB_4_up.txt");
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl8").importData("onera_m6_wing_Cp_YbyB_4_down.txt");
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl9").importData("onera_m6_wing_Cp_YbyB_5_up.txt");
    model.result().table().create("tbl11", "Table");
    model.result().table("tbl10").importData("onera_m6_wing_Cp_YbyB_5_down.txt");
    model.result().table().create("tbl12", "Table");
    model.result().table("tbl11").importData("onera_m6_wing_Cp_YbyB_6_up.txt");
    model.result().table().create("tbl13", "Table");
    model.result().table("tbl12").importData("onera_m6_wing_Cp_YbyB_6_down.txt");
    model.result().table().create("tbl14", "Table");
    model.result().table("tbl13").importData("onera_m6_wing_Cp_YbyB_7_up.txt");
    model.result().table().create("tbl15", "Table");
    model.result().table("tbl14").importData("onera_m6_wing_Cp_YbyB_7_down.txt");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "black");
    model.result("pg1").feature("tblp1").set("linemarker", "square");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "Experiment", 0);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("legend", false);
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().named("sel4");
    model.result("pg1").feature("lngr1").set("expr", "(1[atm] - hmnf.pA)/(0.5*hmnf.gamma*M_INF^2*1[atm])");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "(x-0.13793[m])/(0.87646[m]-0.13793[m])");
    model.result("pg1").feature("lngr1").set("linecolor", "black");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "Simulation", 0);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("table", "tbl3");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("table", "tbl4");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").selection().named("sel5");
    model.result("pg2").feature("lngr1").set("xdataexpr", "(x-0.30346[m])/(0.95700[m]-0.30346[m])");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("table", "tbl5");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").set("table", "tbl6");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").selection().named("sel6");
    model.result("pg3").feature("lngr1").set("xdataexpr", "(x-0.44829[m])/(1.0275[m]-0.44829[m])");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("table", "tbl7");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("table", "tbl8");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").selection().named("sel7");
    model.result("pg4").feature("lngr1").set("xdataexpr", "(x-0.55174[m])/(1.0778[m]-0.55174[m])");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("table", "tbl9");
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").set("table", "tbl10");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").selection().named("sel8");
    model.result("pg5").feature("lngr1").set("xdataexpr", "(x-0.62070[m])/(1.1114[m]-0.62070[m])");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("table", "tbl11");
    model.result("pg6").run();
    model.result("pg6").feature("tblp2").set("table", "tbl12");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").selection().named("sel9");
    model.result("pg6").feature("lngr1").set("xdataexpr", "(x-0.65519[m])/(1.1282[m]-0.65519[m])");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("table", "tbl13");
    model.result("pg7").run();
    model.result("pg7").feature("tblp2").set("table", "tbl14");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").selection().named("sel10");
    model.result("pg7").feature("lngr1").set("xdataexpr", "(x-0.68277[m])/(1.1416[m]-0.68277[m])");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").selection().geom("geom1", 2);
    model.result("pg8").selection().named("uni1");
    model.result("pg8").set("edges", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "hmnf.Ma");
    model.result("pg8").run();
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("expr", "hmnf.pA");
    model.result("pg8").feature("con1").set("number", 10);
    model.result("pg8").feature("con1").set("coloring", "uniform");
    model.result("pg8").feature("con1").set("color", "black");
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").run();

    model.title("ONERA M6 \u673a\u7ffc\u4e0a\u7684\u8de8\u97f3\u901f\u6d41");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u7ecf\u5178\u7684\u5916\u90e8\u6d41\u52a8\u95ee\u9898\uff0c\u5e76\u6c42\u89e3 ONERA M6 \u673a\u7ffc\u4e0a\u7684\u9ad8\u901f\u3001\u53ef\u538b\u7f29\u6e4d\u6d41\u95ee\u9898\u3002\u8be5\u95ee\u9898\u6d89\u53ca\u627e\u5230\u4e09\u7ef4\u540e\u63a0\u7ffc\u51e0\u4f55\u5468\u56f4\u6d41\u573a\u7684\u7a33\u6001\u89e3\uff0c\u5176\u4e2d\u673a\u7ffc\u4ee5\u4e2d\u7b49\u5927\u653b\u89d2\u4ea7\u751f\u8de8\u97f3\u901f\u6d41\u52a8\uff0c\u4ece\u800c\u5728\u673a\u7ffc\u4e0a\u8868\u9762\u5f62\u6210\u5f31\u6fc0\u6ce2\u3002\n\n\u514d\u8d23\u58f0\u660e\uff1a\u672c\u6a21\u578b\u4f7f\u7528 ONERA M6 \u673a\u7ffc\u7684\u6570\u636e\u4f5c\u4e3a\u53c2\u8003\uff0c\u4e0d\u4e0e ONERA \u76f8\u5173\u8054\uff0c\u4ea6\u4e0d\u7531\u5176\u62c5\u4fdd\u6216\u8d5e\u52a9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("onera_m6_wing.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
