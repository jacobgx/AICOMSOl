/*
 * conical_dielectric_probe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:22 by COMSOL 6.3.0.290. */
public class conical_dielectric_probe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Medical_Technology");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

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

    model.param().set("r1", "0.003[m]");
    model.param().descr("r1", "\u6ce2\u5bfc\u534a\u5f84");
    model.param().set("fc", "1.841*c_const/2/pi/r1");
    model.param().descr("fc", "\u622a\u6b62\u9891\u7387");
    model.param().set("f0", "35[GHz]");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("lda0", "c_const/f0");
    model.param().descr("lda0", "\u6ce2\u957f\uff0c\u81ea\u7531\u7a7a\u95f4");
    model.param().set("l_probe", "12.8[mm]");
    model.param().descr("l_probe", "\u9525\u5f62\u63a2\u9488\u957f\u5ea6");
    model.param().set("w1_probe", "3[mm]");
    model.param().descr("w1_probe", "\u9525\u5f62\u63a2\u9488\u5bbd\u5ea6 1");
    model.param().set("w2_probe", "0.58[mm]");
    model.param().descr("w2_probe", "\u9525\u5f62\u63a2\u9488\u5bbd\u5ea6 2");
    model.param().set("T0", "34[degC]");
    model.param().descr("T0", "\u76ae\u80a4\u521d\u59cb\u6e29\u5ea6");

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 75);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "lda0", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r1", "50"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1, 50});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{3, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 w2_probe w2_probe w1_probe w1_probe 0 0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "-l_probe -l_probe -l_probe 0 0 0 0 -l_probe");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r3", 2);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{35, 32.2});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{0, -45});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("r4", 2, 3);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", 10);
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{6.5, 0.7});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{0, -13.5});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1, 9);

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
    model.component("comp1").material("mat2").label("PTFE");
    model.component("comp1").material("mat2").selection().set(5, 6, 7, 10);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});

    model.component("comp1").physics("emw").prop("outofplanewavenumber").set("mFloquet", 1);
    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 1);
    model.component("comp1").physics("emw").feature("pec2").selection().set(23, 24, 25, 27);
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(16);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port1").set("Pin", "1[mW]");
    model.component("comp1").physics("emw").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("emw").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 2);

    model.component("comp1").mesh("mesh1").run();

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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "emw.Er");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u8fdc\u573a\uff0c\u6781\u5750\u6807");
    model.result("pg2").run();
    model.result("pg2").feature("rp1").set("refdir", new int[]{0, 1, 0});
    model.result("pg2").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u4e09\u7ef4\u8fdc\u573a");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    model.component("comp1").physics("emw").create("wee2", "WaveEquationElectric", 2);
    model.component("comp1").physics("emw").feature("wee2").selection().set(3, 4);
    model.component("comp1").physics("emw").feature("wee2").set("DisplacementFieldModel", "DielectricLoss");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Skin");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "3391[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1109[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.37[W/(m*K)]", "0", "0", "0", "0.37[W/(m*K)]", "0", "0", "0", "0.37[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("frequencyfactor", "4.575e72[1/s]");
    model.component("comp1").material("mat3").propertyGroup("def").set("activationenergy", "4.71e5[J/mol]");
    model.component("comp1").material("mat3").selection().set(3, 4);
    model.component("comp1").material("mat3").propertyGroup()
         .create("DielectricLoss", "DielectricLoss", "Dielectric_losses");
    model.component("comp1").material("mat3").propertyGroup("DielectricLoss").set("epsilonBis", new String[]{"10"});
    model.component("comp1").material("mat3").propertyGroup("DielectricLoss").set("epsilonPrim", new String[]{"5"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result("pg3").run();

    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").label("\u80bf\u7624\u76ae\u80a4");
    model.component("comp1").material("mat4").selection().set(4);
    model.component("comp1").material("mat4").propertyGroup("DielectricLoss").set("epsilonBis", new String[]{"15"});
    model.component("comp1").material("mat4").propertyGroup("DielectricLoss").set("epsilonPrim", new String[]{"8"});

    model.component("comp1").physics().create("ht", "BioHeat", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/ht", true);

    model.component("comp1").physics("ht").selection().set(3, 4);
    model.component("comp1").physics("ht").feature("bt1").create("tdam1", "ThermalDamage", 2);
    model.component("comp1").physics("ht").feature("bt1").feature("tdam1")
         .set("TransformationModel", "ArrheniusKinetics");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 2);

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("punit", "Hz");
    model.study("std2").feature("freq").set("plist", "1[MHz]");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ht", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ht", false);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/emh1", false);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/emw", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").feature("time").setSolveFor("/physics/emw", false);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0,15[s],10)");
    model.study("std2").setGenPlots(false);
    model.study("std2").setStoreSolution(true);
    model.study("std2").showAutoSequences("all");

    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 270);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6");
    model.result("pg4").set("data", "rev2");
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "T-T0");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u574f\u6b7b\u7ec4\u7ec7\u5360\u6bd4");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 41, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "ht.theta_d");
    model.result("pg5").feature("surf1").set("descr", "\u635f\u4f24\u5206\u6570");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u963b\u635f\u8017");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "emw.Qrh");
    model.result("pg6").feature("surf1").set("descr", "\u7535\u963b\u635f\u8017");
    model.result("pg6").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg4").run();

    model
         .title("\u7528\u4e8e\u76ae\u80a4\u764c\u8bca\u65ad\u7684\u5706\u9525\u5f62\u4ecb\u7535\u63a2\u9488\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u5229\u7528\u4f4e\u529f\u7387 35\u00a0GHz Ka \u6ce2\u6bb5\u6beb\u7c73\u6ce2\u6280\u672f\uff0c\u5e76\u7ed3\u5408\u5176\u5bf9\u6c34\u5206\u7684\u53cd\u5c04\u7387\uff0c\u5b9e\u73b0\u65e0\u521b\u764c\u75c7\u8bca\u65ad\u3002\u7531\u4e8e\u76ae\u80a4\u80bf\u7624\u6bd4\u5065\u5eb7\u76ae\u80a4\u542b\u6709\u66f4\u591a\u6c34\u5206\uff0c\u4f7f\u5f97\u8be5\u9891\u6bb5\u7684\u53cd\u5c04\u6548\u5e94\u66f4\u5f3a\uff0c\u56e0\u6b64\u63a2\u9488\u80fd\u591f\u68c0\u6d4b\u5230\u80bf\u7624\u90e8\u4f4d\u7684\u5f02\u5e38 S \u53c2\u6570\u3002\u540c\u65f6\uff0c\u8fd8\u53ef\u4ee5\u5206\u6790\u76ae\u80a4\u7684\u6e29\u5ea6\u53d8\u5316\u548c\u574f\u6b7b\u7ec4\u7ec7\u5360\u6bd4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("conical_dielectric_probe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
