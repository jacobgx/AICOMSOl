/*
 * esd_test_pcb.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:34 by COMSOL 6.3.0.290. */
public class esd_test_pcb {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\ESD_and_Lightning_Surge");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "esd_test_pcb.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"21[mm]", "10[mm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "1.5[mm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-3[mm]", "5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "0.508[mm]", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -5);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"100[mm]", "60[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", -5);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"5[mm]", "5[mm]"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-20[mm]", "-15[mm]"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1").set("fullsize", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1")
         .set("displ", new String[]{"40[mm]", "30[mm]"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "5[mm]", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "35[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"1[mm]", "60[mm]"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"0", "-5[mm]"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").setIndex("layer", "1[mm]", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp4").set("quicky", "4[mm]");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"0.508[mm]", "2[mm]"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("pos", new String[]{"0", "2[mm]"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r2")
         .set("pos", new String[]{"0", "14[mm]"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"150[mm]", "150[mm]", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "100[mm]", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "20[mm]"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("blk2", 2, 3, 4);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u5370\u5237\u5c42");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(17, 18, 19, 24, 26, 35, 36, 37, 54, 55, 56, 57, 72, 73, 74, 75, 86, 87, 88, 89, 90, 111, 112, 134, 135, 141, 142, 147, 148, 149, 150, 151, 152, 154, 157, 170, 171, 172);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u901a\u5b54");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(29, 30, 31, 32, 38, 39, 40, 41, 42, 43, 52, 53, 60, 61, 62, 63, 68, 69, 70, 71, 78, 79, 80, 81, 82, 83, 84, 85, 95, 96, 97, 98, 103, 104, 105, 106, 107, 108, 109, 110, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 136, 137, 138, 139, 143, 144, 145, 146, 155, 156, 158, 159, 174, 175, 176, 177, 178, 179, 181, 182, 183, 184, 185, 186);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8df3\u7ebf");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(44, 45, 46, 47, 48, 49, 50, 51, 91, 92, 93, 94, 99, 100, 101, 102);

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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel1");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u9694\u7247");
    model.component("comp1").material("mat4").selection().set(3, 4, 6, 7);
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"2.9"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u82af\u7247");
    model.component("comp1").material("mat5").selection().set(5);
    model.component("comp1").material("mat5").propertyGroup("def").set("relpermittivity", new String[]{"12"});
    model.component("comp1").material("mat5").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().named("sel1");
    model.component("comp1").physics("temw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec3").selection().named("sel2");
    model.component("comp1").physics("temw").create("pec4", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec4").selection().named("sel3");
    model.component("comp1").physics("temw").create("pec5", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec5").selection().set(6, 7, 8, 9, 11, 15, 23, 163, 168, 189);
    model.component("comp1").physics("temw").create("pec6", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec6").selection().set(191);
    model.component("comp1").physics("temw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("temw").feature("lport1").selection().set(190);
    model.component("comp1").physics("temw").feature("lport1").set("TerminalType", "Current");
    model.component("comp1").physics("temw").feature("lport1").set("CurrentPulseType", "ElectrostaticDischarge");
    model.component("comp1").physics("temw").feature("lport1")
         .set("ElectrostaticDischargePulseModel", "ExtendedHumanBodyModel");
    model.component("comp1").physics("temw").feature("lport1").set("ESDFirstPulseAmplitude", "30[A]");
    model.component("comp1").physics("temw").feature("lport1").set("ESDSecondPulseAmplitude", "5[A]");

    model.func().create("Pulse1", "Analytic");
    model.func("Pulse1").set("args", "t");
    model.func("Pulse1").setIndex("argunit", "s", 0);
    model.func("Pulse1").set("fununit", "A");
    model.func("Pulse1").set("expr", "30[A]*(exp(-t/1[ns])-exp(-t/0.5[ns])) +5[A]*(exp(-t/150[ns])-exp(-t/5[ns])) ");
    model.func("Pulse1").setIndex("plotargs", "1.5000000000000002E-7", 0, 2);

    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "t");
    model.result().dataset("grid1").set("parmax1", "1.5000000000000002E-7");
    model.result().dataset("grid1").set("res1", 10000);
    model.result().dataset("grid1").set("distribution", "mixed");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "grid1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u96c6\u603b\u7aef\u53e3\u8109\u51b2\u56fe");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Pulse current (A)");
    model.result("pg1").set("windowtitle", "\u56fe\u5f62");
    model.result("pg1").set("window", "window1");
    model.result("pg1").create("fun1", "Function");
    model.result("pg1").feature("fun1").set("linewidth", "preference");
    model.result("pg1").feature("fun1").set("expr", "Pulse1(t[1/m][s])");
    model.result("pg1").feature("fun1").set("xdataexpr", "t");
    model.result("pg1").feature("fun1").set("xdataunit", "");
    model.result("pg1").feature("fun1").set("xdatadescractive", true);
    model.result("pg1").feature("fun1").set("xdatadescr", "Time (s)");
    model.result("pg1").feature("fun1").set("upperbound", "1.5000000000000002E-7");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().remove("grid1");

    model.func().remove("Pulse1");

    model.component("comp1").physics("temw").create("lelement1", "LumpedElement", 2);
    model.component("comp1").physics("temw").feature("lelement1").selection().set(140);
    model.component("comp1").physics("temw").create("lelement2", "LumpedElement", 2);
    model.component("comp1").physics("temw").feature("lelement2").selection().set(153);
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection().set(1, 2, 3, 4, 5, 192);
    model.component("comp1").physics("temw").prop("MeshControl").set("SizeControlParameter", "Frequency");
    model.component("comp1").physics("temw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumFrequency", "2[GHz]");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.1[ns]");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "temw.normJ");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(2, 5);
    model.result("pg1").run();
    model.result("pg1").create("vol2", "Volume");
    model.result("pg1").feature("vol2").set("expr", "1");
    model.result("pg1").feature("vol2").set("coloring", "uniform");
    model.result("pg1").feature("vol2").set("color", "custom");
    model.result("pg1").feature("vol2")
         .set("customcolor", new double[]{0.6509804129600525, 0.8392156958580017, 0.8156862854957581});
    model.result("pg1").feature("vol2").create("sel1", "Selection");
    model.result("pg1").feature("vol2").feature("sel1").selection().set(3, 4, 6, 7);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "temw.normJ");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(190, 191);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(6);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u611f\u5e94\u7535\u538b");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"temw.Velement_1"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u96c6\u603b\u5143\u4ef6\u201c1\u201d\u7535\u538b"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"temw.Velement_1", "temw.Velement_2"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u96c6\u603b\u5143\u4ef6\u201c1\u201d\u7535\u538b", "\u96c6\u603b\u5143\u4ef6\u201c2\u201d\u7535\u538b"});
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5f15\u811a 1", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u5f15\u811a 2", 1);
    model.result("pg2").run();

    model.title("PCB \u7684\u9759\u7535\u653e\u7535 (ESD) \u6d4b\u8bd5");

    model
         .description("\u672c\u4f8b\u6f14\u793a ESD \u4e8b\u4ef6\u5982\u4f55\u5bfc\u81f4 PCB \u677f\u4e0a\u7684\u5fae\u82af\u7247\u51fa\u73b0\u903b\u8f91\u9519\u8bef\u3002ESD \u7535\u6d41\u57fa\u4e8e\u6269\u5c55\u7684\u201c\u4eba\u4f53\u653e\u7535\u6a21\u578b\u201d(HBM)\uff0c\u5e76\u5728\u201c\u96c6\u603b\u7aef\u53e3\u201d\u7279\u5f81\u4e2d\u8fdb\u884c\u9884\u5b9a\u4e49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("esd_test_pcb.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
