/*
 * microstrip_line_tem_via.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:42 by COMSOL 6.3.0.290. */
public class microstrip_line_tem_via {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Transmission_Lines_and_Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("tbma", "TEMBoundaryModeAnalysis");
    model.study("std1").feature("tbma").set("solnum", "auto");
    model.study("std1").feature("tbma").set("notsolnum", "auto");
    model.study("std1").feature("tbma").set("outputmap", new String[]{});
    model.study("std1").feature("tbma").set("ngenAUX", "1");
    model.study("std1").feature("tbma").set("goalngenAUX", "1");
    model.study("std1").feature("tbma").set("ngenAUX", "1");
    model.study("std1").feature("tbma").set("goalngenAUX", "1");
    model.study("std1").feature("tbma").setSolveFor("/physics/emw", true);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("tsub", "20[mil]");
    model.param().descr("tsub", "\u57fa\u677f\u539a\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"10", "8", "tsub*10"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, -4, 0});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "tsub", 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "tsub");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{7.5, 1.13});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-0.565+0.8"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.8);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new double[]{7.5, 0.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.4);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "tsub");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{7.5, 0.8, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl1", "wp1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new int[]{5, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").create("wee2", "WaveEquationElectric", 3);
    model.component("comp1").physics("emw").feature("wee2").selection().set(1);
    model.component("comp1").physics("emw").feature("wee2").set("DisplacementFieldModel", "LossTangentDF");
    model.component("comp1").physics("emw").create("trans1", "TransitionBoundaryCondition", 2);
    model.component("comp1").physics("emw").feature("trans1").selection().set(8, 11, 12, 13, 16, 17);
    model.component("comp1").physics("emw").feature("trans1").set("d", "38[um]");
    model.component("comp1").physics("emw").create("imp1", "Impedance", 2);
    model.component("comp1").physics("emw").feature("imp1").selection().set(3);
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(1, 4);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "TEM");
    model.component("comp1").physics("emw").feature("port1").create("gnd1", "Ground", 1);
    model.component("comp1").physics("emw").feature("port1").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("emw").feature("port1").feature("pot1").selection().set(9);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(18, 19, 22, 23);
    model.component("comp1").physics("emw").feature("lport1").set("PortType", "Via");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(24, 25);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "TEM");
    model.component("comp1").physics("emw").feature("port2").create("gnd1", "Ground", 1);
    model.component("comp1").physics("emw").feature("port2").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("emw").feature("port2").feature("pot1").selection().set(57);
    model.component("comp1").physics("emw").prop("PortOptions").set("PortFormulation", "ConstraintBased");

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
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().set(3, 8, 11, 12, 13, 14, 16, 17);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("LossTangentDF", "LossTangentDF", "Loss tangent, dissipation factor");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("RO4003C\u2122 Laminates");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF")
         .label("Loss tangent, dissipation factor");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1")
         .set("funcname", "tanDelta_interp");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1")
         .set("table", new String[][]{{"2.5e9", "0.0021"}, {"10e9", "0.0027"}});
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF")
         .set("tanDelta", "tanDelta_interp(freq/1[Hz])");
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF")
         .set("epsilonPrim", new String[]{"3.38", "0", "0", "0", "3.38", "0", "0", "0", "3.38"});
    model.component("comp1").material("mat3").propertyGroup("LossTangentDF").addInput("frequency");
    model.component("comp1").material("mat3").selection().set(1);

    model.study("std1").feature("freq").set("plist", "1 3 5");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(5, 7);

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
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S31"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 5, 7, 9, 10, 14, 20);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 9, 10, 14, 20, 24, 25);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(2, 5, 7, 9, 10, 14, 20);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.53);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(3, 8, 11, 12, 13, 16, 17);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 9, 10, 14, 20, 24, 25);

    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(3, 8, 11, 12, 13, 16, 17);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.27);
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "rosegold");
    model.result("pg4").feature("surf2").set("expr", "1");
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().set(6, 15, 18, 19, 21, 22, 23);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.3);
    model.result("pg4").create("surf4", "Surface");
    model.result("pg4").feature("surf4").set("expr", "emw.normE");
    model.result("pg4").feature("surf4").create("sel1", "Selection");
    model.result("pg4").feature("surf4").feature("sel1").selection().set(1, 4, 24, 25);
    model.result("pg4").feature("surf4").set("colortable", "Dipole");
    model.result("pg4").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf4").create("tran1", "Transparency");
    model.result("pg4").feature("surf4").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-14.005717401919158, -21.572841146717902, 17.283978669539742});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 36.35390758514404);

    model.result("pg4").set("view", "view3");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "tsub");
    model.result("pg1").feature("mslc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").run();
    model.result("pg4").run();

    model.title("\u5e26\u8fc7\u5b54\u7684\u5fae\u5e26\u7ebf\u5efa\u6a21");

    model
         .description("\u901a\u8fc7\u4f7f\u7528\u4e0d\u540c\u7c7b\u578b\u7684\u7aef\u53e3\u548c\u96c6\u603b\u7aef\u53e3\u7279\u5f81\uff0c\u6211\u4eec\u80fd\u591f\u4ee5\u591a\u79cd\u65b9\u5f0f\u6fc0\u52b1\u548c\u7ec8\u6b62\u4f20\u8f93\u7ebf\u3002\u672c\u4f8b\u4f7f\u7528\u6a2a\u5411\u7535\u78c1 (TEM) \u578b\u7aef\u53e3\u548c\u8fc7\u5b54\u578b\u96c6\u603b\u7aef\u53e3\u6a21\u62df\u4e24\u4e2a\u76f8\u90bb\u7684\u5fae\u5e26\u7ebf\u3002\u4e00\u4e2a\u8fc7\u5b54\u7aef\u4f5c\u4e3a\u91d1\u5c5e\u5316\u8fc7\u5b54\u7ec8\u6b62\uff0c\u53e6\u4e00\u4e2a\u8fc7\u5b54\u7aef\u5219\u63a2\u6d4b\u6d41\u5165\u4fe1\u53f7\u3002\u8ba1\u7b97\u5f97\u5230\u7684 S \u53c2\u6570\u663e\u793a\u5fae\u5e26\u7ebf\u4e4b\u95f4\u7684\u4e32\u6270\u91cf\u4ee5\u53ca\u901a\u8fc7\u5706\u67f1\u5f62\u8fc7\u5b54\u8026\u5408\u7684\u4fe1\u53f7\u5f3a\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("microstrip_line_tem_via.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
