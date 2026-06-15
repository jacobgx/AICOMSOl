/*
 * icp_argon_oxygen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:06 by COMSOL 6.3.0.290. */
public class icp_argon_oxygen {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Inductively_Coupled_Plasmas");

    model.param().set("Pw", "100[W]");
    model.param().set("Qf", "250");
    model.param().set("xO2", "0.9");
    model.param().set("xO", "1e-4");
    model.param().set("p0", "0.02[torr]");

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
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{10, 6});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{20, 14});
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{20, 17});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{20, 18});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(5, 6, 7, 9);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(5, 6, 7, 9);
    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection("sel2").label("\u7ebf\u5708");
    model.component("comp1").selection("sel3").label("\u7ebf\u5708\u8fb9\u754c");

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
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").selection().named("sel2");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").selection().set(2);

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");
    model.component("comp1").physics("plas").selection().set(2);
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").create("xsec2", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").create("xsec3", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir2", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir3", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir4", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir5", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir6", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir7", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir8", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir9", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir10", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir11", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir12", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir13", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir14", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir15", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir16", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir17", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir18", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir19", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir20", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir21", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir22", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir23", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir24", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir25", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("eir26", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").create("rxn3", "Reaction", 2);
    model.component("comp1").physics("plas").create("rxn4", "Reaction", 2);
    model.component("comp1").physics("plas").create("rxn5", "Reaction", 2);
    model.component("comp1").physics("plas").create("rxn6", "Reaction", 2);
    model.component("comp1").physics("plas").create("e", "Species", 2);
    model.component("comp1").physics("plas").create("O2", "Species", 2);
    model.component("comp1").physics("plas").create("O", "Species", 2);
    model.component("comp1").physics("plas").create("O_1m", "Species", 2);
    model.component("comp1").physics("plas").create("O2a1Dg", "Species", 2);
    model.component("comp1").physics("plas").create("O1D", "Species", 2);
    model.component("comp1").physics("plas").create("O2_1p", "Species", 2);
    model.component("comp1").physics("plas").create("O_1p", "Species", 2);
    model.component("comp1").physics("plas").create("Ar", "Species", 2);
    model.component("comp1").physics("plas").create("Ars", "Species", 2);
    model.component("comp1").physics("plas").create("Ar_1p", "Species", 2);
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").selection().set(3, 4, 6, 27, 33, 34, 35, 36, 38, 40);
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr3").selection().set(3, 4, 6, 27, 33, 34, 35, 36, 38, 40);
    model.component("comp1").physics("plas").create("sr4", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr4").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr5", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr5").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr6", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr6").selection().set(3, 4, 6, 27, 33, 34, 35, 36, 38, 40);
    model.component("comp1").physics("plas").create("sr7", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr7").selection().set(3, 4, 6, 27, 33, 34, 35, 36, 38, 40);
    model.component("comp1").physics("plas").create("sr8", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr8").selection().named("sel1");
    model.component("comp1").physics("plas").create("in1", "Inflow", 1);
    model.component("comp1").physics("plas").feature("in1").selection().set(35);
    model.component("comp1").physics("plas").create("out1", "Outflow", 1);
    model.component("comp1").physics("plas").feature("out1").selection().set(39);
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(4, 6, 27, 33, 34, 35, 36, 38, 39, 40);
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").selection().set(2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(35);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(39);
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").selection().set(2);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").selection().set(2, 3, 4, 5, 6, 7, 9);
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid", 2);
    model.component("comp1").physics("mf").feature("alf1").selection().all();
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel2");

    model.component("comp1").multiphysics().create("nipf1", "NonIsothermalPlasmaFlowMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(4, 6, 27, 33, 34, 35, 36, 38, 39, 40);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(4, 6, 27, 33, 34, 36, 38, 39, 40);

    model.component("comp1").view("view1").axis().set("xmin", -3.9675216674804688);
    model.component("comp1").view("view1").axis().set("xmax", 33.96752166748047);
    model.component("comp1").view("view1").axis().set("ymin", -0.7522649765014648);
    model.component("comp1").view("view1").axis().set("ymax", 30.75226593017578);

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
    model.component("comp1").material("mat2").label("Glass (quartz)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.99);
    model.component("comp1").material("mat2").set("roughness", 0.02);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});
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

    model.component("comp1").cpl("aveop1").set("axisym", true);

    model.component("comp1").physics("plas").prop("InconsistentStabilization").set("IsotropicDiffusionIons", true);
    model.component("comp1").physics("plas").prop("InconsistentStabilization").set("delidion", 0.1);
    model.component("comp1").physics("plas").prop("TransportSettings").set("calcThermo", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("MixtureDiffusionCorrection", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("Convection", true);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "O2_phelps_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec2").set("Filepath", "O_morgan_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec3").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("eir1")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O"}, {"O_1m"}});
    model.component("comp1").physics("plas").feature("eir1").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir1").set("revSpecies", new String[][]{{"O"}, {"O-"}});
    model.component("comp1").physics("plas").feature("eir1").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir1")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir1")
         .set("revArray", new int[][]{{0}, {0}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir1")
         .set("stoichArray", new int[][]{{-1}, {-1}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir1").set("formula", "e+O2=>O+O-");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir1").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir1")
         .set("xdata", new String[][]{{"4.4"}, 
         {"4.9"}, 
         {"5.38"}, 
         {"5.86"}, 
         {"6.1"}, 
         {"6.48"}, 
         {"6.77"}, 
         {"7.05"}, 
         {"7.3"}, 
         {"7.53"}, 
         {"7.77"}, 
         {"8.0"}, 
         {"8.25"}, 
         {"8.73"}, 
         {"9.2"}, 
         {"9.68"}, 
         {"10.15"}, 
         {"11.35"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir1")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"2.3E-23"}, 
         {"7.2E-23"}, 
         {"1.08E-22"}, 
         {"1.38E-22"}, 
         {"1.52E-22"}, 
         {"1.56E-22"}, 
         {"1.48E-22"}, 
         {"1.31E-22"}, 
         {"1.1E-22"}, 
         {"8.4E-23"}, 
         {"5.4E-23"}, 
         {"2.8E-23"}, 
         {"1.4E-23"}, 
         {"8.0E-24"}, 
         {"8.0E-24"}, 
         {"8.0E-24"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir1").active(true);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("plas").feature("eir1").label("1: e+O2=>O+O-");
    model.component("comp1").physics("plas").feature("eir2").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir2").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir2").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir2").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir2")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir2")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir2")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir2").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir2").set("mratio", "1.71e-5");
    model.component("comp1").physics("plas").feature("eir2").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir2")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.001"}, 
         {"0.002"}, 
         {"0.003"}, 
         {"0.005"}, 
         {"0.007"}, 
         {"0.0085"}, 
         {"0.01"}, 
         {"0.015"}, 
         {"0.02"}, 
         {"0.03"}, 
         {"0.04"}, 
         {"0.05"}, 
         {"0.07"}, 
         {"0.1"}, 
         {"0.12"}, 
         {"0.15"}, 
         {"0.17"}, 
         {"0.2"}, 
         {"0.25"}, 
         {"0.3"}, 
         {"0.35"}, 
         {"0.4"}, 
         {"0.5"}, 
         {"0.7"}, 
         {"1.0"}, 
         {"1.2"}, 
         {"1.3"}, 
         {"1.5"}, 
         {"1.7"}, 
         {"1.9"}, 
         {"2.1"}, 
         {"2.2"}, 
         {"2.5"}, 
         {"2.8"}, 
         {"3.0"}, 
         {"3.3"}, 
         {"3.6"}, 
         {"4.0"}, 
         {"4.5"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"75.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir2")
         .set("ydata", new double[][]{{3.5E-21}, {3.5E-21}, {3.6E-21}, {4.0E-21}, {5.0E-21}, {5.8E-21}, {6.4E-21}, {7.0E-21}, {8.7E-21}, {9.9E-21}, {1.24E-20}, {1.44E-20}, {1.6E-20}, {2.1E-20}, {2.5E-20}, {2.8E-20}, {3.1E-20}, {3.3E-20}, {3.6E-20}, {4.1E-20}, {4.5E-20}, {4.7E-20}, {5.2E-20}, {5.7E-20}, {6.1E-20}, {7.2E-20}, {7.9E-20}, {7.9E-20}, {7.6E-20}, {7.3E-20}, {6.9E-20}, {6.6E-20}, {6.5E-20}, {6.1E-20}, {5.8E-20}, {5.7E-20}, {5.5E-20}, {5.45E-20}, {5.5E-20}, {5.55E-20}, {5.6E-20}, {6.0E-20}, {6.6E-20}, {7.1E-20}, {8.0E-20}, {8.5E-20}, {8.8E-20}, {8.7E-20}, {8.6E-20}, {8.2E-20}, {8.0E-20}, {7.7E-20}, {6.8E-20}, {6.5E-20}, {6.7E-20}, {6.0E-20}, {4.9E-20}, {3.6E-20}, {2.9E-20}, {2.12E-20}, {1.48E-20}, {1.14E-20}, {7.9E-21}, {5.1E-21}, {3.8E-21}, {2.8E-21}});
    model.component("comp1").physics("plas").feature("eir2").active(true);
    model.component("comp1").physics("plas").feature("eir2").label("2: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir3").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir3").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir3").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir3").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir3")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir3")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir3")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir3").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir3").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir3").set("de", "2.000000e-2");
    model.component("comp1").physics("plas").feature("eir3").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir3")
         .set("xdata", new double[][]{{0.07}, {0.08}, {0.1}, {0.2}, {0.21}, {0.22}, {0.32}, {0.33}, {0.35}, {0.44}, {0.45}, {0.47}, {0.56}, {0.57}, {0.59}, {0.68}, {0.69}, {0.71}, {0.79}, {0.8}, {0.81}, {0.9}, {0.91}, {0.93}, {1.02}, {1.03}, {1.05}, {1.13}, {1.14}, {1.16}, {1.22}, {1.23}, {1.26}, {1.34}, {1.35}, {1.37}, {1.44}, {1.45}, {1.47}, {1.54}, {1.55}, {1.57}, {1.64}, {1.65}, {1.67}});
    model.component("comp1").physics("plas").feature("eir3")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.4E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.16E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.84E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.72E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.04E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.36E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"7.2E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.68E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.4E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.2E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.8E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir3").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir3").active(true);
    model.component("comp1").physics("plas").feature("eir3").label("3: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir4").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir4").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir4").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir4").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir4")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir4")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir4")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir4").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir4").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir4").set("de", "1.900000e-1");
    model.component("comp1").physics("plas").feature("eir4").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir4")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.19"}, 
         {"0.2"}, 
         {"0.21"}, 
         {"0.23"}, 
         {"0.32"}, 
         {"0.33"}, 
         {"0.35"}, 
         {"0.44"}, 
         {"0.45"}, 
         {"0.47"}, 
         {"0.56"}, 
         {"0.57"}, 
         {"0.59"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}});
    model.component("comp1").physics("plas").feature("eir4")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"1.0E-23"}, 
         {"1.0E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.35E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.85E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.0E-20"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.85E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.125E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.75E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.9E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"6.0E-24"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir4").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir4").active(true);
    model.component("comp1").physics("plas").feature("eir4").label("4: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir5").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir5").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir5").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir5").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir5")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir5")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir5")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir5").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir5").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir5").set("de", "1.900000e-1");
    model.component("comp1").physics("plas").feature("eir5").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir5")
         .set("xdata", new String[][]{{"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}});
    model.component("comp1").physics("plas").feature("eir5")
         .set("ydata", new String[][]{{"0.0"}, 
         {"4.2E-22"}, 
         {"1.0E-21"}, 
         {"1.76E-21"}, 
         {"2.31E-21"}, 
         {"2.47E-21"}, 
         {"2.34E-21"}, 
         {"1.86E-21"}, 
         {"1.43E-21"}, 
         {"1.02E-21"}, 
         {"7.1E-22"}, 
         {"4.0E-22"}, 
         {"1.0E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir5").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir5").active(true);
    model.component("comp1").physics("plas").feature("eir5").label("5: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir6").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir6").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir6").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir6").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir6")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir6")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir6")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir6").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir6").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir6").set("de", "3.800000e-1");
    model.component("comp1").physics("plas").feature("eir6").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir6")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.38"}, 
         {"0.44"}, 
         {"0.45"}, 
         {"0.47"}, 
         {"0.56"}, 
         {"0.57"}, 
         {"0.59"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir6")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.4E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.35E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.65E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.0E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"4.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.85E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.4E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir6").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir6").active(true);
    model.component("comp1").physics("plas").feature("eir6").label("6: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir7").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir7").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir7").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir7").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir7")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir7")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir7")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir7").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir7").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir7").set("de", "3.800000e-1");
    model.component("comp1").physics("plas").feature("eir7").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir7")
         .set("xdata", new String[][]{{"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}});
    model.component("comp1").physics("plas").feature("eir7")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.8E-22"}, 
         {"4.0E-22"}, 
         {"7.3E-22"}, 
         {"9.4E-22"}, 
         {"1.1E-21"}, 
         {"1.09E-21"}, 
         {"9.3E-22"}, 
         {"7.3E-22"}, 
         {"5.1E-22"}, 
         {"2.8E-22"}, 
         {"1.3E-22"}, 
         {"5.0E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir7").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir7").active(true);
    model.component("comp1").physics("plas").feature("eir7").label("7: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir8").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir8").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir8").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir8").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir8")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir8")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir8")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir8").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir8").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir8").set("de", "5.700000e-1");
    model.component("comp1").physics("plas").feature("eir8").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir8")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.57"}, 
         {"0.68"}, 
         {"0.69"}, 
         {"0.71"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"20.0"}, 
         {"45.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir8")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.7E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.2E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.15E-21"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"9.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.0E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"8.0E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.25E-22"}, 
         {"3.63E-22"}, 
         {"5.88E-22"}, 
         {"7.5E-22"}, 
         {"6.75E-22"}, 
         {"5.63E-22"}, 
         {"4.75E-22"}, 
         {"3.0E-22"}, 
         {"1.75E-22"}, 
         {"8.8E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir8").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir8").active(true);
    model.component("comp1").physics("plas").feature("eir8").label("8: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir9").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir9").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir9").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir9").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir9")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir9")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir9")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir9").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir9").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir9").set("de", "7.500000e-1");
    model.component("comp1").physics("plas").feature("eir9").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir9")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.75"}, 
         {"0.79"}, 
         {"0.8"}, 
         {"0.82"}, 
         {"0.9"}, 
         {"0.91"}, 
         {"0.93"}, 
         {"1.02"}, 
         {"1.03"}, 
         {"1.05"}, 
         {"1.13"}, 
         {"1.14"}, 
         {"1.16"}, 
         {"1.23"}, 
         {"1.24"}, 
         {"1.26"}, 
         {"1.34"}, 
         {"1.35"}, 
         {"1.37"}, 
         {"1.44"}, 
         {"1.45"}, 
         {"1.47"}, 
         {"1.54"}, 
         {"1.55"}, 
         {"1.57"}, 
         {"1.63"}, 
         {"1.65"}, 
         {"1.67"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.0"}, 
         {"12.0"}, 
         {"13.0"}, 
         {"14.0"}, 
         {"15.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir9")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"5.5E-23"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.0E-24"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.35E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.85E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.15E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.65E-22"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"2.75E-22"}, 
         {"3.5E-22"}, 
         {"4.13E-22"}, 
         {"4.62E-22"}, 
         {"3.13E-22"}, 
         {"2.5E-22"}, 
         {"1.75E-22"}, 
         {"8.8E-23"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir9").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir9").active(true);
    model.component("comp1").physics("plas").feature("eir9").label("9: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir10")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O2a1Dg"}});
    model.component("comp1").physics("plas").feature("eir10").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir10").set("revSpecies", new String[][]{{"e"}, {"O2a1Dg"}});
    model.component("comp1").physics("plas").feature("eir10").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir10")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir10")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir10")
         .set("stoichArray", new int[][]{{0}, {-1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir10").set("formula", "e+O2=>e+O2a1Dg");
    model.component("comp1").physics("plas").feature("eir10").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir10").set("de", "9.770000e-1");
    model.component("comp1").physics("plas").feature("eir10").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir10")
         .set("xdata", new String[][]{{"0.977"}, 
         {"1.5"}, 
         {"2.0"}, 
         {"3.0"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"5.62"}, 
         {"5.91"}, 
         {"6.19"}, 
         {"6.53"}, 
         {"6.99"}, 
         {"7.61"}, 
         {"7.89"}, 
         {"8.96"}, 
         {"10.04"}, 
         {"13.0"}, 
         {"15.1"}, 
         {"17.5"}, 
         {"20.5"}, 
         {"24.9"}, 
         {"30.9"}, 
         {"41.0"}, 
         {"45.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir10")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.8E-23"}, 
         {"1.53E-22"}, 
         {"3.8E-22"}, 
         {"4.9E-22"}, 
         {"5.7E-22"}, 
         {"7.4E-22"}, 
         {"8.25E-22"}, 
         {"8.62E-22"}, 
         {"8.88E-22"}, 
         {"9.08E-22"}, 
         {"9.14E-22"}, 
         {"8.91E-22"}, 
         {"8.63E-22"}, 
         {"7.68E-22"}, 
         {"6.79E-22"}, 
         {"5.27E-22"}, 
         {"4.55E-22"}, 
         {"3.87E-22"}, 
         {"3.24E-22"}, 
         {"2.56E-22"}, 
         {"1.96E-22"}, 
         {"1.37E-22"}, 
         {"1.2E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir10").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir10").active(true);
    model.component("comp1").physics("plas").feature("eir10").label("10: e+O2=>e+O2a1Dg");
    model.component("comp1").physics("plas").feature("eir11").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir11").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir11").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir11").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir11")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir11")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir11")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir11").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir11").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir11").set("de", "1.627000e+0");
    model.component("comp1").physics("plas").feature("eir11").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir11")
         .set("xdata", new String[][]{{"1.627"}, 
         {"2.0"}, 
         {"3.0"}, 
         {"3.5"}, 
         {"4.0"}, 
         {"5.0"}, 
         {"5.69"}, 
         {"6.54"}, 
         {"7.34"}, 
         {"8.41"}, 
         {"9.26"}, 
         {"10.0"}, 
         {"13.0"}, 
         {"14.9"}, 
         {"17.0"}, 
         {"19.4"}, 
         {"20.7"}, 
         {"22.5"}, 
         {"24.0"}, 
         {"28.0"}, 
         {"35.1"}, 
         {"41.9"}, 
         {"45.1"}, 
         {"1000.0"}});
    model.component("comp1").physics("plas").feature("eir11")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.6E-23"}, 
         {"9.7E-23"}, 
         {"1.33E-22"}, 
         {"1.49E-22"}, 
         {"1.82E-22"}, 
         {"1.94E-22"}, 
         {"1.94E-22"}, 
         {"1.91E-22"}, 
         {"1.83E-22"}, 
         {"1.74E-22"}, 
         {"1.6E-22"}, 
         {"1.3E-22"}, 
         {"1.3E-22"}, 
         {"1.3E-22"}, 
         {"1.25E-22"}, 
         {"1.25E-22"}, 
         {"1.1E-22"}, 
         {"1.0E-22"}, 
         {"8.0E-23"}, 
         {"6.3E-23"}, 
         {"1.8E-23"}, 
         {"5.0E-24"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir11").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir11").active(true);
    model.component("comp1").physics("plas").feature("eir11").label("11: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir12").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir12").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir12").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir12").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir12")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir12")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir12")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir12").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir12").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir12").set("de", "4.500000e+0");
    model.component("comp1").physics("plas").feature("eir12").set("SpecifyReactionUsing", "UseCrossSectionData");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("plas").feature("eir12")
         .set("xdata", new String[][]{{"4.5"}, 
         {"4.8"}, 
         {"5.0"}, 
         {"5.5"}, 
         {"6.0"}, 
         {"6.5"}, 
         {"7.0"}, 
         {"7.5"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}});
    model.component("comp1").physics("plas").feature("eir12")
         .set("ydata", new String[][]{{"0.0"}, 
         {"3.0E-23"}, 
         {"9.0E-23"}, 
         {"3.0E-22"}, 
         {"6.5E-22"}, 
         {"8.5E-22"}, 
         {"9.5E-22"}, 
         {"1.0E-21"}, 
         {"1.0E-21"}, 
         {"8.5E-22"}, 
         {"7.0E-22"}, 
         {"4.5E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir12").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir12").active(true);
    model.component("comp1").physics("plas").feature("eir12").label("12: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir13").set("Species", new String[][]{{"e"}, {"O2"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir13").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir13").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir13").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir13")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir13")
         .set("revArray", new int[][]{{1}, {0}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir13")
         .set("stoichArray", new int[][]{{0}, {-1}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir13").set("formula", "e+O2=>e+O+O");
    model.component("comp1").physics("plas").feature("eir13").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir13").set("de", "6.000000e+0");
    model.component("comp1").physics("plas").feature("eir13").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir13")
         .set("xdata", new String[][]{{"6.0"}, 
         {"7.0"}, 
         {"7.8"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"45.0"}, 
         {"100.0"}});
    model.component("comp1").physics("plas").feature("eir13")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.5E-21"}, 
         {"2.3E-21"}, 
         {"2.3E-21"}, 
         {"2.1E-21"}, 
         {"1.65E-21"}, 
         {"1.05E-21"}, 
         {"6.5E-22"}, 
         {"4.75E-22"}, 
         {"1.9E-22"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir13").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir13").active(true);
    model.component("comp1").physics("plas").feature("eir13").label("13: e+O2=>e+O+O");
    model.component("comp1").physics("plas").feature("eir14")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("plas").feature("eir14").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir14")
         .set("revSpecies", new String[][]{{"e"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("plas").feature("eir14").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir14")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir14")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir14")
         .set("stoichArray", new int[][]{{0}, {-1}, {1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir14").set("formula", "e+O2=>e+O+O1D");
    model.component("comp1").physics("plas").feature("eir14").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir14").set("de", "8.400000e+0");
    model.component("comp1").physics("plas").feature("eir14").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir14")
         .set("xdata", new String[][]{{"8.4"}, 
         {"9.4"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir14")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.0E-20"}, 
         {"9.0E-21"}, 
         {"7.0E-21"}, 
         {"5.4E-21"}, 
         {"3.2E-21"}, 
         {"2.7E-21"}, 
         {"1.7E-21"}, 
         {"1.09E-21"}, 
         {"8.0E-22"}, 
         {"5.8E-22"}, 
         {"4.2E-22"}, 
         {"3.3E-22"}, 
         {"2.4E-22"}, 
         {"1.6E-22"}, 
         {"1.2E-22"}, 
         {"9.0E-23"}});
    model.component("comp1").physics("plas").feature("eir14").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir14").active(true);
    model.component("comp1").physics("plas").feature("eir14").label("14: e+O2=>e+O+O1D");
    model.component("comp1").physics("plas").feature("eir15").set("Species", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir15").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir15").set("revSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir15").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir15")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir15")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir15")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir15").set("formula", "e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir15").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir15").set("de", "9.970000e+0");
    model.component("comp1").physics("plas").feature("eir15").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir15")
         .set("xdata", new String[][]{{"10.0"}, 
         {"20.0"}, 
         {"30.0"}, 
         {"40.0"}, 
         {"50.0"}, 
         {"60.0"}, 
         {"70.0"}, 
         {"80.0"}, 
         {"100.0"}, 
         {"120.0"}, 
         {"150.0"}, 
         {"170.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}});
    model.component("comp1").physics("plas").feature("eir15")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.3E-22"}, 
         {"2.6E-22"}, 
         {"4.0E-22"}, 
         {"5.0E-22"}, 
         {"6.0E-22"}, 
         {"6.5E-22"}, 
         {"7.0E-22"}, 
         {"7.0E-22"}, 
         {"5.0E-22"}, 
         {"4.0E-22"}, 
         {"3.5E-22"}, 
         {"3.0E-22"}, 
         {"2.0E-22"}, 
         {"1.2E-22"}, 
         {"8.0E-23"}, 
         {"5.0E-23"}, 
         {"0.0"}});
    model.component("comp1").physics("plas").feature("eir15").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir15").active(true);
    model.component("comp1").physics("plas").feature("eir15").label("15: e+O2=>e+O2");
    model.component("comp1").physics("plas").feature("eir16")
         .set("Species", new String[][]{{"e"}, {"O2"}, {"O2_1p"}});
    model.component("comp1").physics("plas").feature("eir16").set("fwdSpecies", new String[][]{{"e"}, {"O2"}});
    model.component("comp1").physics("plas").feature("eir16").set("revSpecies", new String[][]{{"e"}, {"O2+"}});
    model.component("comp1").physics("plas").feature("eir16").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir16")
         .set("fwdArray", new int[][]{{-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir16")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir16")
         .set("stoichArray", new int[][]{{1}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir16").set("formula", "e+O2=>2e+O2+");
    model.component("comp1").physics("plas").feature("eir16").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir16").set("de", "1.206000e+1");
    model.component("comp1").physics("plas").feature("eir16").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir16")
         .set("xdata", new String[][]{{"12.06"}, 
         {"13.0"}, 
         {"18.0"}, 
         {"28.0"}, 
         {"38.0"}, 
         {"48.0"}, 
         {"58.0"}, 
         {"68.0"}, 
         {"78.0"}, 
         {"88.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}});
    model.component("comp1").physics("plas").feature("eir16")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.3E-22"}, 
         {"2.0E-21"}, 
         {"7.4E-21"}, 
         {"1.32E-20"}, 
         {"1.8E-20"}, 
         {"2.1E-20"}, 
         {"2.33E-20"}, 
         {"2.5E-20"}, 
         {"2.6E-20"}, 
         {"2.7E-20"}, 
         {"2.7E-20"}, 
         {"2.5E-20"}, 
         {"2.17E-20"}, 
         {"1.66E-20"}, 
         {"1.35E-20"}, 
         {"1.04E-20"}, 
         {"7.6E-21"}, 
         {"6.0E-21"}, 
         {"4.2E-21"}, 
         {"2.7E-21"}, 
         {"2.0E-21"}, 
         {"1.4E-21"}});
    model.component("comp1").physics("plas").feature("eir16").active(true);
    model.component("comp1").physics("plas").feature("eir16").label("16: e+O2=>2e+O2+");
    model.component("comp1").physics("plas").feature("eir17").set("Species", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir17").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir17").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir17").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir17")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir17")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir17")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir17").set("formula", "e+O=>e+O");
    model.component("comp1").physics("plas").feature("eir17").set("mratio", "3.426200e-5");
    model.component("comp1").physics("plas").feature("eir17").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir17")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.07"}, 
         {"0.2"}, 
         {"0.41"}, 
         {"0.68"}, 
         {"0.95"}, 
         {"1.22"}, 
         {"1.77"}, 
         {"2.6"}, 
         {"3.4"}, 
         {"5.4"}, 
         {"6.8"}, 
         {"9.5"}, 
         {"12.2"}, 
         {"17.7"}, 
         {"23.1"}});
    model.component("comp1").physics("plas").feature("eir17")
         .set("ydata", new double[][]{{1.19E-20}, {1.19E-20}, {1.97E-20}, {2.78E-20}, {3.64E-20}, {4.33E-20}, {4.89E-20}, {5.72E-20}, {6.48E-20}, {6.9E-20}, {7.25E-20}, {7.3E-20}, {7.47E-20}, {8.2E-20}, {1.341E-19}, {2.43E-19}});
    model.component("comp1").physics("plas").feature("eir17").active(true);
    model.component("comp1").physics("plas").feature("eir17").label("17: e+O=>e+O");
    model.component("comp1").physics("plas").feature("eir18").set("Species", new String[][]{{"e"}, {"O"}, {"O1D"}});
    model.component("comp1").physics("plas").feature("eir18").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir18").set("revSpecies", new String[][]{{"e"}, {"O1D"}});
    model.component("comp1").physics("plas").feature("eir18").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir18")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir18")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir18")
         .set("stoichArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir18").set("formula", "e+O=>e+O1D");
    model.component("comp1").physics("plas").feature("eir18").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir18").set("de", "1.968000e+0");
    model.component("comp1").physics("plas").feature("eir18").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir18")
         .set("xdata", new double[][]{{1.968}, {1.97278}, {2.0272}, {2.04081}, {2.10884}, {2.31292}, {2.38094}, {2.58503}, {2.99319}, {3.33332}, {3.40135}, {3.80951}, {4.20407}, {4.62584}, {5.30611}, {5.44216}, {5.71427}, {5.98638}, {6.12243}, {6.66665}, {8.16324}, {10.2041}, {14.0136}, {16.0544}, {20.4081}, {25.8503}, {27.2108}, {28.5713}, {29.9319}, {31.2924}, {32.653}, {34.0135}, {35.374}, {36.7346}, {38.0951}, {39.4557}, {40.8162}, {47.6189}, {54.4216}, {61.2243}, {68.027}, {74.8297}, {81.6324}});
    model.component("comp1").physics("plas").feature("eir18")
         .set("ydata", new String[][]{{"0.0"}, 
         {"1.61835E-24"}, 
         {"4.69189E-23"}, 
         {"6.27065E-23"}, 
         {"1.55873E-22"}, 
         {"4.92557E-22"}, 
         {"6.07994E-22"}, 
         {"9.38333E-22"}, 
         {"1.4852E-21"}, 
         {"1.82381E-21"}, 
         {"1.88041E-21"}, 
         {"2.15619E-21"}, 
         {"2.34151E-21"}, 
         {"2.46477E-21"}, 
         {"2.54541E-21"}, 
         {"2.55002E-21"}, 
         {"2.55244E-21"}, 
         {"2.54684E-21"}, 
         {"2.54126E-21"}, 
         {"2.50793E-21"}, 
         {"2.36534E-21"}, 
         {"2.15142E-21"}, 
         {"1.81755E-21"}, 
         {"1.62176E-21"}, 
         {"1.37591E-21"}, 
         {"1.14161E-21"}, 
         {"1.08633E-21"}, 
         {"1.03732E-21"}, 
         {"9.93853E-22"}, 
         {"9.54868E-22"}, 
         {"9.19421E-22"}, 
         {"8.85851E-22"}, 
         {"8.53637E-22"}, 
         {"8.25266E-22"}, 
         {"7.95328E-22"}, 
         {"7.65782E-22"}, 
         {"7.36755E-22"}, 
         {"6.12078E-22"}, 
         {"4.98162E-22"}, 
         {"3.94179E-22"}, 
         {"3.25808E-22"}, 
         {"2.6827E-22"}, 
         {"2.22122E-22"}});
    model.component("comp1").physics("plas").feature("eir18").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir18").active(true);
    model.component("comp1").physics("plas").feature("eir18").label("18: e+O=>e+O1D");
    model.component("comp1").physics("plas").feature("eir19").set("Species", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir19").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir19").set("revSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir19").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir19")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir19")
         .set("revArray", new int[][]{{1}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir19")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir19").set("formula", "e+O=>e+O");
    model.component("comp1").physics("plas").feature("eir19").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir19").set("de", "4.192000e+0");
    model.component("comp1").physics("plas").feature("eir19").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir19")
         .set("xdata", new double[][]{{4.192}, {4.20407}, {4.2857}, {4.42175}, {4.48978}, {4.62584}, {4.89794}, {5.17005}, {5.71427}, {5.98638}, {6.53059}, {6.8027}, {7.89113}, {8.43535}, {8.97956}, {9.25167}, {9.52378}, {10.2041}, {11.5646}, {12.9251}, {13.7415}, {14.0136}, {14.2857}, {14.5578}, {14.8299}, {15.102}, {15.3741}, {15.6462}, {15.9183}, {16.1904}, {17.0068}, {18.3673}, {19.7278}, {21.0884}, {22.4489}, {24.4897}, {27.2108}, {29.9319}, {32.653}, {35.374}, {38.0951}, {40.8162}, {54.4216}, {68.027}, {81.6324}});
    model.component("comp1").physics("plas").feature("eir19")
         .set("ydata", new String[][]{{"0.0"}, 
         {"2.5314E-25"}, 
         {"5.27675E-24"}, 
         {"1.83216E-23"}, 
         {"2.58067E-23"}, 
         {"4.14682E-23"}, 
         {"7.24623E-23"}, 
         {"1.0058E-22"}, 
         {"1.45801E-22"}, 
         {"1.63107E-22"}, 
         {"1.89134E-22"}, 
         {"1.9864E-22"}, 
         {"2.20635E-22"}, 
         {"2.25198E-22"}, 
         {"2.2709E-22"}, 
         {"2.27298E-22"}, 
         {"2.27118E-22"}, 
         {"2.25443E-22"}, 
         {"2.18648E-22"}, 
         {"2.10031E-22"}, 
         {"2.04524E-22"}, 
         {"2.02641E-22"}, 
         {"2.00709E-22"}, 
         {"1.98739E-22"}, 
         {"1.96707E-22"}, 
         {"1.94546E-22"}, 
         {"1.92141E-22"}, 
         {"1.88774E-22"}, 
         {"1.82029E-22"}, 
         {"1.78855E-22"}, 
         {"1.72053E-22"}, 
         {"1.66522E-22"}, 
         {"1.58119E-22"}, 
         {"1.5292E-22"}, 
         {"1.48215E-22"}, 
         {"1.39031E-22"}, 
         {"1.27993E-22"}, 
         {"1.1881E-22"}, 
         {"1.10808E-22"}, 
         {"1.03758E-22"}, 
         {"9.74923E-23"}, 
         {"9.1247E-23"}, 
         {"6.23757E-23"}, 
         {"3.92841E-23"}, 
         {"2.66209E-23"}});
    model.component("comp1").physics("plas").feature("eir19").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir19").active(true);
    model.component("comp1").physics("plas").feature("eir19").label("19: e+O=>e+O");
    model.component("comp1").physics("plas").feature("eir20").set("Species", new String[][]{{"e"}, {"O"}, {"O_1p"}});
    model.component("comp1").physics("plas").feature("eir20").set("fwdSpecies", new String[][]{{"e"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir20").set("revSpecies", new String[][]{{"e"}, {"O+"}});
    model.component("comp1").physics("plas").feature("eir20").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir20")
         .set("fwdArray", new int[][]{{-1}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir20")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir20")
         .set("stoichArray", new int[][]{{1}, {0}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir20").set("formula", "e+O=>2e+O+");
    model.component("comp1").physics("plas").feature("eir20").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir20").set("de", 13.618);
    model.component("comp1").physics("plas").feature("eir20").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir20")
         .set("xdata", new String[][]{{"13.618"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"40.0"}, 
         {"60.0"}, 
         {"80.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"250.0"}, 
         {"300.0"}, 
         {"400.0"}});
    model.component("comp1").physics("plas").feature("eir20")
         .set("ydata", new String[][]{{"0.0"}, 
         {"5.6E-21"}, 
         {"8.7E-21"}, 
         {"1.08E-20"}, 
         {"1.32E-20"}, 
         {"1.52E-20"}, 
         {"1.58E-20"}, 
         {"1.55E-20"}, 
         {"1.42E-20"}, 
         {"1.31E-20"}, 
         {"1.22E-20"}, 
         {"1.1E-20"}, 
         {"9.26E-21"}});
    model.component("comp1").physics("plas").feature("eir20").active(true);
    model.component("comp1").physics("plas").feature("eir20").label("20: e+O=>2e+O+");
    model.component("comp1").physics("plas").feature("eir21").set("Species", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir21").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir21").set("revSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir21").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir21")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir21")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir21")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir21").set("formula", "e+Ar=>e+Ar");
    model.component("comp1").physics("plas").feature("eir21").set("mratio", "0.136E-04");
    model.component("comp1").physics("plas").feature("eir21").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir21")
         .set("xdata", new String[][]{{"0.0"}, 
         {"0.001"}, 
         {"0.002"}, 
         {"0.003"}, 
         {"0.005"}, 
         {"0.007"}, 
         {"0.0085"}, 
         {"0.01"}, 
         {"0.015"}, 
         {"0.02"}, 
         {"0.03"}, 
         {"0.04"}, 
         {"0.05"}, 
         {"0.07"}, 
         {"0.1"}, 
         {"0.12"}, 
         {"0.15"}, 
         {"0.17"}, 
         {"0.2"}, 
         {"0.25"}, 
         {"0.3"}, 
         {"0.35"}, 
         {"0.4"}, 
         {"0.5"}, 
         {"0.7"}, 
         {"1.0"}, 
         {"1.2"}, 
         {"1.3"}, 
         {"1.5"}, 
         {"1.7"}, 
         {"1.9"}, 
         {"2.1"}, 
         {"2.2"}, 
         {"2.5"}, 
         {"2.8"}, 
         {"3.0"}, 
         {"3.3"}, 
         {"3.6"}, 
         {"4.0"}, 
         {"4.5"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"10.0"}, 
         {"12.0"}, 
         {"15.0"}, 
         {"17.0"}, 
         {"20.0"}, 
         {"25.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"75.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("plas").feature("eir21")
         .set("ydata", new double[][]{{7.5E-20}, {7.5E-20}, {7.1E-20}, {6.7E-20}, {6.1E-20}, {5.4E-20}, {5.05E-20}, {4.6E-20}, {3.75E-20}, {3.25E-20}, {2.5E-20}, {2.05E-20}, {1.73E-20}, {1.13E-20}, {5.9E-21}, {4.0E-21}, {2.3E-21}, {1.6E-21}, {1.03E-21}, {9.1E-22}, {1.53E-21}, {2.35E-21}, {3.3E-21}, {5.1E-21}, {8.6E-21}, {1.38E-20}, {1.66E-20}, {1.82E-20}, {2.1E-20}, {2.3E-20}, {2.5E-20}, {2.8E-20}, {2.9E-20}, {3.3E-20}, {3.8E-20}, {4.1E-20}, {4.5E-20}, {4.9E-20}, {5.4E-20}, {6.1E-20}, {6.7E-20}, {8.1E-20}, {9.6E-20}, {1.17E-19}, {1.5E-19}, {1.45E-19}, {1.37E-19}, {1.1E-19}, {9.2E-20}, {6.8E-20}, {5.5E-20}, {3.2E-20}, {2.15E-20}, {1.6E-20}, {1.1E-20}, {8.8E-21}, {6.0E-21}, {3.7E-21}, {2.6E-21}, {1.7E-21}, {9.8E-22}, {6.6E-22}, {3.5E-22}, {1.5E-22}, {8.8E-23}, {4.9E-23}, {4.9E-23}});
    model.component("comp1").physics("plas").feature("eir21").active(true);
    model.component("comp1").physics("plas").feature("eir21").label("21: e+Ar=>e+Ar");
    model.component("comp1").physics("plas").feature("eir22").set("Species", new String[][]{{"e"}, {"Ar"}, {"Ars"}});
    model.component("comp1").physics("plas").feature("eir22").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir22").set("revSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("plas").feature("eir22").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir22")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir22")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}});
    model.component("comp1").physics("plas").feature("eir22")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {1}, {0}});
    model.component("comp1").physics("plas").feature("eir22").set("formula", "e+Ar=>e+Ars");
    model.component("comp1").physics("plas").feature("eir22").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir22").set("de", "11.50");
    model.component("comp1").physics("plas").feature("eir22").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir22")
         .set("xdata", new String[][]{{"0.0"}, 
         {"11.5"}, 
         {"12.7"}, 
         {"13.7"}, 
         {"14.7"}, 
         {"15.9"}, 
         {"16.5"}, 
         {"17.5"}, 
         {"18.5"}, 
         {"19.9"}, 
         {"22.2"}, 
         {"24.7"}, 
         {"27.0"}, 
         {"30.0"}, 
         {"33.0"}, 
         {"35.3"}, 
         {"42.0"}, 
         {"48.0"}, 
         {"52.0"}, 
         {"70.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("plas").feature("eir22")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"7.0E-22"}, 
         {"1.41E-21"}, 
         {"2.28E-21"}, 
         {"3.8E-21"}, 
         {"4.8E-21"}, 
         {"6.1E-21"}, 
         {"7.5E-21"}, 
         {"9.2E-21"}, 
         {"1.17E-20"}, 
         {"1.33E-20"}, 
         {"1.42E-20"}, 
         {"1.44E-20"}, 
         {"1.41E-20"}, 
         {"1.34E-20"}, 
         {"1.25E-20"}, 
         {"1.16E-20"}, 
         {"1.11E-20"}, 
         {"9.4E-21"}, 
         {"7.6E-21"}, 
         {"6.0E-21"}, 
         {"5.05E-21"}, 
         {"3.95E-21"}, 
         {"2.8E-21"}, 
         {"2.25E-21"}, 
         {"1.77E-21"}, 
         {"1.36E-21"}, 
         {"1.1E-21"}, 
         {"8.3E-22"}, 
         {"5.8E-22"}, 
         {"4.5E-22"}, 
         {"3.5E-22"}, 
         {"3.5E-22"}});
    model.component("comp1").physics("plas").feature("eir22").set("gw", 12);
    model.component("comp1").physics("plas").feature("eir22").active(true);
    model.component("comp1").physics("plas").feature("eir22").label("22: e+Ar=>e+Ars");
    model.component("comp1").physics("plas").feature("eir23").set("Species", new String[][]{{"e"}, {"Ars"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir23").set("fwdSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("plas").feature("eir23").set("revSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir23").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir23")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("eir23")
         .set("revArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir23")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("eir23").set("formula", "e+Ars=>e+Ar");
    model.component("comp1").physics("plas").feature("eir23").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir23").set("de", "-11.50");
    model.component("comp1").physics("plas").feature("eir23").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir23")
         .set("xdata", new String[][]{{"-11.5"}, 
         {"0.0"}, 
         {"1.1999999999999993"}, 
         {"2.1999999999999993"}, 
         {"3.1999999999999993"}, 
         {"4.4"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.399999999999999"}, 
         {"10.7"}, 
         {"13.2"}, 
         {"15.5"}, 
         {"18.5"}, 
         {"21.5"}, 
         {"23.799999999999997"}, 
         {"30.5"}, 
         {"36.5"}, 
         {"40.5"}, 
         {"58.5"}, 
         {"88.5"}, 
         {"138.5"}, 
         {"188.5"}, 
         {"288.5"}, 
         {"488.5"}, 
         {"688.5"}, 
         {"988.5"}, 
         {"1488.5"}, 
         {"1988.5"}, 
         {"2988.5"}, 
         {"4988.5"}, 
         {"6988.5"}, 
         {"9988.5"}, 
         {"999988.5"}});
    model.component("comp1").physics("plas").feature("eir23")
         .set("ydata", new String[][]{{"-0.0"}, 
         {"0.0"}, 
         {"6.173611111111115E-22"}, 
         {"7.317045454545457E-22"}, 
         {"8.728125E-22"}, 
         {"1.1443181818181814E-21"}, 
         {"1.3199999999999998E-21"}, 
         {"1.4826388888888888E-21"}, 
         {"1.6517857142857143E-21"}, 
         {"1.8162698412698414E-21"}, 
         {"2.0228971962616822E-21"}, 
         {"2.073926767676768E-21"}, 
         {"2.061290322580645E-21"}, 
         {"1.945945945945946E-21"}, 
         {"1.803488372093023E-21"}, 
         {"1.6562324929971988E-21"}, 
         {"1.4344262295081967E-21"}, 
         {"1.2712328767123287E-21"}, 
         {"1.1876543209876544E-21"}, 
         {"9.373219373219375E-22"}, 
         {"7.156308851224105E-22"}, 
         {"5.415162454873646E-22"}, 
         {"4.465075154730327E-22"}, 
         {"3.4228769497400347E-22"}, 
         {"2.388263391334016E-22"}, 
         {"1.906318082788671E-22"}, 
         {"1.4921598381385938E-22"}, 
         {"1.1420893516963384E-22"}, 
         {"9.219679825664236E-23"}, 
         {"6.943282583235737E-23"}, 
         {"4.8444756272760683E-23"}, 
         {"3.756170852114187E-23"}, 
         {"2.920024695065992E-23"}, 
         {"2.916700208719067E-23"}});

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").physics("plas").feature("eir23").set("gw", 1);
    model.component("comp1").physics("plas").feature("eir23").active(true);
    model.component("comp1").physics("plas").feature("eir23").label("23: e+Ars=>e+Ar");
    model.component("comp1").physics("plas").feature("eir24")
         .set("Species", new String[][]{{"e"}, {"Ar"}, {"Ar_1p"}});
    model.component("comp1").physics("plas").feature("eir24").set("fwdSpecies", new String[][]{{"e"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("eir24").set("revSpecies", new String[][]{{"e"}, {"Ar+"}});
    model.component("comp1").physics("plas").feature("eir24").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir24")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir24")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}});
    model.component("comp1").physics("plas").feature("eir24")
         .set("stoichArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {1}});
    model.component("comp1").physics("plas").feature("eir24").set("formula", "e+Ar=>2e+Ar+");
    model.component("comp1").physics("plas").feature("eir24").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir24").set("de", "15.80");
    model.component("comp1").physics("plas").feature("eir24").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir24")
         .set("xdata", new String[][]{{"0.0"}, 
         {"15.8"}, 
         {"16.0"}, 
         {"17.0"}, 
         {"18.0"}, 
         {"20.0"}, 
         {"22.0"}, 
         {"23.75"}, 
         {"25.0"}, 
         {"26.5"}, 
         {"30.0"}, 
         {"32.5"}, 
         {"35.0"}, 
         {"37.5"}, 
         {"40.0"}, 
         {"50.0"}, 
         {"55.0"}, 
         {"100.0"}, 
         {"150.0"}, 
         {"200.0"}, 
         {"300.0"}, 
         {"500.0"}, 
         {"700.0"}, 
         {"1000.0"}, 
         {"1500.0"}, 
         {"2000.0"}, 
         {"3000.0"}, 
         {"5000.0"}, 
         {"7000.0"}, 
         {"10000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("plas").feature("eir24")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"2.02E-22"}, 
         {"1.34E-21"}, 
         {"2.94E-21"}, 
         {"6.3E-21"}, 
         {"9.3E-21"}, 
         {"1.15E-20"}, 
         {"1.3E-20"}, 
         {"1.45E-20"}, 
         {"1.8E-20"}, 
         {"1.99E-20"}, 
         {"2.17E-20"}, 
         {"2.31E-20"}, 
         {"2.39E-20"}, 
         {"2.53E-20"}, 
         {"2.6E-20"}, 
         {"2.85E-20"}, 
         {"2.52E-20"}, 
         {"2.39E-20"}, 
         {"2.0E-20"}, 
         {"1.45E-20"}, 
         {"1.15E-20"}, 
         {"8.6E-21"}, 
         {"6.4E-21"}, 
         {"5.2E-21"}, 
         {"3.6E-21"}, 
         {"2.4E-21"}, 
         {"1.8E-21"}, 
         {"1.35E-21"}, 
         {"1.35E-21"}});
    model.component("comp1").physics("plas").feature("eir24").active(true);
    model.component("comp1").physics("plas").feature("eir24").label("24: e+Ar=>2e+Ar+");
    model.component("comp1").physics("plas").feature("eir25")
         .set("Species", new String[][]{{"e"}, {"Ars"}, {"Ar_1p"}});
    model.component("comp1").physics("plas").feature("eir25").set("fwdSpecies", new String[][]{{"e"}, {"Ars"}});
    model.component("comp1").physics("plas").feature("eir25").set("revSpecies", new String[][]{{"e"}, {"Ar+"}});
    model.component("comp1").physics("plas").feature("eir25").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir25")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("eir25")
         .set("revArray", new int[][]{{2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}});
    model.component("comp1").physics("plas").feature("eir25")
         .set("stoichArray", new int[][]{{1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {1}});
    model.component("comp1").physics("plas").feature("eir25").set("formula", "e+Ars=>2e+Ar+");
    model.component("comp1").physics("plas").feature("eir25").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir25").set("de", 4.427);
    model.component("comp1").physics("plas").feature("eir25").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir25")
         .set("xdata", new String[][]{{"0.0"}, 
         {"4.427"}, 
         {"4.628"}, 
         {"5.0"}, 
         {"6.0"}, 
         {"7.0"}, 
         {"8.0"}, 
         {"9.0"}, 
         {"10.0"}, 
         {"11.5"}, 
         {"15.5"}, 
         {"20.0"}, 
         {"30.0"}, 
         {"50.0"}, 
         {"100.0"}, 
         {"200.0"}, 
         {"1000.0"}, 
         {"10000.0"}, 
         {"100000.0"}, 
         {"1000000.0"}});
    model.component("comp1").physics("plas").feature("eir25")
         .set("ydata", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"1.849E-20"}, 
         {"3.1E-20"}, 
         {"5.8E-20"}, 
         {"6.9E-20"}, 
         {"7.6E-20"}, 
         {"8.0E-20"}, 
         {"8.2E-20"}, 
         {"8.35E-20"}, 
         {"7.8E-20"}, 
         {"7.0E-20"}, 
         {"5.4E-20"}, 
         {"3.8E-20"}, 
         {"2.05E-20"}, 
         {"1.2E-20"}, 
         {"3.5E-21"}, 
         {"6.1E-22"}, 
         {"1.08E-22"}, 
         {"1.08E-22"}});
    model.component("comp1").physics("plas").feature("eir25").active(true);
    model.component("comp1").physics("plas").feature("eir25").label("25: e+Ars=>2e+Ar+");
    model.component("comp1").physics("plas").feature("eir26").set("Species", new String[][]{{"e"}, {"O_1m"}, {"O"}});
    model.component("comp1").physics("plas").feature("eir26").set("fwdSpecies", new String[][]{{"e"}, {"O-"}});
    model.component("comp1").physics("plas").feature("eir26").set("revSpecies", new String[][]{{"O"}, {"e"}});
    model.component("comp1").physics("plas").feature("eir26").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("eir26")
         .set("fwdArray", new int[][]{{-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir26")
         .set("revArray", new int[][]{{2}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir26")
         .set("stoichArray", new int[][]{{1}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("eir26").set("formula", "e+O-=>O+e+e");
    model.component("comp1").physics("plas").feature("eir26").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir26").set("de", "12.0");
    model.component("comp1").physics("plas").feature("eir26")
         .set("kf", "5.47e-14[m^3/s]*N_A_const*exp(-2.98[V]/plas.Te)*(plas.Te/1[V])^0.324");
    model.component("comp1").physics("plas").feature("eir26").active(true);
    model.component("comp1").physics("plas").feature("eir26").label("26: e+O-=>O+e+e");
    model.component("comp1").physics("plas").feature("rxn1").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn1")
         .set("Species", new String[][]{{"O_1p"}, {"O2"}, {"O"}, {"O2_1p"}});
    model.component("comp1").physics("plas").feature("rxn1").set("fwdSpecies", new String[][]{{"O+"}, {"O2"}});
    model.component("comp1").physics("plas").feature("rxn1").set("revSpecies", new String[][]{{"O"}, {"O2+"}});
    model.component("comp1").physics("plas").feature("rxn1").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn1")
         .set("fwdArray", new int[][]{{0}, {-1}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn1")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn1")
         .set("stoichArray", new int[][]{{0}, {-1}, {1}, {0}, {0}, {0}, {1}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "O++O2=>O+O2+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "2e-17[m^3/s]*N_A_const*(300[K]/plas.T)^0.5");
    model.component("comp1").physics("plas").feature("rxn1").active(true);
    model.component("comp1").physics("plas").feature("rxn1").label("27: O++O2=>O+O2+");
    model.component("comp1").physics("plas").feature("rxn2").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn2")
         .set("Species", new String[][]{{"O_1m"}, {"O_1p"}, {"O"}});
    model.component("comp1").physics("plas").feature("rxn2").set("fwdSpecies", new String[][]{{"O-"}, {"O+"}});
    model.component("comp1").physics("plas").feature("rxn2").set("revSpecies", "O");
    model.component("comp1").physics("plas").feature("rxn2").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn2")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn2")
         .set("revArray", new int[][]{{0}, {0}, {2}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn2")
         .set("stoichArray", new int[][]{{0}, {0}, {2}, {-1}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "O-+O+=>O+O");
    model.component("comp1").physics("plas").feature("rxn2")
         .set("kf", "4e-14[m^3/s]*N_A_const*(300[K]/plas.T)^0.43");
    model.component("comp1").physics("plas").feature("rxn2").active(true);
    model.component("comp1").physics("plas").feature("rxn2").label("28: O-+O+=>O+O");
    model.component("comp1").physics("plas").feature("rxn3").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn3")
         .set("Species", new String[][]{{"O_1m"}, {"O2_1p"}, {"O"}});
    model.component("comp1").physics("plas").feature("rxn3").set("fwdSpecies", new String[][]{{"O-"}, {"O2+"}});
    model.component("comp1").physics("plas").feature("rxn3").set("revSpecies", "O");
    model.component("comp1").physics("plas").feature("rxn3").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn3")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn3")
         .set("revArray", new int[][]{{0}, {0}, {3}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn3")
         .set("stoichArray", new int[][]{{0}, {0}, {3}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn3").set("formula", "O-+O2+=>O+O+O");
    model.component("comp1").physics("plas").feature("rxn3")
         .set("kf", "2.6e-14[m^3/s]*N_A_const*(300[K]/plas.T)^0.44");
    model.component("comp1").physics("plas").feature("rxn3").active(true);
    model.component("comp1").physics("plas").feature("rxn3").label("29: O-+O2+=>O+O+O");
    model.component("comp1").physics("plas").feature("rxn4").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn4")
         .set("Species", new String[][]{{"O_1m"}, {"O2_1p"}, {"O"}, {"O2"}});
    model.component("comp1").physics("plas").feature("rxn4").set("fwdSpecies", new String[][]{{"O-"}, {"O2+"}});
    model.component("comp1").physics("plas").feature("rxn4").set("revSpecies", new String[][]{{"O"}, {"O2"}});
    model.component("comp1").physics("plas").feature("rxn4").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn4")
         .set("revArray", new int[][]{{0}, {1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn4")
         .set("stoichArray", new int[][]{{0}, {1}, {1}, {-1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn4").set("formula", "O-+O2+=>O+O2");
    model.component("comp1").physics("plas").feature("rxn4")
         .set("kf", "2.6e-14[m^3/s]*N_A_const*(300[K]/plas.T)^0.44");
    model.component("comp1").physics("plas").feature("rxn4").active(true);
    model.component("comp1").physics("plas").feature("rxn4").label("30: O-+O2+=>O+O2");
    model.component("comp1").physics("plas").feature("rxn5").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn5")
         .set("Species", new String[][]{{"O_1m"}, {"O"}, {"O2"}, {"e"}});
    model.component("comp1").physics("plas").feature("rxn5").set("fwdSpecies", new String[][]{{"O-"}, {"O"}});
    model.component("comp1").physics("plas").feature("rxn5").set("revSpecies", new String[][]{{"O2"}, {"e"}});
    model.component("comp1").physics("plas").feature("rxn5").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn5")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn5")
         .set("revArray", new int[][]{{1}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn5")
         .set("stoichArray", new int[][]{{1}, {1}, {-1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn5").set("formula", "O-+O=>O2+e");
    model.component("comp1").physics("plas").feature("rxn5").set("kf", "3.0e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").feature("rxn5").active(true);
    model.component("comp1").physics("plas").feature("rxn5").label("31: O-+O=>O2+e");
    model.component("comp1").physics("plas").feature("rxn6").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("rxn6")
         .set("Species", new String[][]{{"O_1m"}, {"Ar_1p"}, {"O"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("rxn6").set("fwdSpecies", new String[][]{{"O-"}, {"Ar+"}});
    model.component("comp1").physics("plas").feature("rxn6").set("revSpecies", new String[][]{{"O"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("rxn6").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("rxn6")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("plas").feature("rxn6")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("rxn6")
         .set("stoichArray", new int[][]{{0}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {1}, {0}, {-1}});
    model.component("comp1").physics("plas").feature("rxn6").set("formula", "O-+Ar+=>O+Ar");
    model.component("comp1").physics("plas").feature("rxn6")
         .set("kf", "4e-14[m^3/s]*N_A_const*(300[K]/plas.T)^0.43");
    model.component("comp1").physics("plas").feature("rxn6").active(true);
    model.component("comp1").physics("plas").feature("rxn6").label("32: O-+Ar+=>O+Ar");
    model.component("comp1").physics("plas").feature("e").set("specName", "e");
    model.component("comp1").physics("plas").feature("e").set("specLabel", "e");
    model.component("comp1").physics("plas").feature("e").set("sType", "electron");
    model.component("comp1").physics("plas").feature("e").set("sisDef", true);
    model.component("comp1").physics("plas").feature("e").label("\u7269\u8d28: e");
    model.component("comp1").physics("plas").feature("O2").set("specName", "O2");
    model.component("comp1").physics("plas").feature("O2").set("specLabel", "O2");
    model.component("comp1").physics("plas").feature("O2").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O2").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("plas").feature("O2").set("x0", "xO2");
    model.component("comp1").physics("plas").feature("O2").label("\u7269\u8d28: O2");
    model.component("comp1").physics("plas").feature("O").set("specName", "O");
    model.component("comp1").physics("plas").feature("O").set("specLabel", "O");
    model.component("comp1").physics("plas").feature("O").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O").set("PresetSpeciesData", "O");
    model.component("comp1").physics("plas").feature("O").set("x0", "xO");
    model.component("comp1").physics("plas").feature("O").label("\u7269\u8d28: O");
    model.component("comp1").physics("plas").feature("O_1m").set("specName", "O_1m");
    model.component("comp1").physics("plas").feature("O_1m").set("specLabel", "O-");
    model.component("comp1").physics("plas").feature("O_1m").set("sType", "ion");
    model.component("comp1").physics("plas").feature("O_1m").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O_1m").set("PresetSpeciesData", "O");
    model.component("comp1").physics("plas").feature("O_1m").set("z", -1);
    model.component("comp1").physics("plas").feature("O_1m").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("plas").feature("O_1m").label("\u7269\u8d28: O-");
    model.component("comp1").physics("plas").feature("O2a1Dg").set("specName", "O2a1Dg");
    model.component("comp1").physics("plas").feature("O2a1Dg").set("specLabel", "O2a1Dg");
    model.component("comp1").physics("plas").feature("O2a1Dg").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O2a1Dg").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("plas").feature("O2a1Dg").set("hadd", "9.77e-1");
    model.component("comp1").physics("plas").feature("O2a1Dg").label("\u7269\u8d28: O2a1Dg");
    model.component("comp1").physics("plas").feature("O1D").set("specName", "O1D");
    model.component("comp1").physics("plas").feature("O1D").set("specLabel", "O1D");
    model.component("comp1").physics("plas").feature("O1D").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O1D").set("PresetSpeciesData", "O");
    model.component("comp1").physics("plas").feature("O1D").set("hadd", 1.968);
    model.component("comp1").physics("plas").feature("O1D").label("\u7269\u8d28: O1D");
    model.component("comp1").physics("plas").feature("O2_1p").set("specName", "O2_1p");
    model.component("comp1").physics("plas").feature("O2_1p").set("specLabel", "O2+");
    model.component("comp1").physics("plas").feature("O2_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("O2_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("O2_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O2_1p").set("PresetSpeciesData", "O2");
    model.component("comp1").physics("plas").feature("O2_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("O2_1p").set("hadd", 12.06);
    model.component("comp1").physics("plas").feature("O2_1p").label("\u7269\u8d28: O2+");
    model.component("comp1").physics("plas").feature("O_1p").set("specName", "O_1p");
    model.component("comp1").physics("plas").feature("O_1p").set("specLabel", "O+");
    model.component("comp1").physics("plas").feature("O_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("O_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("O_1p").set("PresetSpeciesData", "O");
    model.component("comp1").physics("plas").feature("O_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("O_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("plas").feature("O_1p").set("hadd", 13.618);
    model.component("comp1").physics("plas").feature("O_1p").label("\u7269\u8d28: O+");
    model.component("comp1").physics("plas").feature("Ar").set("specName", "Ar");
    model.component("comp1").physics("plas").feature("Ar").set("specLabel", "Ar");
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("sisDef", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar").label("\u7269\u8d28: Ar");
    model.component("comp1").physics("plas").feature("Ars").set("specName", "Ars");
    model.component("comp1").physics("plas").feature("Ars").set("specLabel", "Ars");
    model.component("comp1").physics("plas").feature("Ars").set("sisDef", true);
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("hadd", 11.5);
    model.component("comp1").physics("plas").feature("Ars").label("\u7269\u8d28: Ars");
    model.component("comp1").physics("plas").feature("Ar_1p").set("specName", "Ar_1p");
    model.component("comp1").physics("plas").feature("Ar_1p").set("specLabel", "Ar+");
    model.component("comp1").physics("plas").feature("Ar_1p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("Ar_1p").set("sisDef", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("z", 1);
    model.component("comp1").physics("plas").feature("Ar_1p").set("n0", "1E10[1/m^3]");
    model.component("comp1").physics("plas").feature("Ar_1p").set("hadd", 15.8);
    model.component("comp1").physics("plas").feature("Ar_1p").label("\u7269\u8d28: Ar+");
    model.component("comp1").physics("plas").feature("sr1").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr1").set("Species", new String[][]{{"Ar_1p"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("sr1").set("fwdSpecies", "Ar+");
    model.component("comp1").physics("plas").feature("sr1").set("revSpecies", "Ar");
    model.component("comp1").physics("plas").feature("sr1").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr1")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}});
    model.component("comp1").physics("plas").feature("sr1")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr1")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {-1}});
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr1").set("gammai", 0.07);
    model.component("comp1").physics("plas").feature("sr1").set("ebari", 5.8);
    model.component("comp1").physics("plas").feature("sr1").active(true);
    model.component("comp1").physics("plas").feature("sr1").label("1: Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr2").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr2").set("Species", new String[][]{{"Ars"}, {"Ar"}});
    model.component("comp1").physics("plas").feature("sr2").set("fwdSpecies", "Ars");
    model.component("comp1").physics("plas").feature("sr2").set("revSpecies", "Ar");
    model.component("comp1").physics("plas").feature("sr2").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr2")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("sr2")
         .set("revArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr2")
         .set("stoichArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {-1}, {0}});
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr2").set("gammai", 0.07);
    model.component("comp1").physics("plas").feature("sr2").set("ebari", 5.8);
    model.component("comp1").physics("plas").feature("sr2").active(true);
    model.component("comp1").physics("plas").feature("sr2").label("2: Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr3").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr3").set("Species", new String[][]{{"O"}, {"O2"}});
    model.component("comp1").physics("plas").feature("sr3").set("fwdSpecies", "O");
    model.component("comp1").physics("plas").feature("sr3").set("revSpecies", "O2");
    model.component("comp1").physics("plas").feature("sr3").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr3")
         .set("fwdArray", new int[][]{{0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3")
         .set("revArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3")
         .set("stoichArray", new double[][]{{0}, {0.5}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr3").set("formula", "O=>0.5O2");
    model.component("comp1").physics("plas").feature("sr3").set("gammaf", 0.2);
    model.component("comp1").physics("plas").feature("sr3").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr3").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr3").active(true);
    model.component("comp1").physics("plas").feature("sr3").label("3: O=>0.5O2");
    model.component("comp1").physics("plas").feature("sr4").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr4").set("Species", new String[][]{{"O2_1p"}, {"O2"}});
    model.component("comp1").physics("plas").feature("sr4").set("fwdSpecies", "O2+");
    model.component("comp1").physics("plas").feature("sr4").set("revSpecies", "O2");
    model.component("comp1").physics("plas").feature("sr4").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr4")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr4").set("formula", "O2+=>O2");
    model.component("comp1").physics("plas").feature("sr4").set("gammai", 0.05);
    model.component("comp1").physics("plas").feature("sr4").set("ebari", "4.0");
    model.component("comp1").physics("plas").feature("sr4").active(true);
    model.component("comp1").physics("plas").feature("sr4").label("4: O2+=>O2");
    model.component("comp1").physics("plas").feature("sr5").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr5").set("Species", new String[][]{{"O_1m"}, {"O"}});
    model.component("comp1").physics("plas").feature("sr5").set("fwdSpecies", "O-");
    model.component("comp1").physics("plas").feature("sr5").set("revSpecies", "O");
    model.component("comp1").physics("plas").feature("sr5").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr5")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr5")
         .set("revArray", new int[][]{{0}, {0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr5")
         .set("stoichArray", new int[][]{{0}, {0}, {1}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr5").set("formula", "O-=>O");
    model.component("comp1").physics("plas").feature("sr5").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr5").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr5").active(true);
    model.component("comp1").physics("plas").feature("sr5").label("5: O-=>O");
    model.component("comp1").physics("plas").feature("sr6").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr6").set("Species", new String[][]{{"O2a1Dg"}, {"O2"}});
    model.component("comp1").physics("plas").feature("sr6").set("fwdSpecies", "O2a1Dg");
    model.component("comp1").physics("plas").feature("sr6").set("revSpecies", "O2");
    model.component("comp1").physics("plas").feature("sr6").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr6")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr6")
         .set("revArray", new int[][]{{0}, {1}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr6")
         .set("stoichArray", new int[][]{{0}, {1}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr6").set("formula", "O2a1Dg=>O2");
    model.component("comp1").physics("plas").feature("sr6").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr6").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr6").active(true);
    model.component("comp1").physics("plas").feature("sr6").label("6: O2a1Dg=>O2");
    model.component("comp1").physics("plas").feature("sr7").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr7").set("Species", new String[][]{{"O1D"}, {"O2"}});
    model.component("comp1").physics("plas").feature("sr7").set("fwdSpecies", "O1D");
    model.component("comp1").physics("plas").feature("sr7").set("revSpecies", "O2");
    model.component("comp1").physics("plas").feature("sr7").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr7")
         .set("fwdArray", new int[][]{{0}, {0}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr7")
         .set("revArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr7")
         .set("stoichArray", new double[][]{{0}, {0.5}, {0}, {0}, {0}, {-1}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("plas").feature("sr7").set("formula", "O1D=>0.5O2");
    model.component("comp1").physics("plas").feature("sr7").set("gammaf", 0.2);
    model.component("comp1").physics("plas").feature("sr7").set("gammai", "0.0");
    model.component("comp1").physics("plas").feature("sr7").set("ebari", "0.0");
    model.component("comp1").physics("plas").feature("sr7").active(true);
    model.component("comp1").physics("plas").feature("sr7").label("7: O1D=>0.5O2");
    model.component("comp1").physics("plas").feature("sr8").set("rType", "irrev");
    model.component("comp1").physics("plas").feature("sr8").set("Species", new String[][]{{"O_1p"}, {"O"}});
    model.component("comp1").physics("plas").feature("sr8").set("fwdSpecies", "O+");
    model.component("comp1").physics("plas").feature("sr8").set("revSpecies", "O");
    model.component("comp1").physics("plas").feature("sr8").set("rIsValid", true);
    model.component("comp1").physics("plas").feature("sr8").set("formula", "O+=>O");
    model.component("comp1").physics("plas").feature("sr8").set("gammai", 0.05);
    model.component("comp1").physics("plas").feature("sr8").set("ebari", "4.0");
    model.component("comp1").physics("plas").feature("sr8").active(true);
    model.component("comp1").physics("plas").feature("sr8").label("8: O+=>O");
    model.component("comp1").physics("plas").feature("pes1")
         .set("SpecifyElectronDensityAndEnergy", "FromElectronImpact");

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1E15[1/m^3]");
    model.component("comp1").physics("plas").feature("init1").set("ebarinit", "2[V]");
    model.component("comp1").physics("plas").feature("in1").set("specNames", "O2");
    model.component("comp1").physics("plas").feature("in1").set("xIn", "xO2");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p0");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("MassFlowType", "StandardFlowRateSCCM");
    model.component("comp1").physics("spf").feature("inl1").set("sccmmfr", "Qf");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "300[K]");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Power");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("PCoil", "Pw");

    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("fstat", "FrequencyStationary");
    model.study().create("std2");
    model.study("std2").create("fstat", "FrequencyStationary");
    model.study().create("std3");
    model.study("std3").create("fstat", "FrequencyStationary");

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
    model.sol().create("sol3");
    model.sol("sol3").attach("std3");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").create("s1", "Stationary");
    model.sol("sol3").feature("s1").create("p1", "Parametric");
    model.sol("sol3").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("s1").create("d1", "Direct");
    model.sol("sol3").feature("s1").create("i1", "Iterative");
    model.sol("sol3").feature("s1").create("i2", "Iterative");
    model.sol("sol3").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").create("sc1", "SCGS");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").create("sc1", "SCGS");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol3").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol3").feature("s1").feature().remove("fcDef");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("rev3").set("data", "dset3");
    model.result().create("pg1", "PlotGroup2D");
    model.result().create("pg2", "PlotGroup2D");
    model.result().create("pg3", "PlotGroup2D");
    model.result().create("pg4", "PlotGroup2D");
    model.result().create("pg5", "PlotGroup2D");
    model.result().create("pg6", "PlotGroup3D");
    model.result().create("pg7", "PlotGroup2D");
    model.result().create("pg8", "PlotGroup2D");
    model.result().create("pg9", "PlotGroup3D");
    model.result().create("pg10", "PlotGroup2D");
    model.result().create("pg11", "PlotGroup2D");
    model.result().create("pg12", "PlotGroup2D");
    model.result().create("pg13", "PlotGroup2D");
    model.result().create("pg14", "PlotGroup2D");
    model.result().create("pg15", "PlotGroup3D");
    model.result().create("pg16", "PlotGroup2D");
    model.result().create("pg17", "PlotGroup2D");
    model.result().create("pg18", "PlotGroup3D");
    model.result().create("pg28", "PlotGroup2D");
    model.result().create("pg29", "PlotGroup2D");
    model.result().create("pg30", "PlotGroup1D");
    model.result().create("pg31", "PlotGroup1D");
    model.result().create("pg19", "PlotGroup2D");
    model.result().create("pg20", "PlotGroup2D");
    model.result().create("pg21", "PlotGroup2D");
    model.result().create("pg22", "PlotGroup2D");
    model.result().create("pg23", "PlotGroup2D");
    model.result().create("pg24", "PlotGroup3D");
    model.result().create("pg25", "PlotGroup2D");
    model.result().create("pg26", "PlotGroup2D");
    model.result().create("pg27", "PlotGroup3D");
    model.result().create("pg32", "PlotGroup1D");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "plas.Te");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "spf.U");
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("surf1").set("expr", "mf.normB");
    model.result("pg8").feature("str1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43);
    model.result("pg8").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg8").feature("str1").create("col1", "Color");
    model.result("pg8").feature("str1").create("filt1", "Filter");
    model.result("pg8").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg8").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg8").feature("con1").set("expr", "mf.Psi");
    model.result("pg8").feature("con1").create("filt1", "Filter");
    model.result("pg8").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg9").create("vol1", "Volume");
    model.result("pg9").create("con1", "Contour");
    model.result("pg9").feature("vol1").set("expr", "mf.normB");
    model.result("pg9").feature("con1").set("expr", "mf.Psi");
    model.result("pg9").feature("con1").create("filt1", "Filter");
    model.result("pg9").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "plas.Te");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "V");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "spf.U");
    model.result("pg14").set("data", "dset2");
    model.result("pg14").create("con1", "Contour");
    model.result("pg14").feature("con1").set("expr", "p");
    model.result("pg15").set("data", "rev2");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "spf.U");
    model.result("pg16").set("data", "dset2");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "T");
    model.result("pg17").set("data", "dset2");
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").create("str1", "Streamline");
    model.result("pg17").create("con1", "Contour");
    model.result("pg17").feature("surf1").set("expr", "mf.normB");
    model.result("pg17").feature("str1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43);
    model.result("pg17").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg17").feature("str1").create("col1", "Color");
    model.result("pg17").feature("str1").create("filt1", "Filter");
    model.result("pg17").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg17").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg17").feature("con1").set("expr", "mf.Psi");
    model.result("pg17").feature("con1").create("filt1", "Filter");
    model.result("pg17").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg18").set("data", "rev2");
    model.result("pg18").create("vol1", "Volume");
    model.result("pg18").create("con1", "Contour");
    model.result("pg18").feature("vol1").set("expr", "mf.normB");
    model.result("pg18").feature("con1").set("expr", "mf.Psi");
    model.result("pg18").feature("con1").create("filt1", "Filter");
    model.result("pg18").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg28").set("data", "dset2");
    model.result("pg28").create("surf1", "Surface");
    model.result("pg28").feature("surf1").set("expr", "plas.n_wO_1m");
    model.result("pg29").set("data", "dset2");
    model.result("pg29").create("surf1", "Surface");
    model.result("pg29").feature("surf1").set("expr", "mf.Qrh");
    model.result("pg29").feature("surf1").create("sel1", "Selection");
    model.result("pg29").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg30").set("data", "dset2");
    model.result("pg30").create("lngr1", "LineGraph");
    model.result("pg30").create("lngr2", "LineGraph");
    model.result("pg30").create("lngr3", "LineGraph");
    model.result("pg30").create("lngr4", "LineGraph");
    model.result("pg30").create("lngr5", "LineGraph");
    model.result("pg30").feature("lngr1").set("xdata", "expr");
    model.result("pg30").feature("lngr1").selection().set(3);
    model.result("pg30").feature("lngr2").set("xdata", "expr");
    model.result("pg30").feature("lngr2").selection().set(3);
    model.result("pg30").feature("lngr2").set("expr", "plas.n_wO2_1p");
    model.result("pg30").feature("lngr3").set("xdata", "expr");
    model.result("pg30").feature("lngr3").selection().set(3);
    model.result("pg30").feature("lngr3").set("expr", "plas.n_wO_1p");
    model.result("pg30").feature("lngr4").set("xdata", "expr");
    model.result("pg30").feature("lngr4").selection().set(3);
    model.result("pg30").feature("lngr4").set("expr", "plas.n_wAr_1p");
    model.result("pg30").feature("lngr5").set("xdata", "expr");
    model.result("pg30").feature("lngr5").selection().set(3);
    model.result("pg30").feature("lngr5").set("expr", "plas.n_wO_1m");
    model.result("pg31").set("data", "dset2");
    model.result("pg31").create("glob1", "Global");
    model.result("pg31").feature("glob1")
         .set("expr", new String[]{"aveop1(plas.ne)", "aveop1(plas.n_wAr_1p)", "aveop1(plas.n_wO2_1p)", "aveop1(plas.n_wO_1p)", "aveop1(plas.n_wO_1m)"});
    model.result("pg19").set("data", "dset3");
    model.result("pg19").create("surf1", "Surface");
    model.result("pg20").set("data", "dset3");
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf1").set("expr", "plas.Te");
    model.result("pg21").set("data", "dset3");
    model.result("pg21").create("surf1", "Surface");
    model.result("pg21").feature("surf1").set("expr", "V");
    model.result("pg22").set("data", "dset3");
    model.result("pg22").create("surf1", "Surface");
    model.result("pg22").feature("surf1").set("expr", "spf.U");
    model.result("pg23").set("data", "dset3");
    model.result("pg23").create("con1", "Contour");
    model.result("pg23").feature("con1").set("expr", "p");
    model.result("pg24").set("data", "rev3");
    model.result("pg24").create("surf1", "Surface");
    model.result("pg24").feature("surf1").set("expr", "spf.U");
    model.result("pg25").set("data", "dset3");
    model.result("pg25").create("surf1", "Surface");
    model.result("pg25").feature("surf1").set("expr", "T");
    model.result("pg26").set("data", "dset3");
    model.result("pg26").create("surf1", "Surface");
    model.result("pg26").create("str1", "Streamline");
    model.result("pg26").create("con1", "Contour");
    model.result("pg26").feature("surf1").set("expr", "mf.normB");
    model.result("pg26").feature("str1").selection()
         .set(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 42, 43);
    model.result("pg26").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg26").feature("str1").create("col1", "Color");
    model.result("pg26").feature("str1").create("filt1", "Filter");
    model.result("pg26").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg26").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg26").feature("con1").set("expr", "mf.Psi");
    model.result("pg26").feature("con1").create("filt1", "Filter");
    model.result("pg26").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg27").set("data", "rev3");
    model.result("pg27").create("vol1", "Volume");
    model.result("pg27").create("con1", "Contour");
    model.result("pg27").feature("vol1").set("expr", "mf.normB");
    model.result("pg27").feature("con1").set("expr", "mf.Psi");
    model.result("pg27").feature("con1").create("filt1", "Filter");
    model.result("pg27").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg32").set("data", "dset3");
    model.result("pg32").create("glob1", "Global");
    model.result("pg32").feature("glob1")
         .set("expr", new String[]{"aveop1(plas.ne)", "aveop1(plas.n_wAr_1p)", "aveop1(plas.n_wO2_1p)", "aveop1(plas.n_wAr_1p)", "aveop1(plas.n_wO_1m)"});

    model.nodeGroup().create("grp1", "Physics", "plas");
    model.nodeGroup("grp1").placeAfter(null);
    model.nodeGroup().create("grp2", "Physics", "plas");
    model.nodeGroup("grp2").placeAfter(null);
    model.nodeGroup().create("grp3", "Physics", "plas");
    model.nodeGroup("grp3").placeAfter(null);
    model.nodeGroup().create("grp4", "Physics", "plas");
    model.nodeGroup("grp4").placeAfter(null);
    model.nodeGroup().create("grp5", "Results");
    model.nodeGroup("grp5").set("type", "plotgroup");
    model.nodeGroup("grp5").placeAfter(null);
    model.nodeGroup().create("grp6", "Results");
    model.nodeGroup("grp6").set("type", "plotgroup");
    model.nodeGroup().move("grp6", 5);
    model.nodeGroup("grp6").placeAfter(null);
    model.nodeGroup().create("grp7", "Results");
    model.nodeGroup("grp7").set("type", "plotgroup");
    model.nodeGroup().move("grp7", 6);
    model.nodeGroup("grp7").placeAfter(null);

    model.study("std1").label("\u57fa\u7840\u6848\u4f8b");
    model.study("std1").feature("fstat").set("freq", "13.56[MHz]");
    model.study("std2").label("\u529f\u7387\u626b\u63cf");
    model.study("std2").feature("fstat").set("freq", "13.56[MHz]");
    model.study("std2").feature("fstat").set("useinitsol", true);
    model.study("std2").feature("fstat").set("initmethod", "sol");
    model.study("std2").feature("fstat").set("initstudy", "std1");
    model.study("std2").feature("fstat").set("useparam", true);
    model.study("std2").feature("fstat").set("pname", new String[]{"Pw"});
    model.study("std2").feature("fstat").set("plistarr", new String[]{"range(100,50,1000)"});
    model.study("std2").feature("fstat").set("punit", new String[]{"W"});
    model.study("std3").label("xO2 \u626b\u63cf");
    model.study("std3").feature("fstat").set("freq", "13.56[MHz]");
    model.study("std3").feature("fstat").set("useinitsol", true);
    model.study("std3").feature("fstat").set("initmethod", "sol");
    model.study("std3").feature("fstat").set("initstudy", "std2");
    model.study("std3").feature("fstat").set("solnum", 9);
    model.study("std3").feature("fstat").set("useparam", true);
    model.study("std3").feature("fstat").set("sweeptype", "filled");
    model.study("std3").feature("fstat").set("pname", new String[]{"xO2", "Pw"});
    model.study("std3").feature("fstat").set("plistarr", new String[]{"range(0.9,-0.1,0.1)", "500"});
    model.study("std3").feature("fstat").set("punit", new String[]{"", "W"});

    model.sol("sol1").feature("st1").label("\u7f16\u8bd1\u65b9\u7a0b: \u9891\u57df-\u7a33\u6001");
    model.sol("sol1").feature("st1").set("splitcomplex", true);
    model.sol("sol1").feature("v1").label("\u56e0\u53d8\u91cf 1.1");
    model.sol("sol1").feature("s1").label("\u7a33\u6001\u6c42\u89e3\u5668 1.1");
    model.sol("sol1").feature("s1").set("stol", 1.0E-4);
    model.sol("sol1").feature("s1").feature("dDef").label("\u76f4\u63a5 2");
    model.sol("sol1").feature("s1").feature("aDef").label("\u9ad8\u7ea7 1");
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").feature("aDef").set("matherr", false);
    model.sol("sol1").feature("s1").feature("fc1").label("\u5168\u8026\u5408 1.1");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 1.0E-4);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-8);
    model.sol("sol1").feature("s1").feature("fc1").set("rstep", 1.5);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 500);
    model.sol("sol1").feature("s1").feature("fc1").set("termonres", false);
    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol1").feature("s1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht) (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
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

    model.sol("sol2").feature("st1").label("\u7f16\u8bd1\u65b9\u7a0b: \u9891\u57df-\u7a33\u6001");
    model.sol("sol2").feature("st1").set("splitcomplex", true);
    model.sol("sol2").feature("v1").label("\u56e0\u53d8\u91cf 1.1");
    model.sol("sol2").feature("v1").set("initmethod", "sol");
    model.sol("sol2").feature("v1").set("initsol", "sol1");
    model.sol("sol2").feature("v1").set("solnum", "auto");
    model.sol("sol2").feature("v1").set("clistctrl", new String[]{"p1"});
    model.sol("sol2").feature("v1").set("cname", new String[]{"Pw"});
    model.sol("sol2").feature("v1").set("clist", new String[]{"range(100,50,1000)[W]"});
    model.sol("sol2").feature("s1").label("\u7a33\u6001\u6c42\u89e3\u5668 1.1");
    model.sol("sol2").feature("s1").set("stol", 1.0E-4);
    model.sol("sol2").feature("s1").set("probesel", "none");
    model.sol("sol2").feature("s1").feature("dDef").label("\u76f4\u63a5 2");
    model.sol("sol2").feature("s1").feature("aDef").label("\u9ad8\u7ea7 1");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").feature("aDef").set("matherr", false);
    model.sol("sol2").feature("s1").feature("p1").label("\u53c2\u6570\u5316 1.1");
    model.sol("sol2").feature("s1").feature("p1").set("pname", new String[]{"Pw"});
    model.sol("sol2").feature("s1").feature("p1").set("plistarr", new String[]{"range(100,50,1000)"});
    model.sol("sol2").feature("s1").feature("p1").set("punit", new String[]{"W"});
    model.sol("sol2").feature("s1").feature("p1").set("excludelsqvalues", false);
    model.sol("sol2").feature("s1").feature("fc1").label("\u5168\u8026\u5408 1.1");
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("minstep", 1.0E-8);
    model.sol("sol2").feature("s1").feature("fc1").set("rstep", 1.5);
    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 500);
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", false);
    model.sol("sol2").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol2").feature("s1").feature("fc1").set("plotgroup", "pg10");
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

    return model;
  }

  public static Model run6(Model model) {
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

    model.sol("sol3").feature("st1").label("\u7f16\u8bd1\u65b9\u7a0b: \u9891\u57df-\u7a33\u6001");
    model.sol("sol3").feature("st1").set("splitcomplex", true);
    model.sol("sol3").feature("v1").label("\u56e0\u53d8\u91cf 1.1");
    model.sol("sol3").feature("v1").set("initmethod", "sol");
    model.sol("sol3").feature("v1").set("initsol", "sol2");
    model.sol("sol3").feature("v1").set("solnum", 9);
    model.sol("sol3").feature("v1").set("clistctrl", new String[]{"p1", "p1"});
    model.sol("sol3").feature("v1").set("cname", new String[]{"xO2", "Pw"});
    model.sol("sol3").feature("v1").set("clist", new String[]{"range(0.9,-0.1,0.1)", "500[W]"});
    model.sol("sol3").feature("s1").label("\u7a33\u6001\u6c42\u89e3\u5668 1.1");
    model.sol("sol3").feature("s1").set("stol", 1.0E-4);
    model.sol("sol3").feature("s1").set("probesel", "none");
    model.sol("sol3").feature("s1").feature("dDef").label("\u76f4\u63a5 2");
    model.sol("sol3").feature("s1").feature("aDef").label("\u9ad8\u7ea7 1");
    model.sol("sol3").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("s1").feature("aDef").set("matherr", false);
    model.sol("sol3").feature("s1").feature("p1").label("\u53c2\u6570\u5316 1.1");
    model.sol("sol3").feature("s1").feature("p1").set("sweeptype", "filled");
    model.sol("sol3").feature("s1").feature("p1").set("pname", new String[]{"xO2", "Pw"});
    model.sol("sol3").feature("s1").feature("p1").set("plistarr", new String[]{"range(0.9,-0.1,0.1)", "500"});
    model.sol("sol3").feature("s1").feature("p1").set("punit", new String[]{"", "W"});
    model.sol("sol3").feature("s1").feature("p1").set("excludelsqvalues", false);
    model.sol("sol3").feature("s1").feature("fc1").label("\u5168\u8026\u5408 1.1");
    model.sol("sol3").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol3").feature("s1").feature("fc1").set("minstep", 1.0E-8);
    model.sol("sol3").feature("s1").feature("fc1").set("rstep", 1.5);
    model.sol("sol3").feature("s1").feature("fc1").set("maxiter", 500);
    model.sol("sol3").feature("s1").feature("fc1").set("termonres", false);
    model.sol("sol3").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol3").feature("s1").feature("fc1").set("plotgroup", "pg19");
    model.sol("sol3").feature("s1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht) (\u5df2\u5408\u5e76)");
    model.sol("sol3").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol3").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("s1").feature("i1").label("AMG\uff0c\u6d41\u4f53\u6d41\u52a8\u53d8\u91cf (spf)");
    model.sol("sol3").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol3").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol3").feature("s1").feature("i1").set("rhob", 20);
    model.sol("sol3").feature("s1").feature("i1").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 80000);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").set("strconn", 0.02);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("soDef").label("SOR 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").label("SCGS 1.1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1").set("iter", 0);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("soDef").label("SOR 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").label("SCGS 1.1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("linesweeptype", "ssor");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1").set("iter", 1);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavarsactive", true);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("vankavars", new String[]{"comp1_spf_inl1_Pmf"});
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("approxscgs", true);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("po").feature("sc1")
         .set("scgsdirectmaxsize", 1000);
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol3").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol3").feature("s1").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol3").feature("s1").feature("i2").set("nlinnormuse", true);
    model.sol("sol3").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol3").feature("s1").feature("i2").feature("ilDef").label("\u4e0d\u5b8c\u5168 LU \u5206\u89e3 1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").label("\u591a\u91cd\u7f51\u683c 1.1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("pr").label("\u9884\u5e73\u6ed1\u5668 1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("pr").feature("soDef").label("SOR 2");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").label("SOR 1.1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("po").label("\u540e\u5e73\u6ed1\u5668 1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("po").feature("soDef").label("SOR 2");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").label("SOR 1.1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs")
         .label("\u7c97\u5316\u6c42\u89e3\u5668 1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs").feature("dDef")
         .label("\u76f4\u63a5 2");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .label("\u76f4\u63a5 1.1");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol3").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);

    model.study("std3").runNoGen();

    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c 2");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("colortable", "Rainbow");
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("resolution", "normal");
    model.result("pg6").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg7").label("\u6e29\u5ea6 (ht)");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("resolution", "normal");
    model.result("pg8").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg8").feature("surf1").set("resolution", "normal");
    model.result("pg8").feature("str1").set("titletype", "none");
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("udist", 0.03);
    model.result("pg8").feature("str1").set("maxlen", 0.4);
    model.result("pg8").feature("str1").set("maxsteps", 5000);
    model.result("pg8").feature("str1").set("inheritplot", "surf1");
    model.result("pg8").feature("str1").set("inheritcolor", false);
    model.result("pg8").feature("str1").set("resolution", "normal");
    model.result("pg8").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg8").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg8").feature("con1").set("titletype", "none");
    model.result("pg8").feature("con1").set("number", 10);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("coloring", "uniform");
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").feature("con1").set("color", "custom");
    model.result("pg8").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg8").feature("con1").set("resolution", "fine");
    model.result("pg8").feature("con1").set("inheritplot", "surf1");
    model.result("pg8").feature("con1").set("inheritcolor", false);
    model.result("pg8").feature("con1").set("resolution", "fine");
    model.result("pg9").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg9").feature("vol1").set("resolution", "normal");
    model.result("pg9").feature("con1").set("titletype", "none");
    model.result("pg9").feature("con1").set("number", 10);
    model.result("pg9").feature("con1").set("levelrounding", false);
    model.result("pg9").feature("con1").set("coloring", "uniform");
    model.result("pg9").feature("con1").set("colorlegend", false);
    model.result("pg9").feature("con1").set("color", "custom");
    model.result("pg9").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg9").feature("con1").set("resolution", "fine");
    model.result("pg9").feature("con1").set("inheritplot", "vol1");
    model.result("pg9").feature("con1").set("inheritcolor", false);
    model.result("pg9").feature("con1").set("resolution", "fine");
    model.result("pg10").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 1");
    model.result("pg10").feature("surf1").set("resolution", "normal");
    model.result("pg11").label("\u7535\u5b50\u6e29\u5ea6 (plas) 1");
    model.result("pg11").feature("surf1").set("resolution", "normal");
    model.result("pg12").label("\u7535\u52bf (plas) 1");
    model.result("pg12").feature("surf1").set("colortable", "Dipole");
    model.result("pg12").feature("surf1").set("resolution", "normal");
    model.result("pg13").label("\u901f\u5ea6 (spf) 1");
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("colortable", "Rainbow");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("resolution", "normal");
    model.result("pg14").label("\u538b\u529b (spf) 1");
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg14").feature("con1").set("number", 40);
    model.result("pg14").feature("con1").set("levelrounding", false);
    model.result("pg14").feature("con1").set("colortable", "Rainbow");
    model.result("pg14").feature("con1").set("smooth", "internal");
    model.result("pg14").feature("con1").set("resolution", "normal");
    model.result("pg15").label("\u4e09\u7ef4\u901f\u5ea6 (spf) 1");
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").feature("surf1").label("\u8868\u9762");
    model.result("pg15").feature("surf1").set("colortable", "Rainbow");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("resolution", "normal");
    model.result("pg16").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg16").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg16").feature("surf1").set("resolution", "normal");
    model.result("pg17").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg17").set("frametype", "spatial");
    model.result("pg17").set("showlegendsmaxmin", true);
    model.result("pg17").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg17").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg17").feature("surf1").set("resolution", "normal");
    model.result("pg17").feature("str1").set("titletype", "none");
    model.result("pg17").feature("str1").set("posmethod", "uniform");
    model.result("pg17").feature("str1").set("udist", 0.03);
    model.result("pg17").feature("str1").set("maxlen", 0.4);
    model.result("pg17").feature("str1").set("maxsteps", 5000);
    model.result("pg17").feature("str1").set("inheritplot", "surf1");
    model.result("pg17").feature("str1").set("inheritcolor", false);
    model.result("pg17").feature("str1").set("resolution", "normal");
    model.result("pg17").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg17").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg17").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg17").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg17").feature("con1").set("titletype", "none");
    model.result("pg17").feature("con1").set("number", 10);
    model.result("pg17").feature("con1").set("levelrounding", false);
    model.result("pg17").feature("con1").set("coloring", "uniform");
    model.result("pg17").feature("con1").set("colorlegend", false);
    model.result("pg17").feature("con1").set("color", "custom");
    model.result("pg17").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg17").feature("con1").set("resolution", "fine");
    model.result("pg17").feature("con1").set("inheritplot", "surf1");
    model.result("pg17").feature("con1").set("inheritcolor", false);
    model.result("pg17").feature("con1").set("resolution", "fine");
    model.result("pg18").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf) 1");
    model.result("pg18").set("frametype", "spatial");
    model.result("pg18").set("showlegendsmaxmin", true);
    model.result("pg18").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg18").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg18").feature("vol1").set("resolution", "normal");
    model.result("pg18").feature("con1").set("titletype", "none");
    model.result("pg18").feature("con1").set("number", 10);
    model.result("pg18").feature("con1").set("levelrounding", false);
    model.result("pg18").feature("con1").set("coloring", "uniform");
    model.result("pg18").feature("con1").set("colorlegend", false);
    model.result("pg18").feature("con1").set("color", "custom");
    model.result("pg18").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg18").feature("con1").set("resolution", "fine");
    model.result("pg18").feature("con1").set("inheritplot", "vol1");
    model.result("pg18").feature("con1").set("inheritcolor", false);
    model.result("pg18").feature("con1").set("resolution", "fine");
    model.result("pg28").label("\u8d1f\u79bb\u5b50\u5bc6\u5ea6");
    model.result("pg28").feature("surf1").set("resolution", "normal");
    model.result("pg29").label("\u5438\u6536\u529f\u7387");
    model.result("pg29").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg29").feature("surf1").set("resolution", "normal");
    model.result("pg30").label("\u6cbf\u5bf9\u79f0\u8f74\u7684\u5e26\u7535\u7269\u8d28");
    model.result("pg30").set("looplevelinput", new String[]{"last"});
    model.result("pg30").set("titletype", "none");
    model.result("pg30").set("xlabel", "z \u5750\u6807 (cm)");
    model.result("pg30").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg30").set("ylabelactive", true);
    model.result("pg30").set("ylog", true);
    model.result("pg30").set("legendpos", "lowermiddle");
    model.result("pg30").set("xlabelactive", false);
    model.result("pg30").feature("lngr1").set("xdataexpr", "z");
    model.result("pg30").feature("lngr1").set("xdataunit", "cm");
    model.result("pg30").feature("lngr1").set("xdatadescr", "z \u5750\u6807");
    model.result("pg30").feature("lngr1").set("linewidth", "preference");
    model.result("pg30").feature("lngr1").set("legend", true);
    model.result("pg30").feature("lngr1").set("autosolution", false);
    model.result("pg30").feature("lngr1").set("autoexpr", true);
    model.result("pg30").feature("lngr1").set("resolution", "normal");
    model.result("pg30").feature("lngr2").set("xdataexpr", "z");
    model.result("pg30").feature("lngr2").set("xdataunit", "cm");
    model.result("pg30").feature("lngr2").set("xdatadescr", "z \u5750\u6807");
    model.result("pg30").feature("lngr2").set("linewidth", "preference");
    model.result("pg30").feature("lngr2").set("legend", true);
    model.result("pg30").feature("lngr2").set("autosolution", false);
    model.result("pg30").feature("lngr2").set("autoexpr", true);
    model.result("pg30").feature("lngr2").set("resolution", "normal");
    model.result("pg30").feature("lngr3").set("xdataexpr", "z");
    model.result("pg30").feature("lngr3").set("xdataunit", "cm");
    model.result("pg30").feature("lngr3").set("xdatadescr", "z \u5750\u6807");
    model.result("pg30").feature("lngr3").set("linewidth", "preference");
    model.result("pg30").feature("lngr3").set("legend", true);
    model.result("pg30").feature("lngr3").set("autosolution", false);
    model.result("pg30").feature("lngr3").set("autoexpr", true);
    model.result("pg30").feature("lngr3").set("resolution", "normal");
    model.result("pg30").feature("lngr4").set("xdataexpr", "z");
    model.result("pg30").feature("lngr4").set("xdataunit", "cm");
    model.result("pg30").feature("lngr4").set("xdatadescr", "z \u5750\u6807");
    model.result("pg30").feature("lngr4").set("linewidth", "preference");
    model.result("pg30").feature("lngr4").set("legend", true);
    model.result("pg30").feature("lngr4").set("autosolution", false);
    model.result("pg30").feature("lngr4").set("autoexpr", true);
    model.result("pg30").feature("lngr4").set("resolution", "normal");
    model.result("pg30").feature("lngr5").set("xdataexpr", "z");
    model.result("pg30").feature("lngr5").set("xdataunit", "cm");
    model.result("pg30").feature("lngr5").set("xdatadescr", "z \u5750\u6807");
    model.result("pg30").feature("lngr5").set("linewidth", "preference");
    model.result("pg30").feature("lngr5").set("legend", true);
    model.result("pg30").feature("lngr5").set("autosolution", false);
    model.result("pg30").feature("lngr5").set("autoexpr", true);
    model.result("pg30").feature("lngr5").set("resolution", "normal");
    model.result("pg31").label("\u7a7a\u95f4\u5e73\u5747\u5e26\u7535\u7269\u8d28 vs. \u529f\u7387");
    model.result("pg31").set("titletype", "none");
    model.result("pg31").set("xlabel", "\u529f\u7387 (W)");
    model.result("pg31").set("xlabelactive", true);
    model.result("pg31").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg31").set("ylabelactive", true);
    model.result("pg31").set("ylog", true);
    model.result("pg31").set("legendpos", "lowerright");
    model.result("pg31").feature("glob1").set("descr", new String[]{"ne", "Ar+", "O2+", "O+", "O-"});
    model.result("pg31").feature("glob1").set("linewidth", "preference");
    model.result("pg31").feature("glob1").set("autosolution", false);
    model.result("pg19").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 2");
    model.result("pg19").feature("surf1").set("resolution", "normal");
    model.result("pg20").label("\u7535\u5b50\u6e29\u5ea6 (plas) 2");
    model.result("pg20").set("looplevel", new int[]{1, 1});
    model.result("pg20").feature("surf1").set("resolution", "normal");
    model.result("pg21").label("\u7535\u52bf (plas) 2");
    model.result("pg21").set("looplevel", new int[]{1, 1});
    model.result("pg21").feature("surf1").set("colortable", "Dipole");
    model.result("pg21").feature("surf1").set("resolution", "normal");
    model.result("pg22").label("\u901f\u5ea6 (spf) 2");
    model.result("pg22").set("looplevel", new int[]{1, 1});
    model.result("pg22").set("frametype", "spatial");
    model.result("pg22").feature("surf1").label("\u8868\u9762");
    model.result("pg22").feature("surf1").set("colortable", "Rainbow");
    model.result("pg22").feature("surf1").set("smooth", "internal");
    model.result("pg22").feature("surf1").set("resolution", "normal");
    model.result("pg23").label("\u538b\u529b (spf) 2");
    model.result("pg23").set("looplevel", new int[]{1, 1});
    model.result("pg23").set("frametype", "spatial");
    model.result("pg23").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg23").feature("con1").set("number", 40);
    model.result("pg23").feature("con1").set("levelrounding", false);
    model.result("pg23").feature("con1").set("colortable", "Rainbow");
    model.result("pg23").feature("con1").set("smooth", "internal");
    model.result("pg23").feature("con1").set("resolution", "normal");
    model.result("pg24").label("\u4e09\u7ef4\u901f\u5ea6 (spf) 2");
    model.result("pg24").set("looplevel", new int[]{1, 1});
    model.result("pg24").set("frametype", "spatial");
    model.result("pg24").feature("surf1").label("\u8868\u9762");
    model.result("pg24").feature("surf1").set("colortable", "Rainbow");

    return model;
  }

  public static Model run7(Model model) {
    model.result("pg24").feature("surf1").set("smooth", "internal");
    model.result("pg24").feature("surf1").set("resolution", "normal");
    model.result("pg25").label("\u6e29\u5ea6 (ht) 2");
    model.result("pg25").set("looplevel", new int[]{1, 1});
    model.result("pg25").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg25").feature("surf1").set("resolution", "normal");
    model.result("pg26").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 2");
    model.result("pg26").set("looplevel", new int[]{1, 1});
    model.result("pg26").set("frametype", "spatial");
    model.result("pg26").set("showlegendsmaxmin", true);
    model.result("pg26").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg26").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg26").feature("surf1").set("resolution", "normal");
    model.result("pg26").feature("str1").set("titletype", "none");
    model.result("pg26").feature("str1").set("posmethod", "uniform");
    model.result("pg26").feature("str1").set("udist", 0.03);
    model.result("pg26").feature("str1").set("maxlen", 0.4);
    model.result("pg26").feature("str1").set("maxsteps", 5000);
    model.result("pg26").feature("str1").set("inheritplot", "surf1");
    model.result("pg26").feature("str1").set("inheritcolor", false);
    model.result("pg26").feature("str1").set("resolution", "normal");
    model.result("pg26").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg26").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg26").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg26").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg26").feature("con1").set("titletype", "none");
    model.result("pg26").feature("con1").set("number", 10);
    model.result("pg26").feature("con1").set("levelrounding", false);
    model.result("pg26").feature("con1").set("coloring", "uniform");
    model.result("pg26").feature("con1").set("colorlegend", false);
    model.result("pg26").feature("con1").set("color", "custom");
    model.result("pg26").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg26").feature("con1").set("resolution", "fine");
    model.result("pg26").feature("con1").set("inheritplot", "surf1");
    model.result("pg26").feature("con1").set("inheritcolor", false);
    model.result("pg26").feature("con1").set("resolution", "fine");
    model.result("pg27").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf) 2");
    model.result("pg27").set("looplevel", new int[]{1, 1});
    model.result("pg27").set("frametype", "spatial");
    model.result("pg27").set("showlegendsmaxmin", true);
    model.result("pg27").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg27").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg27").feature("vol1").set("resolution", "normal");
    model.result("pg27").feature("con1").set("titletype", "none");
    model.result("pg27").feature("con1").set("number", 10);
    model.result("pg27").feature("con1").set("levelrounding", false);
    model.result("pg27").feature("con1").set("coloring", "uniform");
    model.result("pg27").feature("con1").set("colorlegend", false);
    model.result("pg27").feature("con1").set("color", "custom");
    model.result("pg27").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg27").feature("con1").set("resolution", "fine");
    model.result("pg27").feature("con1").set("inheritplot", "vol1");
    model.result("pg27").feature("con1").set("inheritcolor", false);
    model.result("pg27").feature("con1").set("resolution", "fine");
    model.result("pg32").label("\u7a7a\u95f4\u5e73\u5747\u5e26\u7535\u7269\u8d28 vs. xO2");
    model.result("pg32").set("looplevelinput", new String[]{"first", "all"});
    model.result("pg32").set("titletype", "none");
    model.result("pg32").set("xlabel", "xO2");
    model.result("pg32").set("xlabelactive", true);
    model.result("pg32").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg32").set("ylabelactive", true);
    model.result("pg32").set("ylog", true);
    model.result("pg32").set("legendpos", "lowermiddle");
    model.result("pg32").feature("glob1").set("descr", new String[]{"ne", "Ar+", "O2+", "O+", "O-"});
    model.result("pg32").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg32").feature("glob1").set("linewidth", "preference");
    model.result("pg32").feature("glob1").set("autosolution", false);

    model.nodeGroup("grp1").label("\u7535\u5b50\u78b0\u649e\u622a\u9762");
    model.nodeGroup("grp1").add("xsec1");
    model.nodeGroup("grp1").add("xsec2");
    model.nodeGroup("grp1").add("xsec3");
    model.nodeGroup("grp1").add("eir1");
    model.nodeGroup("grp1").add("eir2");
    model.nodeGroup("grp1").add("eir3");
    model.nodeGroup("grp1").add("eir4");
    model.nodeGroup("grp1").add("eir5");
    model.nodeGroup("grp1").add("eir6");
    model.nodeGroup("grp1").add("eir7");
    model.nodeGroup("grp1").add("eir8");
    model.nodeGroup("grp1").add("eir9");
    model.nodeGroup("grp1").add("eir10");
    model.nodeGroup("grp1").add("eir11");
    model.nodeGroup("grp1").add("eir12");
    model.nodeGroup("grp1").add("eir13");
    model.nodeGroup("grp1").add("eir14");
    model.nodeGroup("grp1").add("eir15");
    model.nodeGroup("grp1").add("eir16");
    model.nodeGroup("grp1").add("eir17");
    model.nodeGroup("grp1").add("eir18");
    model.nodeGroup("grp1").add("eir19");
    model.nodeGroup("grp1").add("eir20");
    model.nodeGroup("grp1").add("eir21");
    model.nodeGroup("grp1").add("eir22");
    model.nodeGroup("grp1").add("eir23");
    model.nodeGroup("grp1").add("eir24");
    model.nodeGroup("grp1").add("eir25");
    model.nodeGroup("grp1").add("eir26");
    model.nodeGroup("grp2").label("\u91cd\u7269\u8d28\u53cd\u5e94");
    model.nodeGroup("grp2").add("rxn1");
    model.nodeGroup("grp2").add("rxn2");
    model.nodeGroup("grp2").add("rxn3");
    model.nodeGroup("grp2").add("rxn4");
    model.nodeGroup("grp2").add("rxn5");
    model.nodeGroup("grp2").add("rxn6");
    model.nodeGroup("grp3").label("\u7269\u8d28");
    model.nodeGroup("grp3").add("e");
    model.nodeGroup("grp3").add("O2");
    model.nodeGroup("grp3").add("O");
    model.nodeGroup("grp3").add("O_1m");
    model.nodeGroup("grp3").add("O2a1Dg");
    model.nodeGroup("grp3").add("O1D");
    model.nodeGroup("grp3").add("O2_1p");
    model.nodeGroup("grp3").add("O_1p");
    model.nodeGroup("grp3").add("Ar");
    model.nodeGroup("grp3").add("Ars");
    model.nodeGroup("grp3").add("Ar_1p");
    model.nodeGroup("grp4").label("\u8868\u9762\u53cd\u5e94");
    model.nodeGroup("grp4").add("sr1");
    model.nodeGroup("grp4").add("sr2");
    model.nodeGroup("grp4").add("sr3");
    model.nodeGroup("grp4").add("sr4");
    model.nodeGroup("grp4").add("sr5");
    model.nodeGroup("grp4").add("sr6");
    model.nodeGroup("grp4").add("sr7");
    model.nodeGroup("grp4").add("sr8");
    model.nodeGroup("grp5").label("\u57fa\u7840\u6848\u4f8b");
    model.nodeGroup("grp5").add("plotgroup", "pg1");
    model.nodeGroup("grp5").add("plotgroup", "pg2");
    model.nodeGroup("grp5").add("plotgroup", "pg3");
    model.nodeGroup("grp5").add("plotgroup", "pg4");
    model.nodeGroup("grp5").add("plotgroup", "pg5");
    model.nodeGroup("grp5").add("plotgroup", "pg6");
    model.nodeGroup("grp5").add("plotgroup", "pg7");
    model.nodeGroup("grp5").add("plotgroup", "pg8");
    model.nodeGroup("grp5").add("plotgroup", "pg9");
    model.nodeGroup("grp6").label("\u529f\u7387\u626b\u63cf");
    model.nodeGroup("grp6").add("plotgroup", "pg10");
    model.nodeGroup("grp6").add("plotgroup", "pg11");
    model.nodeGroup("grp6").add("plotgroup", "pg12");
    model.nodeGroup("grp6").add("plotgroup", "pg13");
    model.nodeGroup("grp6").add("plotgroup", "pg14");
    model.nodeGroup("grp6").add("plotgroup", "pg15");
    model.nodeGroup("grp6").add("plotgroup", "pg16");
    model.nodeGroup("grp6").add("plotgroup", "pg17");
    model.nodeGroup("grp6").add("plotgroup", "pg18");
    model.nodeGroup("grp6").add("plotgroup", "pg28");
    model.nodeGroup("grp6").add("plotgroup", "pg29");
    model.nodeGroup("grp6").add("plotgroup", "pg30");
    model.nodeGroup("grp6").add("plotgroup", "pg31");
    model.nodeGroup("grp7").label("xO2 \u626b\u63cf");
    model.nodeGroup("grp7").add("plotgroup", "pg19");
    model.nodeGroup("grp7").add("plotgroup", "pg20");
    model.nodeGroup("grp7").add("plotgroup", "pg21");
    model.nodeGroup("grp7").add("plotgroup", "pg22");
    model.nodeGroup("grp7").add("plotgroup", "pg23");
    model.nodeGroup("grp7").add("plotgroup", "pg24");
    model.nodeGroup("grp7").add("plotgroup", "pg25");
    model.nodeGroup("grp7").add("plotgroup", "pg26");
    model.nodeGroup("grp7").add("plotgroup", "pg27");
    model.nodeGroup("grp7").add("plotgroup", "pg32");

    model.title("\u6c29/\u6c27\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u6a21\u578b");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u6c29/\u6c27\u6df7\u5408\u7269\u4e2d\u7684\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u95ee\u9898\uff0c\u8ba1\u7b97\u4e86\u6d41\u4f53\u6d41\u52a8\u548c\u6c14\u4f53\u52a0\u70ed\uff0c\u5e76\u8ba8\u8bba\u5bf9\u7535\u8d1f\u6027\u653e\u7535\u8fdb\u884c\u5efa\u6a21\u7684\u91cd\u8981\u65b9\u9762\u548c\u7b56\u7565\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("icp_argon_oxygen.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    run7(model);
  }

}
