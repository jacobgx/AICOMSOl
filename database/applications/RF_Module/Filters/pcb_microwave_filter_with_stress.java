/*
 * pcb_microwave_filter_with_stress.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:36 by COMSOL 6.3.0.290. */
public class pcb_microwave_filter_with_stress {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

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

    model.param().set("fstart", "750[MHz]");
    model.param().descr("fstart", "\u8d77\u59cb\u9891\u7387");
    model.param().set("fstop", "1.5[GHz]");
    model.param().descr("fstop", "\u505c\u6b62\u9891\u7387");
    model.param().set("fstep", "50[MHz]");
    model.param().descr("fstep", "\u9891\u7387\u6b65\u8fdb");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{89.49, 29.54, 1.27});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, -10, 0});
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 1.27);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new double[]{13.88, 1.125});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{5, 5.86});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{13.88, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{13.32, 0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{18.88, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{5, 9.54});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new double[]{32.2, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new double[]{15.09, 0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new double[]{37.2, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input")
         .set("r1", "r2", "r3", "r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("pos", new double[]{44.745, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("mir1", "r1", "r2", "r3", "r4", "r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", 1.27);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord2", new double[]{0, 1.125});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("pos", new double[]{44.745, 0});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("mir1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", -1.27, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{100, 40, 15});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{-5, -15, -10});
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material("mat2").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat2").set("family", "pcb");
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"10.8"});

    model.component("comp1").physics("emw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("emw").feature("sctr1").selection().set(1, 2, 3, 4, 5, 18);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(10);
    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(16);
    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(8, 11);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "S11");
    model.component("comp1").probe("var1").set("expr", "emw.S11dB");
    model.component("comp1").probe("var1").set("descr", "S11");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("probename", "S21");
    model.component("comp1").probe("var2").set("expr", "emw.S21dB");
    model.component("comp1").probe("var2").set("descr", "S21");

    model.study("std1").feature("freq").set("plist", "range(fstart,fstep,fstop)");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (emw)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg2").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg3").label("S \u53c2\u6570 (emw)");
    model.result("pg3").feature("glob1").set("titletype", "none");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg3").feature("glob1").set("xdataexpr", "freq");
    model.result("pg3").feature("glob1").set("xdataunit", "GHz");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg4", "SmithGroup");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("rgr1", "ReflectionGraph");
    model.result("pg4").feature("rgr1").set("unit", new String[]{""});
    model.result("pg4").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg4").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg4").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg4").feature("rgr1").set("titletype", "manual");
    model.result("pg4").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg4").feature("rgr1").set("linemarker", "point");
    model.result("pg4").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("rgr1").create("col1", "Color");
    model.result("pg4").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg4").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(8, 11);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 18);

    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "emw.normE");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(8, 11);
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg5").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg5").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg5").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "emw.normE");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(6, 7, 9, 10, 12, 13, 14, 15, 16, 17);
    model.result("pg5").feature("surf2").set("colortable", "Dipole");
    model.result("pg5").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg5").feature("surf2").create("tran1", "Transparency");
    model.result("pg5").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-91.22977878736414, -145.50493323284647, 110.96020905867867});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 30.40121078491211);

    model.result("pg5").set("view", "view4");
    model.result("pg2").run();

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);

    model.component("comp1").physics("solid").selection().set(2);

    model.param().set("fload", "40[N]");
    model.param().descr("fload", "PCB \u4e0a\u7684\u8f7d\u8377");

    model.component("comp1").geom("geom1").measure().selection().init(3);
    model.component("comp1").geom("geom1").measure().selection().set("fin", 2);

    model.param().set("V", "3357.0[mm^3]");
    model.param().descr("V", "PCB \u4f53\u79ef");

    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").selection().set(2);
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "0", "-fload/V"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(6, 10, 12, 15, 16, 17);

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(1);

    model.study("std1").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std1").feature("freq").setSolveFor("/frame/spatial1", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/emw", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std2").feature("freq").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("freq").set("plist", "range(fstart,fstep,fstop)");
    model.study("std2").feature("freq").set("plot", true);
    model.study("std2").feature("freq").set("plotgroup", "pg2");
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw) 1");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 16, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg6").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg7").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg7").label("S \u53c2\u6570 (emw) 1");
    model.result("pg7").feature("glob1").set("titletype", "none");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg7").feature("glob1").set("xdataexpr", "freq");
    model.result("pg7").feature("glob1").set("xdataunit", "Hz");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");

    model.study("std2").feature("freq").set("plotgroup", "pg7");

    model.result().create("pg8", "SmithGroup");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("rgr1", "ReflectionGraph");
    model.result("pg8").feature("rgr1").set("unit", new String[]{""});
    model.result("pg8").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg8").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg8").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg8").feature("rgr1").set("titletype", "manual");
    model.result("pg8").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg8").feature("rgr1").set("linemarker", "point");
    model.result("pg8").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("rgr1").create("col1", "Color");
    model.result("pg8").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg8").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").measure().selection().set(8, 11);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 18);

    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "emw.normE");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(8, 11);
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg9").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg9").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("expr", "emw.normE");
    model.result("pg9").feature("surf2").create("sel1", "Selection");
    model.result("pg9").feature("surf2").feature("sel1").selection().set(6, 7, 9, 10, 12, 13, 14, 15, 16, 17);
    model.result("pg9").feature("surf2").set("colortable", "Dipole");
    model.result("pg9").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf2").create("tran1", "Transparency");
    model.result("pg9").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view5", "geom1");
    model.component("comp1").view("view5").camera()
         .set("position", new double[]{-91.22977878736414, -145.50493323284647, 110.96020905867867});
    model.component("comp1").view("view5").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view5").camera().set("zoomanglefull", 30.40121078491211);

    model.result("pg9").set("view", "view5");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 16, 0);
    model.result("pg10").label("\u5e94\u529b (solid)");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg10").feature("vol1").set("threshold", "manual");
    model.result("pg10").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg10").feature("vol1").set("colortable", "Rainbow");
    model.result("pg10").feature("vol1").set("colortabletrans", "none");
    model.result("pg10").feature("vol1").set("colorscalemode", "linear");
    model.result("pg10").feature("vol1").set("resolution", "custom");
    model.result("pg10").feature("vol1").set("refine", 2);
    model.result("pg10").feature("vol1").set("colortable", "Prism");
    model.result("pg10").feature("vol1").create("def", "Deform");
    model.result("pg10").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg10").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 16, 0);
    model.result("pg11").label("\u52a8\u7f51\u683c");
    model.result("pg11").create("mesh1", "Mesh");
    model.result("pg11").feature("mesh1").set("meshdomain", "volume");
    model.result("pg11").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg11").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg11").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg11").feature("mesh1").feature("sel1").selection().set(1, 2);
    model.result("pg11").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg11").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg11").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("data", "dset1");
    model.result("pg7").feature("glob2").setIndex("descr", "\u65e0\u53d8\u5f62\u60c5\u51b5\u4e0b\u7684 S11", 0);
    model.result("pg7").feature("glob2").setIndex("descr", "\u65e0\u53d8\u5f62\u60c5\u51b5\u4e0b\u7684 S21", 1);
    model.result("pg7").run();
    model.result("pg10").run();
    model.result("pg10").feature("vol1").set("expr", "solid.disp");
    model.result("pg10").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset4");
    model.result("pg10").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u96c6\u603b\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(10);

    model.component("comp1").physics("emw").feature("lport1").selection().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u96c6\u603b\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(16);

    model.component("comp1").physics("emw").feature("lport2").selection().named("sel2");

    model.study().create("std3");
    model.study("std3").create("frawe", "FrequencyAdaptive");
    model.study("std3").feature("frawe").set("plotgroup", "Default");
    model.study("std3").feature("frawe").set("solnum", "auto");
    model.study("std3").feature("frawe").set("notsolnum", "auto");
    model.study("std3").feature("frawe").set("outputmap", new String[]{});
    model.study("std3").feature("frawe").setSolveFor("/physics/emw", true);
    model.study("std3").feature("frawe").setSolveFor("/physics/solid", false);
    model.study("std3").feature("frawe").set("plist", "range(fstart,fstep/20,fstop)");
    model.study("std3").feature("frawe").setSolveFor("/frame/spatial1", false);
    model.study("std3").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "emw", "sel1;sel2");
    model.study("std3").feature("frawe").setEntry("outputmap", "solid", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "solid", "sel1;sel2");
    model.study("std3").feature("frawe").setEntry("outputmap", "frame:spatial1", "selection");
    model.study("std3").feature("frawe").setEntry("outputselectionmap", "frame:spatial1", "sel1;sel2");
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol4").runAll();

    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u7535\u573a (emw) 2");
    model.result("pg12").set("data", "dset5");
    model.result("pg12").setIndex("looplevel", 301, 0);
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").set("showlegendsmaxmin", true);
    model.result("pg12").feature().create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg12").feature("mslc1").set("smooth", "internal");
    model.result("pg12").feature("mslc1").set("data", "parent");
    model.result("pg12").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg12").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg13").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg13").label("S \u53c2\u6570 (emw) 2");
    model.result("pg13").feature("glob1").set("titletype", "none");
    model.result("pg13").feature("glob1").set("xdata", "expr");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg13").feature("glob1").set("xdataexpr", "freq");
    model.result("pg13").feature("glob1").set("xdataunit", "GHz");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg14", "SmithGroup");
    model.result("pg14").set("data", "dset5");
    model.result("pg14").create("rgr1", "ReflectionGraph");
    model.result("pg14").feature("rgr1").set("unit", new String[]{""});
    model.result("pg14").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg14").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg14").label("\u53f2\u5bc6\u65af\u56fe (emw) 2");
    model.result("pg14").feature("rgr1").set("titletype", "manual");
    model.result("pg14").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg14").feature("rgr1").set("linemarker", "point");
    model.result("pg14").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg14").feature("rgr1").create("col1", "Color");
    model.result("pg14").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg14").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").set("data", "dset5");
    model.result("pg15").setIndex("looplevel", 301, 0);
    model.result("pg15").label("\u52a8\u7f51\u683c 1");
    model.result("pg15").create("mesh1", "Mesh");
    model.result("pg15").feature("mesh1").set("meshdomain", "volume");
    model.result("pg15").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg15").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg15").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg15").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg15").feature("mesh1").feature("sel1").selection().set(1, 2);
    model.result("pg15").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg15").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg15").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").feature().remove("mslc1");
    model.result("pg12").run();
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().set(10, 16);
    model.result("pg12").run();
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("glob1").setIndex("descr", "S11 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg13").feature("glob1").setIndex("descr", "S21 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 1);
    model.result("pg13").feature().duplicate("glob2", "glob1");
    model.result("pg13").run();
    model.result("pg13").feature("glob2").set("data", "dset1");
    model.result("pg13").feature("glob2").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg13").feature("glob2").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 1);
    model.result("pg13").feature("glob2").set("linestyle", "cycle");
    model.result("pg13").feature("glob2").set("linemarker", "cycle");
    model.result("pg13").run();

    model.title("\u5e94\u529b\u4f5c\u7528\u4e0b PCB \u4e0a\u7684\u5fae\u6ce2\u6ee4\u6ce2\u5668");

    model
         .description("\u5fae\u5e26\u6ee4\u6ce2\u5668\u53ef\u4ee5\u76f4\u63a5\u5e03\u8bbe\u5728\u5370\u5237\u7535\u8def\u677f (PCB) \u4e0a\uff0c\u8f93\u5165\u4e0e\u8f93\u51fa\u4e4b\u95f4\u63a5\u6709\u5fae\u5e26\u7ebf\uff0c\u6cbf\u7740\u5fae\u5e26\u7ebf\u6709\u8bb8\u591a\u957f\u5ea6\u548c\u5bbd\u5ea6\u4e00\u5b9a\u7684\u5206\u652f\u7ebf\u3002\u8fd9\u79cd\u6ee4\u6ce2\u5668\u7684\u8bbe\u8ba1\u4e3b\u8981\u662f\u9009\u62e9\u5fae\u5e26\u7ebf\u7684\u963b\u6297\u3001\u5206\u652f\u7ebf\u7684\u963b\u6297\u4ee5\u53ca\u5206\u652f\u7ebf\u7684\u957f\u5ea6\u3002\u8fd9\u4e00\u7279\u5b9a\u6ee4\u6ce2\u5668\u6709\u4e03\u6781\u4f4e\u901a Chebyshev \u54cd\u5e94\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\u201d\u63a5\u53e3\u6c42\u89e3\u77e2\u91cf\u4ea5\u59c6\u970d\u5179\u6ce2\u52a8\u65b9\u7a0b\u3002\u7531\u4e8e\u6ee4\u6ce2\u5668\u7684\u7279\u6027\u5bf9\u5206\u652f\u7ebf\u7684\u4f4d\u7f6e\u548c\u957f\u5ea6\u5341\u5206\u654f\u611f\uff1b\u56e0\u6b64\uff0c\u8fd8\u5206\u6790\u4e86\u6ee4\u6ce2\u5668\u7279\u6027\u968f\u673a\u68b0\u53d8\u5f62\u800c\u53d8\u5316\u7684\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("pcb_microwave_filter_with_stress.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
