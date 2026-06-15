/*
 * ray_release_from_dipole_antenna_source_2daxi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:25 by COMSOL 6.3.0.290. */
public class ray_release_from_dipole_antenna_source_2daxi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");
    model.component("comp1").physics().create("emw2", "ElectromagneticWaves", "geom1");

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
    model.study("std1").feature("freq").setSolveFor("/physics/emw2", true);

    model.param().set("f0", "1[GHz]");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("lam0", "c_const/f0");
    model.param().descr("lam0", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("theta0", "45[deg]");
    model.param().descr("theta0", "\u6781\u89d2");
    model.param().set("phi0", "0[deg]");
    model.param().descr("phi0", "\u65b9\u4f4d\u89d2");
    model.param().set("L0x", "sin(theta0)*cos(phi0)");
    model.param().descr("L0x", "\u5c04\u7ebf\u65b9\u5411\uff0cx \u5206\u91cf");
    model.param().set("L0y", "sin(theta0)*sin(phi0)");
    model.param().descr("L0y", "\u5c04\u7ebf\u65b9\u5411\uff0cy \u5206\u91cf");
    model.param().set("L0z", "cos(theta0)");
    model.param().descr("L0z", "\u5c04\u7ebf\u65b9\u5411\uff0cz \u5206\u91cf");
    model.param().set("L0r", "sin(theta0)");
    model.param().descr("L0r", "\u5c04\u7ebf\u65b9\u5411\uff0cr \u5206\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{2.5, 140});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -70});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{2.5, 2});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 300);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 2000);
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("rot", -90);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1", "c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r1", "r2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").selection().set(2);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 1);
    model.component("comp1").physics("emw").feature("lport1").selection().set(8);
    model.component("comp1").physics("emw").feature("lport1").set("PortType", "UserDefined");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").feature("lport1").set("hPort", "2[mm]");
    model.component("comp1").physics("emw").feature("lport1").set("wPort", "2.5*2*pi[mm]");
    model.component("comp1").physics("emw").feature("lport1").set("ahPort", new int[]{0, 0, 1});
    model.component("comp1").physics("emw").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("emw").feature("sctr1").selection().set(11, 12);
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 2);
    model.component("comp1").physics("emw").feature("ffd1").feature("ffc1").selection().geom("geom1", 1);
    model.component("comp1").physics("emw").feature("ffd1").feature("ffc1").selection().set(11, 12);
    model.component("comp1").physics("emw2").create("lport1", "LumpedPort", 1);
    model.component("comp1").physics("emw2").feature("lport1").selection().set(8);
    model.component("comp1").physics("emw2").feature("lport1").set("PortType", "UserDefined");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw2").feature("lport1").set("hPort", "2[mm]");
    model.component("comp1").physics("emw2").feature("lport1").set("wPort", "2.5*2*pi[mm]");
    model.component("comp1").physics("emw2").feature("lport1").set("ahPort", new int[]{0, 0, 1});
    model.component("comp1").physics("emw2").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("emw2").feature("sctr1").selection().set(10, 13);

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

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PolarGroup");
    model.result("pg2").label("\u4e8c\u7ef4\u8fdc\u573a (emw)");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("rp1", "RadiationPattern");
    model.result("pg2").feature("rp1").set("legend", "on");
    model.result("pg2").feature("rp1").set("phidisc", "180");
    model.result("pg2").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg2").feature("rp1").create("exp1", "Export");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u8fdc\u573a\uff0c\u589e\u76ca (emw)");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("view", "new");
    model.result("pg3").set("edges", "off");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("data", "dset1");
    model.result("pg3").feature("rp1").set("expr", new String[]{"emw.rGaindBEfar"});
    model.result("pg3").feature("rp1").set("colorexpr", new String[]{"emw.normEfar"});
    model.result("pg3").feature("rp1").set("useradiusascolor", true);
    model.result("pg3").feature("rp1").set("directivityexpr", new String[]{"emw.normEfar^2"});
    model.result("pg3").feature("rp1").set("thetadisc", "180");
    model.result("pg3").feature("rp1").set("phidisc", "90");
    model.result("pg3").feature("rp1").set("directivity", "on");
    model.result("pg3").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg3").feature("rp1").create("exp1", "Export");
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg2").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (emw2)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("expr", "emw2.normE");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("S \u53c2\u6570 (emw2)");
    model.result().numerical("gev2").set("data", "dset1");
    model.result().numerical("gev2").set("expr", new String[]{"emw2.S11dB"});
    model.result().table().create("tbl2", "Table");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").run();
    model.result().numerical("gev2").setResult();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "emw2.Ez");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -1);
    model.result("pg4").feature("surf1").set("rangecolormax", 1);
    model.result("pg4").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickz", "-3[m]");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("c1").set("r", "3[m]");

    model.component("comp2").physics().create("gop", "GeometricalOptics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/gop", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp2").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp2").physics("gop").prop("ComputePhase").setIndex("ComputePhase", 1, 0);
    model.component("comp2").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp2").physics("gop").create("rffr1", "ReleaseFromFarFieldRadiationPattern", -1);
    model.component("comp2").physics("gop").feature("rffr1").set("RayDirectionVector", "Conical");
    model.component("comp2").physics("gop").feature("rffr1").set("cax", new String[]{"L0x", "L0y", "L0z"});
    model.component("comp2").physics("gop").feature("rffr1").setIndex("Nw", 1, 0);
    model.component("comp2").physics("gop").feature("rffr1").set("alphac", "1[deg]");
    model.component("comp2").physics("gop").create("wall1", "Wall", 2);
    model.component("comp2").physics("gop").feature("wall1").selection().set(1);

    model.component("comp2").mesh("mesh2").autoMeshSize(6);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/emw", false);
    model.study("std2").feature("rtrac").setSolveFor("/physics/emw2", false);
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "range(0,0.01,2)");
    model.study("std2").feature("rtrac").set("usesol", true);
    model.study("std2").feature("rtrac").set("notsolmethod", "sol");
    model.study("std2").feature("rtrac").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp2.qx", "comp2.qy", "comp2.qz"});
    model.result().dataset("ray1").set("geom", "geom2");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "ray1");
    model.result("pg5").setIndex("looplevel", 201, 0);
    model.result("pg5").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg5").create("rtrj1", "RayTrajectories");
    model.result("pg5").feature("rtrj1").set("linetype", "line");
    model.result("pg5").feature("rtrj1").create("col1", "Color");
    model.result("pg5").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg5").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg5").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "100*L0r", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "100*L0z", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "2000*L0r", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "2000*L0z", 1, 1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "cln1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "emw2.Ez");
    model.result("pg6").feature("lngr1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "sqrt(r^2+z^2)");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "Ez (FEM)", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("data", "ray1");
    model.result("pg6").feature("glob1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg6").feature("glob1").setIndex("looplevelindices", "range(11,1,201)", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "gop.sum(gop.Ez)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Ez (Ray)", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "gop.sum(gop.L)");
    model.result("pg6").feature("glob1").set("xdataunit", "mm");
    model.result("pg6").run();

    model
         .title("\u5076\u6781\u5929\u7ebf\u6e90\u7684\u5c04\u7ebf\u91ca\u653e\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\uff09");

    model
         .description("\u672c\u6559\u7a0b\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u7ec4\u4ef6\u4e2d\u8ba1\u7b97\u5076\u6781\u5929\u7ebf\u7684\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u3002\u7136\u540e\uff0c\u5728\u5355\u72ec\u7684\u4e09\u7ef4\u6a21\u578b\u7ec4\u4ef6\u4e2d\u4f7f\u7528\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u91ca\u653e\u5c04\u7ebf\uff0c\u4ee5\u521d\u59cb\u5316\u5c04\u7ebf\u7684\u5f3a\u5ea6\u3001\u504f\u632f\u548c\u76f8\u4f4d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ray_release_from_dipole_antenna_source_2daxi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
