/*
 * blasius_boundary_layer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class blasius_boundary_layer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Fluid_Dynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("c").field("dimensionless").field("f");
    model.component("comp1").physics("c").field("dimensionless").component(new String[]{"f", "fprime"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/c", true);

    model.param().set("U0", "0.75[m/s]");
    model.param().descr("U0", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("nu", "1.506137e-5[m^2/s]");
    model.param().descr("nu", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("xE", "2[m]");
    model.param().descr("xE", "\u8ba1\u7b97\u4f4d\u7f6e");
    model.param().set("b0", "nu/U0");
    model.param().descr("b0", "B-L \u6bd4\u4f8b");
    model.param().set("N", "1");
    model.param().descr("N", "\u7f51\u683c\u7ec6\u5316\u56e0\u5b50");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 10, 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", -2, 3);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("a", 1, 2);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", 0, 1);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", 0, 3);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("be", -1, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("be", "f", 3);
    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("c").feature("dir1").selection().set(1);
    model.component("comp1").physics("c").create("dir2", "DirichletBoundary", 0);
    model.component("comp1").physics("c").feature("dir2").selection().set(2);
    model.component("comp1").physics("c").feature("dir2").setIndex("useDirichletCondition", 0, 0);
    model.component("comp1").physics("c").feature("dir2").setIndex("r", 1, 1);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 10000);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run("edg1");

    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");

    model.sol("sol1").feature("s1").feature("dDef").set("mumpsalloc", 1.5);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "f");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").run();

    model.study("std1").label("\u8fd1\u4f3c\u89e3");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("expr", "fprime");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u8fd1\u4f3c\u89e3", 0);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\\eta");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "u/U0");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 10);
    model.result("pg1").set("ymin", 0);
    model.result("pg1").set("ymax", 1.1);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().set(1);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"root.y/sqrt(b0*root.x)"});

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("spf", "LaminarFlow", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/c", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new double[]{3.1, 0.5});
    model.component("comp2").geom("geom2").feature("r1").set("pos", new int[]{-1, 0});
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("pt1", "Point");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("pt1");
    model.component("comp2").geom("geom2").create("pt2", "Point");
    model.component("comp2").geom("geom2").feature("pt2").setIndex("p", 0.5, 1);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").material().create("mat1", "Common");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat1").label("Air");
    model.component("comp2").material("mat1").set("family", "air");
    model.component("comp2").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat1").materialType("nonSolid");

    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp2").physics("spf").feature("inl1").selection().set(1);
    model.component("comp2").physics("spf").feature("inl1").set("U0in", "U0");
    model.component("comp2").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp2").physics("spf").feature("open1").selection().set(3, 5);
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp2").physics("spf").feature("out1").selection().set(6);
    model.component("comp2").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp2").physics("spf").feature("sym1").selection().set(2);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(4, 5);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", "21*N");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("numelem", "10*N");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis3", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").selection().set(1, 6);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("elemcount", "20*N");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("elemratio", 15);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("growthrate", "exponential");
    model.component("comp2").mesh("mesh2").run("map1");

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "U0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "U0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "N", 0);
    model.study("std2").feature("param").setIndex("plistarr", "1 2 4", 0);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-5");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 50);

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "f");
    model.result("pg2").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 1");
    model.result().dataset("dset5").set("geom", "geom2");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset5");
    model.result().dataset("cln1").setIndex("genpoints", "xE", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "xE", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "10*sqrt(b0*xE)", 1, 1);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "u/U0");
    model.result("pg1").feature("lngr2").set("data", "cln1");
    model.result("pg1").feature("lngr2").set("xdataexpr", "y/sqrt(b0*x)");
    model.result("pg1").feature("lngr2").set("legendmethod", "automatic");
    model.result("pg1").feature("lngr2").set("legendprefix", "N= ");
    model.result("pg1").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").set("data", "cln1");
    model.result().numerical("int1").set("expr", new String[]{});
    model.result().numerical("int1").set("descr", new String[]{});
    model.result().numerical("int1").setIndex("expr", "(u/U0-comp1.genext1(fprime))^2/sqrt(b0*x)", 0);
    model.result().numerical("int1").setIndex("descr", "Err^2", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("min1", "MinSurface");
    model.result().numerical("min1").selection().set(1);
    model.result().numerical("min1").setIndex("expr", "1/h", 0);
    model.result().numerical("min1").set("data", "dset5");
    model.result().numerical("min1").set("table", "tbl1");
    model.result().numerical("min1").appendResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("xaxisdata", 3);
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg5").feature("tblp1").set("linemarker", "diamond");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("xlog", true);
    model.result("pg1").run();

    model.title("Blasius \u8fb9\u754c\u5c42");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e0d\u540c\u7f51\u683c\u7684 Blasius \u8fb9\u754c\u5c42\uff0c\u5e76\u63d0\u4f9b\u4eff\u771f\u7ed3\u679c\u4e0e\u76f8\u4f3c\u89e3\u7684\u6bd4\u8f83\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("blasius_boundary_layer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
