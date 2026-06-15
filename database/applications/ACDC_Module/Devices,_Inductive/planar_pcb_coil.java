/*
 * planar_pcb_coil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class planar_pcb_coil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat1").label("Copper");
    model.material("mat1").set("family", "copper");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").label("FR4 (Circuit Board)");
    model.material("mat2").set("family", "pcb");
    model.material("mat2").set("color", "custom");
    model.material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.material("lmat1").setIndex("layername", "Cu_up", 0);
    model.material("lmat1").setIndex("thickness", "1E-4", 0);
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").label("\u4e0b\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.material("lmat2").setIndex("layername", "Cu_down", 0);
    model.material("lmat2").setIndex("thickness", "1E-4", 0);
    model.material().create("lmat3", "LayeredMaterial", "");
    model.material("lmat3").label("\u901a\u5b54\u91d1\u5c5e\u9540\u5c42");
    model.material("lmat3").setIndex("layername", "Cu_vias", 0);
    model.material("lmat3").setIndex("thickness", "2E-4", 0);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "-6 -6 -5.5 -5.5 -4 -4 4.5 4.5 -3 -3 3.5 3.5 -2 -2 -2 2.5 2.5 -1 -1 -0.5 -0.5 2 2 -1.5 -1.5 -1.5 3 3 -2.5 -2.5 4 4 -3.5 -3.5 -5.5 -5.5 -6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "0.5 2 2 1.5 1.5 3.5 3.5 -4 -4 2.5 2.5 -3 -3 -2 1.5 1.5 -2 -2 -1 -1 -1.5 -1.5 1 1 -2 -2.5 -2.5 2 2 -3.5 -3.5 3 3 1 1 0.5 0.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new double[]{-0.75, -0.75});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .set("x", "-6  -6 -5.5 -5.5 -4 -4 -5.5 -5.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .set("y", "-1.5 0 0 -0.5 -0.5 -1 -1 -1.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new double[]{-3.75, -0.75});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("c1", "c2", "pol1", "pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 15, 16, 17, 38, 39, 42);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("x", "-3.5  -1 -1 -3.5");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("y", "-0.5 -0.5 -1 -1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new double[]{-0.75, -0.75});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2")
         .set("pos", new double[]{-3.75, -0.75});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("uni1").selection("input")
         .set("c1", "c2", "pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("del1").selection("input")
         .set("uni1", 1, 2, 3, 6, 7, 8);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("size", new double[]{0.5, 0.5});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("pos", new int[]{-6, 0});
    model.component("comp1").geom("geom1").feature("wp1").label("\u4e0a\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u9876\u90e8");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp2").label("\u4e0b\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5e95\u90e8");
    model.component("comp1").geom("geom1").feature("wp2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp3").label("\u78c1\u7edd\u7f18\u5c42");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u7edd\u7f18");
    model.component("comp1").geom("geom1").feature("wp3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{18, 17, 10});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("fin", 1, 2, 4);

    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u901a\u5b54");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 9, 17);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").label("PCB");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel1", "csel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").label("\u4ea4\u53c9\u70b9\u548c\u901a\u5b54");
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"csel1", "csel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").label("\u4ea4\u53c9\u70b9");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"intsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat3").selection().geom("geom1", 3);
    model.component("comp1").material("mat3").selection().all();
    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material("stlmat1").selection().named("geom1_csel1_bnd");
    model.component("comp1").material("stlmat1").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat1").feature().create("lmat1", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("layername", "\u80cc\u886c", 0);
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("link", "mat2", 0);
    model.component("comp1").material("stlmat1").feature("lmat1").setIndex("thickness", "3E-4", 0);
    model.component("comp1").material("stlmat1").feature("lmat1")
         .label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42\u7684\u80cc\u886c");
    model.component("comp1").material("stlmat1").feature().move("lmat1", 0);
    model.component("comp1").material("stlmat1").feature("stllmat1").label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material().create("stlmat2", "LayeredMaterialStack");
    model.component("comp1").material("stlmat2").label("\u4e0b\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material("stlmat2").selection().named("geom1_csel2_bnd");
    model.component("comp1").material("stlmat2").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat2").feature("stllmat1").set("link", "lmat2");
    model.component("comp1").material("stlmat2").feature("stllmat1").label("\u4e0b\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material().create("stlmat3", "LayeredMaterialStack");
    model.component("comp1").material("stlmat3").label("\u4ea4\u53c9\u70b9");
    model.component("comp1").material("stlmat3").selection().named("geom1_difsel1");
    model.component("comp1").material("stlmat3").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat3").feature("stllmat1").set("link", "lmat2");
    model.component("comp1").material("stlmat3").feature("stllmat1").label("\u4e0b\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material("stlmat3").feature().create("lmat1", "LayeredMaterial", "comp1");
    model.component("comp1").material("stlmat3").feature("lmat1").label("\u4ea4\u53c9\u70b9\u7edd\u7f18\u4f53");
    model.component("comp1").material("stlmat3").feature("lmat1").setIndex("layername", "\u7edd\u7f18\u4f53", 0);
    model.component("comp1").material("stlmat3").feature("lmat1").setIndex("link", "mat2", 0);
    model.component("comp1").material("stlmat3").feature("lmat1").setIndex("thickness", "2E-4", 0);
    model.component("comp1").material("stlmat3").feature().create("stllmat2", "LayeredMaterialStackLink", "comp1");
    model.component("comp1").material("stlmat3").feature("stllmat2").label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material().create("stlmat4", "LayeredMaterialStack");
    model.component("comp1").material("stlmat4").selection().named("geom1_sel1");
    model.component("comp1").material("stlmat4").label("\u901a\u5b54");
    model.component("comp1").material("stlmat4").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat4").feature("stllmat1").label("\u4e0b\u5c42\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material("stlmat4").feature("stllmat1").set("link", "lmat2");
    model.component("comp1").material("stlmat4").feature().create("stllmat2", "LayeredMaterialStackLink", "comp1");
    model.component("comp1").material("stlmat4").feature("stllmat2").set("link", "lmat3");
    model.component("comp1").material("stlmat4").feature("stllmat2").label("\u901a\u5b54\u91d1\u5c5e\u9540\u5c42");
    model.component("comp1").material("stlmat4").feature().create("stllmat3", "LayeredMaterialStackLink", "comp1");
    model.component("comp1").material("stlmat4").feature("stllmat3").label("\u4e0a\u5c42\u91d1\u5c5e\u9540\u5c42");

    model.component("comp1").physics("ecis").selection().named("geom1_unisel1");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");
    model.component("comp1").physics("ecis").feature("csh1").create("bterm1", "BoundaryTerminal", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm1").selection().set(12);
    model.component("comp1").physics("ecis").feature("csh1").feature("bterm1").set("I0", 1);
    model.component("comp1").physics("ecis").feature("csh1").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("csh1").feature("bgnd1").selection().set(14);
    model.component("comp1").physics("ecis").create("inl1", "InsulatingLayer", 2);
    model.component("comp1").physics("ecis").feature("inl1").selection().all();
    model.component("comp1").physics("ecis").feature("inl1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").physics("ecis").create("inl2", "InsulatingLayer", 2);
    model.component("comp1").physics("ecis").feature("inl2").selection().all();
    model.component("comp1").physics("ecis").feature("inl2").set("shelllist", "stlmat3");
    model.component("comp1").physics("ecis").feature("inl2").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").feature("inl2").setIndex("shelllist_lCheck", 0, 2, 0);
    model.component("comp1").physics("ecis").create("cls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "stlmat1");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist", "stlmat3");
    model.component("comp1").physics("ecis").create("cls2", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls2").set("shelllist_src", "stlmat1");
    model.component("comp1").physics("ecis").feature("cls2").set("shelllist", "stlmat4");
    model.component("comp1").physics("ecis").create("cls3", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls3").set("shelllist_src", "stlmat2");
    model.component("comp1").physics("ecis").feature("cls3").set("shelllist", "stlmat3");
    model.component("comp1").physics("ecis").create("cls4", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls4").set("shelllist_src", "stlmat2");
    model.component("comp1").physics("ecis").feature("cls4").set("shelllist", "stlmat4");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.component("comp1").physics("mf").create("scu1", "SurfaceCurrent", 2);
    model.component("comp1").physics("mf").feature("scu1")
         .set("Js0", new String[]{"ecis.JsX", "ecis.JsY", "ecis.JsZ"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mf").feature("scu1").selection().named("geom1_unisel1");
    model.component("comp1").physics("mf").create("mi2", "MagneticInsulation", 2);
    model.component("comp1").physics("mf").feature("mi2").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.35);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ecis)");
    model.result("pg1").set("data", "lshl1");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "mf.normB");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"mf.Bx", "mf.By", "mf.Bz"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "mf.normB");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("strmsl1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"mf.scu1.Js0x", "mf.scu1.Js0y", "mf.scu1.Js0z"});
    model.result("pg2").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "2e-4");
    model.result("pg2").feature("arws1").set("arrowcount", "1e4");
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ecis.V0_1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7ec8\u7aef\u7535\u538b"});
    model.result().numerical("gev1").setIndex("expr", "ecis.V0_1/ecis.I0_1", 0);
    model.result().numerical("gev1").setIndex("unit", "m\u03a9", 0);
    model.result().numerical("gev1").setIndex("descr", "\u7535\u963b", 0);
    model.result().numerical("gev1").setIndex("expr", "2*mf.intWm/ecis.I0_1^2", 1);
    model.result().numerical("gev1").setIndex("unit", "uH", 1);
    model.result().numerical("gev1").setIndex("descr", "\u7535\u611f", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u5e73\u9762 PCB \u7ebf\u5708");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7535\u6d41\uff0c\u591a\u5c42\u58f3 \u63a5\u53e3\u548c\u78c1\u573a \u63a5\u53e3\u6765\u4f30\u7b97\u5e73\u9762 PCB \u7ebf\u5708\u5728\u9759\u6b62\u72b6\u6001\u4e0b\u7684\u7535\u963b\u548c\u7535\u611f\u3002\u8fd9\u79cd\u88c5\u7f6e\u7684\u5178\u578b\u5e94\u7528\u6db5\u76d6\u4ece\u80fd\u91cf\u91c7\u96c6\u5230\u65e0\u7ebf\u5145\u7535\u7684\u5e7f\u6cdb\u9886\u57df\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("planar_pcb_coil.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
