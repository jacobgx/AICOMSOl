/*
 * incense_stick.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:53 by COMSOL 6.3.0.290. */
public class incense_stick {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LESRBVM", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics("ht").prop("ConsistentStabilization").set("heatCrosswindDiffusion", "0");
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").func().create("rn1", "Random");
    model.component("comp1").func("rn1").set("uniformrange", 2);
    model.component("comp1").func("rn1").set("seedactive", true);
    model.component("comp1").func("rn1").set("seed", 11301);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "rn1(floor(x))");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.4, 1.85});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", 0.12, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layername", "Layer 2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", 0.08, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{0.4, 1.35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{0.2, 0.53});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{0, 0.12});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("layer", 0.18, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{0.2, 1.73});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new double[]{0, 0.12});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("layerright", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layer", 0.08, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layername", "Layer 2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("layer", 0.085, 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "0.25e-2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.2);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.005, 0, 0.145});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, -1});
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", 0.02, 0);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.2, 0.05, 0.01});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.05, -0.025, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "Ideal gas");
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
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "(def.gamma+1)/2");
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
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material("mat1").set("family", "air");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("UseReducedPressure", true);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection()
         .set(9, 10, 19, 20, 41, 42, 59, 60, 81, 82, 92, 97, 108, 117, 126, 137, 147, 156, 164, 169);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1")
         .set(9, 10, 19, 20, 41, 42, 59, 60, 81, 82, 92, 97, 108, 117, 126, 137, 147, 156, 164, 169);

    model.component("comp1").physics("spf").feature("out1").selection().named("sel1");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(1, 2, 5, 6, 88, 90, 170, 171);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u663e\u5f0f 2");
    model.component("comp1").selection("sel2").set(1, 2, 5, 6, 88, 90, 170, 171);

    model.component("comp1").physics("spf").feature("open1").selection().named("sel2");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(83, 84, 85, 86, 87);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u663e\u5f0f 3");
    model.component("comp1").selection("sel3").set(83, 84, 85, 86, 87);

    model.component("comp1").physics("ht").feature("temp1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "293.15[K]+500[K]+50[K]*an1(t)");
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ht").feature("open1").selection().named("sel2");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel1");
    model.component("comp1").physics("fpt").prop("Formulation").setIndex("Formulation", "Massless", 0);
    model.component("comp1").physics("fpt").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("fpt").feature("pp1").set("v", new String[]{"u", "v", "w"});
    model.component("comp1").physics("fpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("rt", "range(0,0.01,10)", 0);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("x0", "range(-0.005,0.001,0.005)", 0);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("x0", "range(-0.005,0.001,0.005)", 1);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("x0", 0.16, 2);

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.002);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4, 8, 9, 11, 12, 13, 14, 15, 16, 17);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(9, 10, 92, 169);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(83, 84, 85, 86, 87, 172, 173, 174, 175, 176, 177, 178, 179);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").run();

    model.result().dataset().create("mesh1", "Mesh");
    model.result().numerical().create("min1", "MinVolume");
    model.result().numerical("min1").selection().set(8, 11, 12, 15, 16);
    model.result().numerical("min1").setIndex("expr", "h", 0);
    model.result().numerical("min1").setIndex("descr", "Element size", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u6700\u5c0f\u503c 1");
    model.result().numerical("min1").set("table", "tbl1");
    model.result().numerical("min1").setResult();

    model.study("std1").feature("time").set("tlist", "range(0,0.005,10)");
    model.study("std1").feature("time").setEntry("activate", "fpt", false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.005,10)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.005);
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.05);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1")
         .set("atolmethod", new String[]{"comp1_p", "scaled", "comp1_qfpt", "global", "comp1_T", "global", "comp1_u", "global"});
    model.sol("sol1").feature("t1")
         .set("atol", new String[]{"comp1_p", "1e-3", "comp1_qfpt", "1e-3", "comp1_T", "1e-3", "comp1_u", "1e-3"});
    model.sol("sol1").feature("t1")
         .set("atolvaluemethod", new String[]{"comp1_p", "factor", "comp1_qfpt", "factor", "comp1_T", "factor", "comp1_u", "factor"});
    model.sol("sol1").feature("t1")
         .set("atolfactor", new String[]{"comp1_p", "1", "comp1_qfpt", "0.1", "comp1_T", "0.1", "comp1_u", "0.1"});
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("timemethod", "genalpha");
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").set("rhoinf", 0.5);
    model.sol("sol1").feature("t1").set("predictor", "constant");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("stabcntrl", true);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.01");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("iDef", "Iterative");
    model.sol("sol1").feature("t1").create("seDef", "Segregated");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 8);
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i1").set("maxlinit", 100);
    model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i1")
         .label("AMG\uff0c\u975e\u7b49\u6e29\u6d41\u52a8 (nitf1) (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("scgsrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsmethod", "lines_vertices");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsvertexrelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgssolv", "stored");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1").label("\u76f4\u63a5\uff0c\u975e\u7b49\u6e29\u6d41\u52a8 (nitf1)");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "i1");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.5);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 8);
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").feature().remove("seDef");
    model.sol("sol1").feature("t1").feature().remove("iDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("v1").feature("comp1_p").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_qfpt").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_T").set("out", false);
    model.sol("sol1").feature("t1").set("tstepsgenalpha", "strict");
    model.sol("sol1").feature("t1").set("initialstepgenalphaactive", true);
    model.sol("sol1").feature("t1").set("initialstepgenalpha", "0.0001");
    model.sol("sol1").feature("t1").set("reacf", false);
    model.sol("sol1").feature("t1").set("storeudot", false);

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(3, 4, 13, 14, 23, 24, 61, 62, 63, 64, 83, 84, 85, 86, 87, 89, 94, 99, 138, 158, 166, 172, 173, 174, 175, 176, 177, 178, 179, 189, 190);
    model.result().dataset("surf1").selection().inherit(false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u538b\u529b");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.WRHeightExpr");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").label("\u7b49\u503c\u9762");
    model.result("pg5").feature("iso1").set("expr", "T");
    model.result("pg5").feature("iso1").set("number", 10);
    model.result("pg5").feature("iso1").set("levelrounding", false);
    model.result("pg5").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("iso1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").set("data", "surf1");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.04,10)");
    model.study("std2").feature("time").setEntry("activate", "spf", false);
    model.study("std2").feature("time").setEntry("activate", "ht", false);
    model.study("std2").feature("time").setEntry("activateCoupling", "nitf1", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").feature("time").set("notsolnum", "all");

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "all");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "auto");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.04,10)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 1.0E-5);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsgenalpha", "strict");
    model.sol("sol2").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol2").feature("t1").set("timemethod", "genalpha");
    model.sol("sol2").feature("t1").set("estrat", "exclude");
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.1);
    model.sol("sol2").feature("t1").create("i1", "Iterative");
    model.sol("sol2").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("t1").feature("i1").create("ja1", "Jacobi");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "i1");
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 0.1);
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").feature("v1").feature("comp1_p").set("out", false);
    model.sol("sol2").feature("v1").feature("comp1_T").set("out", false);
    model.sol("sol2").feature("t1").set("reacf", false);
    model.sol("sol2").feature("t1").set("storeudot", false);

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg6").set("data", "part1");
    model.result("pg6").create("traj1", "ParticleTrajectories");
    model.result("pg6").feature("traj1").set("pointtype", "point");
    model.result("pg6").feature("traj1").set("linetype", "none");
    model.result("pg6").feature("traj1").create("col1", "Color");
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.V");

    model.sol("sol2").runAll();

    model.result("pg6").run();

    model.sol("sol1").clearSolutionData();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 1);
    model.result("pg1").feature("slc1").set("colortable", "AuroraAustralisDark");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "custom");
    model.result("pg1").feature("surf1")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(61, 62, 63, 64, 189, 190);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u663e\u5f0f 4");
    model.component("comp1").selection("sel4").set(61, 62, 63, 64, 189, 190);

    model.result("pg1").feature("surf1").feature("sel1").selection().named("sel4");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(83, 84, 85, 86, 87, 172, 173, 174, 175, 176, 177, 178, 179);

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").label("\u663e\u5f0f 5");
    model.component("comp1").selection("sel5").set(83, 84, 85, 86, 87, 172, 173, 174, 175, 176, 177, 178, 179);

    model.result("pg1").feature("surf2").feature("sel1").selection().named("sel5");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 151, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 201, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 251, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").active(false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("edges", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("color", "red");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("color", "red");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").active(true);
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("data", "part1");
    model.result("pg7").setIndex("looplevel", 51, 0);
    model.result("pg7").set("edges", false);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("traj1", "ParticleTrajectories");
    model.result("pg7").feature("traj1").set("linetype", "none");
    model.result("pg7").feature("traj1").set("pointtype", "point");
    model.result("pg7").feature("traj1").set("sphereradiusscaleactive", true);
    model.result("pg7").feature("traj1").set("sphereradiusscale", 2);
    model.result("pg7").feature("traj1").set("pointcolor", "gray");
    model.result("pg7").feature("traj1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("traj1").feature("tran1").set("transparency", 0.8);

    model.component("comp1").view("view1").set("ssao", true);
    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg7").run();
    model.result("pg7").feature().duplicate("traj2", "traj1");
    model.result("pg7").run();
    model.result("pg7").feature("traj2").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("traj2").feature("def1").set("expr", new String[]{"0.55", "", ""});
    model.result("pg7").feature("traj2").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("traj2").feature("def1").set("scale", 1);
    model.result("pg7").run();
    model.result("pg7").feature("traj2").set("data", "part1");
    model.result("pg7").feature("traj2").setIndex("looplevel", 101, 0);
    model.result("pg7").feature().duplicate("traj3", "traj2");
    model.result("pg7").run();
    model.result("pg7").feature("traj3").setIndex("looplevel", 151, 0);
    model.result("pg7").run();
    model.result("pg7").feature("traj3").feature("def1").set("expr", new String[]{"1.1", "", ""});
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("traj4", "traj3");
    model.result("pg7").run();
    model.result("pg7").feature("traj4").setIndex("looplevel", 201, 0);
    model.result("pg7").run();
    model.result("pg7").feature("traj4").feature("def1").set("expr", new String[]{"1.65", "", ""});
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("traj5", "traj4");
    model.result("pg7").run();
    model.result("pg7").feature("traj5").setIndex("looplevel", 251, 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("traj5").feature("def1").set("expr", new String[]{"2.2", "", ""});
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("col1").active(false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("traj1").set("pointcolor", "gray");
    model.result("pg6").feature("traj1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("tran1").set("transparency", 0.8);
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "custom");
    model.result("pg6").feature("surf1")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().named("sel4");
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().named("sel5");
    model.result("pg6").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "file");
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").set("fps", 25);
    model.result().export("anim1").set("framesel", "all");
    model.result("pg6").run();

    model
         .title("\u718f\u9999\u70df\u96fe - \u53ef\u89c6\u5316\u81ea\u7136\u5bf9\u6d41\u4e2d\u5c42\u6d41\u5230\u6e4d\u6d41\u7684\u8fc7\u6e21");

    model
         .description("\u672c\u4f8b\u5206\u6790\u9634\u71c3\u9999\u68d2\u4e0a\u65b9\u7a7a\u6c14\u7684\u81ea\u7136\u5bf9\u6d41\u3002\u5728\u73b0\u5b9e\u4e2d\uff0c\u8fd9\u79cd\u6d41\u52a8\u5f80\u5f80\u8868\u73b0\u51fa\u4ece\u5c42\u6d41\u5230\u6e4d\u6d41\u7684\u8fc7\u6e21\uff0c\u8fd9\u53ef\u4ee5\u901a\u8fc7\u9999\u7f13\u6162\u71c3\u70e7\u65f6\u4ea7\u751f\u7684\u70df\u96fe\u5f88\u597d\u5730\u5448\u73b0\u51fa\u6765\u3002\u6b64\u6a21\u578b\u7684\u76ee\u7684\u662f\u6a21\u62df\u8fd9\u79cd\u8fc7\u6e21\uff0c\u5e76\u901a\u8fc7\u91cd\u73b0\u70df\u7fbd\u7684\u5f62\u72b6\u8fdb\u884c\u8bf4\u660e\u3002\n\n\u4e3a\u4e86\u80fd\u591f\u6355\u6349\u5c42\u6d41\u5230\u6e4d\u6d41\u7684\u8fc7\u6e21\uff0c\u8be5\u6a21\u578b\u91c7\u7528\u201c\u975e\u7b49\u6e29\u6d41\u52a8\uff0cLES RBVM\u201d\u63a5\u53e3\uff0c\u5e76\u4f7f\u7528\u201c\u6d41\u4f53\u6d41\u52a8\u9897\u7c92\u8ddf\u8e2a\u201d\u63a5\u53e3\u6765\u751f\u6210\u6d41\u52a8\u7684\u53ef\u89c6\u5316\u6548\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("incense_stick.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
