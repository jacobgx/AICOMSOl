/*
 * symmetric_laser_cavity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class symmetric_laser_cavity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");

    model.param().set("wlref", "1[um]");
    model.param().descr("wlref", "\u53c2\u8003\u6ce2\u957f");
    model.param().set("d", "1[m]");
    model.param().descr("d", "\u8154\u4f53\u957f\u5ea6");
    model.param().set("R", "1.5*d");
    model.param().descr("R", "\u955c\u9762\u534a\u5f84");
    model.param().set("g", "1-d/R");
    model.param().descr("g", "\u7a33\u5b9a\u6027\u53c2\u6570");
    model.param().set("q", "floor(2*d/wlref)");
    model.param().descr("q", "\u7eb5\u6a21\u6570");
    model.param().set("n", "0");
    model.param().descr("n", "\u6a2a\u6a21\u6570");
    model.param().set("fqn", "c_const/(2*d)*(q+1+1/pi*(1+2*n)*atan(sqrt((1-g)/(1+g))))");
    model.param().descr("fqn", "\u6a21\u5f0f\u9891\u7387");
    model.param().set("wlqn", "c_const/fqn");
    model.param().descr("wlqn", "\u6a21\u5f0f\u6ce2\u957f");
    model.param().set("w0", "sqrt(d*wlqn/(2*pi)*sqrt((1+g)/(1-g)))");
    model.param().descr("w0", "\u5149\u6591\u534a\u5f84");
    model.param().set("z0", "pi*w0^2/wlqn");
    model.param().descr("z0", "\u745e\u5229\u8303\u56f4");
    model.param().set("wR", "sqrt(d*wlqn/pi*sqrt(1/(1-g^2)))");
    model.param().descr("wR", "\u955c\u9762\u5904\u7684\u5149\u6591\u534a\u5f84");
    model.param().set("h0", "10*wR");
    model.param().descr("h0", "\u8154\u4f53\u9ad8\u5ea6");
    model.param().set("k0", "2*pi*fqn/c_const");
    model.param().descr("k0", "\u6ce2\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "hermite");
    model.func("an1").set("expr", "if(n==0,1,if(n==1,2*x,if(n==2,4*x^2-2,0)))");
    model.func("an1").set("args", "n,x");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"d/2-R", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d/2", "h0/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("int1", "mir1");
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view2").axis().set("yscale", 40);

    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "freq1", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "1-nEz", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "fqn", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u9891\u7387", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "frequency");
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "V^2/m^2", 0, 0);
    model.component("comp1").physics("ewbe").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("ewbe").prop("EquationForm").setIndex("freq_src", "userdef", 0);
    model.component("comp1").physics("ewbe").prop("EquationForm").setIndex("freq", "freq1", 0);
    model.component("comp1").physics("ewbe").feature("init1")
         .set("E1", new String[]{"0", "0", "hermite(n,sqrt(2)*y/w0)*exp(-(y/w0)^2)"});
    model.component("comp1").physics("ewbe").feature("init1")
         .set("E2", new String[]{"0", "0", "-hermite(n,sqrt(2)*y/w0)*exp(-(y/w0)^2)*exp(-j*k0*d)"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("A", "intop1(1)");
    model.component("comp1").variable("var1").descr("A", "\u9762\u79ef");
    model.component("comp1").variable("var1").set("nEz", "intop1(realdot(ewbe.Ez,ewbe.Ez))/A");
    model.component("comp1").variable("var1").descr("nEz", "\u5f52\u4e00\u5316");

    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountT", 50);
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountL", 30);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "wlref", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "wlref", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "n", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1 2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("st1").set("splitcomplex", true);

    model.component("comp1").physics("ge").feature("ge1").set("valueType", "real");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u7535\u573a");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "ewbe.normE2");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"freq1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u9891\u7387"});
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical("gev1").setIndex("expr", "fqn", 0);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result("pg1").run();

    model.title("\u5bf9\u79f0\u6fc0\u5149\u8154\u7684\u6a2a\u6a21");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u975e\u7ebf\u6027\u65b9\u7a0b\u7ec4\u6765\u6c42\u89e3\u5bf9\u79f0\u6fc0\u5149\u8154\u7684\u7279\u5f81\u9891\u7387\uff0c\u5176\u4e2d\u4f7f\u7528\u4e86\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u7269\u7406\u573a\u63a5\u53e3\u7684\u53cc\u5411\u516c\u5f0f\u3002\n\n\u8ba1\u7b97\u5f97\u5230\u7684\u7279\u5f81\u9891\u7387\u7ecf\u8fc7\u9a8c\u8bc1\uff0c\u4e0e\u901a\u8fc7\u89e3\u6790\u5f0f\u5f97\u5230\u7684\u503c\u76f8\u5339\u914d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("symmetric_laser_cavity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
