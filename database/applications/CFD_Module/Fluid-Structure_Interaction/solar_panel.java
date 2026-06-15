/*
 * solar_panel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class solar_panel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("Utop", "25[m/s]");
    model.param().descr("Utop", "\u9876\u90e8\u901f\u5ea6\uff0cCouette \u6d41");
    model.param().set("yLen", "6[m]");
    model.param().descr("yLen", "\u6cbf\u6d41\u5411\u7684\u8fb9\u6846\u957f\u5ea6");
    model.param().set("yEnd", "4[m]");
    model.param().descr("yEnd", "\u6cbf\u6d41\u5411\u7684\u8fb9\u6846\u672b\u7aef\u70b9");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "solar_panel.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();
    model.component("comp1").mesh("mesh1").run();

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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
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

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(7);

    model.component("comp1").physics("spf").selection().set(1, 2);
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("IsotropicDiffusion", true);
    model.component("comp1").physics("spf").prop("InconsistentStabilization").set("delid", "0.5/CFLCMP");
    model.component("comp1").physics("spf").feature("init1")
         .set("u_init", new String[]{"0", "flc1hs(z[1/m]-5,5)*Utop", "0"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "0.5[Pa]*(yEnd-y)/yLen");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 2);
    model.component("comp1").physics("spf").feature("pfc1").selection().set(2, 5, 134, 135);
    model.component("comp1").physics("spf").feature("pfc1").set("pdiff", "pdiff");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1, 4, 319, 320);
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(7);
    model.component("comp1").physics("spf").feature("open1").set("f0", "pdiff*(yEnd-y)/yLen");
    model.component("comp1").physics("spf").feature("open1").set("Uref", "spf.U");
    model.component("comp1").physics("spf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "pdiff", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "-aveop1(v)+Utop", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("initialValueU", "0.5[Pa]", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("spf").feature("ge1").set("SourceTermQuantity", "velocity");

    model.component("comp1").view("view1").camera().set("zoomanglefull", 4.98873);
    model.component("comp1").view("view1").camera().setIndex("position", 83.18067, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -104.07946, 1);
    model.component("comp1").view("view1").camera().setIndex("position", 30.44896, 2);
    model.component("comp1").view("view1").camera().setIndex("target", "1.50", 0);
    model.component("comp1").view("view1").camera().setIndex("target", "1.0", 1);
    model.component("comp1").view("view1").camera().set("target", new String[]{"1.50", "1.0", "3.0"});
    model.component("comp1").view("view1").camera().setIndex("up", -0.1172, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0.16472, 1);
    model.component("comp1").view("view1").camera().setIndex("up", 0.9793517589569092, 2);
    model.component("comp1").view("view1").camera().set("rotationpoint", new String[]{"1.500", "1.0", "3.00"});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", 0.003835, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{0.003835, 0.009164});

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);

    model.sol("sol1").study("std1");

    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_u", "comp1_p", "comp1_ODE1"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdamp", 0.5);
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_ODE1"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", "on");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_ODE1"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u901f\u5ea6 u\uff0c\u538b\u529b p");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_k", "comp1_ep"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", 0.35);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subiter", 3);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subtermconst", "itertol");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subntolfact", 1);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "uncoupled");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").label("\u6e4d\u6d41\u53d8\u91cf");
    model.sol("sol1").feature("s1").feature("se1").set("segstabacc", "segcflcmp");
    model.sol("sol1").feature("s1").feature("se1").set("subinitcfl", 3);
    model.sol("sol1").feature("s1").feature("se1").set("subkppid", 0.65);
    model.sol("sol1").feature("s1").feature("se1").set("subkdpid", 0.05);
    model.sol("sol1").feature("s1").feature("se1").set("subkipid", 0.05);
    model.sol("sol1").feature("s1").feature("se1").set("subcfltol", 0.1);
    model.sol("sol1").feature("s1").feature("se1").set("maxsegiter", 400);
    model.sol("sol1").feature("s1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("s1").feature("se1").feature("ll1").set("lowerlimit", "comp1.k 0 comp1.ep 0 ");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").create("d2", "Direct");
    model.sol("sol1").feature("s1").feature("d2").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d2").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d2").label("\u76f4\u63a5\uff0c\u6e4d\u6d41\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
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
    model.result().dataset("surf1").selection()
         .set(3, 9, 10, 11, 14, 17, 18, 19, 22, 23, 24, 25, 26, 27, 28, 30, 31, 33, 34, 35, 38, 41, 42, 43, 45, 46, 47, 53, 54, 55, 57, 60, 63, 66, 69, 99, 100, 101, 102, 104, 105, 106, 112, 113, 114, 116, 117, 120, 121, 122, 123, 127, 128, 130, 131, 132, 133, 141, 142, 143, 146, 147, 148, 150, 152, 153, 154, 156, 157, 159, 160, 161, 163, 164, 166, 167, 168, 169, 170, 171, 172, 173, 174, 176, 177, 179, 180, 181, 183, 184, 185, 187, 189, 192, 194, 195, 196, 197, 198, 200, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 214, 215, 216, 218, 219, 222, 223, 224, 225, 226, 229, 231, 234, 235, 236, 241, 242, 245, 246, 247, 248, 249, 254, 257, 258, 259, 263, 264, 265, 267, 268, 270, 271, 272, 273, 274, 276, 277, 278, 279, 280, 281, 282, 284, 285, 286, 287, 288, 289, 290, 292, 293, 294, 295, 296, 297, 298, 300, 301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318);
    model.result().dataset("surf1").selection().inherit(false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u538b\u529b");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    return model;
  }

  public static Model run2(Model model) {

    model.result("pg1").run();
    model.result("pg2").set("data", "surf1");
    model.result("pg3").set("data", "surf1");
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg3").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view().duplicate("view2", "view1");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 18.111951);
    model.component("comp1").view("view2").camera().setIndex("position", 105.75335, 0);
    model.component("comp1").view("view2").camera().setIndex("position", -27.82139, 1);
    model.component("comp1").view("view2").camera().setIndex("position", "47.929550", 2);
    model.component("comp1").view("view2").camera().setIndex("target", 1.5000762, 0);
    model.component("comp1").view("view2").camera().setIndex("target", "2.085700", 1);
    model.component("comp1").view("view2").camera().setIndex("target", "10.000", 2);
    model.component("comp1").view("view2").camera().setIndex("up", -0.319806, 0);
    model.component("comp1").view("view2").camera().setIndex("up", "0.082280", 1);
    model.component("comp1").view("view2").camera().setIndex("up", 0.9439024, 2);
    model.component("comp1").view("view2").camera().set("rotationpoint", new String[]{"1.500", "2.085808", "3"});
    model.component("comp1").view("view2").camera().setIndex("rotationpoint", "10.00", 2);
    model.component("comp1").view("view2").camera().setIndex("viewoffset", -0.0134036, 0);
    model.component("comp1").view("view2").camera().set("viewoffset", new String[]{"-0.0134036", "-0.0023040"});

    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").selection().geom("geom1", 1);
    model.result().dataset("dset2").selection().geom("geom1", 1);
    model.result().dataset("dset2").selection().set(1, 4, 198, 200);
    model.result("pg1").feature("slc1").set("quickxmethod", "coord");
    model.result("pg1").feature("slc1").set("quickx", "1e-3");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "surf1");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("data", "dset2");
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", 0.09);
    model.result("pg1").feature("arwl1").set("arrowcount", 100);
    model.result("pg1").feature("arwl1").set("color", "black");
    model.result("pg1").run();

    model.component("comp1").view("view2").camera().set("zoomanglefull", 13.8744935);
    model.component("comp1").view("view2").camera().setIndex("position", 28.756454, 0);
    model.component("comp1").view("view2").camera().setIndex("position", -24.94273, 1);
    model.component("comp1").view("view2").camera().setIndex("position", 11.989544, 2);
    model.component("comp1").view("view2").camera().setIndex("target", 1.500087, 0);
    model.component("comp1").view("view2").camera().set("target", new double[]{1.500087, 0.982494, 10});
    model.component("comp1").view("view2").camera().setIndex("target", 1.63173, 2);
    model.component("comp1").view("view2").camera().setIndex("up", -0.196717, 0);
    model.component("comp1").view("view2").camera().setIndex("up", 0.178311, 1);
    model.component("comp1").view("view2").camera().setIndex("up", 0.964093, 2);
    model.component("comp1").view("view2").camera().setIndex("rotationpoint", "1.50010", 0);
    model.component("comp1").view("view2").camera().set("rotationpoint", new String[]{"1.50010", "0.982500", "10"});
    model.component("comp1").view("view2").camera().setIndex("rotationpoint", 1.63173, 2);
    model.component("comp1").view("view2").camera().setIndex("viewoffset", -0.059072, 0);
    model.component("comp1").view("view2").camera().set("viewoffset", new double[]{-0.059072, 0.003542});

    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset2");
    model.result().dataset("cpl1").set("quickplane", "zx");
    model.result().dataset("cpl1").set("quicky", "2.0");
    model.result("pg2").feature().remove("con1");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "p");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("data", "cpl1");
    model.result("pg2").feature("arws1").set("expr", new String[]{"u", "0", "w"});
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").set("arrowcount", 800);
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").label("\u6d41\u7ebf\u56fe");
    model.result("pg4").feature().remove("surf2");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("data", "dset2");
    model.result("pg4").feature("str1").set("startmethod", "coord");
    model.result("pg4").feature("str1").set("xcoord", "0.5*1^range(1,7), 1.0*1^range(1,7), 1.75*1^range(1,7)");
    model.result("pg4").feature("str1").set("ycoord", -2);
    model.result("pg4").feature("str1").set("zcoord", "range(0.5,3/6,3.5), range(0.5,3/6,3.5), range(0.5,3/6,3.5)");
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result("pg4").feature("str1").set("widthscaleactive", true);
    model.result("pg4").feature("str1").set("widthscale", 0.05);
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "k/(0.5*25^2)");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "WaveLight");
    model.result("pg4").run();

    model.component("comp1").view().duplicate("view3", "view2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", "13.026120");
    model.component("comp1").view("view3").camera().setIndex("position", -21.9256687, 0);
    model.component("comp1").view("view3").camera().setIndex("position", 31.06747055, 1);
    model.component("comp1").view("view3").camera().setIndex("position", 9.904746055, 2);
    model.component("comp1").view("view3").camera().setIndex("target", 1.500137, 0);
    model.component("comp1").view("view3").camera().setIndex("target", "0.9825000", 1);
    model.component("comp1").view("view3").camera().setIndex("target", 1.631793, 2);
    model.component("comp1").view("view3").camera().setIndex("up", 0.1274994, 0);
    model.component("comp1").view("view3").camera().setIndex("up", -0.1694078, 1);
    model.component("comp1").view("view3").camera().setIndex("up", 0.977248, 2);
    model.component("comp1").view("view3").camera().setIndex("rotationpoint", "1.500", 0);
    model.component("comp1").view("view3").camera().setIndex("rotationpoint", "0.982500", 1);
    model.component("comp1").view("view3").camera().setIndex("rotationpoint", "1.631740", 2);
    model.component("comp1").view("view3").camera().setIndex("viewoffset", -0.03788, 0);
    model.component("comp1").view("view3").camera().set("viewoffset", new double[]{-0.03788, -0.0057357121});
    model.component("comp1").view().duplicate("view4", "view3");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 12.5);
    model.component("comp1").view("view4").camera().setIndex("position", 33, 0);
    model.component("comp1").view("view4").camera().setIndex("position", -24, 1);
    model.component("comp1").view("view4").camera().set("position", new int[]{33, -24, 13});
    model.component("comp1").view("view4").camera().setIndex("target", 0, 0);
    model.component("comp1").view("view4").camera().setIndex("target", 1, 1);
    model.component("comp1").view("view4").camera().set("target", new double[]{0, 1, 1.9});
    model.component("comp1").view("view4").camera().setIndex("up", -0.2, 0);
    model.component("comp1").view("view4").camera().setIndex("up", 0.2, 1);
    model.component("comp1").view("view4").camera().set("up", new double[]{-0.2, 0.2, 1});
    model.component("comp1").view("view4").camera().setIndex("rotationpoint", 0, 0);
    model.component("comp1").view("view4").camera().setIndex("rotationpoint", 1, 1);
    model.component("comp1").view("view4").camera().set("rotationpoint", new int[]{0, 1, 2});
    model.component("comp1").view("view4").camera().setIndex("viewoffset", -0.075, 0);
    model.component("comp1").view("view4").camera().set("viewoffset", new double[]{-0.075, 0.04});
    model.component("comp1").view("view4").light("lgt1").set("intensity", 0.6);
    model.component("comp1").view("view4").light("lgt3").set("intensity", 0.4);

    model.result("pg4").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(3, 9, 10, 11, 14, 17, 18, 19, 22, 23, 24, 25, 26, 27, 28, 30, 31, 33, 34, 35, 38, 41, 42, 43, 45, 46, 47, 53, 54, 55, 57, 60, 63, 66, 69, 99, 100, 101, 102, 104, 105, 106, 112, 113, 114, 116, 117, 120, 121, 122, 123, 127, 128, 130, 131, 132, 133, 141, 142, 143, 146, 147, 148, 150, 152, 153, 154, 156, 157, 159, 160, 161, 163, 164, 166, 167, 168, 169, 170, 171, 172, 173, 174, 176, 177, 179, 180, 181, 183, 184, 185, 187, 189, 192, 194, 195, 196, 197, 198, 200, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 214, 215, 216, 218, 219, 222, 223, 224, 225, 226, 229, 231, 234, 235, 236, 241, 242, 245, 246, 247, 248, 249, 254, 257, 258, 259, 263, 264, 265, 267, 268, 270, 271, 272, 273, 274, 276, 277, 278, 279, 280, 281, 282, 284, 285, 286, 287, 288, 289, 290, 292, 293, 294, 295, 296, 297, 298, 300, 301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u652f\u67b6");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(29, 58, 93, 175, 182);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(8, 12, 15, 20, 32, 36, 39, 44, 48, 51, 56, 59, 62, 65, 68, 78, 81, 84, 91, 95, 103, 107, 110, 115, 118, 124, 129);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u7ed3\u6784\u94a2");
    model.component("comp1").selection("sel4")
         .set(4, 5, 6, 8, 9, 11, 12, 13, 14, 15, 16, 17, 21, 22, 24, 25, 27, 33, 34, 35, 40, 42, 45, 47);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u94dd");
    model.component("comp1").selection("sel5").set(3, 7, 10, 18, 19, 20, 23, 26, 28, 30, 36, 38, 41, 43, 46);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u73bb\u7483");
    model.component("comp1").selection("sel6").set(29, 31, 32, 37, 39, 44);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u56fa\u4f53\u529b\u5b66");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel4", "sel5", "sel6"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9664\u73bb\u7483\u5916\u7684\u56fa\u4f53\u57df");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel4", "sel5"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u56fa\u4f53\u57df\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1")
         .label("\u5730\u677f\u548c\u592a\u9633\u80fd\u7535\u6c60\u677f\u5e95\u5ea7");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"adj1"});

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").physics("solid").selection().named("uni1");
    model.component("comp1").physics("solid").feature("lemm1").create("sf1", "Safety", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").selection().named("uni2");
    model.component("comp1").physics("solid").feature("lemm1").create("sf2", "Safety", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("sf2").selection().named("sel6");
    model.component("comp1").physics("solid").feature("lemm1").feature("sf2").set("FailureCriterion", "Rankine");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/fsi1", true);

    model.component("comp1").multiphysics("fsi1").selection().all();
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");

    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics("fsi1").set("CouplingType", "FluidLoading");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("metallic", 0);
    model.component("comp1").material("mat2").set("pearl", 0);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("clearcoat", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "200e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("lambLame", "1.15e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", "7.69e10[Pa]");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("lighting", "cooktorrance");
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("metallic", 0);
    model.component("comp1").material("mat2").set("pearl", 0);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("clearcoat", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("specular", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noisecolor", "custom");
    model.component("comp1").material("mat2").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat2").set("noisescale", 0);
    model.component("comp1").material("mat2").set("noise", "off");
    model.component("comp1").material("mat2").set("noisefreq", 1);
    model.component("comp1").material("mat2").set("normalnoisebrush", "0");
    model.component("comp1").material("mat2").set("normalnoisetype", "0");
    model.component("comp1").material("mat2").set("alpha", 1);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat3").label("Aluminum");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-2.5e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-3.3e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-3.5e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("lambLame", "5.1e10[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("muLame", "2.6e10[Pa]");
    model.component("comp1").material("mat3").set("family", "aluminum");
    model.component("comp1").material("mat1").selection().set(1, 2);
    model.component("comp1").material("mat2").selection().named("sel4");
    model.component("comp1").material("mat2").propertyGroup()
         .create("IsotropicStrengthParameters", "Isotropic_strength_parameters");
    model.component("comp1").material("mat2").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", new String[]{"250[MPa]"});
    model.component("comp1").material("mat3").selection().named("sel5");
    model.component("comp1").material("mat3").propertyGroup()
         .create("IsotropicStrengthParameters", "Isotropic_strength_parameters");
    model.component("comp1").material("mat3").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", new String[]{"270[MPa]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u73bb\u7483");
    model.component("comp1").material("mat4").selection().named("sel6");
    model.component("comp1").material("mat4").propertyGroup().create("Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", new String[]{"35e8"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat4").propertyGroup()
         .create("IsotropicStrengthParameters", "Isotropic_strength_parameters");
    model.component("comp1").material("mat4").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", new String[]{"45[MPa]"});
    model.component("comp1").material("mat4").propertyGroup("IsotropicStrengthParameters")
         .set("sigmac", new String[]{"45[MPa]"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel2");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel3");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/fsi1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setEntry("activate", "spf", false);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2);

    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "auto");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").feature("s1").feature("i1").active(true);
    model.sol("sol2").runAll();

    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").label("\u7814\u7a76 2/\u89e3 2\uff0c\u56fa\u4f53\u57df");
    model.result().dataset("dset4").set("solution", "sol2");
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().named("uni1");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u4e09\u7ef4\u955c\u50cf\uff0c\u89e3 1");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").label("\u4e09\u7ef4\u955c\u50cf\uff0c\u56fa\u4f53\u57df");
    model.result().dataset("mir2").set("data", "dset4");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5730\u677f\u548c\u592a\u9633\u80fd\u7535\u6c60\u677f\u5e95\u5ea7");
    model.result().dataset("surf2").selection().named("dif1");
    model.result().dataset().create("mir3", "Mirror3D");
    model.result().dataset("mir3")
         .label("\u4e09\u7ef4\u955c\u50cf\uff0c\u5730\u677f\u548c\u592a\u9633\u80fd\u7535\u6c60\u677f\u5e95\u5ea7");
    model.result().dataset("mir3").set("data", "surf2");
    model.result().create("pg5", "PlotGroup3D");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "solid.mises");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4f4d\u79fb\u5927\u5c0f\u548c\u6d41\u7ebf");
    model.result("pg6").set("edges", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "mir2");
    model.result("pg6").feature("surf1").set("expr", "solid.disp");
    model.result("pg6").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("data", "mir1");
    model.result("pg6").feature("str1").set("startmethod", "coord");
    model.result("pg6").feature("str1")
         .set("xcoord", "-1.75*1^range(1,7), -0.5*1^range(1,7), 0.5*1^range(1,7), 1.75*1^range(1,7)");
    model.result("pg6").feature("str1").set("ycoord", -2);
    model.result("pg6").feature("str1")
         .set("zcoord", "range(0.5,3/6,3.5), range(0.5,3/6,3.5), range(0.5,3/6,3.5), range(0.5,3/6,3.5)");
    model.result("pg6").feature("str1").set("linetype", "ribbon");
    model.result("pg6").feature("str1").set("widthexpr", "0.03");
    model.result("pg6").feature("str1").set("widthscaleactive", true);
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "WaveLight");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("data", "mir3");
    model.result("pg6").feature("surf2").set("expr", "0");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").set("edges", false);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "solid.lemm1.sf1.s_f");
    model.result("pg7").feature("vol1").set("descr", "von Mises \u5b89\u5168\u7cfb\u6570");
    model.result("pg7").feature("vol1").set("rangecoloractive", true);
    model.result("pg7").feature("vol1").set("rangecolormax", 2000);
    model.result("pg7").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg7").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg7").create("mmv1", "MaxMinVolume");
    model.result("pg7").feature("mmv1").set("expr", "solid.lemm1.sf1.s_f");
    model.result("pg7").feature("mmv1").set("descr", "von Mises \u5b89\u5168\u7cfb\u6570");
    model.result("pg7").feature("mmv1").set("display", "min");
    model.result("pg7").feature("mmv1").set("backgroundcolor", "white");
    model.result("pg7").label("\u5b89\u5168\u7cfb\u6570\uff0c\u97e7\u6027\u6750\u6599");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").set("edges", false);
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", "solid.lemm1.sf2.s_f");
    model.result("pg8").feature("vol1").set("descr", "\u6717\u80af\u5b89\u5168\u7cfb\u6570");
    model.result("pg8").feature("vol1").set("rangecoloractive", true);
    model.result("pg8").feature("vol1").set("rangecolormax", 2000);
    model.result("pg8").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg8").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg8").create("mmv1", "MaxMinVolume");
    model.result("pg8").feature("mmv1").set("expr", "solid.lemm1.sf2.s_f");
    model.result("pg8").feature("mmv1").set("descr", "\u6717\u80af\u5b89\u5168\u7cfb\u6570");
    model.result("pg8").feature("mmv1").set("display", "min");
    model.result("pg8").feature("mmv1").set("backgroundcolor", "white");
    model.result("pg8").label("\u5b89\u5168\u7cfb\u6570\uff0c\u73bb\u7483");
    model.result("pg8").run();

    model.study("std1").feature("stat").setEntry("activate", "solid", false);
    model.study("std1").feature("stat").setEntry("activateCoupling", "fsi1", false);

    model.title("\u5468\u671f\u6027\u6d41\u573a\u4e2d\u7684\u592a\u9633\u80fd\u7535\u6c60\u677f");

    model
         .description("\u672c\u6a21\u578b\u4e3e\u4f8b\u5206\u6790\u5f3a\u98ce\u8377\u8f7d\u5f15\u8d77\u7684\u592a\u9633\u80fd\u7535\u6c60\u677f\u5468\u56f4\u7684\u6d41\u52a8\u4ee5\u53ca\u76f8\u5e94\u7684\u7ed3\u6784\u4f4d\u79fb\u3002\u6240\u7814\u7a76\u7684\u592a\u9633\u80fd\u7535\u6c60\u677f\u4f4d\u4e8e\u89c4\u5219\u6392\u5217\u7684\u76f8\u540c\u7535\u6c60\u677f\u9635\u5217\u4e2d\uff0c\u8fd9\u610f\u5473\u7740\u53ef\u4ee5\u5728\u6d41\u5411\u65bd\u52a0\u5468\u671f\u6027\u6d41\u52a8\u6761\u4ef6\u3002\u8be5\u6a21\u578b\u6c42\u89e3\u81ea\u7531\u6d41\u901f\u4e3a 25\u00a0m/s \u7684\u6d41\u573a\uff0c\u5e76\u8ba1\u7b97\u7531\u6b64\u4ea7\u751f\u7684\u7ed3\u6784\u5e94\u529b\u548c\u4f4d\u79fb\u3002\n\n\u6ce8\uff1a\u672c\u4f8b\u9700\u8981\u201cCFD \u6a21\u5757\u201d\u548c\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u3002");

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("solar_panel.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
