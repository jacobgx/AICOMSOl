/*
 * sar_wifi_antenna.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:33 by COMSOL 6.3.0.290. */
public class sar_wifi_antenna {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\EMI_EMC_Applications");

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
    model.study("std1").feature("freq").set("plist", "2.45[GHz]");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "sar_wifi_antenna.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new double[]{0.035, 0.045, 0.025});
    model.component("comp1").geom("geom1").feature("elp1").set("pos", new double[]{0, -0.005, 0.04});
    model.component("comp1").geom("geom1").run("elp1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.004, 0.05, 0.05});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0.10", "0", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("blk1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 0.0275);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new double[]{0.0039, 0.01125});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{0, 0.019375});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("r1", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new double[]{0.006, 0.00925});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new double[]{0.00195, 0.0045});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2")
         .set("mir1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("blk1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new double[]{0.004, 0.0039});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 0.18);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", 0.05, 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4, 8, 9, 10, 11);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(62, 66);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(63);
    model.component("comp1").physics("emw").create("sar1", "SpecificAbsorptionRate", 3);
    model.component("comp1").physics("emw").feature("sar1").selection().set(6, 7);
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 3);

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
    model.component("comp1").material("mat2").selection().set(12);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5927\u8111");
    model.component("comp1").material("mat3").selection().set(7);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"54.7"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"2.09"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5934\u90e8");
    model.component("comp1").material("mat4").selection().set(6);
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"11.35"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"0.4"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"2000"});

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 8, 9);
    model.component("comp1").view("view1").hideEntities().create("hide2");
    model.component("comp1").view("view1").hideEntities("hide2").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide2").set(9, 10, 13, 14, 15, 16, 39, 40);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(62, 66);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(5, 6, 7, 8, 37, 38, 47, 52);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(62, 66);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 41, 42, 43, 44, 54, 55, 56, 57);
    model.result("pg2").feature("surf2").set("colortable", "ThermalWave");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf2").feature("mtrl1").set("family", "plastic");
    model.result("pg2").feature("surf2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "emw.normE");
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().set(5);
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("mslc1").set("colortabletype", "discrete");
    model.result("pg2").feature("mslc1").set("bandcount", 20);
    model.result("pg2").feature("mslc1").create("tran1", "Transparency");
    model.result("pg2").feature("mslc1").feature("tran1").set("transparency", 0.5);
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("expr", "emw.normE");
    model.result("pg2").feature("surf3").create("sel1", "Selection");
    model.result("pg2").feature("surf3").feature("sel1").selection().set(58, 59, 60, 61, 63, 64, 65);
    model.result("pg2").feature("surf3").set("colortable", "Dipole");
    model.result("pg2").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf3").create("tran1", "Transparency");
    model.result("pg2").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-0.7322786683621615, -0.9763714541559635, 0.7322786683621615});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 23.965349197387695);

    model.result("pg2").set("view", "view4");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6bd4\u5438\u6536\u7387 (sar1)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("expr", "emw.SAR");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").label("\u4e8c\u7ef4\u8fdc\u573a (emw)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("legend", "on");
    model.result("pg4").feature("rp1").set("phidisc", "180");
    model.result("pg4").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg4").feature("rp1").create("exp1", "Export");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u8fdc\u573a\uff0c\u589e\u76ca (emw)");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("view", "new");
    model.result("pg5").set("edges", "off");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("expr", new String[]{"emw.rGaindBEfar"});
    model.result("pg5").feature("rp1").set("colorexpr", new String[]{"emw.normEfar"});
    model.result("pg5").feature("rp1").set("useradiusascolor", true);
    model.result("pg5").feature("rp1").set("directivityexpr", new String[]{"emw.normEfar^2"});
    model.result("pg5").feature("rp1").set("thetadisc", "45");
    model.result("pg5").feature("rp1").set("phidisc", "90");
    model.result("pg5").feature("rp1").set("directivity", "on");
    model.result("pg5").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg5").feature("rp1").create("exp1", "Export");
    model.result("pg5").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg5").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "HeatCamera");
    model.result("pg1").feature("mslc1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("filt1").set("expr", "z<0.04 && x>0");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("thetadisc", 90);
    model.result("pg5").feature("rp1").set("colortable", "Twilight");
    model.result("pg5").feature("rp1").set("colortabletrans", "reverse");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("SAR 1g");
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "emw.SAR1g");
    model.result("pg6").feature("slc1").set("descr", "\u6bd4\u5438\u6536\u7387\uff0c1g");
    model.result("pg6").feature("slc1").set("quickplane", "zx");
    model.result("pg6").feature("slc1").set("quickynumber", 1);
    model.result("pg6").feature("slc1").set("colortable", "ThermalWaveDark");
    model.result("pg6").run();

    model.title("Wi-Fi \u5929\u7ebf\u9644\u8fd1\u4eba\u4f53\u5934\u90e8\u7684 SAR");

    model
         .description("\u4f7f\u7528\u542b\u8f90\u5c04\u88c5\u7f6e\u7684\u6d88\u8d39\u7535\u5b50\u4ea7\u54c1\u7684\u7528\u6237\u4f1a\u53d7\u5230\u5c04\u9891 (RF) \u53d1\u5c04\u7684\u5f71\u54cd\u3002\u8f90\u5c04\u66dd\u5149\u91cf\u5b9a\u4e49\u4e3a\u6bd4\u5438\u6536\u7387 (SAR)\uff0c\u4e5f\u5c31\u662f\u8bf4\uff0cSAR \u503c\u8868\u793a\u4eba\u4f53\u5438\u6536\u7684\u5c04\u9891 (RF) \u80fd\u7387\u3002\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u5c06\u4e00\u4e2a\u5728 Wi-Fi \u9891\u7387\u8303\u56f4\u5185\u5de5\u4f5c\u7684\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u653e\u7f6e\u5728\u4eba\u4f53\u5934\u90e8\u9644\u8fd1\uff0c\u6b64\u6a21\u578b\u57fa\u4e8e\u7b80\u5316\u7684\u5934\u90e8\u548c\u5927\u8111\u6a21\u578b\u8ba1\u7b97\u5c40\u90e8 SAR \u503c\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sar_wifi_antenna.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
