/*
 * icp_ccp_argon_chlorine.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:06 by COMSOL 6.3.0.290. */
public class icp_ccp_argon_chlorine {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Inductively_Coupled_Plasmas");

    model.param().set("Isopar", "0.1", "\u79bb\u5b50\u5404\u5411\u540c\u6027\u6269\u6563");
    model.param().set("Picp", "250[W]", "\u7ebf\u5708\u529f\u7387");
    model.param().set("Qf", "250", "SCCCM \u4e2d\u7684\u8d28\u91cf\u6d41");
    model.param().set("xCl2", "0.9", "Cl2 \u7684\u6d41\u5165\u6469\u5c14\u5206\u6570");
    model.param().set("xAr", "1-xCl2", "Ar \u7684\u6d41\u5165\u6469\u5c14\u5206\u6570");
    model.param().set("Vrf", "0[V]", "\u5c04\u9891\u504f\u538b\u7684\u7535\u538b\u5e45\u503c");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{30, 30});
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{30, 3});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 20});
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{3, 23});
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{4.5, 0});
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{15, 5});
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new int[]{6, 5});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{15, 0});
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{15, 1});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{0, 5});
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new int[]{10, 6});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new int[]{20, 14});
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{20, 17});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{20, 18});
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin(1)", 6, 29);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(5, 6, 7, 9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(5, 6, 7, 9);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(2);
    model.component("comp1").selection("sel1").label("\u7ebf\u5708");
    model.component("comp1").selection("sel2").label("\u7ebf\u5708\u8fb9\u754c");
    model.component("comp1").selection("sel3").label("\u58c1");

    model.view().create("view3", 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat1").selection().set(4);
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
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");

    model.component("comp1").physics().create("ptp", "ColdPlasmaTimePeriodic", "geom1");
    model.component("comp1").physics("ptp").selection().set(2);
    model.component("comp1").physics("ptp").create("rg1", "ReactionGroup", 2);
    model.component("comp1").physics("ptp").create("rg2", "ReactionGroup", 2);
    model.component("comp1").physics("ptp").create("rg3", "ReactionGroup", 2);
    model.component("comp1").physics("ptp").create("e", "Species", 2);
    model.component("comp1").physics("ptp").create("Ar", "Species", 2);
    model.component("comp1").physics("ptp").create("Ars", "Species", 2);
    model.component("comp1").physics("ptp").create("Ar_1p", "Species", 2);
    model.component("comp1").physics("ptp").create("Cl2", "Species", 2);
    model.component("comp1").physics("ptp").create("Cl", "Species", 2);
    model.component("comp1").physics("ptp").create("Cl_1m", "Species", 2);
    model.component("comp1").physics("ptp").create("Cl_1p", "Species", 2);
    model.component("comp1").physics("ptp").create("Cl2_1p", "Species", 2);
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr1").selection().named("sel3");
    model.component("comp1").physics("ptp").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr2").selection().named("sel3");
    model.component("comp1").physics("ptp").create("sr3", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr3").selection().named("sel3");
    model.component("comp1").physics("ptp").create("sr4", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr4").selection().named("sel3");
    model.component("comp1").physics("ptp").create("sr5", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr5").selection().named("sel3");
    model.component("comp1").physics("ptp").create("sr6", "SurfaceReaction", 1);
    model.component("comp1").physics("ptp").feature("sr6").selection().named("sel3");
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("ptp").feature("wall1").selection().named("sel3");
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(6, 33, 34, 35, 36, 39, 40);
    model.component("comp1").physics("ptp").create("in1", "Inflow", 1);
    model.component("comp1").physics("ptp").feature("in1").selection().set(35);
    model.component("comp1").physics("ptp").create("out1", "Outflow", 1);
    model.component("comp1").physics("ptp").feature("out1").selection().set(39);
    model.component("comp1").physics("ptp").create("dct1", "DielectricContact", 1);
    model.component("comp1").physics("ptp").feature("dct1").selection().set(27, 38);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(4);
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").selection().set(2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(35);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(39);
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").selection().set(2);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel3");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").selection().set(2, 3, 4, 5, 6, 7, 9);
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid", 2);
    model.component("comp1").physics("mf").feature("alf1").selection().all();
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel1");

    model.component("comp1").multiphysics()
         .create("nipfptp1", "NonIsothermalPlasmaFlowTimePeriodicMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics()
         .create("pccptp1", "PlasmaConductivityTimePeriodicMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics()
         .create("ehsptp1", "ElectronHeatSourceTimePeriodicMultiphysicsCoupling", 2);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 10, 11);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(35);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(4, 45);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(3, 46);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2, 11);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(4, 6, 27, 33, 34, 36, 38, 39, 40);

    model.component("comp1").view("view1").axis().set("xmin", -7.146181106567383);
    model.component("comp1").view("view1").axis().set("xmax", 37.14617919921875);
    model.component("comp1").view("view1").axis().set("ymin", -0.7873773574829102);
    model.component("comp1").view("view1").axis().set("ymax", 30.787376403808594);

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
    model.component("comp1").material("mat3").label("Glass (quartz)");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.99);
    model.component("comp1").material("mat3").set("roughness", 0.02);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});

    model.component("comp1").physics("ptp").prop("Stabilization").set("ReactionSourceStabilization", true);
    model.component("comp1").physics("ptp").prop("InconsistentStabilization").set("IsotropicDiffusionIons", true);
    model.component("comp1").physics("ptp").prop("InconsistentStabilization").set("delidion", "Isopar");
    model.component("comp1").physics("ptp").prop("TransportSettings").set("calcThermo", true);
    model.component("comp1").physics("ptp").prop("TransportSettings").set("MixtureDiffusionCorrection", true);
    model.component("comp1").physics("ptp").prop("TransportSettings").set("Convection", true);
    model.component("comp1").physics("ptp").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("ptp").prop("EEDFSettings").set("eedf", "Maxwellian");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 45);
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings")
         .set("HeavySpeciesSelection", "BaseGeometry");
    model.component("comp1").physics("ptp").feature("rg1").set("reactionGroupType", "ElectronImpactReactions");
    model.component("comp1").physics("ptp").feature("rg1")
         .set("Species", new String[][]{{"Ar"}, {"Ar_1p"}, {"Ars"}, {"e"}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("fwdSpecies", new String[][]{{"e+Ar"}, {"e+Ar"}, {"e+Ars"}, {"e+Ar"}, {"e+Ars"}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("revSpecies", new String[][]{{"e+Ar"}, {"e+Ars"}, {"e+Ar"}, {"2e+Ar+"}, {"2e+Ar+"}});
    model.component("comp1").physics("ptp").feature("rg1").set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}});
    model.component("comp1").physics("ptp").feature("rg1").set("Af", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1").set("nf", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1").set("Ef", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1").set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1").set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1").set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("xdata_crossSection", new String[][]{{}, 
         {"0.0", "0.001", "0.002", "0.003", "0.005", "0.007", "0.0085", "0.01", "0.015", "0.02", 
         "0.03", "0.04", "0.05", "0.07", "0.1", "0.12", "0.15", "0.17", "0.2", "0.25", 
         "0.3", "0.35", "0.4", "0.5", "0.7", "1.0", "1.2", "1.3", "1.5", "1.7", 
         "1.9", "2.1", "2.2", "2.5", "2.8", "3.0", "3.3", "3.6", "4.0", "4.5", 
         "5.0", "6.0", "7.0", "8.0", "10.0", "12.0", "15.0", "17.0", "20.0", "25.0", 
         "30.0", "50.0", "75.0", "100.0", "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", 
         "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0", "1000000.0"}, 
         {"0.0", "11.5", "12.7", "13.7", "14.7", "15.9", "16.5", "17.5", "18.5", "19.9", 
         "22.2", "24.7", "27.0", "30.0", "33.0", "35.3", "42.0", "48.0", "52.0", "70.0", 
         "100.0", "150.0", "200.0", "300.0", "500.0", "700.0", "1000.0", "1500.0", "2000.0", "3000.0", 
         "5000.0", "7000.0", "10000.0", "1000000.0"}, 
         {"-11.5", "0.0", "1.1999999999999993", "2.1999999999999993", "3.1999999999999993", "4.4", "5.0", "6.0", "7.0", "8.399999999999999", 
         "10.7", "13.2", "15.5", "18.5", "21.5", "23.799999999999997", "30.5", "36.5", "40.5", "58.5", 
         "88.5", "138.5", "188.5", "288.5", "488.5", "688.5", "988.5", "1488.5", "1988.5", "2988.5", 
         "4988.5", "6988.5", "9988.5", "999988.5"}, 
         {"0.0", "15.8", "16.0", "17.0", "18.0", "20.0", "22.0", "23.75", "25.0", "26.5", 
         "30.0", "32.5", "35.0", "37.5", "40.0", "50.0", "55.0", "100.0", "150.0", "200.0", 
         "300.0", "500.0", "700.0", "1000.0", "1500.0", "2000.0", "3000.0", "5000.0", "7000.0", "10000.0", 
         "1000000.0"}, 
         {"0.0", "4.427", "4.628", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0", "11.5", 
         "15.5", "20.0", "30.0", "50.0", "100.0", "200.0", "1000.0", "10000.0", "100000.0", "1000000.0"}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("ydata_crossSection", new String[][]{{}, 
         {"7.5E-20", "7.5E-20", "7.1E-20", "6.7E-20", "6.1E-20", "5.4E-20", "5.05E-20", "4.6E-20", "3.75E-20", "3.25E-20", 
         "2.5E-20", "2.05E-20", "1.73E-20", "1.13E-20", "5.9E-21", "4.0E-21", "2.3E-21", "1.6E-21", "1.03E-21", "9.1E-22", 
         "1.53E-21", "2.35E-21", "3.3E-21", "5.1E-21", "8.6E-21", "1.38E-20", "1.66E-20", "1.82E-20", "2.1E-20", "2.3E-20", 
         "2.5E-20", "2.8E-20", "2.9E-20", "3.3E-20", "3.8E-20", "4.1E-20", "4.5E-20", "4.9E-20", "5.4E-20", "6.1E-20", 
         "6.7E-20", "8.1E-20", "9.6E-20", "1.17E-19", "1.5E-19", "1.45E-19", "1.37E-19", "1.1E-19", "9.2E-20", "6.8E-20", 
         "5.5E-20", "3.2E-20", "2.15E-20", "1.6E-20", "1.1E-20", "8.8E-21", "6.0E-21", "3.7E-21", "2.6E-21", "1.7E-21", 
         "9.8E-22", "6.6E-22", "3.5E-22", "1.5E-22", "8.8E-23", "4.9E-23", "4.9E-23"}, 
         {"0.0", "0.0", "7.0E-22", "1.41E-21", "2.28E-21", "3.8E-21", "4.8E-21", "6.1E-21", "7.5E-21", "9.2E-21", 
         "1.17E-20", "1.33E-20", "1.42E-20", "1.44E-20", "1.41E-20", "1.34E-20", "1.25E-20", "1.16E-20", "1.11E-20", "9.4E-21", 
         "7.6E-21", "6.0E-21", "5.05E-21", "3.95E-21", "2.8E-21", "2.25E-21", "1.77E-21", "1.36E-21", "1.1E-21", "8.3E-22", 
         "5.8E-22", "4.5E-22", "3.5E-22", "3.5E-22"}, 
         {"-0.0", "0.0", "6.173611111111115E-22", "7.317045454545457E-22", "8.728125E-22", "1.1443181818181814E-21", "1.3199999999999998E-21", "1.4826388888888888E-21", "1.6517857142857143E-21", "1.8162698412698414E-21", 
         "2.0228971962616822E-21", "2.073926767676768E-21", "2.061290322580645E-21", "1.945945945945946E-21", "1.803488372093023E-21", "1.6562324929971988E-21", "1.4344262295081967E-21", "1.2712328767123287E-21", "1.1876543209876544E-21", "9.373219373219375E-22", 
         "7.156308851224105E-22", "5.415162454873646E-22", "4.465075154730327E-22", "3.4228769497400347E-22", "2.388263391334016E-22", "1.906318082788671E-22", "1.4921598381385938E-22", "1.1420893516963384E-22", "9.219679825664236E-23", "6.943282583235737E-23", 
         "4.8444756272760683E-23", "3.756170852114187E-23", "2.920024695065992E-23", "2.916700208719067E-23"}, 
         {"0.0", "0.0", "2.02E-22", "1.34E-21", "2.94E-21", "6.3E-21", "9.3E-21", "1.15E-20", "1.3E-20", "1.45E-20", 
         "1.8E-20", "1.99E-20", "2.17E-20", "2.31E-20", "2.39E-20", "2.53E-20", "2.6E-20", "2.85E-20", "2.52E-20", "2.39E-20", 
         "2.0E-20", "1.45E-20", "1.15E-20", "8.6E-21", "6.4E-21", "5.2E-21", "3.6E-21", "2.4E-21", "1.8E-21", "1.35E-21", 
         "1.35E-21"}, 
         {"0.0", "0.0", "1.849E-20", "3.1E-20", "5.8E-20", "6.9E-20", "7.6E-20", "8.0E-20", "8.2E-20", "8.35E-20", 
         "7.8E-20", "7.0E-20", "5.4E-20", "3.8E-20", "2.05E-20", "1.2E-20", "3.5E-21", "6.1E-22", "1.08E-22", "1.08E-22"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ptp").feature("rg1")
         .set("collType", new String[][]{{"Elastic"}, {"Excitation"}, {"Excitation"}, {"Ionization"}, {"Ionization"}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("mratio", new String[][]{{"0.136E-04"}, {"0"}, {"0"}, {"0"}, {"0"}});
    model.component("comp1").physics("ptp").feature("rg1")
         .set("de", new String[][]{{"0"}, {"11.50"}, {"-11.50"}, {"15.80"}, {"4.427"}});
    model.component("comp1").physics("ptp").feature("rg1").set("Filepath", "Ar_Cl2_plasma_chemistry_import_Ar.txt");
    model.component("comp1").physics("ptp").feature("rg1").label("Ar - Electron Impact Reactions");
    model.component("comp1").physics("ptp").feature("rg2").set("reactionGroupType", "ElectronImpactReactions");
    model.component("comp1").physics("ptp").feature("rg2")
         .set("Species", new String[][]{{"Cl"}, {"Cl2"}, {"Cl2_1p"}, {"Cl_1m"}, {"Cl_1p"}, {"e"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("fwdSpecies", new String[][]{{"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl"}, 
         {"e+Cl-"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("revSpecies", new String[][]{{"Cl+Cl-"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl+Cl"}, 
         {"e+Cl+Cl"}, 
         {"e+Cl+Cl"}, 
         {"e+Cl+Cl"}, 
         {"e+Cl+Cl"}, 
         {"e+Cl2"}, 
         {"e+Cl2"}, 
         {"e+Cl-+Cl+"}, 
         {"2e+Cl2+"}, 
         {"2e+Cl+Cl+"}, 
         {"Cl++2e"}, 
         {"Cl+2e"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("Af", new String[][]{{"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"4e-14[m^3/s]*(ptp.Te/1[V])^(0.5)*exp(-12[V]/ptp.Te)*N_A_const"}, 
         {"1e-14[m^3/s]*(ptp.Te/1[V])^(0.8)*exp(-5[V]/ptp.Te)*N_A_const"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("nf", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("Ef", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("xdata_crossSection", new String[][]{{}, 
         {"0.0", "3.138E-4", "0.002275", "0.008121", "0.01918", "0.03315", "0.04649", "0.05951", "0.07576", "0.09204", 
         "0.1106", "0.1311", "0.1476", "0.1645", "0.1824", "0.2052", "0.3", "0.4", "0.5", "0.6", 
         "0.7", "0.8", "0.9", "1.0", "1.2", "1.4", "1.6", "1.8", "2.0", "2.4", 
         "2.6", "2.8", "3.0", "3.2", "3.4", "3.6", "3.8", "4.0", "4.2", "4.4", 
         "4.6", "4.8", "5.0", "5.2", "5.4", "5.6", "5.8", "6.0", "6.2", "6.6", 
         "7.0", "7.4", "7.8", "8.2", "8.4", "8.6", "9.0", "9.4", "9.8", "10.0", 
         "10.4", "11.0", "11.2", "11.4", "11.6", "11.8"}, 
         {"0.01983", "0.02391", "0.02797", "0.03204", "0.03603", "0.04011", "0.04386", "0.04741", "0.05175", "0.05705", 
         "0.05878", "0.06353", "0.0681", "0.07216", "0.07812", "0.08527", "0.08961", "0.09401", "0.1016", "0.11", 
         "0.1225", "0.1311", "0.1419", "0.1519", "0.1658", "0.1777", "0.1864", "0.1921", "0.2", "0.21", 
         "0.22", "0.23", "0.24", "0.25", "0.3359", "0.4655", "0.5858", "0.7639", "1.041", "1.405", 
         "2.003", "3.254", "5.062", "6.314", "8.456", "10.93", "15.28", "21.78", "29.17"}, 
         {"0.0", "0.069", "0.08", "0.081", "0.082", "0.083", "0.084", "0.085", "0.086", "0.087", 
         "0.088", "0.089", "0.09", "0.091", "0.092", "0.093", "0.094", "0.095", "0.096", "0.097", 
         "0.098", "0.099", "0.1", "0.11", "0.12", "0.13", "0.14", "0.15", "0.16", "0.17", 
         "0.18", "0.19", "0.2", "0.21", "0.22", "0.23", "0.24", "0.25", "0.26", "0.27", 
         "0.28", "0.29", "0.3", "0.31", "0.32", "0.33", "0.34", "0.35", "0.36", "0.37", 
         "0.38", "0.39", "0.4", "0.41", "0.42", "0.43", "0.44", "0.45", "0.46", "0.47", 
         "0.48", "0.49", "0.5", "0.51", "0.52", "0.53", "0.54", "0.55", "0.56", "0.57", 
         "0.58", "0.59", "0.6", "0.61", "0.62", "0.63", "0.64", "0.65", "0.66", "0.67", 
         "0.68", "0.69", "0.7", "0.71", "0.72", "0.73", "0.74", "0.75", "0.76", "0.77", 
         "0.78", "0.79", "0.8", "0.81", "0.82", "0.83", "0.84", "0.85", "0.86", "0.87", 
         "0.88", "0.89", "0.9", "0.91", "0.92", "0.93", "0.94", "0.95", "0.96", "0.97", 
         "0.98", "0.99", "1.0", "1.01", "1.02", "1.03", "1.032", "1.034", "1.036", "1.038", 
         "1.04", "1.042", "1.044", "1.046", "1.048", "1.05", "1.051", "1.052", "1.053", "1.054", 
         "1.055", "1.056", "1.057", "1.059"}, 
         {"0.0", "0.139", "2.45", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0", "3.1", 
         "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "4.0", "4.1", 
         "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8", "4.9", "5.0", "5.1", 
         "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8", "5.9", "6.0", "6.1", 
         "6.2", "6.3", "6.4", "6.5", "6.6", "6.7", "6.8", "6.9", "7.0", "7.1", 
         "7.2", "7.3", "7.4", "7.5", "7.6", "7.7", "7.8", "7.9", "8.0", "8.1", 
         "8.2", "8.3", "8.4", "8.5", "8.6", "8.7", "8.8", "8.9", "9.0", "9.1", 
         "9.2", "9.3", "9.4", "9.5", "9.6", "9.7", "9.8", "9.9", "10.0", "10.5", 
         "11.0", "11.5", "12.0", "12.5", "13.0", "13.5", "14.0", "14.5", "15.0", "15.5", 
         "16.0", "16.5", "17.0", "17.5", "18.0"}, 
         {"0.0", "3.36", "4.366", "4.584", "4.998", "5.503", "6.007", "6.39", "7.24", "7.558", 
         "8.095", "10.02", "12.11", "15.05", "20.04", "25.04", "30.0", "47.4", "97.4"}, 
         {"0.0", "4.3", "4.393", "4.551", "5.115", "5.553", "6.117", "6.528", "7.309", "7.562", 
         "8.063", "10.04", "12.11", "15.09", "20.02", "25.04", "30.0", "47.4", "97.4"}, 
         {"0.0", "6.38", "7.127", "7.351", "7.984", "10.04", "11.94", "15.02", "20.12", "25.12", 
         "30.0", "47.4", "97.4"}, 
         {"0.0", "7.1", "8.048", "10.01", "12.04", "15.05", "20.08", "25.15", "30.0", "47.4", 
         "97.4"}, 
         {"0.0", "7.02", "7.191", "7.575", "8.048", "10.04", "12.04", "15.08", "19.99", "25.03", 
         "29.97", "47.4", "97.4"}, 
         {"0.0", "10.541", "10.57", "10.62", "10.68", "10.8", "10.88", "11.0", "11.08", "11.17", 
         "11.31", "11.43", "11.63", "11.81", "11.92", "12.09", "12.24", "12.44", "12.61", "12.79", 
         "12.93", "13.13", "13.36", "13.59", "13.97", "14.4", "14.75", "15.01", "15.38", "15.84", 
         "16.39", "16.74", "17.34", "17.75", "18.21", "18.58", "19.22", "19.71", "20.25", "20.89", 
         "21.29", "21.78", "22.53", "23.57", "24.38", "25.07", "25.62", "26.19", "26.57", "27.95", 
         "28.39", "30.0"}, 
         {"0.0", "10.7", "10.74", "10.8", "10.88", "11.03", "11.17", "11.34", "11.66", "11.86", 
         "12.01", "12.18", "12.38", "12.58", "12.79", "12.93", "13.16", "13.33", "13.51", "13.71", 
         "13.94", "14.17", "14.4", "14.66", "14.95", "15.27", "15.58", "15.9", "16.1", "16.42", 
         "16.74", "17.11", "17.43", "17.69", "18.06", "18.58", "18.99", "19.36", "19.97", "20.46", 
         "20.83", "21.38", "22.16", "23.51", "24.78", "26.22", "26.54", "27.52", "27.92", "28.64", 
         "30.0"}, 
         {"0.0", "11.0", "12.0", "13.0", "14.0", "16.0", "18.0", "20.0", "22.0", "24.0", 
         "26.0", "28.0", "30.0", "32.0", "34.0", "36.0", "38.0", "40.0", "44.0", "46.0", 
         "48.0", "50.0", "52.0", "54.0", "56.0", "58.0", "60.0", "62.0", "64.0", "66.0", 
         "68.0", "70.0", "72.0", "74.0", "76.0", "78.0", "79.0", "80.0", "82.0", "84.0", 
         "86.0", "88.0", "90.0", "92.0", "94.0", "96.0", "98.0", "100.0"}, 
         {"0.0", "11.49", "12.0", "13.0", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", 
         "20.0", "22.0", "24.0", "26.0", "28.0", "30.0", "32.0", "34.0", "36.0", "38.0", 
         "40.0", "42.0", "44.0", "46.0", "48.0", "50.0", "52.5", "55.0", "57.5", "60.0", 
         "65.0", "70.0", "80.0", "90.0", "100.0", "120.0", "140.0", "160.0", "180.0", "200.0", 
         "300.0", "400.0", "500.0", "600.0", "700.0", "800.0", "900.0"}, 
         {"0.0", "11.49", "12.0", "13.0", "14.0", "15.0", "16.0", "17.0", "18.0", "19.0", 
         "20.0", "22.0", "24.0", "26.0", "28.0", "30.0", "32.0", "34.0", "36.0", "38.0", 
         "40.0", "42.0", "44.0", "46.0", "48.0", "50.0", "52.5", "55.0", "57.5", "60.0", 
         "65.0", "70.0", "80.0", "90.0", "100.0", "120.0", "140.0", "160.0", "180.0", "200.0", 
         "300.0", "400.0", "500.0", "600.0", "700.0", "800.0", "900.0"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("ydata_crossSection", new String[][]{{}, 
         {"0.0", "6.388E-22", "5.349E-21", "1.06E-20", "1.461E-20", "1.696E-20", "1.75E-20", "1.728E-20", "1.616E-20", "1.445E-20", 
         "1.252E-20", "1.038E-20", "8.776E-21", "7.221E-21", "5.885E-21", "4.44E-21", "6.2E-22", "2.0E-22", "9.8E-23", "6.8E-23", 
         "6.3E-23", "5.0E-23", "4.4E-23", "4.2E-23", "4.8E-23", "5.9E-23", "8.4E-23", "1.32E-22", "1.81E-22", "2.77E-22", 
         "2.79E-22", "2.49E-22", "1.92E-22", "1.36E-22", "1.06E-22", "9.5E-23", "1.05E-22", "1.29E-22", "1.69E-22", "2.09E-22", 
         "2.57E-22", "3.08E-22", "3.63E-22", "4.08E-22", "4.46E-22", "4.74E-22", "4.84E-22", "4.78E-22", "4.62E-22", "3.99E-22", 
         "3.01E-22", "1.78E-22", "1.0E-22", "5.1E-23", "4.5E-23", "4.1E-23", "3.95E-23", "3.86E-23", "3.9E-23", "3.8E-23", 
         "3.65E-23", "3.34E-23", "3.25E-23", "3.16E-23", "3.18E-23", "3.28E-23"}, 
         {"4.029E-19", "3.853E-19", "3.72E-19", "3.428E-19", "3.092E-19", "2.725E-19", "2.291E-19", "1.886E-19", "1.55E-19", "1.261E-19", 
         "1.073E-19", "9.02E-20", "7.674E-20", "7.338E-20", "8.328E-20", "9.562E-20", "1.048E-19", "1.012E-19", "9.451E-20", "9.02E-20", 
         "9.126E-20", "9.903E-20", "9.903E-20", "8.709E-20", "7.764E-20", "6.466E-20", "5.632E-20", "4.5E-20", "3.8E-20", "2.8E-20", 
         "2.0E-20", "1.7E-20", "1.55E-20", "1.45E-20", "1.531E-20", "1.886E-20", "2.448E-20", "3.375E-20", "4.902E-20", "6.765E-20", 
         "8.936E-20", "1.169E-19", "1.368E-19", "1.531E-19", "1.869E-19", "2.022E-19", "1.902E-19", "1.571E-19", "1.276E-19"}, 
         {"0.0", "0.0", "7.0E-23", "8.83E-23", "1.069E-22", "1.3E-22", "1.533E-22", "1.767E-22", "2.0E-22", "2.25E-22", 
         "2.5E-22", "2.75E-22", "3.0E-22", "3.269E-22", "3.537E-22", "3.806E-22", "4.075E-22", "4.344E-22", "4.612E-22", "4.881E-22", 
         "5.149E-22", "5.417E-22", "5.686E-22", "9.024E-22", "1.29E-21", "1.741E-21", "2.235E-21", "2.686E-21", "3.126E-21", "3.579E-21", 
         "4.057E-21", "4.466E-21", "4.87E-21", "5.213E-21", "5.534E-21", "5.855E-21", "6.162E-21", "6.465E-21", "6.739E-21", "6.972E-21", 
         "7.202E-21", "7.431E-21", "7.659E-21", "7.886E-21", "8.056E-21", "8.19E-21", "8.32E-21", "8.452E-21", "8.582E-21", "8.711E-21", 
         "8.841E-21", "8.969E-21", "9.097E-21", "9.225E-21", "9.347E-21", "9.442E-21", "9.534E-21", "9.625E-21", "9.716E-21", "9.807E-21", 
         "9.896E-21", "9.979E-21", "1.006E-20", "1.018E-20", "1.026E-20", "1.035E-20", "1.044E-20", "1.048E-20", "1.051E-20", "1.054E-20", 
         "1.057E-20", "1.06E-20", "1.062E-20", "1.065E-20", "1.067E-20", "1.066E-20", "1.065E-20", "1.065E-20", "1.064E-20", "1.063E-20", 
         "1.061E-20", "1.06E-20", "1.056E-20", "1.049E-20", "1.042E-20", "1.035E-20", "1.028E-20", "1.021E-20", "1.014E-20", "9.999E-21", 
         "9.861E-21", "9.717E-21", "9.574E-21", "9.431E-21", "9.292E-21", "9.118E-21", "8.863E-21", "8.609E-21", "8.352E-21", "8.107E-21", 
         "7.849E-21", "7.567E-21", "7.274E-21", "6.984E-21", "6.696E-21", "6.337E-21", "5.953E-21", "5.582E-21", "5.099E-21", "4.612E-21", 
         "4.129E-21", "3.72E-21", "3.323E-21", "2.968E-21", "2.559E-21", "2.096E-21", "2.027E-21", "1.958E-21", "1.874E-21", "1.774E-21", 
         "1.674E-21", "1.583E-21", "1.5E-21", "1.418E-21", "1.338E-21", "1.259E-21", "1.224E-21", "1.188E-21", "1.153E-21", "1.118E-21", 
         "7.782E-22", "7.82E-22", "7.859E-22", "0.0"}, 
         {"0.0", "0.0", "0.0", "2.22E-22", "3.21E-22", "8.36E-22", "1.451E-21", "2.079E-21", "2.708E-21", "3.836E-21", 
         "4.964E-21", "5.979E-21", "7.094E-21", "8.199E-21", "9.305E-21", "1.03E-20", "1.149E-20", "1.288E-20", "1.437E-20", "1.635E-20", 
         "1.843E-20", "2.041E-20", "2.151E-20", "2.351E-20", "2.617E-20", "2.897E-20", "3.176E-20", "3.466E-20", "3.775E-20", "4.345E-20", 
         "4.909E-20", "5.483E-20", "6.058E-20", "6.623E-20", "7.198E-20", "7.773E-20", "8.339E-20", "8.906E-20", "9.483E-20", "9.949E-20", 
         "1.043E-19", "1.089E-19", "1.137E-19", "1.184E-19", "1.23E-19", "1.278E-19", "1.324E-19", "1.372E-19", "1.418E-19", "1.415E-19", 
         "1.393E-19", "1.383E-19", "1.372E-19", "1.363E-19", "1.352E-19", "1.344E-19", "1.335E-19", "1.325E-19", "1.316E-19", "1.273E-19", 
         "1.232E-19", "1.192E-19", "1.152E-19", "1.111E-19", "1.077E-19", "1.044E-19", "1.01E-19", "9.769E-20", "9.429E-20", "9.199E-20", 
         "8.98E-20", "8.75E-20", "8.53E-20", "8.3E-20", "8.13E-20", "7.98E-20", "7.84E-20", "7.69E-20", "7.55E-20", "7.077E-20", 
         "6.569E-20", "6.084E-20", "5.685E-20", "5.11E-20", "4.533E-20", "4.131E-20", "3.711E-20", "3.104E-20", "2.482E-20", "1.92E-20", 
         "1.449E-20", "1.063E-20", "6.862E-21", "3.169E-21", "0.0"}, 
         {"0.0", "0.0", "8.45E-22", "1.208E-21", "1.544E-21", "1.94E-21", "2.332E-21", "2.672E-21", "3.597E-21", "3.904E-21", 
         "4.159E-21", "5.112E-21", "5.225E-21", "6.099E-21", "5.617E-21", "5.112E-21", "4.3E-21", "2.08E-21", "8.71E-22"}, 
         {"0.0", "0.0", "1.68E-22", "4.995E-22", "1.055E-21", "1.307E-21", "1.638E-21", "1.806E-21", "2.39E-21", "2.665E-21", 
         "2.973E-21", "4.387E-21", "4.084E-21", "4.387E-21", "2.833E-21", "3.556E-21", "2.082E-21", "1.04E-21", "4.81E-22"}, 
         {"0.0", "0.0", "1.09E-21", "1.485E-21", "1.621E-21", "1.513E-21", "1.26E-21", "2.966E-21", "3.412E-21", "4.169E-21", 
         "3.412E-21", "1.69E-21", "7.41E-22"}, 
         {"0.0", "0.0", "2.259E-22", "3.671E-22", "1.205E-21", "1.318E-21", "1.374E-21", "2.24E-21", "1.12E-21", "5.2E-22", 
         "2.34E-22"}, 
         {"0.0", "0.0", "4.479E-22", "1.291E-21", "2.583E-21", "2.107E-21", "1.711E-21", "2.555E-21", "4.459E-21", "2.918E-21", 
         "3.337E-21", "1.43E-21", "6.76E-22"}, 
         {"0.0", "0.0", "4.87E-23", "8.56E-23", "1.072E-22", "1.681E-22", "2.105E-22", "2.472E-22", "2.811E-22", "3.03E-22", 
         "3.372E-22", "3.674E-22", "4.133E-22", "4.551E-22", "4.906E-22", "5.344E-22", "5.885E-22", "6.481E-22", "7.06E-22", "7.692E-22", 
         "8.202E-22", "9.032E-22", "1.005E-21", "1.119E-21", "1.342E-21", "1.593E-21", "1.831E-21", "1.995E-21", "2.269E-21", "2.664E-21", 
         "3.128E-21", "3.408E-21", "3.794E-21", "3.96E-21", "4.045E-21", "4.045E-21", "4.045E-21", "4.045E-21", "4.089E-21", "4.222E-21", 
         "4.314E-21", "4.455E-21", "4.7E-21", "4.958E-21", "5.175E-21", "5.344E-21", "5.46E-21", "5.578E-21", "5.638E-21", "5.869E-21", 
         "5.918E-21", "6.143E-21"}, 
         {"0.0", "0.0", "2.64E-23", "3.06E-23", "3.56E-23", "4.22E-23", "4.7E-23", "5.12E-23", "5.89E-23", "6.69E-23", 
         "7.45E-23", "8.47E-23", "9.84E-23", "1.143E-22", "1.259E-22", "1.401E-22", "1.593E-22", "1.793E-22", "1.995E-22", "2.174E-22", 
         "2.525E-22", "2.841E-22", "3.231E-22", "3.674E-22", "4.268E-22", "5.012E-22", "6.013E-22", "6.911E-22", "7.61E-22", "8.561E-22", 
         "9.632E-22", "1.038E-21", "1.107E-21", "1.119E-21", "1.115E-21", "1.072E-21", "1.025E-21", "9.84E-22", "9.228E-22", "9.13E-22", 
         "9.13E-22", "9.376E-22", "9.736E-22", "1.027E-21", "1.072E-21", "1.119E-21", "1.131E-21", "1.168E-21", "1.18E-21", "1.206E-21", 
         "1.245E-21"}, 
         {"0.0", "0.0", "1.22E-23", "4.6E-23", "9.0E-23", "1.8E-22", "2.4E-22", "2.58E-22", "2.4E-22", "2.07E-22", 
         "1.66E-22", "1.35E-22", "1.08E-22", "8.9E-23", "7.4E-23", "6.5E-23", "6.2E-23", "6.0E-23", "6.2E-23", "6.4E-23", 
         "6.9E-23", "7.6E-23", "8.8E-23", "1.05E-22", "1.28E-22", "1.52E-22", "1.76E-22", "1.96E-22", "2.18E-22", "2.34E-22", 
         "2.52E-22", "2.72E-22", "3.18E-22", "3.73E-22", "4.06E-22", "4.24E-22", "4.28E-22", "4.25E-22", "4.2E-22", "4.13E-22", 
         "4.04E-22", "3.94E-22", "3.82E-22", "3.7E-22", "3.57E-22", "3.4E-22", "3.22E-22", "3.07E-22"}, 
         {"0.0", "0.0", "1.0E-21", "3.46E-21", "7.46E-21", "1.13E-20", "1.58E-20", "2.0E-20", "2.39E-20", "2.72E-20", 
         "3.13E-20", "3.65E-20", "4.0E-20", "4.3E-20", "4.49E-20", "4.57E-20", "4.59E-20", "4.54E-20", "4.44E-20", "4.36E-20", 
         "4.31E-20", "4.28E-20", "4.26E-20", "4.25E-20", "4.27E-20", "4.31E-20", "4.34E-20", "4.38E-20", "4.41E-20", "4.43E-20", 
         "4.49E-20", "4.52E-20", "4.55E-20", "4.51E-20", "4.5E-20", "4.41E-20", "4.17E-20", "4.12E-20", "4.03E-20", "3.87E-20", 
         "3.22E-20", "2.79E-20", "2.45E-20", "2.14E-20", "1.98E-20", "1.8E-20", "1.63E-20"}, 
         {"0.0", "0.0", "0.0", "1.4E-22", "3.2E-22", "5.5E-22", "8.1E-22", "1.26E-21", "2.09E-21", "2.97E-21", 
         "4.35E-21", "7.15E-21", "1.08E-20", "1.43E-20", "1.85E-20", "2.18E-20", "2.67E-20", "2.93E-20", "3.13E-20", "3.3E-20", 
         "3.4E-20", "3.55E-20", "3.63E-20", "3.68E-20", "3.74E-20", "3.8E-20", "3.85E-20", "3.87E-20", "3.9E-20", "3.95E-20", 
         "3.99E-20", "4.03E-20", "4.01E-20", "3.9E-20", "3.84E-20", "3.67E-20", "3.35E-20", "3.23E-20", "3.04E-20", "2.86E-20", 
         "2.16E-20", "1.76E-20", "1.45E-20", "1.25E-20", "1.11E-20", "9.73E-21", "8.9E-21"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("collType", new String[][]{{"Attachment"}, 
         {"Elastic"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Excitation"}, 
         {"Ionization"}, 
         {"Ionization"}, 
         {"Ionization"}, 
         {"Ionization"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("mratio", new String[][]{{"0"}, 
         {"7.680000e-6"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}, 
         {"0"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("de", new String[][]{{"0"}, 
         {"0"}, 
         {"6.900000e-2"}, 
         {"1.390000e-1"}, 
         {"3.360000e+0"}, 
         {"4.300000e+0"}, 
         {"6.380000e+0"}, 
         {"7.010000e+0"}, 
         {"7.020000e+0"}, 
         {"1.054000e+1"}, 
         {"1.070000e+1"}, 
         {"1.100000e+1"}, 
         {"1.149000e+1"}, 
         {"1.149000e+1"}, 
         {"14.25"}, 
         {"14.25"}});
    model.component("comp1").physics("ptp").feature("rg2")
         .set("SpecifyReactionUsing", new String[][]{{"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"UseCrossSectionData"}, 
         {"arrheniusparameters"}, 
         {"arrheniusparameters"}});
    model.component("comp1").physics("ptp").feature("rg2").set("Filepath", "Ar_Cl2_plasma_chemistry_import_Cl.txt");
    model.component("comp1").physics("ptp").feature("rg2").label("Cl - Electron Impact Reactions");
    model.component("comp1").physics("ptp").feature("rg3")
         .set("Species", new String[][]{{"Ar"}, {"Ar_1p"}, {"Ars"}, {"Cl"}, {"Cl2_1p"}, {"Cl_1m"}, {"Cl_1p"}, {"e"}});
    model.component("comp1").physics("ptp").feature("rg3")
         .set("fwdSpecies", new String[][]{{"Ars+Ars"}, {"Cl++Cl-"}, {"Cl2++Cl-"}, {"Cl-+Ar+"}});
    model.component("comp1").physics("ptp").feature("rg3")
         .set("revSpecies", new String[][]{{"Ar++Ar+e"}, {"2Cl"}, {"3Cl"}, {"Cl+Ar"}});
    model.component("comp1").physics("ptp").feature("rg3").set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}});
    model.component("comp1").physics("ptp").feature("rg3")
         .set("Af", new String[][]{{"5e-10[cm^3/s]*N_A_const"}, {"1e-14[m^3/s]*(ptp.T/300[K])^0.5*N_A_const"}, {"1e-14[m^3/s]*(ptp.T/300[K])^0.5*N_A_const"}, {"1e-14[m^3/s]*(ptp.T/300[K])^0.5*N_A_const"}});
    model.component("comp1").physics("ptp").feature("rg3").set("nf", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg3").set("Ef", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg3").set("Ar", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg3").set("nr", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg3").set("Er", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("rg3")
         .set("Filepath", "Ar_Cl2_plasma_chemistry_import_mixed.txt");
    model.component("comp1").physics("ptp").feature("rg3").label("Mixed - Heavy Species Reactions");
    model.component("comp1").physics("ptp").feature("e").set("specName", "e");
    model.component("comp1").physics("ptp").feature("e").set("specLabel", "e");
    model.component("comp1").physics("ptp").feature("e").set("sType", "electron");
    model.component("comp1").physics("ptp").feature("e").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("e").label("\u7269\u8d28: e");
    model.component("comp1").physics("ptp").feature("Ar").set("specName", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").set("specLabel", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("Ar").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar").label("\u7269\u8d28: Ar");
    model.component("comp1").physics("ptp").feature("Ars").set("specName", "Ars");
    model.component("comp1").physics("ptp").feature("Ars").set("specLabel", "Ars");
    model.component("comp1").physics("ptp").feature("Ars").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ars").set("hadd", 11.5);
    model.component("comp1").physics("ptp").feature("Ars").label("\u7269\u8d28: Ars");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("specName", "Ar_1p");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("specLabel", "Ar+");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("Ar_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("hadd", "15.80");
    model.component("comp1").physics("ptp").feature("Ar_1p").label("\u7269\u8d28: Ar+");
    model.component("comp1").physics("ptp").feature("Cl2").set("specName", "Cl2");
    model.component("comp1").physics("ptp").feature("Cl2").set("specLabel", "Cl2");
    model.component("comp1").physics("ptp").feature("Cl2").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Cl2").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("ptp").feature("Cl2").set("x0", "xCl2");
    model.component("comp1").physics("ptp").feature("Cl2").label("\u7269\u8d28: Cl2");
    model.component("comp1").physics("ptp").feature("Cl").set("specName", "Cl");
    model.component("comp1").physics("ptp").feature("Cl").set("specLabel", "Cl");
    model.component("comp1").physics("ptp").feature("Cl").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Cl").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("ptp").feature("Cl").set("x0", "1e-4");
    model.component("comp1").physics("ptp").feature("Cl").label("\u7269\u8d28: Cl");
    model.component("comp1").physics("ptp").feature("Cl_1m").set("specName", "Cl_1m");
    model.component("comp1").physics("ptp").feature("Cl_1m").set("specLabel", "Cl-");
    model.component("comp1").physics("ptp").feature("Cl_1m").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("Cl_1m").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Cl_1m").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("ptp").feature("Cl_1m").set("z", -1);
    model.component("comp1").physics("ptp").feature("Cl_1m").set("n0", "1e10");
    model.component("comp1").physics("ptp").feature("Cl_1m").label("\u7269\u8d28: Cl-");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("specName", "Cl_1p");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("specLabel", "Cl+");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Cl_1p").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("Cl_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("ptp").feature("Cl_1p").set("hadd", 14.25);
    model.component("comp1").physics("ptp").feature("Cl_1p").label("\u7269\u8d28: Cl+");
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("specName", "Cl2_1p");
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("specLabel", "Cl2+");
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("sType", "ion");
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("sisDef", true);
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("z", 1);
    model.component("comp1").physics("ptp").feature("Cl2_1p").set("hadd", 11.49);
    model.component("comp1").physics("ptp").feature("Cl2_1p").label("\u7269\u8d28: Cl2+");
    model.component("comp1").physics("ptp").feature("sr1").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr1").set("Species", new String[][]{{"Ar_1p"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("sr1").set("fwdSpecies", "Ar+");
    model.component("comp1").physics("ptp").feature("sr1").set("revSpecies", "Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr1")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr1")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr1")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr1").active(true);
    model.component("comp1").physics("ptp").feature("sr1").label("1: Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr2").set("Species", new String[][]{{"Ars"}, {"Ar"}});
    model.component("comp1").physics("ptp").feature("sr2").set("fwdSpecies", "Ars");
    model.component("comp1").physics("ptp").feature("sr2").set("revSpecies", "Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr2")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr2")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr2")
         .set("stoichArray", new int[][]{{0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr2").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr2").active(true);
    model.component("comp1").physics("ptp").feature("sr2").label("2: Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr3").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr3").set("Species", new String[][]{{"Cl"}, {"Cl2"}});
    model.component("comp1").physics("ptp").feature("sr3").set("fwdSpecies", "Cl");
    model.component("comp1").physics("ptp").feature("sr3").set("revSpecies", "Cl2");
    model.component("comp1").physics("ptp").feature("sr3").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr3")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3")
         .set("revArray", new double[][]{{0}, {0}, {0}, {0}, {0.5}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3")
         .set("stoichArray", new double[][]{{0}, {0}, {0}, {0}, {0.5}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr3").set("formula", "Cl=>0.5Cl2");
    model.component("comp1").physics("ptp").feature("sr3").set("gammaf", 0.01);
    model.component("comp1").physics("ptp").feature("sr3").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr3").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr3").active(true);
    model.component("comp1").physics("ptp").feature("sr3").label("3: Cl=>0.5Cl2");
    model.component("comp1").physics("ptp").feature("sr4").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr4").set("Species", new String[][]{{"Cl_1m"}, {"Cl"}});
    model.component("comp1").physics("ptp").feature("sr4").set("fwdSpecies", "Cl-");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("ptp").feature("sr4").set("revSpecies", "Cl");
    model.component("comp1").physics("ptp").feature("sr4").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr4").set("formula", "Cl-=>Cl");
    model.component("comp1").physics("ptp").feature("sr4").set("gammai", "0.0");
    model.component("comp1").physics("ptp").feature("sr4").set("ebari", "0.0");
    model.component("comp1").physics("ptp").feature("sr4").active(true);
    model.component("comp1").physics("ptp").feature("sr4").label("4: Cl-=>Cl");
    model.component("comp1").physics("ptp").feature("sr5").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr5").set("Species", new String[][]{{"Cl2_1p"}, {"Cl2"}});
    model.component("comp1").physics("ptp").feature("sr5").set("fwdSpecies", "Cl2+");
    model.component("comp1").physics("ptp").feature("sr5").set("revSpecies", "Cl2");
    model.component("comp1").physics("ptp").feature("sr5").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr5")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("ptp").feature("sr5")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("ptp").feature("sr5")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("ptp").feature("sr5").set("formula", "Cl2+=>Cl2");
    model.component("comp1").physics("ptp").feature("sr5").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr5").active(true);
    model.component("comp1").physics("ptp").feature("sr5").label("5: Cl2+=>Cl2");
    model.component("comp1").physics("ptp").feature("sr6").set("rType", "irrev");
    model.component("comp1").physics("ptp").feature("sr6").set("Species", new String[][]{{"Cl_1p"}, {"Cl"}});
    model.component("comp1").physics("ptp").feature("sr6").set("fwdSpecies", "Cl+");
    model.component("comp1").physics("ptp").feature("sr6").set("revSpecies", "Cl");
    model.component("comp1").physics("ptp").feature("sr6").set("rIsValid", true);
    model.component("comp1").physics("ptp").feature("sr6").set("formula", "Cl+=>Cl");
    model.component("comp1").physics("ptp").feature("sr6").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature("sr6").active(true);
    model.component("comp1").physics("ptp").feature("sr6").label("6: Cl+=>Cl");
    model.component("comp1").physics("ptp").feature("pes1")
         .set("muN", new String[][]{{"0.7e24[1/V/m/s]*(ptp.Te/1[V])^-0.6*exp(1.3[V]/ptp.Te)"}, {"0"}, {"0"}, {"0"}, {"0.7e24[1/V/m/s]*(ptp.Te/1[V])^-0.6*exp(1.3[V]/ptp.Te)"}, {"0"}, {"0"}, {"0"}, {"0.7e24[1/V/m/s]*(ptp.Te/1[V])^-0.6*exp(1.3[V]/ptp.Te)"}});
    model.component("comp1").physics("ptp").feature("pes1").set("SpecifyElectronDensityAndEnergy", "SpecifyMueOnly");
    model.component("comp1").physics("ptp").feature("init1").set("neinit", "1E15[1/m^3]");
    model.component("comp1").physics("ptp").feature("init1").set("ebarinit", "2[V]");
    model.component("comp1").physics("ptp").feature("in1").set("specNames", new String[][]{{"Ar"}, {"Cl2"}, {"Cl"}});
    model.component("comp1").physics("ptp").feature("in1").set("xIn", new String[][]{{"xAr"}, {"xCl2"}, {"1e-4"}});
    model.component("comp1").physics("ptp").feature("mct1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ptp").feature("mct1").set("Va", "Vrf");
    model.component("comp1").physics("ptp").feature("mct1").set("CompBias", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "0.015[torr]");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("MassFlowType", "StandardFlowRateSCCM");
    model.component("comp1").physics("spf").feature("inl1").set("sccmmfr", "Qf");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Power");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("PCoil", "Picp");

    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.7);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("ftper", "FrequencyTimePeriodic");
    model.study().create("std2");
    model.study("std2").create("ftper", "FrequencyTimePeriodic");

    model.sol().create("sol1");
    model.sol("sol1").attach("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol().create("sol2");
    model.sol("sol2").attach("std2");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").create("p1", "Parametric");
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").create("i2", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature().remove("fcDef");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("dset2").set("solution", "sol1");
    model.result().dataset("dset2").set("comp", "ptp_xdim");
    model.result().dataset("dset3").set("solution", "sol2");
    model.result().dataset("dset4").set("solution", "sol2");
    model.result().dataset("dset4").set("comp", "ptp_xdim");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().create("pg1", "PlotGroup2D");
    model.result().create("pg2", "PlotGroup2D");
    model.result().create("pg3", "PlotGroup2D");
    model.result().create("pg4", "PlotGroup2D");
    model.result().create("pg5", "PlotGroup1D");
    model.result().create("pg6", "PlotGroup2D");
    model.result().create("pg7", "PlotGroup2D");
    model.result().create("pg8", "PlotGroup3D");
    model.result().create("pg9", "PlotGroup2D");
    model.result().create("pg10", "PlotGroup2D");
    model.result().create("pg11", "PlotGroup3D");
    model.result().create("pg12", "PlotGroup2D");
    model.result().create("pg13", "PlotGroup2D");
    model.result().create("pg14", "PlotGroup2D");
    model.result().create("pg15", "PlotGroup2D");
    model.result().create("pg16", "PlotGroup2D");
    model.result().create("pg17", "PlotGroup1D");
    model.result().create("pg18", "PlotGroup2D");
    model.result().create("pg19", "PlotGroup2D");
    model.result().create("pg20", "PlotGroup3D");
    model.result().create("pg21", "PlotGroup2D");
    model.result().create("pg22", "PlotGroup2D");
    model.result().create("pg23", "PlotGroup3D");
    model.result().create("pg24", "PlotGroup2D");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ptp.Teav");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ptp.Vav");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ptp.Pcap_av");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "ptp.mct1.V");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("expr", "ptp.mct1.I");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "spf.U");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "T");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").create("con1", "Contour");
    model.result("pg10").feature("surf1").set("expr", "mf.normB");
    model.result("pg10").feature("str1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43);
    model.result("pg10").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg10").feature("str1").create("col1", "Color");
    model.result("pg10").feature("str1").create("filt1", "Filter");
    model.result("pg10").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg10").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg10").feature("con1").set("expr", "mf.Psi");
    model.result("pg10").feature("con1").create("filt1", "Filter");
    model.result("pg10").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg11").create("vol1", "Volume");
    model.result("pg11").create("con1", "Contour");
    model.result("pg11").feature("vol1").set("expr", "mf.normB");
    model.result("pg11").feature("con1").set("expr", "mf.Psi");
    model.result("pg11").feature("con1").create("filt1", "Filter");
    model.result("pg11").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg12").feature("surf1").create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg13").set("data", "dset3");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg14").set("data", "dset3");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "ptp.Teav");
    model.result("pg15").set("data", "dset3");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "ptp.Vav");
    model.result("pg16").set("data", "dset3");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "ptp.Pcap_av");
    model.result("pg17").set("data", "dset4");
    model.result("pg17").create("lngr1", "LineGraph");
    model.result("pg17").create("lngr2", "LineGraph");
    model.result("pg17").feature("lngr1").set("xdata", "expr");
    model.result("pg17").feature("lngr1").selection().all();
    model.result("pg17").feature("lngr1").set("expr", "ptp.mct1.V");
    model.result("pg17").feature("lngr2").set("xdata", "expr");
    model.result("pg17").feature("lngr2").selection().all();
    model.result("pg17").feature("lngr2").set("expr", "ptp.mct1.I");
    model.result("pg18").set("data", "dset3");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "spf.U");
    model.result("pg19").set("data", "dset3");
    model.result("pg19").create("con1", "Contour");
    model.result("pg19").feature("con1").set("expr", "p");
    model.result("pg20").set("data", "rev2");
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf1").set("expr", "spf.U");
    model.result("pg21").set("data", "dset3");
    model.result("pg21").create("surf1", "Surface");
    model.result("pg21").feature("surf1").set("expr", "T");
    model.result("pg22").set("data", "dset3");
    model.result("pg22").create("surf1", "Surface");
    model.result("pg22").create("str1", "Streamline");
    model.result("pg22").create("con1", "Contour");
    model.result("pg22").feature("surf1").set("expr", "mf.normB");
    model.result("pg22").feature("str1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43);
    model.result("pg22").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg22").feature("str1").create("col1", "Color");
    model.result("pg22").feature("str1").create("filt1", "Filter");
    model.result("pg22").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg22").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg22").feature("con1").set("expr", "mf.Psi");
    model.result("pg22").feature("con1").create("filt1", "Filter");
    model.result("pg22").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg23").set("data", "rev2");
    model.result("pg23").create("vol1", "Volume");
    model.result("pg23").create("con1", "Contour");
    model.result("pg23").feature("vol1").set("expr", "mf.normB");
    model.result("pg23").feature("con1").set("expr", "mf.Psi");
    model.result("pg23").feature("con1").create("filt1", "Filter");
    model.result("pg23").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg24").set("data", "dset3");
    model.result("pg24").create("surf1", "Surface");
    model.result("pg24").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg24").feature("surf1").create("sel1", "Selection");
    model.result("pg24").feature("surf1").feature("sel1").selection().set(2);

    model.nodeGroup().create("grp1", "Physics", "ptp");
    model.nodeGroup("grp1").placeAfter("rg3");
    model.nodeGroup().create("grp2", "Physics", "ptp");
    model.nodeGroup("grp2").placeAfter("rg3");
    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").placeAfter(null);
    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").placeAfter(null);

    model.study("std1").label("ICP");
    model.study("std1").feature("ftper").set("freq", "13.56[MHz]");
    model.study("std2").label("ICP/CCP");
    model.study("std2").feature("ftper").set("freq", "13.56[MHz]");
    model.study("std2").feature("ftper").set("useinitsol", true);
    model.study("std2").feature("ftper").set("initmethod", "sol");
    model.study("std2").feature("ftper").set("initstudy", "std1");
    model.study("std2").feature("ftper").set("useparam", true);
    model.study("std2").feature("ftper").set("pname", new String[]{"Vrf"});
    model.study("std2").feature("ftper").set("plistarr", new String[]{"range(0,5,100)"});
    model.study("std2").feature("ftper").set("punit", new String[]{"V"});
    model.study("std2").feature("ftper").set("pcontinuationmode", "no");
    model.study("std2").feature("ftper").set("preusesol", "yes");

    model.sol("sol1").feature("st1").label("\u7f16\u8bd1\u65b9\u7a0b: \u9891\u7387-\u65f6\u95f4\u5468\u671f");
    model.sol("sol1").feature("st1").set("splitcomplex", true);
    model.sol("sol1").feature("v1").label("\u56e0\u53d8\u91cf 1.1");
    model.sol("sol1").feature("v1").feature("comp1_En_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_En_per").set("scaleval", 35);
    model.sol("sol1").feature("v1").feature("comp1_Ne_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_Ne_per").set("scaleval", 35);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Ar_1p_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Ar_1p_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Ars_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Ars_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl2_1p_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl2_1p_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl2_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl2_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_1m_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_1m_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_1p_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_1p_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_W_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_Cl_W_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_ptp_dct1_sigs_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_dct1_sigs_per").set("scaleval", "1E-7");
    model.sol("sol1").feature("v1").feature("comp1_ptp_mct1_Jdep_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_mct1_Jdep_per").set("scaleval", 10);
    model.sol("sol1").feature("v1").feature("comp1_V_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_V_per").set("scaleval", 500);
    model.sol("sol1").feature("v1").feature("comp1_ptp_mct1_Vdcb_per").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ptp_mct1_Vdcb_per").set("scaleval", 100);
    model.sol("sol1").feature("s1").label("\u7a33\u6001\u6c42\u89e3\u5668 1.1");
    model.sol("sol1").feature("s1").feature("dDef").label("\u76f4\u63a5 2");
    model.sol("sol1").feature("s1").feature("aDef").label("\u9ad8\u7ea7 1");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").feature("aDef").set("matherr", false);
    model.sol("sol1").feature("s1").feature("fc1").label("\u5168\u8026\u5408 1.1");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 1.0E-4);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("rstep", 5);
    model.sol("sol1").feature("s1").feature("fc1").set("rstepabs", 0.05);
    model.sol("sol1").feature("s1").feature("fc1").set("minsteprecovery", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("updweightsauto", "wthresh");
    model.sol("sol1").feature("s1").feature("fc1").set("updweightsdampval", 0.1);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 200);
    model.sol("sol1").feature("s1").feature("fc1").set("termonres", false);
    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol1").feature("s1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht) (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("errorchk", false);
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i1").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("soDef").label("SOR 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").label("SCGS 1.1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("soDef").label("SOR 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").label("SCGS 1.1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", true);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i2").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("soDef").label("SOR 2");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").label("SOR 1.1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("soDef").label("SOR 2");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").label("SOR 1.1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);

    model.study("std1").runNoGen();

    model.sol("sol2").feature("st1").label("\u7f16\u8bd1\u65b9\u7a0b: \u9891\u7387-\u65f6\u95f4\u5468\u671f");
    model.sol("sol2").feature("st1").set("splitcomplex", true);
    model.sol("sol2").feature("v1").label("\u56e0\u53d8\u91cf 1.1");
    model.sol("sol2").feature("v1").set("initmethod", "sol");
    model.sol("sol2").feature("v1").set("initsol", "sol1");
    model.sol("sol2").feature("v1").set("solnum", "auto");
    model.sol("sol2").feature("v1").set("clistctrl", new String[]{"p1"});
    model.sol("sol2").feature("v1").set("cname", new String[]{"Vrf"});
    model.sol("sol2").feature("v1").set("clist", new String[]{"range(0,5,100)[V]"});
    model.sol("sol2").feature("v1").feature("comp1_En_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_En_per").set("scaleval", 35);
    model.sol("sol2").feature("v1").feature("comp1_Ne_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_Ne_per").set("scaleval", 35);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Ar_1p_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Ar_1p_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Ars_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Ars_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl2_1p_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl2_1p_W_per").set("scaleval", 10);

    return model;
  }

  public static Model run4(Model model) {
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl2_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl2_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_1m_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_1m_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_1p_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_1p_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_W_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_Cl_W_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_ptp_dct1_sigs_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_dct1_sigs_per").set("scaleval", "1E-7");
    model.sol("sol2").feature("v1").feature("comp1_ptp_mct1_Jdep_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_mct1_Jdep_per").set("scaleval", 10);
    model.sol("sol2").feature("v1").feature("comp1_V_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_V_per").set("scaleval", 500);
    model.sol("sol2").feature("v1").feature("comp1_ptp_mct1_Vdcb_per").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_ptp_mct1_Vdcb_per").set("scaleval", 100);
    model.sol("sol2").feature("s1").label("\u7a33\u6001\u6c42\u89e3\u5668 1.1");
    model.sol("sol2").feature("s1").set("probesel", "none");
    model.sol("sol2").feature("s1").feature("dDef").label("\u76f4\u63a5 2");
    model.sol("sol2").feature("s1").feature("aDef").label("\u9ad8\u7ea7 1");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").feature("aDef").set("matherr", false);
    model.sol("sol2").feature("s1").feature("p1").label("\u53c2\u6570\u5316 1.1");
    model.sol("sol2").feature("s1").feature("p1").set("pname", new String[]{"Vrf"});
    model.sol("sol2").feature("s1").feature("p1").set("plistarr", new String[]{"range(0,5,100)"});
    model.sol("sol2").feature("s1").feature("p1").set("punit", new String[]{"V"});
    model.sol("sol2").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol2").feature("s1").feature("p1").set("preusesol", "yes");
    model.sol("sol2").feature("s1").feature("p1").set("excludelsqvalues", false);
    model.sol("sol2").feature("s1").feature("fc1").label("\u5168\u8026\u5408 1.1");
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol2").feature("s1").feature("fc1").set("rstep", 5);
    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 200);
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", false);
    model.sol("sol2").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol2").feature("s1").feature("fc1").set("plotgroup", "pg13");
    model.sol("sol2").feature("s1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht) (\u5df2\u5408\u5e76)");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol2").feature("s1").feature("i1").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("soDef").label("SOR 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").label("SCGS 1.1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("soDef").label("SOR 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").label("SCGS 1.1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol2").feature("s1").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol2").feature("s1").feature("i2").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol2").feature("s1").feature("i2").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("pr").feature("soDef").label("SOR 2");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").label("SOR 1.1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("po").feature("soDef").label("SOR 2");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").label("SOR 1.1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);

    model.study("std2").runNoGen();

    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg3").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg4").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg4").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg5").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp)");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabel", "Period fraction");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5")
         .set("plotonsecyaxis", new String[][]{{"\u7ebf\u7ed3\u679c\u56fe 1", "on", "lngr1"}, {"\u7ebf\u7ed3\u679c\u56fe 2", "off", "lngr2"}});
    model.result("pg5").feature("lngr1").set("xdataexpr", "x1_ptp");
    model.result("pg5").feature("lngr1").set("xdataunit", "m");
    model.result("pg5").feature("lngr1").set("xdatadescr", "x1_ptp \u5750\u6807");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"V"});
    model.result("pg5").feature("lngr1").set("resolution", "normal");
    model.result("pg5").feature("lngr2").set("xdataexpr", "x1_ptp");
    model.result("pg5").feature("lngr2").set("xdataunit", "m");
    model.result("pg5").feature("lngr2").set("xdatadescr", "x1_ptp \u5750\u6807");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"I"});
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg6").label("\u901f\u5ea6 (spf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg7").label("\u538b\u529b (spf)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("colortable", "Rainbow");
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("resolution", "normal");
    model.result("pg8").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("resolution", "normal");
    model.result("pg9").label("\u6e29\u5ea6 (ht)");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("surf1").set("resolution", "normal");
    model.result("pg10").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg10").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg10").feature("surf1").set("resolution", "normal");
    model.result("pg10").feature("str1").set("titletype", "none");
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("udist", 0.03);
    model.result("pg10").feature("str1").set("maxlen", 0.4);
    model.result("pg10").feature("str1").set("maxsteps", 5000);
    model.result("pg10").feature("str1").set("inheritplot", "surf1");
    model.result("pg10").feature("str1").set("inheritcolor", false);
    model.result("pg10").feature("str1").set("resolution", "normal");
    model.result("pg10").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg10").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg10").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg10").feature("con1").set("titletype", "none");
    model.result("pg10").feature("con1").set("number", 10);
    model.result("pg10").feature("con1").set("levelrounding", false);
    model.result("pg10").feature("con1").set("coloring", "uniform");
    model.result("pg10").feature("con1").set("colorlegend", false);
    model.result("pg10").feature("con1").set("color", "custom");
    model.result("pg10").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg10").feature("con1").set("resolution", "fine");
    model.result("pg10").feature("con1").set("inheritplot", "surf1");
    model.result("pg10").feature("con1").set("inheritcolor", false);
    model.result("pg10").feature("con1").set("resolution", "fine");
    model.result("pg11").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg11").feature("vol1").set("resolution", "normal");
    model.result("pg11").feature("con1").set("titletype", "none");
    model.result("pg11").feature("con1").set("number", 10);
    model.result("pg11").feature("con1").set("levelrounding", false);
    model.result("pg11").feature("con1").set("coloring", "uniform");
    model.result("pg11").feature("con1").set("colorlegend", false);
    model.result("pg11").feature("con1").set("color", "custom");
    model.result("pg11").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg11").feature("con1").set("resolution", "fine");
    model.result("pg11").feature("con1").set("inheritplot", "vol1");
    model.result("pg11").feature("con1").set("inheritcolor", false);
    model.result("pg11").feature("con1").set("resolution", "fine");
    model.result("pg12").label("\u7535\u5b50\u5438\u6536\u7684\u611f\u5e94\u529f\u7387");
    model.result("pg12").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg12").feature("surf1").set("resolution", "normal");
    model.result("pg13").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp) 1");
    model.result("pg13").feature("surf1").set("resolution", "normal");
    model.result("pg14").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp) 1");
    model.result("pg14").feature("surf1").set("resolution", "normal");
    model.result("pg15").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp) 1");
    model.result("pg15").feature("surf1").set("colortable", "Dipole");
    model.result("pg15").feature("surf1").set("resolution", "normal");
    model.result("pg16").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp) 1");
    model.result("pg16").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg16").feature("surf1").set("resolution", "norefine");
    model.result("pg17").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp) 1");
    model.result("pg17").set("looplevelinput", new String[]{"last"});
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("xlabel", "Period fraction");
    model.result("pg17").set("xlabelactive", true);
    model.result("pg17").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg17").set("yseclabel", "\u7535\u52bf (V)");
    model.result("pg17").set("twoyaxes", true);
    model.result("pg17")
         .set("plotonsecyaxis", new String[][]{{"\u7ebf\u7ed3\u679c\u56fe 1", "on", "lngr1"}, {"\u7ebf\u7ed3\u679c\u56fe 2", "off", "lngr2"}});
    model.result("pg17").set("legendpos", "lowerright");
    model.result("pg17").set("ylabelactive", false);
    model.result("pg17").set("yseclabelactive", false);
    model.result("pg17").feature("lngr1").set("xdataexpr", "x1_ptp");
    model.result("pg17").feature("lngr1").set("xdataunit", "m");
    model.result("pg17").feature("lngr1").set("xdatadescr", "x1_ptp \u5750\u6807");
    model.result("pg17").feature("lngr1").set("legend", true);
    model.result("pg17").feature("lngr1").set("legendmethod", "manual");
    model.result("pg17").feature("lngr1").set("legends", new String[]{"V"});
    model.result("pg17").feature("lngr1").set("resolution", "normal");
    model.result("pg17").feature("lngr2").set("xdataexpr", "x1_ptp");
    model.result("pg17").feature("lngr2").set("xdataunit", "m");
    model.result("pg17").feature("lngr2").set("xdatadescr", "x1_ptp \u5750\u6807");
    model.result("pg17").feature("lngr2").set("legend", true);
    model.result("pg17").feature("lngr2").set("legendmethod", "manual");
    model.result("pg17").feature("lngr2").set("legends", new String[]{"I"});
    model.result("pg17").feature("lngr2").set("resolution", "norefine");
    model.result("pg18").label("\u901f\u5ea6 (spf) 1");
    model.result("pg18").set("frametype", "spatial");
    model.result("pg18").feature("surf1").label("\u8868\u9762");
    model.result("pg18").feature("surf1").set("colortable", "Rainbow");
    model.result("pg18").feature("surf1").set("smooth", "internal");
    model.result("pg18").feature("surf1").set("resolution", "normal");
    model.result("pg19").label("\u538b\u529b (spf) 1");
    model.result("pg19").set("frametype", "spatial");
    model.result("pg19").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg19").feature("con1").set("number", 40);
    model.result("pg19").feature("con1").set("levelrounding", false);
    model.result("pg19").feature("con1").set("colortable", "Rainbow");
    model.result("pg19").feature("con1").set("smooth", "internal");
    model.result("pg19").feature("con1").set("resolution", "normal");
    model.result("pg20").label("\u4e09\u7ef4\u901f\u5ea6 (spf) 1");
    model.result("pg20").set("frametype", "spatial");
    model.result("pg20").feature("surf1").label("\u8868\u9762");
    model.result("pg20").feature("surf1").set("colortable", "Rainbow");
    model.result("pg20").feature("surf1").set("smooth", "internal");
    model.result("pg20").feature("surf1").set("resolution", "normal");
    model.result("pg21").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg21").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg21").feature("surf1").set("resolution", "normal");
    model.result("pg22").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg22").set("frametype", "spatial");
    model.result("pg22").set("showlegendsmaxmin", true);
    model.result("pg22").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg22").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg22").feature("surf1").set("resolution", "normal");
    model.result("pg22").feature("str1").set("titletype", "none");
    model.result("pg22").feature("str1").set("posmethod", "uniform");
    model.result("pg22").feature("str1").set("udist", 0.03);
    model.result("pg22").feature("str1").set("maxlen", 0.4);
    model.result("pg22").feature("str1").set("maxsteps", 5000);
    model.result("pg22").feature("str1").set("inheritplot", "surf1");
    model.result("pg22").feature("str1").set("inheritcolor", false);
    model.result("pg22").feature("str1").set("resolution", "normal");
    model.result("pg22").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg22").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg22").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg22").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg22").feature("con1").set("titletype", "none");
    model.result("pg22").feature("con1").set("number", 10);
    model.result("pg22").feature("con1").set("levelrounding", false);
    model.result("pg22").feature("con1").set("coloring", "uniform");
    model.result("pg22").feature("con1").set("colorlegend", false);
    model.result("pg22").feature("con1").set("color", "custom");
    model.result("pg22").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg22").feature("con1").set("resolution", "fine");
    model.result("pg22").feature("con1").set("inheritplot", "surf1");
    model.result("pg22").feature("con1").set("inheritcolor", false);
    model.result("pg22").feature("con1").set("resolution", "fine");
    model.result("pg23").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf) 1");
    model.result("pg23").set("frametype", "spatial");
    model.result("pg23").set("showlegendsmaxmin", true);
    model.result("pg23").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg23").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg23").feature("vol1").set("resolution", "normal");
    model.result("pg23").feature("con1").set("titletype", "none");
    model.result("pg23").feature("con1").set("number", 10);
    model.result("pg23").feature("con1").set("levelrounding", false);
    model.result("pg23").feature("con1").set("coloring", "uniform");
    model.result("pg23").feature("con1").set("colorlegend", false);
    model.result("pg23").feature("con1").set("color", "custom");
    model.result("pg23").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg23").feature("con1").set("resolution", "fine");
    model.result("pg23").feature("con1").set("inheritplot", "vol1");
    model.result("pg23").feature("con1").set("inheritcolor", false);
    model.result("pg23").feature("con1").set("resolution", "fine");
    model.result("pg24").label("\u7535\u5b50\u5438\u6536\u7684\u611f\u5e94\u529f\u7387 ICP/CCP");
    model.result("pg24").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg24").feature("surf1").set("resolution", "normal");

    model.nodeGroup("grp1").label("\u7269\u8d28");
    model.nodeGroup("grp1").add("e");
    model.nodeGroup("grp1").add("Ar");
    model.nodeGroup("grp1").add("Ars");
    model.nodeGroup("grp1").add("Ar_1p");
    model.nodeGroup("grp1").add("Cl2");
    model.nodeGroup("grp1").add("Cl");
    model.nodeGroup("grp1").add("Cl_1m");
    model.nodeGroup("grp1").add("Cl_1p");
    model.nodeGroup("grp1").add("Cl2_1p");
    model.nodeGroup("grp2").label("\u8868\u9762\u53cd\u5e94");
    model.nodeGroup("grp2").add("sr1");
    model.nodeGroup("grp2").add("sr2");
    model.nodeGroup("grp2").add("sr3");
    model.nodeGroup("grp2").add("sr4");
    model.nodeGroup("grp2").add("sr5");
    model.nodeGroup("grp2").add("sr6");
    model.nodeGroup("grp3").label("ICP");
    model.nodeGroup("grp3").add("plotgroup", "pg1");
    model.nodeGroup("grp3").add("plotgroup", "pg2");
    model.nodeGroup("grp3").add("plotgroup", "pg3");
    model.nodeGroup("grp3").add("plotgroup", "pg4");
    model.nodeGroup("grp3").add("plotgroup", "pg5");
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");
    model.nodeGroup("grp3").add("plotgroup", "pg10");
    model.nodeGroup("grp3").add("plotgroup", "pg11");
    model.nodeGroup("grp3").add("plotgroup", "pg12");
    model.nodeGroup("grp4").label("ICP/CCP");
    model.nodeGroup("grp4").add("plotgroup", "pg13");
    model.nodeGroup("grp4").add("plotgroup", "pg14");
    model.nodeGroup("grp4").add("plotgroup", "pg15");
    model.nodeGroup("grp4").add("plotgroup", "pg16");
    model.nodeGroup("grp4").add("plotgroup", "pg17");
    model.nodeGroup("grp4").add("plotgroup", "pg18");
    model.nodeGroup("grp4").add("plotgroup", "pg19");
    model.nodeGroup("grp4").add("plotgroup", "pg20");
    model.nodeGroup("grp4").add("plotgroup", "pg21");
    model.nodeGroup("grp4").add("plotgroup", "pg22");
    model.nodeGroup("grp4").add("plotgroup", "pg23");
    model.nodeGroup("grp4").add("plotgroup", "pg24");

    model
         .title("\u5e26\u5c04\u9891\u504f\u538b\u7684\u6c29/\u6c2f\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u6a21\u578b");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u6c29/\u6c2f\u6df7\u5408\u7269\u4e2d\u5e26\u6709\u5c04\u9891\u504f\u538b\u7684\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u95ee\u9898\uff0c\u5176\u4e2d\u8ba1\u7b97\u4e86\u6d41\u4f53\u6d41\u52a8\u548c\u6c14\u4f53\u52a0\u70ed\uff0c\u5e76\u8ba8\u8bba\u5bf9\u7535\u8d1f\u6027\u653e\u7535\u8fdb\u884c\u5efa\u6a21\u7684\u91cd\u8981\u65b9\u9762\u548c\u7b56\u7565\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("icp_ccp_argon_chlorine.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
