/*
 * radar_cross_section.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:40 by COMSOL 6.3.0.290. */
public class radar_cross_section {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Scattering_and_RCS");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").set("plist", "100[MHz]");

    model.param().set("phi", "0[deg]");
    model.param().descr("phi", "\u5165\u5c04\u89d2\uff0c\u5ea6");
    model.param().set("r0", "12[m]");
    model.param().descr("r0", "\u534a\u5f84\uff0c\u7a7a\u6c14\u57df");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r0+3[m]");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "3[m]", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new int[]{6, 0});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new int[]{4, 2});
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca2").set("point1", new int[]{4, -2});
    model.component("comp1").geom("geom1").feature("ca2").set("point2", new int[]{6, 0});
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 270);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{-4, -2});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{4, -2});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new int[]{-4, 2});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new int[]{4, 2});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -4, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 2, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -6, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 2, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -8, 0, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -8, 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -6, 0, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -2, 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -4, 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -2, 1, 2);
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input")
         .set("ca1", "ca2", "ls1", "ls2", "qb1", "qb2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u8239\u8eab\u8fb9\u754c");
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(4);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 5, 6);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").physics("emw").selection().set(1, 2, 3, 5, 6);
    model.component("comp1").physics("emw").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("emw").prop("BackgroundField")
         .set("Eb", new String[]{"0", "0", "1[V/m]*exp(j*emw.k0*(x*cos(phi)+y*sin(phi)))"});
    model.component("comp1").physics("emw").prop("components").set("components", "outofplane");
    model.component("comp1").physics("emw").create("imp1", "Impedance", 1);
    model.component("comp1").physics("emw").feature("imp1").selection().named("sel1");
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 2);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").setIndex("pname_aux", "phi", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "rad", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "phi", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "rad", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "range(0,1,360)", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a\uff0c\u80cc\u666f (emw)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "emw.normEbi");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").label("\u4e8c\u7ef4\u8fdc\u573a (emw)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("legend", "on");
    model.result("pg3").feature("rp1").set("phidisc", "180");
    model.result("pg3").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg3").feature("rp1").create("exp1", "Export");
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "emw.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "emw.relEz");
    model.result("pg1").feature("surf1").set("descr", "\u76f8\u5bf9\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "abs(comp1.emw.relEz)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{31}, 0);
    model.result("pg3").run();
    model.result("pg3").feature("rp1").set("phidisc", 360);
    model.result("pg3").run();
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "10*log10(emw.mRCS2D)", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "phi");
    model.result("pg4").feature("glob1").set("titletype", "manual");
    model.result("pg4").feature("glob1").set("title", "\u5355\u4f4d\u957f\u5ea6 RCS\uff0c\u5206\u8d1d");
    model.result("pg4").run();

    model.title("\u96f7\u8fbe\u6563\u5c04\u622a\u9762");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6a21\u62df\u4f7f\u7528\u96f7\u8fbe\u68c0\u6d4b\u8239\u53ea\uff0c\u6f14\u793a\u7535\u78c1\u6563\u5c04\u95ee\u9898\u4e2d\u80cc\u666f\u573a\u7684\u5e94\u7528\u3002\u7ed3\u679c\u5305\u62ec\u96f7\u8fbe\u6563\u5c04\u622a\u9762\uff0c\u4ee5\u53ca\u4e0e\u5165\u5c04\u6ce2\u89d2\u5ea6\u6210\u51fd\u6570\u5173\u7cfb\u7684\u8fdc\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("radar_cross_section.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
