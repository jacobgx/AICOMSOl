/*
 * thermoacoustic_engine_heat_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class thermoacoustic_engine_heat_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ht2", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("tatd", "ThermoacousticsSinglePhysicsTransient", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tatd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("stack_end", "0.03[m]", "\u53e0\u5c42\u4e2d\u6563\u70ed\u5668\u7684\u957f\u5ea6");
    model.param().set("stack_n", "7", "\u5806\u53e0\u5143\u4ef6\u6570");
    model.param().set("stack_z", "1.4[m]", "\u53e0\u5c42\u4f4d\u7f6e");
    model.param().set("stack_L", "140[mm]", "\u53e0\u5c42\u957f\u5ea6");
    model.param().set("tube_L", "4[m]", "\u7ba1\u957f\u5ea6");
    model.param().set("tube_R", "(stack_n*2-1/2)*stack_w", "\u7ba1\u534a\u5f84");
    model.param().set("stack_w", "1.5[mm]", "\u5806\u53e0\u5143\u4ef6\u7684\u5bbd\u5ea6");
    model.param().set("DeltaT", "270[K]", "\u53e0\u5c42\u4e2d\u7684\u6e29\u5dee");
    model.param().set("p0", "1[kPa]", "\u521d\u59cb\u538b\u529b\u5e45\u503c");
    model.param().set("T0", "293.15[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("f0", "c/(4*tube_L)", "\u521d\u59cb\u8109\u51b2\u9891\u7387");
    model.param().set("t0", "1/f0", "\u6ce2\u5468\u671f");
    model.param().set("c", "343[m/s]", "\u58f0\u901f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"tube_R", "tube_L*2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"stack_w", "stack_L"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"3/2*stack_w", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "stack_z", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{6, 2});
    model.component("comp1").geom("geom1").feature("arr1")
         .set("displ", new String[]{"2*stack_w", "2*tube_L-2*stack_z-stack_L"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"stack_w*0.5", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "stack_L", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "stack_z"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{1, 2});
    model.component("comp1").geom("geom1").feature("arr2")
         .set("displ", new String[]{"0", "2*tube_L-2*stack_z-stack_L"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2")
         .set("arr1(1,1)", "arr1(2,1)", "arr1(3,1)", "arr1(4,1)", "arr1(5,1)", "arr1(6,1)", "arr2(1,1)");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1")
         .set("coord1", new String[]{"0", "2*tube_L-stack_z-stack_end"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1")
         .set("coord2", new String[]{"tube_R", "2*tube_L-stack_z-stack_end"});
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"0", "stack_z"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"tube_R", "stack_z"});
    model.component("comp1").geom("geom1").feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"0", "stack_z+stack_L"});
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"tube_R", "stack_z+stack_L"});
    model.component("comp1").geom("geom1").feature().duplicate("ls4", "ls3");
    model.component("comp1").geom("geom1").feature("ls4")
         .set("coord1", new String[]{"0", "2*tube_L-stack_z-stack_L"});
    model.component("comp1").geom("geom1").feature("ls4")
         .set("coord2", new String[]{"tube_R", "2*tube_L-stack_z-stack_L"});
    model.component("comp1").geom("geom1").feature().duplicate("ls5", "ls4");
    model.component("comp1").geom("geom1").feature("ls5").set("coord1", new String[]{"0", "2*tube_L-stack_z"});
    model.component("comp1").geom("geom1").feature("ls5").set("coord2", new String[]{"tube_R", "2*tube_L-stack_z"});
    model.component("comp1").geom("geom1").feature().duplicate("ls6", "ls5");
    model.component("comp1").geom("geom1").feature("ls6")
         .set("coord1", new String[]{"0", "2*tube_L-stack_z+stack_L"});
    model.component("comp1").geom("geom1").feature("ls6")
         .set("coord2", new String[]{"tube_R", "2*tube_L-stack_z+stack_L"});
    model.component("comp1").geom("geom1").feature().duplicate("ls7", "ls6");
    model.component("comp1").geom("geom1").feature("ls7")
         .set("coord1", new String[]{"0", "2*tube_L-stack_z-2*stack_L"});
    model.component("comp1").geom("geom1").feature("ls7")
         .set("coord2", new String[]{"tube_R", "2*tube_L-stack_z-2*stack_L"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u70ed\u5806");
    model.component("comp1").selection("sel1").set(4, 5, 11, 12, 16, 17, 21, 22, 26, 27, 31, 32, 36, 37);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u7a7a\u6c14");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});

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
    model.component("comp1").material("mat1").selection().named("com1");
    model.component("comp1").material().create("sw1", "Switch");
    model.component("comp1").material("sw1").feature().create("mat1", "Common");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat1").label("Acrylic plastic");
    model.component("comp1").material("sw1").feature("mat1").set("family", "custom");
    model.component("comp1").material("sw1").feature("mat1")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("sw1").feature("mat1").set("diffuse", "custom");
    model.component("comp1").material("sw1").feature("mat1")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("sw1").feature("mat1").set("ambient", "custom");
    model.component("comp1").material("sw1").feature("mat1")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("sw1").feature("mat1").set("noise", true);
    model.component("comp1").material("sw1").feature("mat1").set("lighting", "phong");
    model.component("comp1").material("sw1").feature("mat1").set("shininess", 1000);
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu")
         .label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("sw1").feature().create("mat2", "Common");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup()
         .create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("sw1").feature("mat2").label("Copper");
    model.component("comp1").material("sw1").feature("mat2").set("family", "copper");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu")
         .label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("linzRes")
         .label("Linearized resistivity");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("sw1").selection().named("sel1");

    model.func().create("rm1", "Ramp");
    model.func("rm1").set("location", "stack_z+stack_end");
    model.func("rm1").set("slope", "-1/(stack_L-2*stack_end)");
    model.func("rm1").set("cutoffactive", true);
    model.func("rm1").set("cutoff", -1);
    model.func("rm1").set("smoothzonelocactive", true);
    model.func("rm1").set("smoothzoneloc", 0.02);
    model.func("rm1").set("smoothzonecutoffactive", true);
    model.func("rm1").set("smoothzonecutoff", 0.02);
    model.func().create("an1", "Analytic");
    model.func("an1").label("Tstack");
    model.func("an1").set("funcname", "Tstack");
    model.func("an1").set("expr", "(1+rm1(z/1[m]))*DeltaT");
    model.func("an1").set("args", "z");
    model.func("an1").set("fununit", "K");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func().create("an2", "Analytic");
    model.func("an2").label("p_initial");
    model.func("an2").set("funcname", "p_initial");
    model.func("an2").set("expr", "sin((z-tube_L)/(2*tube_L)*pi)");
    model.func("an2").set("args", "z");
    model.func("an2").setIndex("argunit", "m", 0);

    model.component("comp1").physics("ht").selection().named("sel1");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").feature("temp1").selection()
         .set(13, 22, 30, 32, 38, 46, 48, 54, 62, 64, 70, 78, 80, 86, 94, 96, 102, 110, 112, 118);
    model.component("comp1").physics("ht2").selection().named("com1");
    model.component("comp1").physics("ht2").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht2").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht2").feature("temp1").selection()
         .set(5, 17, 18, 25, 27, 33, 41, 42, 43, 49, 57, 58, 59, 65, 73, 74, 75, 81, 89, 90, 91, 97, 105, 106, 107, 113);
    model.component("comp1").physics("ht2").feature("temp1").set("T0", "T0+Tstack(z)");
    model.component("comp1").physics("ht2").feature("temp1").label("\u6e29\u5ea6 - \u5f15\u64ce\u5806");
    model.component("comp1").physics("ht2").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht2").feature("temp2").set("T0", "T0");
    model.component("comp1").physics("ht2").feature("temp2").selection()
         .set(2, 9, 13, 16, 20, 22, 28, 29, 30, 32, 36, 38, 44, 45, 46, 48, 52, 54, 60, 61, 62, 64, 68, 70, 76, 77, 78, 80, 84, 86, 92, 93, 94, 96, 100, 102, 108, 109, 110, 112, 116, 118, 121, 122, 123, 124, 125, 126, 127, 128);
    model.component("comp1").physics("ht2").feature("temp2")
         .label("\u6e29\u5ea6 - \u5916\u90e8\u73af\u5883\u548c\u70ed\u6cf5\u5806");
    model.component("comp1").physics("tatd").selection().named("com1");
    model.component("comp1").physics("tatd").prop("ShapeProperty").set("shapeorder_u", 1);
    model.component("comp1").physics("tatd").prop("ShapeProperty").set("shapeorder_T", 1);
    model.component("comp1").physics("tatd").prop("TransientSettings").set("fmax", "f0");
    model.component("comp1").physics("tatd").prop("Stabilization").set("selStab", "SUPGStab");
    model.component("comp1").physics("tatd").feature("tam1").set("minput_temperature_src", "root.comp1.T2");
    model.component("comp1").physics("tatd").feature("init1").set("p", "p0*p_initial(z)");
    model.component("comp1").physics("tatd").feature("wall1").label("\u58c1 - \u7edd\u70ed");
    model.component("comp1").physics("tatd").feature("wall1").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("tatd").create("ntac1", "NonlinearThermoviscousAcousticsContributions", 2);
    model.component("comp1").physics("tatd").feature("ntac1").selection()
         .set(3, 6, 9, 10, 14, 15, 19, 20, 24, 25, 29, 30, 34, 35, 39, 40);
    model.component("comp1").physics("tatd").feature("ntac1").set("minput_temperature_src", "root.comp1.T2");
    model.component("comp1").physics("tatd").create("wall2", "Wall", 1);
    model.component("comp1").physics("tatd").feature("wall2").selection()
         .set(3, 5, 13, 17, 22, 25, 26, 27, 30, 32, 33, 38, 41, 42, 43, 46, 48, 49, 54, 57, 58, 59, 62, 64, 65, 70, 73, 74, 75, 78, 80, 81, 86, 89, 90, 91, 94, 96, 97, 102, 105, 106, 107, 110, 112, 113, 118);
    model.component("comp1").physics("tatd").feature("wall2").label("\u58c1 - \u7b49\u6e29");
    model.component("comp1").physics("tatd").create("noslip1", "NoSlip", 1);
    model.component("comp1").physics("tatd").feature("noslip1").selection()
         .set(9, 20, 28, 29, 36, 44, 45, 52, 60, 61, 68, 76, 77, 84, 92, 93, 100, 108, 109, 116);

    model.component("comp1").multiphysics().create("tatpb1", "ThermoviscousAcousticThermalPerturbationBoundary", 1);
    model.component("comp1").multiphysics("tatpb1").selection()
         .set(9, 20, 28, 29, 36, 44, 45, 52, 60, 61, 68, 76, 77, 84, 92, 93, 100, 108, 109, 116);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").label("\u6620\u5c04 - \u70ed\u5f15\u64ce");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(8, 13, 18, 23, 28, 33, 38);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(19, 35, 51, 67, 83, 99, 115);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(17, 25, 33, 41, 49, 57, 65, 73, 81, 89, 97, 105, 113, 122);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 15);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").label("\u6620\u5c04 - \u70ed\u6cf5");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection()
         .set(4, 5, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 29, 30, 31, 32, 34, 35, 36, 37, 39, 40);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection()
         .set(24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 120);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection()
         .set(22, 30, 38, 46, 54, 62, 70, 78, 86, 94, 102, 110, 118);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("numelem", 11);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").selection()
         .set(20, 28, 36, 44, 52, 60, 68, 76, 84, 92, 100, 108, 116, 125);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis4").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(3, 5, 9, 13, 18, 19, 21, 24, 26, 27, 29, 32, 34, 35, 37, 40, 42, 43, 45, 48, 50, 51, 53, 56, 58, 59, 61, 64, 66, 67, 69, 72, 74, 75, 77, 80, 82, 83, 85, 88, 90, 91, 93, 96, 98, 99, 101, 104, 106, 107, 109, 112, 114, 115, 117, 120, 121, 123, 124, 127, 128);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "tube_R");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7a33\u6001\u6e29\u5ea6\u573a");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/tatpb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u77ac\u6001\u58f0\u5b66 - \u5806\u6750\u6599");
    model.study("std2").create("matsw", "MaterialSweep");
    model.study("std2").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std2").feature("time").set("tlist", "range(0,t0/20,0.5)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std3").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/tatpb1", true);
    model.study("std3").label("\u7814\u7a76 3 - \u77ac\u6001\u58f0\u5b66 - \u5f15\u64ce\u5173\u95ed");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std3").feature("time").set("tlist", "range(0,t0/20,0.5)");
    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/ht", true);
    model.study("std4").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std4").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std4").feature("time").setSolveFor("/multiphysics/tatpb1", true);
    model.study("std4").label("\u7814\u7a76 4 - \u77ac\u6001\u58f0\u5b66 - \u65e0\u975e\u7ebf\u6027\u8d21\u732e");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std4").feature("time").set("tlist", "range(0,t0/20,0.5)");
    model.study("std4").feature("time").set("usesol", true);
    model.study("std4").feature("time").set("notsolmethod", "sol");
    model.study("std4").feature("time").set("notstudy", "std1");
    model.study("std4").feature("time").set("useadvanceddisable", true);
    model.study("std4").feature("time").set("disabledphysics", new String[]{"tatd/ntac1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht2)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "T2");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol3");
    model.batch("pm1").run("compute");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 215, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58f0\u538b (tatd)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 215, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "tatd.p_t");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58f0\u901f (tatd)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 215, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "tatd.v_inst");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection()
         .set(1, 2, 3, 6, 7, 8, 9, 10, 13, 14, 15, 18, 19, 20, 23, 24, 25, 28, 29, 30, 33, 34, 35, 38, 39, 40);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6\u53d8\u5316 (tatd)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "tatd.T_t");
    model.result("pg5").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u6e29\u5ea6\u53d8\u5316 (tatpb1)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 215, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "tatpb1.T_t");
    model.result("pg6").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg6").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg2").run();

    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.study("std4").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u538b\u529b - \u5f15\u64ce\u5f00/\u5173");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevelinput", "first", 1);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(1);
    model.result("pg7").feature("ptgr1").set("expr", "tatd.p_t");
    model.result("pg7").run();
    model.result("pg7").create("ptgr2", "PointGraph");
    model.result("pg7").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr2").set("linewidth", "preference");
    model.result("pg7").feature("ptgr2").set("data", "dset4");
    model.result("pg7").feature("ptgr2").set("expr", "tatd.p_t");
    model.result("pg7").feature("ptgr2").selection().set(1);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6 - \u975e\u7ebf\u6027\u8d21\u732e\u5f00/\u5173");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevelinput", "first", 1);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(12);
    model.result("pg8").feature("ptgr1").set("expr", "tatd.T_t");
    model.result("pg8").run();
    model.result("pg8").create("ptgr2", "PointGraph");
    model.result("pg8").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr2").set("linewidth", "preference");
    model.result("pg8").feature("ptgr2").selection().set(12);
    model.result("pg8").feature("ptgr2").set("data", "dset5");
    model.result("pg8").feature("ptgr2").set("expr", "tatd.T_t");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6e29\u5ea6 - \u5806\u6750\u6599");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").selection().set(12);
    model.result("pg9").feature("ptgr1").set("expr", "tatd.T_t");
    model.result("pg9").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 201, 0);
    model.result("pg4").run();

    model.title("\u70ed\u58f0\u53d1\u52a8\u673a\u548c\u70ed\u6cf5");

    model
         .description("\u70ed\u58f0\u53d1\u52a8\u673a\u662f\u4e00\u79cd\u4e0d\u542b\u6d3b\u52a8\u90e8\u4ef6\u7684\u88c5\u7f6e\uff0c\u901a\u8fc7\u53d1\u52a8\u673a\u5185\u7684\u6e29\u5ea6\u68af\u5ea6\u4ea7\u751f\u58f0\u80fd\uff0c\u5176\u4e2d\u5229\u7528\u4e86\u632f\u8361\u7a7a\u6c14\u7684\u8fd0\u52a8\u4e0e\u88ab\u58f0\u6ce2\u538b\u7f29\u81a8\u80c0\u7684\u7a7a\u6c14\u7684\u6e29\u5ea6\u53d8\u5316\u4e4b\u95f4\u7684\u5173\u7cfb\u3002\u76f8\u540c\u7684\u673a\u5236\u53ef\u7528\u4e8e\u901a\u8fc7\u58f0\u80fd\u4ea7\u751f\u6e29\u5ea6\u68af\u5ea6\uff0c\u8fd9\u79f0\u4e3a\u70ed\u58f0\u70ed\u6cf5\u3002\u672c\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u8026\u5408\u4e86\u9a7b\u6ce2\u70ed\u58f0\u53d1\u52a8\u673a\u4e0e\u70ed\u6cf5\u7684\u7cfb\u7edf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("thermoacoustic_engine_heat_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
