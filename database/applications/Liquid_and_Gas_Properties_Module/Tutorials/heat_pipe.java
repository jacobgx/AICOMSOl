/*
 * heat_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:39 by COMSOL 6.3.0.290. */
public class heat_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Liquid_and_Gas_Properties_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("br", "PorousMediaFlowBrinkman", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/br", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_outer", "10[mm]", "\u7ba1\u5916\u5f84");
    model.param().set("w_casing", "1[mm]", "\u58f3\u539a\u5ea6");
    model.param().set("w_wick", "2[mm]", "\u82af\u539a\u5ea6");
    model.param().set("length", "150[mm]", "\u7ba1\u957f");
    model.param().set("r_inner_casing", "r_outer-w_casing", "\u58c1\u7684\u5185\u534a\u5f84");
    model.param().set("r_wick", "r_inner_casing-w_wick", "\u82af\u7684\u5185\u534a\u5f84");
    model.param().set("wick_porosity", "0.5", "\u82af\u7684\u4f53\u79ef\u7a7a\u9699\u7387");
    model.param().set("wick_permeability", "1e-9[m^2]", "\u82af\u7684\u6e17\u900f\u7387");
    model.param().set("Q_in", "30[W]", "\u70ed\u6e90\u8f7d\u8377");
    model.param().set("h_conv", "1200[W/(m^2*K)]", "\u6563\u70ed\u5668\u5bf9\u6d41\u7cfb\u6570");
    model.param().set("l_heatsink", "1[cm]", "\u5706\u5f62\u76d6\u540e\u7684\u6563\u70ed\u5668\u957f\u5ea6");
    model.param().set("l_heatsource", "1[cm]", "\u5706\u5f62\u76d6\u540e\u7684\u70ed\u6e90\u957f\u5ea6");
    model.param().set("w_contact", "1.5[mm]", "\u63a5\u89e6\u9762\u539a\u5ea6");
    model.param().set("phi_in", "Q_in/2/pi/(r_outer+w_contact)/l_heatsource", "\u6d41\u5165\u70ed\u901a\u91cf");
    model.param().set("p_ref", "1[bar]", "\u53c2\u8003\u538b\u529b");
    model.param().set("mesh_factor", "1.0", "\u7f51\u683c\u5927\u5c0f\u7f29\u653e\u56e0\u5b50");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_outer");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "length/2"});
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "w_casing", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "w_wick", 1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_outer + w_contact", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "l_heatsource", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "length/2-l_heatsource"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "w_casing + w_contact", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "w_wick", 1);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "r_outer", 0);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "length/2-l_heatsource*2"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "w_casing", 0);
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3")
         .set("size", new String[]{"r_outer", "length/2-l_heatsource*2"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, 0});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("c1", "r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 9, 13, 25, 29, 38, 42);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("mce1", 5, 7, 11, 13, 17, 19, 23, 24);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("ige2", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige2").selection("input").set("ige1", 12, 14, 18, 19);
    model.component("comp1").geom("geom1").feature("ige2").set("ignorevtx", false);
    model.component("comp1").geom("geom1").run("ige2");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input").set("ige2", 9, 10, 12, 13);
    model.component("comp1").geom("geom1").run("igv1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u84b8\u6c7d\u8154");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("igv1", 3, 4);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u591a\u5b54\u94dc\u82af");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("igv1", 2, 5);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5b9e\u5fc3\u94dc\u7ba1\u5916\u58f3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("igv1", 1, 6);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6563\u70ed\u5668");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("igv1", 21);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("igv1", 20);
    model.component("comp1").geom("geom1").feature("sel5").label("\u70ed\u6e90");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u8154\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("igv1", 5);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u82af\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("igv1", 10);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u7ba1\u5957\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("igv1", 13);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u5185\u82af\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("igv1", 8, 9);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u6240\u6709\u82af\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("igv1", 8, 9, 11, 12);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6240\u6709\u6a2a\u622a\u9762");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel6", "sel7", "sel8"});

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(1);
    model.component("comp1").view("view1").hideObjects("hide1").named("unisel1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4, 7, 8, 9, 11, 12);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1")
         .set("elemcount", "length/r_outer/mesh_factor");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "0.9*min(w_casing, w_wick)*mesh_factor");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.3*min(w_casing, w_wick)*mesh_factor");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2, 3, 4, 5, 8, 9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_sel10");
    model.component("comp1").mesh("mesh1").run();

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

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u5bc6\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("funcname", "Density_water_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Density_water_Gas11_Dtemperature", "Density_water_Gas11_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("funcname", "HeatCapacityCp_water_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_water_Gas12_Dtemperature", "HeatCapacityCp_water_Gas12_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u70ed\u5bb9\u6bd4 (Cp/Cv) 1");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "HeatCapacityRatioCpCv_water_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_water_Gas13_Dtemperature", "HeatCapacityRatioCpCv_water_Gas13_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("funcname", "ThermalConductivity_water_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_water_Gas14_Dtemperature", "ThermalConductivity_water_Gas14_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("funcname", "Viscosity_water_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_water_Gas15_Dtemperature", "Viscosity_water_Gas15_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").tag("mat_singlephase1");
    model.thermodynamics().feature("pp1").feature("mat_singlephase1").set("funcname", "Densitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase2").tag("mat_singlephase2");
    model.thermodynamics().feature("pp1").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp1");
    model.thermodynamics().feature("pp1").feature("singlephase3").tag("mat_singlephase3");
    model.thermodynamics().feature("pp1").feature("mat_singlephase3").set("funcname", "HeatCapacityRatioCpCvpp1");
    model.thermodynamics().feature("pp1").feature("singlephase4").tag("mat_singlephase4");
    model.thermodynamics().feature("pp1").feature("mat_singlephase4").set("funcname", "ThermalConductivitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase5").tag("mat_singlephase5");
    model.thermodynamics().feature("pp1").feature("mat_singlephase5").set("funcname", "Viscositypp1");
    model.thermodynamics()
         .createMaterial("Global", "pp1", "Gas", new String[]{"water"}, new String[]{"1"}, new String[]{}, new String[][]{{"density", "Densitypp1"}, {"heatcapacitycp", "HeatCapacityCppp1"}, {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp1"}, {"thermalconductivity", "ThermalConductivitypp1"}, {"viscosity", "Viscositypp1"}}, "Thermodynamics", new String[][]{{"0", "273", "373", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"68", "273", "373", "20", "101325", "201325", "15"}, 
         {"48", "273", "373", "20", "101325", "201325", "15"}, 
         {"52", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mole"});
    model.thermodynamics().feature().create("pp2", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp2")
         .set("compoundlist", new String[][]{{"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp2")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp2").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp2").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp2").set("manager_version", "1.0");
    model.thermodynamics().feature("pp2").set("packagename", "pp2");
    model.thermodynamics().feature("pp2").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp2").set("managerindex", "0");
    model.thermodynamics().feature("pp2").set("packageid", "COMSOL2");
    model.thermodynamics().feature("pp2").set("ThermodynamicModel", "Water");
    model.thermodynamics().feature("pp2").set("EOS", "IdealGas");
    model.thermodynamics().feature("pp2").set("LiquidPhaseModel", "Water");
    model.thermodynamics().feature("pp2").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp2").set("EOSModel", "Water");
    model.thermodynamics().feature("pp2").set("GasPhaseModel", "Water");
    model.thermodynamics().feature("pp2").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp2").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp2").set("VLSurfaceTension", "Water");
    model.thermodynamics().feature("pp2").set("VapThermalConductivity", "Water");
    model.thermodynamics().feature("pp2").set("VapViscosity", "Water");
    model.thermodynamics().feature("pp2").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp2").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp2").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp2").set("LiqThermalConductivity", "Water");
    model.thermodynamics().feature("pp2").set("LiqViscosity", "Water");
    model.thermodynamics().feature("pp2")
         .set("property", new String[]{"Automatic", "Water", "Water", "Water", "WesselinghKrishna", "Automatic", "None", "Water", "Water", "EOS"});
    model.thermodynamics().feature("pp2").storePersistenceData();
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp2").feature().create("tdep1", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp2").feature("tdep1").label("\u6c7d\u5316\u70ed 1");
    model.thermodynamics().feature("pp2").feature("tdep1").set("funcname", "HeatOfVaporization_water21");
    model.thermodynamics().feature("pp2").feature("tdep1").set("property", "HeatOfVaporization");
    model.thermodynamics().feature("pp2").feature("tdep1").set("propertydescr", "\u6c7d\u5316\u70ed");
    model.thermodynamics().feature("pp2").feature("tdep1").set("unit", "J/kg");
    model.thermodynamics().feature("pp2").feature("tdep1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("tdep1").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("tdep1").comments("Steam tables");
    model.thermodynamics().feature("pp2").feature("tdep1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[273.15 ,647.10]"}});
    model.thermodynamics().feature("pp2").feature("tdep1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp2").feature("tdep1")
         .set("derivatives", new String[]{"HeatOfVaporization_water21_Dtemperature"});
    model.thermodynamics().feature("pp2").feature("tdep1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("tdep1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature().create("tdep2", "TemperatureDependentProperty");
    model.thermodynamics().feature("pp2").feature("tdep2").label("Ln \u84b8\u6c7d\u538b 1");
    model.thermodynamics().feature("pp2").feature("tdep2").set("funcname", "LnVaporPressure_water22");
    model.thermodynamics().feature("pp2").feature("tdep2").set("property", "LnVaporPressure");
    model.thermodynamics().feature("pp2").feature("tdep2").set("propertydescr", "Ln \u84b8\u6c7d\u538b");
    model.thermodynamics().feature("pp2").feature("tdep2").set("unit", "1");
    model.thermodynamics().feature("pp2").feature("tdep2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("tdep2").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("tdep2").comments("Refitted to IAPWS");

    return model;
  }

  public static Model run2(Model model) {
    model.thermodynamics().feature("pp2").feature("tdep2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6", "[273.15 ,647.10]"}});
    model.thermodynamics().feature("pp2").feature("tdep2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}});
    model.thermodynamics().feature("pp2").feature("tdep2")
         .set("derivatives", new String[]{"LnVaporPressure_water22_Dtemperature"});
    model.thermodynamics().feature("pp2").feature("tdep2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("tdep2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u6c34\u7684\u84b8\u6c7d\u538b");
    model.func("an1").set("funcname", "pH2O");
    model.func("an1").set("expr", "exp(LnVaporPressure_water22(T))");
    model.func("an1").set("args", "T");
    model.func("an1").setIndex("argunit", "K", 0);
    model.func("an1").set("fununit", "Pa");
    model.func("an1").setIndex("plotargs", 273.15, 0, 1);
    model.func("an1").setIndex("plotargs", 373.15, 0, 2);

    model.thermodynamics().feature("pp2").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase1").label("\u5bc6\u5ea6 1");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("funcname", "Density_water_Liquid21");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp2").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase1")
         .set("derivatives", new String[]{"Density_water_Liquid21_Dtemperature", "Density_water_Liquid21_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase2").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("funcname", "HeatCapacityCp_water_Liquid22");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp2").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_water_Liquid22_Dtemperature", "HeatCapacityCp_water_Liquid22_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase3").label("\u70ed\u5bb9\u6bd4 (Cp/Cv) 1");
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("funcname", "HeatCapacityRatioCpCv_water_Liquid23");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("unit", "1");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp2").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_water_Liquid23_Dtemperature", "HeatCapacityRatioCpCv_water_Liquid23_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase4").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("funcname", "ThermalConductivity_water_Liquid24");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp2").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_water_Liquid24_Dtemperature", "ThermalConductivity_water_Liquid24_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp2").feature("singlephase5").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("funcname", "Viscosity_water_Liquid25");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("prop_basis", "mass");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("phase", "Liquid");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp2").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp2").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_water_Liquid25_Dtemperature", "Viscosity_water_Liquid25_Dpressure"});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp2").feature("singlephase1").tag("mat_singlephase1");
    model.thermodynamics().feature("pp2").feature("mat_singlephase1").set("funcname", "Densitypp2");
    model.thermodynamics().feature("pp2").feature("singlephase2").tag("mat_singlephase2");
    model.thermodynamics().feature("pp2").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp2");
    model.thermodynamics().feature("pp2").feature("singlephase3").tag("mat_singlephase3");
    model.thermodynamics().feature("pp2").feature("mat_singlephase3").set("funcname", "HeatCapacityRatioCpCvpp2");
    model.thermodynamics().feature("pp2").feature("singlephase4").tag("mat_singlephase4");
    model.thermodynamics().feature("pp2").feature("mat_singlephase4").set("funcname", "ThermalConductivitypp2");
    model.thermodynamics().feature("pp2").feature("singlephase5").tag("mat_singlephase5");
    model.thermodynamics().feature("pp2").feature("mat_singlephase5").set("funcname", "Viscositypp2");
    model.thermodynamics()
         .createMaterial("Global", "pp2", "Liquid", new String[]{"water"}, new String[]{"1"}, new String[]{}, new String[][]{{"density", "Densitypp2"}, {"heatcapacitycp", "HeatCapacityCppp2"}, {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp2"}, {"thermalconductivity", "ThermalConductivitypp2"}, {"viscosity", "Viscositypp2"}}, "Thermodynamics", new String[][]{{"0", "273", "373", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"68", "273", "373", "20", "101325", "201325", "15"}, 
         {"48", "273", "373", "20", "101325", "201325", "15"}, 
         {"52", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mole"});

    model.material().create("sw1", "Switch", "");
    model.material("sw1").label("\u7ba1\u82af\u4e2d\u7684\u6d41\u4f53");
    model.material("sw1").feature().copy("pp2mat1", "pp2mat1", "");
    model.material("sw1").feature().copy("pp1mat1", "pp1mat1", "");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().named("geom1_sel2");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "sw1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-wick_porosity");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6c34\u84b8\u6c14");
    model.component("comp1").material("matlnk1").selection().named("geom1_sel1");
    model.component("comp1").material("matlnk1").set("link", "pp1mat1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u94dc\u91d1\u5c5e");
    model.component("comp1").material("matlnk2").selection().named("geom1_sel3");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics().create("nitf2", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf2").set("Fluid_physics", "br");

    model.component("comp1").physics("spf").selection().named("geom1_sel1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel9");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "pH2O(T)-p_ref");
    model.component("comp1").physics("spf").feature("inl1").set("SuppressBackflow", false);
    model.component("comp1").physics("spf").feature("init1").set("p_init", "p_ref");
    model.component("comp1").physics("br").selection().named("geom1_sel2");
    model.component("comp1").physics("br").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("br").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("br").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("br").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"wick_permeability", "0", "0", "0", "wick_permeability", "0", "0", "0", "wick_permeability"});
    model.component("comp1").physics("br").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("br").feature("inl1").selection().named("geom1_sel9");
    model.component("comp1").physics("br").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("br").feature("inl1")
         .set("u0", new String[]{"u*spf.rho/br.rho", "0", "w*spf.rho/br.rho"});
    model.component("comp1").physics("br").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("br").feature("prpc1").selection().set(9);
    model.component("comp1").physics("br").feature("prpc1").set("p0", "pH2O(T)-p_ref");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").create("fluid1", "FluidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_sel5");
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "phi_in");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_sel4");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_conv");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").selection().named("geom1_sel9");
    model.component("comp1").physics("ht").feature("bhs1")
         .set("Qb_input", "(u*spf.nr+w*spf.nz)*HeatOfVaporization_water21(T)*spf.rho");

    model.study("std1").label("\u7814\u7a76 1 - \u5e72\u71e5\u7ba1\u82af");
    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "user", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", 2, 0);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/br", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol2");
    model.batch("pm1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "T");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "nitf1.T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(3, 4);
    model.result("pg2").feature().create("surf2", "Surface");
    model.result("pg2").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("expr", "nitf1.T");
    model.result("pg2").feature("surf2").set("smooth", "internal");
    model.result("pg2").feature("surf2").set("showsolutionparams", "on");
    model.result("pg2").feature("surf2").set("data", "parent");
    model.result("pg2").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf2").feature("sel1").selection().set(1, 6);
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature().create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg2").feature("arws1").set("showsolutionparams", "on");
    model.result("pg2").feature("arws1").set("solutionparams", "parent");
    model.result("pg2").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg2").feature("arws1").set("xnumber", 30);
    model.result("pg2").feature("arws1").set("ynumber", 30);
    model.result("pg2").feature("arws1").set("arrowtype", "cone");
    model.result("pg2").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arws1").set("showsolutionparams", "on");
    model.result("pg2").feature("arws1").set("data", "parent");
    model.result("pg2").feature("arws1").feature().create("col1", "Color");
    model.result("pg2").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "nitf2.T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("surf1").feature("sel1").selection().set(2, 5);
    model.result("pg3").feature().create("surf2", "Surface");
    model.result("pg3").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg3").feature("surf2").set("showsolutionparams", "on");
    model.result("pg3").feature("surf2").set("solutionparams", "parent");
    model.result("pg3").feature("surf2").set("expr", "nitf2.T");
    model.result("pg3").feature("surf2").set("smooth", "internal");
    model.result("pg3").feature("surf2").set("showsolutionparams", "on");
    model.result("pg3").feature("surf2").set("data", "parent");
    model.result("pg3").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("surf2").feature("sel1").selection().set(1, 6);
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature().create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg3").feature("arws1").set("showsolutionparams", "on");
    model.result("pg3").feature("arws1").set("solutionparams", "parent");
    model.result("pg3").feature("arws1").set("expr", new String[]{"nitf2.ur", "nitf2.uz"});
    model.result("pg3").feature("arws1").set("xnumber", 30);
    model.result("pg3").feature("arws1").set("ynumber", 30);
    model.result("pg3").feature("arws1").set("arrowtype", "cone");
    model.result("pg3").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arws1").set("showsolutionparams", "on");
    model.result("pg3").feature("arws1").set("data", "parent");
    model.result("pg3").feature("arws1").feature().create("col1", "Color");
    model.result("pg3").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg3").feature("arws1").feature("col1").set("expr", "br.U");
    model.result("pg3").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg3").feature("arws1").feature("filt1").set("expr", "br.U>nitf2.Uave");
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6 - \u5e72\u71e5\u7ba1\u82af");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6e29\u5ea6");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "\u00b0C");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/br", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf2", true);
    model.study("std2").label("\u7814\u7a76 2 - \u9971\u548c\u7ba1\u82af");
    model.study("std2").create("matsw", "MaterialSweep");
    model.study("std2").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "user", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", 1, 0);
    model.study("std2").feature().duplicate("stat1", "stat");
    model.study("std2").feature("stat").setSolveFor("/physics/br", false);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol().create("sol6");
    model.sol("sol6").study("std2");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pm2").feature("so1").set("psol", "sol6");
    model.batch("pm2").getInitialValue();

    model.result().dataset("dset5").set("geom", "geom1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset5");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().dataset("dset5").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (br)");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "br.U");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");

    return model;
  }

  public static Model run3(Model model) {
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (br)");
    model.result("pg8").set("data", "dset5");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").setIndex("looplevel", 1, 1);
    model.result("pg8").set("dataisaxisym", "off");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p2");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset5");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u4e09\u7ef4\u901f\u5ea6 (br)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "br.U");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6e29\u5ea6 (ht)");
    model.result("pg10").set("data", "dset5");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").setIndex("looplevel", 1, 1);
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg11").set("data", "dset5");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").setIndex("looplevel", 1, 1);
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "nitf1.T");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf1").feature("sel1").selection().set(3, 4);
    model.result("pg11").feature().create("surf2", "Surface");
    model.result("pg11").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg11").feature("surf2").set("showsolutionparams", "on");
    model.result("pg11").feature("surf2").set("solutionparams", "parent");
    model.result("pg11").feature("surf2").set("expr", "nitf1.T");
    model.result("pg11").feature("surf2").set("smooth", "internal");
    model.result("pg11").feature("surf2").set("showsolutionparams", "on");
    model.result("pg11").feature("surf2").set("data", "parent");
    model.result("pg11").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf2").feature("sel1").selection().set(1, 6);
    model.result("pg11").feature("surf2").set("inheritplot", "surf1");
    model.result("pg11").feature().create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("solutionparams", "parent");
    model.result("pg11").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg11").feature("arws1").set("xnumber", 30);
    model.result("pg11").feature("arws1").set("ynumber", 30);
    model.result("pg11").feature("arws1").set("arrowtype", "cone");
    model.result("pg11").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("data", "parent");
    model.result("pg11").feature("arws1").feature().create("col1", "Color");
    model.result("pg11").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg11").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg11").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2) 1");
    model.result("pg12").set("data", "dset5");
    model.result("pg12").setIndex("looplevel", 1, 0);
    model.result("pg12").setIndex("looplevel", 1, 1);
    model.result("pg12").set("dataisaxisym", "off");
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("solutionparams", "parent");
    model.result("pg12").feature("surf1").set("expr", "nitf2.T");
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg12").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg12").feature("surf1").feature("sel1").selection().set(2, 5);
    model.result("pg12").feature().create("surf2", "Surface");
    model.result("pg12").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg12").feature("surf2").set("showsolutionparams", "on");
    model.result("pg12").feature("surf2").set("solutionparams", "parent");
    model.result("pg12").feature("surf2").set("expr", "nitf2.T");
    model.result("pg12").feature("surf2").set("smooth", "internal");
    model.result("pg12").feature("surf2").set("showsolutionparams", "on");
    model.result("pg12").feature("surf2").set("data", "parent");
    model.result("pg12").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg12").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg12").feature("surf2").feature("sel1").selection().set(1, 6);
    model.result("pg12").feature("surf2").set("inheritplot", "surf1");
    model.result("pg12").feature().create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg12").feature("arws1").set("showsolutionparams", "on");
    model.result("pg12").feature("arws1").set("solutionparams", "parent");
    model.result("pg12").feature("arws1").set("expr", new String[]{"nitf2.ur", "nitf2.uz"});
    model.result("pg12").feature("arws1").set("xnumber", 30);
    model.result("pg12").feature("arws1").set("ynumber", 30);
    model.result("pg12").feature("arws1").set("arrowtype", "cone");
    model.result("pg12").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("arws1").set("showsolutionparams", "on");
    model.result("pg12").feature("arws1").set("data", "parent");
    model.result("pg12").feature("arws1").feature().create("col1", "Color");
    model.result("pg12").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg12").feature("arws1").feature("col1").set("expr", "br.U");
    model.result("pg12").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg12").feature("arws1").feature("filt1").set("expr", "br.U>nitf2.Uave");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6 (spf) \u548c\u6e29\u5ea6 (ht)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6d41\u4f53\u901f\u5ea6\u548c\u6e29\u5ea6");
    model.result("pg4").set("paramindicator", "Fluid in Wick = Liquid : water");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").label("\u8868\u9762 1 - \u6d41\u4f53\u901f\u5ea6\uff0clg(|u|)");
    model.result("pg4").feature("surf1").set("expr", "log10(ht.uz^2 + ht.ur^2)/2");
    model.result("pg4").feature("surf1").set("colortable", "Prionace");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature().duplicate("arws2", "arws1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").label("\u8868\u9762 2 - \u6e29\u5ea6");
    model.result("pg4").feature("surf2").set("expr", "T");
    model.result("pg4").feature("surf2").set("colortable", "HeatCamera");
    model.result("pg4").feature("surf2").set("colortabletrans", "none");
    model.result("pg4").feature("surf2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1").set("expr", new String[]{"r_outer", ""});
    model.result("pg4").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf2").feature("def1").set("scale", 3);
    model.result("pg4").run();
    model.result("pg4").feature("arws1").label("\u9762\u4e0a\u7bad\u5934 1 - \u84b8\u6c7d\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("xnumber", 9);
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", 0.0035);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").feature("arws2").label("\u9762\u4e0a\u7bad\u5934 2 - \u6db2\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws2").set("expr", new String[]{"u2", "w2"});
    model.result("pg4").feature("arws2").set("xnumber", 5);
    model.result("pg4").feature("arws2").set("scaleactive", true);
    model.result("pg4").feature("arws2").set("scale", 50);
    model.result("pg4").feature("arws2").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").label("\u7ebf 1 - \u6750\u6599\u8fb9\u754c");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("line1").feature("def1").set("expr", new String[]{"r_outer", ""});
    model.result("pg4").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("line1").feature("def1").set("scale", 3);

    model.sol("sol4").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol4").feature("s1").feature("fc1").set("plot", true);
    model.sol("sol4").feature("s1").feature("fc1").set("plotgroup", "pg4");
    model.sol("sol4").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol4").feature("s2").feature("fc1").set("plot", true);
    model.sol("sol4").feature("s2").feature("fc1").set("plotgroup", "pg4");

    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u80fd\u91cf\u5e73\u8861");
    model.result().evaluationGroup("eg1").set("data", "dset5");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("int1", "IntLine");
    model.result().evaluationGroup("eg1").feature("int1").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("int1").label("\u6563\u70ed\u5668");
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_sel4");
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"ht.ndflux"});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("descr", new String[]{"\u6cd5\u5411\u4f20\u5bfc\u70ed\u901a\u91cf"});
    model.result().evaluationGroup("eg1").feature("int1").set("unit", new String[]{"W"});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u6c47\uff1andflux", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").label("\u70ed\u6e90");
    model.result().evaluationGroup("eg1").feature("int2").selection().named("geom1_sel5");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "\u6e90\uff1andflux", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int3").label("\u5916\u58f3");
    model.result().evaluationGroup("eg1").feature("int3").selection().named("geom1_sel8");
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "\u7ba1\u5957\uff1andflux", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int4", "int3");
    model.result().evaluationGroup("eg1").feature("int4").label("\u7ba1\u82af");
    model.result().evaluationGroup("eg1").feature("int4").selection().named("geom1_sel7");
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "\u7ba1\u82af\uff1andflux", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int5", "int4");
    model.result().evaluationGroup("eg1").feature("int5").label("\u8154\u4f53");
    model.result().evaluationGroup("eg1").feature("int5").selection().named("geom1_sel6");
    model.result().evaluationGroup("eg1").feature("int5").setIndex("descr", "\u8154\u4f53\uff1andflux", 0);
    model.result().evaluationGroup("eg1").feature("int5")
         .setIndex("expr", "w*spf.rho*HeatOfVaporization_water21(T)", 1);
    model.result().evaluationGroup("eg1").feature("int5").setIndex("descr", "\u8154\u4f53\uff1a\u6f5c\u70ed", 1);
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u76f8\u53d8\u5f15\u8d77\u7684\u6f5c\u70ed\u901a\u91cf");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").set("data", "dset5");
    model.result("pg13").feature("lngr1").selection().named("geom1_sel9");
    model.result("pg13").feature("lngr1").set("expr", "(u*spf.nr + w*spf.nz)*spf.rho*HeatOfVaporization_water21(T)");
    model.result("pg13").feature("lngr1").set("descractive", true);
    model.result("pg13").feature("lngr1").set("descr", "(<B>u</B>\\cdot<B>n</B>)\\rho\\DELTA H<sub>vap</sub>");
    model.result("pg13").feature("lngr1").set("titletype", "manual");
    model.result("pg13").feature("lngr1").set("title", "\u76f8\u53d8\u5f15\u8d77\u7684\u6f5c\u70ed\u901a\u91cf");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "z");

    model.study("std2").createAutoSequences("all");

    model.batch("pm2").run("compute");

    model.result("pg4").run();
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6e29\u5ea6");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").set("startangle", 135.1);
    model.result().dataset("rev2").set("revangle", 134.8);
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u4e09\u7ef4\u6e29\u5ea6");
    model.result("pg14").set("data", "rev2");
    model.result("pg14").set("edges", false);
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").create("iso1", "Isosurface");
    model.result("pg14").feature("iso1").set("expr", "T");
    model.result("pg14").feature("iso1").set("number", 20);
    model.result("pg14").feature("iso1").set("colortable", "HeatCamera");
    model.result("pg14").run();
    model.result("pg14").create("vol1", "Volume");
    model.result("pg14").feature("vol1").set("expr", "T");
    model.result("pg14").feature("vol1").set("inheritplot", "iso1");
    model.result("pg14").feature("vol1").create("tran1", "Transparency");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "1");
    model.result("pg14").feature("surf1").set("data", "rev1");
    model.result("pg14").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg14").feature("surf1").feature("mtrl1").set("family", "copper");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().set(1, 6);
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg7");
    model.result().remove("pg11");
    model.result().remove("pg12");
    model.result("pg4").run();
    model.result("pg14").run();

    model.title("\u5177\u6709\u7cbe\u786e\u6c14\u6db2\u5c5e\u6027\u7684\u70ed\u7ba1");

    model
         .description("\u70ed\u7ba1\u901a\u8fc7\u5de5\u4f5c\u6d41\u4f53\u7684\u84b8\u53d1\u3001\u8d28\u91cf\u4f20\u9012\u548c\u51b7\u51dd\u6709\u6548\u5730\u4f20\u9012\u70ed\u91cf\uff0c\u88ab\u5e7f\u6cdb\u7528\u4e8e\u70ed\u63a7\u5236\u5177\u6709\u91cd\u8981\u610f\u4e49\u7684\u5404\u79cd\u5e94\u7528\u4e2d\uff0c\u7535\u5b50\u51b7\u5374\u4fbf\u662f\u4e00\u4e2a\u7a81\u51fa\u7684\u4f8b\u5b50\u3002\n\n\u8be5\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06\u84b8\u6c7d\u5ba4\u4e2d\u7684\u5c42\u6d41\u4e0e\u591a\u5b54\u82af\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u8fdb\u884c\u8026\u5408\uff0c\u4ee5\u53ca\u5982\u4f55\u4ece\u201c\u70ed\u529b\u5b66\u201d\u8282\u70b9\u83b7\u53d6\u6c34\u7684\u70ed\u529b\u5b66\u5c5e\u6027\u3002\u51b7\u70ed\u4fa7\u4e4b\u95f4\u7684\u6e29\u5dee\u52a0\u4e0a\u84b8\u6c7d\u538b\u7684\u6e29\u5ea6\u4f9d\u5b58\u6027\uff0c\u4f1a\u5728\u6574\u4e2a\u84b8\u6c7d\u5ba4\u5f15\u8d77\u538b\u5dee\u3002\u84b8\u53d1\u5728\u70ed\u4fa7\u7684\u84b8\u6c7d/\u82af\u754c\u9762\u5904\u5145\u5f53\u6563\u70ed\u5668\uff1b\u76f8\u53cd\uff0c\u51b7\u51dd\u5728\u51b7\u4fa7\u5145\u5f53\u70ed\u6e90\u3002\n\n\u672c\u4f8b\u5c06\u84b8\u6c7d\u8f93\u9001\u7684\u91cd\u8981\u6027\u4e0e\u7ba1\u58c1\u4e2d\u7684\u4f20\u5bfc\u4f20\u70ed\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u663e\u793a\u524d\u8005\u9ad8\u4e8e\u540e\u8005\u51e0\u4e2a\u6570\u91cf\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("heat_pipe.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
