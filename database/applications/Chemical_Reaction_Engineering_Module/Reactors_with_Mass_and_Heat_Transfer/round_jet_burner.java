/*
 * round_jet_burner.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:23 by COMSOL 6.3.0.290. */
public class round_jet_burner {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp1").physics("tcs").prop("AdvancedSettings").set("UsePseudoTime", "1");
    model.component("comp1").physics("tcs").field("massfraction").field("wCO");
    model.component("comp1").physics("tcs").field("massfraction")
         .component(new String[]{"wCO", "wO2", "wCO2", "wH2", "wH2O", "wN2"});
    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 2);
    model.component("comp1").multiphysics("nirf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nirf1").set("Species_physics", "tcs");
    model.component("comp1").multiphysics("nirf1").set("Chemistry_physics", "chem");
    model.component("comp1").multiphysics("nirf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nirf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Di", "4.58[mm]", "\u7ba1\u5185\u5f84");
    model.param().set("Pth", "0.88[mm]", "\u7ba1\u539a\u5ea6");
    model.param().set("Pl", "Di*10", "\u7ba1\u957f");
    model.param().set("GeomW", "Di*50", "\u6a21\u62df\u7684\u51e0\u4f55\u5bbd\u5ea6");
    model.param().set("GeomH", "Pl+Di*120", "\u6a21\u62df\u7684\u51e0\u4f55\u9ad8\u5ea6");
    model.param().set("Ujet", "76[m/s]", "\u55b7\u5c04\u901f\u5ea6");
    model.param().set("Ucf", "0.7[m/s]", "\u534f\u6d41\u901f\u5ea6");
    model.param().set("T0", "292[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("x0_CO", "0.4", "CO \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("x0_O2", "0", "O2 \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("x0_CO2", "0", "CO2 \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("x0_H2", "0.3", "H2 \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("x0_H2O", "0", "H2O \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("x0_N2", "0.3", "N2 \u7684\u5165\u53e3\u4f53\u79ef\u5206\u6570");
    model.param().set("wcf_O2", "0.21", "O2 \u7684\u534f\u6d41\u8d28\u91cf\u5206\u6570");
    model.param().set("mu_mix", "1e-5[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6df7\u5408\u7269");
    model.param().set("k_mix", "0.1[W/m/K]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u6df7\u5408\u7269");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"GeomW", "GeomH"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Pth", "Pl"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Di/2", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("pointinsketch").set("r2", 3, 4);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", "Pth*0.15");
    model.component("comp1").geom("geom1").run("cha1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "GeomW GeomW*1.5 GeomW*1.5 GeomW");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 GeomH GeomH GeomH");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pol1", "r1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cha1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0", "Pl-0.15*Pth"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"Di/2", "Pl-0.15*Pth"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"Di/2+Pth", "Pl-0.15*Pth"});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord2", new String[]{"GeomW+0.5*(Pl-0.15*Pth)*GeomW/GeomH", "0"});
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord2", "Pl-0.15*Pth", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"Di/2", "Pl-0.15*Pth"});
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3")
         .set("coord2", new String[]{"Di/2+(GeomH-Pl+Pth*0.15)*tan(pi/180)", "0"});
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord2", "GeomH", 1);
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls4").set("coord1", new String[]{"Di/2+Pth", "Pl-0.15*Pth"});
    model.component("comp1").geom("geom1").feature("ls4").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls4")
         .set("coord2", new String[]{"Di/2+Pth+(GeomH-Pl+Pth*0.15)*tan(5*pi/180)", "0"});
    model.component("comp1").geom("geom1").feature("ls4").setIndex("coord2", "GeomH", 1);
    model.component("comp1").geom("geom1").run("ls4");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 8, 13, 14);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("cme1", "CompositeEdges");
    model.component("comp1").geom("geom1").feature("cme1").selection("input").set("mce1", 3, 11);
    model.component("comp1").geom("geom1").run("cme1");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"water", "7732-18-5", "H2O", "COMSOL"}, 
         {"carbon monoxide", "630-08-0", "CO", "COMSOL"}, 
         {"carbon dioxide", "124-38-9", "CO2", "COMSOL"}, 
         {"nitrogen", "7727-37-9", "N2", "COMSOL"}, 
         {"oxygen", "7782-44-7", "O2", "COMSOL"}, 
         {"hydrogen", "1333-74-0", "H2", "COMSOL"}});
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
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "HeatCapacityCp_carbon_dioxide_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"HeatCapacityCp_carbon_dioxide_Gas11_Dtemperature", "HeatCapacityCp_carbon_dioxide_Gas11_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u751f\u6210\u7113 1");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("funcname", "EnthalpyF_carbon_dioxide_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"EnthalpyF_carbon_dioxide_Gas12_Dtemperature", "EnthalpyF_carbon_dioxide_Gas12_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u70ed\u5bb9 (Cp) 2");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "HeatCapacityCp_carbon_monoxide_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("compounds", new String[]{"carbon monoxide"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityCp_carbon_monoxide_Gas13_Dtemperature", "HeatCapacityCp_carbon_monoxide_Gas13_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u751f\u6210\u7113 2");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("funcname", "EnthalpyF_carbon_monoxide_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("compounds", new String[]{"carbon monoxide"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"EnthalpyF_carbon_monoxide_Gas14_Dtemperature", "EnthalpyF_carbon_monoxide_Gas14_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5").label("\u70ed\u5bb9 (Cp) 3");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("funcname", "HeatCapacityCp_hydrogen_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("compounds", new String[]{"hydrogen"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"HeatCapacityCp_hydrogen_Gas15_Dtemperature", "HeatCapacityCp_hydrogen_Gas15_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase6", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase6").label("\u751f\u6210\u7113 3");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("funcname", "EnthalpyF_hydrogen_Gas16");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("compounds", new String[]{"hydrogen"});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("derivatives", new String[]{"EnthalpyF_hydrogen_Gas16_Dtemperature", "EnthalpyF_hydrogen_Gas16_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase7", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase7").label("\u70ed\u5bb9 (Cp) 4");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("funcname", "HeatCapacityCp_nitrogen_Gas17");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("compounds", new String[]{"nitrogen"});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("derivatives", new String[]{"HeatCapacityCp_nitrogen_Gas17_Dtemperature", "HeatCapacityCp_nitrogen_Gas17_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase8", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase8").label("\u751f\u6210\u7113 4");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("funcname", "EnthalpyF_nitrogen_Gas18");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("compounds", new String[]{"nitrogen"});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("derivatives", new String[]{"EnthalpyF_nitrogen_Gas18_Dtemperature", "EnthalpyF_nitrogen_Gas18_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase9", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase9").label("\u70ed\u5bb9 (Cp) 5");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("funcname", "HeatCapacityCp_oxygen_Gas19");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("compounds", new String[]{"oxygen"});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("derivatives", new String[]{"HeatCapacityCp_oxygen_Gas19_Dtemperature", "HeatCapacityCp_oxygen_Gas19_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase10", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase10").label("\u751f\u6210\u7113 5");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("funcname", "EnthalpyF_oxygen_Gas110");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("compounds", new String[]{"oxygen"});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("derivatives", new String[]{"EnthalpyF_oxygen_Gas110_Dtemperature", "EnthalpyF_oxygen_Gas110_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase11", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase11").label("\u70ed\u5bb9 (Cp) 6");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("funcname", "HeatCapacityCp_water_Gas111");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase11").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase11").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase11")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase11")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase11")
         .set("derivatives", new String[]{"HeatCapacityCp_water_Gas111_Dtemperature", "HeatCapacityCp_water_Gas111_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase11").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase11").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase12", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase12").label("\u751f\u6210\u7113 6");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("funcname", "EnthalpyF_water_Gas112");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("compounds", new String[]{"water"});
    model.thermodynamics().feature("pp1").feature("singlephase12").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase12").set("comp_basis", "mole");

    return model;
  }

  public static Model run2(Model model) {
    model.thermodynamics().feature("pp1").feature("singlephase12").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase12")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase12")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase12")
         .set("derivatives", new String[]{"EnthalpyF_water_Gas112_Dtemperature", "EnthalpyF_water_Gas112_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase12").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase12").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "2 CO + O2 => 2 CO2");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "2 H2 + O2 => 2 H2O");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "N2");
    model.component("comp1").physics("chem").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wCO", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "carbon monoxide", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wCO2", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "carbon dioxide", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wH2", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "hydrogen", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wH2O", 3, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "water", 3, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wN2", 4, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "nitrogen", 4, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wO2", 5, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "oxygen", 5, 0);
    model.component("comp1").physics("chem").prop("calcTransport").set("thermalConductivitySel", "UserDefined");
    model.component("comp1").physics("chem").prop("calcTransport").set("k", "k_mix");
    model.component("comp1").physics("chem").prop("calcTransport").set("dynamicViscositySel", "UserDefined");
    model.component("comp1").physics("chem").prop("calcTransport").set("eta", "mu_mix");
    model.component("comp1").physics("chem").feature("rch1").set("TurbulentReactionModel", "EddyDissipation");
    model.component("comp1").physics("chem").feature("rch1").set("infinitelyFastReaction", true);
    model.component("comp1").physics("chem").feature("rch1").set("reactionRateRegularization", true);
    model.component("comp1").physics("chem").feature("rch2").set("TurbulentReactionModel", "EddyDissipation");
    model.component("comp1").physics("chem").feature("rch2").set("infinitelyFastReaction", true);
    model.component("comp1").physics("chem").feature("rch2").set("reactionRateRegularization", true);
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "FicksLaw");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 6);
    model.component("comp1").physics("tcs").create("reac1", "ReactionSources", 2);
    model.component("comp1").physics("tcs").feature("reac1").selection().set(1);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wCO_src", "root.comp1.chem.Rw_CO", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wO2_src", "root.comp1.chem.Rw_O2", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wCO2_src", "root.comp1.chem.Rw_CO2", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wH2_src", "root.comp1.chem.Rw_H2", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wH2O_src", "root.comp1.chem.Rw_H2O", 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "wcf_O2", 1);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 2);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 3);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", 0, 4);
    model.component("comp1").physics("tcs").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcs").feature("in1").selection().set(2);
    model.component("comp1").physics("tcs").feature("in1").set("MixtureSpecification", "MoleFractions");
    model.component("comp1").physics("tcs").feature("in1").setIndex("x0", "x0_CO", 0);
    model.component("comp1").physics("tcs").feature("in1").setIndex("x0", "x0_O2", 1);
    model.component("comp1").physics("tcs").feature("in1").setIndex("x0", "x0_CO2", 2);
    model.component("comp1").physics("tcs").feature("in1").setIndex("x0", "x0_H2", 3);
    model.component("comp1").physics("tcs").feature("in1").setIndex("x0", "x0_H2O", 4);
    model.component("comp1").physics("tcs").create("in2", "Inflow", 1);
    model.component("comp1").physics("tcs").feature("in2").setIndex("wbnd", "1e-5", 0);
    model.component("comp1").physics("tcs").feature("in2").setIndex("wbnd", "wcf_O2", 1);
    model.component("comp1").physics("tcs").feature("in2").setIndex("wbnd", "1e-5", 2);
    model.component("comp1").physics("tcs").feature("in2").setIndex("wbnd", "1e-5", 3);
    model.component("comp1").physics("tcs").feature("in2").setIndex("wbnd", "1e-5", 4);
    model.component("comp1").physics("tcs").feature("in2").selection().set(9, 10);
    model.component("comp1").physics("tcs").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcs").feature("out1").selection().set(3);
    model.component("comp1").physics("spf").feature("fp1").set("editModelInputs", true);
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "root.comp1.chem.rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "root.comp1.chem.eta");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Ujet");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(9, 10);
    model.component("comp1").physics("spf").feature("inl2").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl2").set("u0", new String[]{"0", "0", "Ucf"});
    model.component("comp1").physics("spf").feature("inl2").set("IT_list", "low_turbulent_intensity");
    model.component("comp1").physics("spf").feature("inl2").set("LT_list", "user_defined");
    model.component("comp1").physics("spf").feature("inl2").set("LT", "0.1*Di");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").feature("fluid1").set("editModelInputs", true);
    model.component("comp1").physics("ht").feature("fluid1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.chem.krr");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "root.comp1.chem.rho");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "root.comp1.chem.Cptot");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(2, 9, 10);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(11, 16, 17);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 250);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(9, 18);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 400);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 4, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").selection().set(3, 13);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdamp", "0.40");

    model.thermodynamics().feature("pp1").feature("singlephase2").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase2").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u751f\u6210\u7113");
    model.result("pg1").set("savedatainmodel", true);

    model.thermodynamics().feature("pp1").feature("singlephase4").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase4").createPlot("pg2");

    model.result("pg2").run();

    model.thermodynamics().feature("pp1").feature("singlephase6").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase6").createPlot("pg3");

    model.result("pg3").run();

    model.thermodynamics().feature("pp1").feature("singlephase8").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase8").createPlot("pg4");

    model.result("pg4").run();

    model.thermodynamics().feature("pp1").feature("singlephase10").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase10").createPlot("pg5");

    model.result("pg5").run();

    model.thermodynamics().feature("pp1").feature("singlephase12").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase12").createPlot("pg6");

    model.result("pg6").run();
    model.result("pg2").run();
    model.result("pg1").feature().copy("plot2", "pg2/plot1");
    model.result("pg2").feature().remove("plot1");
    model.result("pg1").feature("plot2").set("outerinput", "all");
    model.result("pg1").feature("plot2").set("innerinput", "all");
    model.result("pg1").feature("plot2").set("titletype", "custom");
    model.result("pg3").run();
    model.result("pg1").feature().copy("plot3", "pg3/plot1");
    model.result("pg3").feature().remove("plot1");
    model.result("pg1").feature("plot3").set("outerinput", "all");
    model.result("pg1").feature("plot3").set("innerinput", "all");
    model.result("pg1").feature("plot3").set("titletype", "custom");
    model.result("pg4").run();
    model.result("pg1").feature().copy("plot4", "pg4/plot1");
    model.result("pg4").feature().remove("plot1");
    model.result("pg1").feature("plot4").set("outerinput", "all");
    model.result("pg1").feature("plot4").set("innerinput", "all");
    model.result("pg1").feature("plot4").set("titletype", "custom");
    model.result("pg5").run();
    model.result("pg1").feature().copy("plot5", "pg5/plot1");
    model.result("pg5").feature().remove("plot1");
    model.result("pg1").feature("plot5").set("outerinput", "all");
    model.result("pg1").feature("plot5").set("innerinput", "all");
    model.result("pg1").feature("plot5").set("titletype", "custom");
    model.result("pg6").run();
    model.result("pg1").feature().copy("plot6", "pg6/plot1");
    model.result("pg6").feature().remove("plot1");
    model.result("pg1").feature("plot6").set("outerinput", "all");
    model.result("pg1").feature("plot6").set("innerinput", "all");
    model.result("pg1").feature("plot6").set("titletype", "custom");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");

    model.thermodynamics().feature("pp1").feature("singlephase1").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase1").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("\u70ed\u5bb9");
    model.result("pg2").set("savedatainmodel", true);

    model.thermodynamics().feature("pp1").feature("singlephase3").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase3").createPlot("pg3");

    model.result("pg3").run();

    model.thermodynamics().feature("pp1").feature("singlephase5").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase5").createPlot("pg4");

    model.result("pg4").run();

    model.thermodynamics().feature("pp1").feature("singlephase7").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase7").createPlot("pg5");

    model.result("pg5").run();

    model.thermodynamics().feature("pp1").feature("singlephase9").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase9").createPlot("pg6");

    model.result("pg6").run();

    model.thermodynamics().feature("pp1").feature("singlephase11").setIndex("plotargs", 2000, 0, 2);
    model.thermodynamics().feature("pp1").feature("singlephase11").createPlot("pg7");

    model.result("pg7").run();
    model.result("pg3").run();
    model.result("pg2").feature().copy("plot2", "pg3/plot1");
    model.result("pg3").feature().remove("plot1");
    model.result("pg2").feature("plot2").set("outerinput", "all");
    model.result("pg2").feature("plot2").set("innerinput", "all");
    model.result("pg2").feature("plot2").set("titletype", "custom");
    model.result("pg4").run();
    model.result("pg2").feature().copy("plot3", "pg4/plot1");
    model.result("pg4").feature().remove("plot1");
    model.result("pg2").feature("plot3").set("outerinput", "all");
    model.result("pg2").feature("plot3").set("innerinput", "all");
    model.result("pg2").feature("plot3").set("titletype", "custom");
    model.result("pg5").run();
    model.result("pg2").feature().copy("plot4", "pg5/plot1");
    model.result("pg5").feature().remove("plot1");
    model.result("pg2").feature("plot4").set("outerinput", "all");
    model.result("pg2").feature("plot4").set("innerinput", "all");
    model.result("pg2").feature("plot4").set("titletype", "custom");
    model.result("pg6").run();
    model.result("pg2").feature().copy("plot5", "pg6/plot1");
    model.result("pg6").feature().remove("plot1");
    model.result("pg2").feature("plot5").set("outerinput", "all");
    model.result("pg2").feature("plot5").set("innerinput", "all");
    model.result("pg2").feature("plot5").set("titletype", "custom");
    model.result("pg7").run();
    model.result("pg2").feature().copy("plot6", "pg7/plot1");
    model.result("pg7").feature().remove("plot1");
    model.result("pg2").feature("plot6").set("outerinput", "all");
    model.result("pg2").feature("plot6").set("innerinput", "all");
    model.result("pg2").feature("plot6").set("titletype", "custom");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u751f\u6210\u7113 (J/mol)");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("legend", true);
    model.result("pg1").feature("plot1").set("legendmethod", "manual");
    model.result("pg1").feature("plot1").setIndex("legends", "CO2", 0);
    model.result("pg1").run();
    model.result("pg1").feature("plot2").set("legend", true);
    model.result("pg1").feature("plot2").set("legendmethod", "manual");
    model.result("pg1").feature("plot2").setIndex("legends", "CO", 0);
    model.result("pg1").run();
    model.result("pg1").feature("plot3").set("legend", true);
    model.result("pg1").feature("plot3").set("legendmethod", "manual");
    model.result("pg1").feature("plot3").setIndex("legends", "H2", 0);
    model.result("pg1").run();
    model.result("pg1").feature("plot4").set("legend", true);
    model.result("pg1").feature("plot4").set("legendmethod", "manual");
    model.result("pg1").feature("plot4").setIndex("legends", "N2", 0);
    model.result("pg1").run();
    model.result("pg1").feature("plot5").set("legend", true);
    model.result("pg1").feature("plot5").set("legendmethod", "manual");
    model.result("pg1").feature("plot5").setIndex("legends", "O2", 0);
    model.result("pg1").run();
    model.result("pg1").feature("plot6").set("legend", true);
    model.result("pg1").feature("plot6").set("legendmethod", "manual");
    model.result("pg1").feature("plot6").setIndex("legends", "H2O", 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "singlephase2_ds1");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u70ed\u5bb9 (J/mol/K)");
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("legend", true);
    model.result("pg2").feature("plot1").set("legendmethod", "manual");
    model.result("pg2").feature("plot1").setIndex("legends", "CO2", 0);
    model.result("pg2").run();
    model.result("pg2").feature("plot2").set("legend", true);
    model.result("pg2").feature("plot2").set("legendmethod", "manual");
    model.result("pg2").feature("plot2").setIndex("legends", "CO", 0);
    model.result("pg2").run();
    model.result("pg2").feature("plot3").set("legend", true);
    model.result("pg2").feature("plot3").set("legendmethod", "manual");
    model.result("pg2").feature("plot3").setIndex("legends", "H2", 0);
    model.result("pg2").run();
    model.result("pg2").feature("plot4").set("legend", true);
    model.result("pg2").feature("plot4").set("legendmethod", "manual");
    model.result("pg2").feature("plot4").setIndex("legends", "N2", 0);
    model.result("pg2").run();
    model.result("pg2").feature("plot5").set("legend", true);
    model.result("pg2").feature("plot5").set("legendmethod", "manual");
    model.result("pg2").feature("plot5").setIndex("legends", "O2", 0);
    model.result("pg2").run();
    model.result("pg2").feature("plot6").set("legend", true);
    model.result("pg2").feature("plot6").set("legendmethod", "manual");
    model.result("pg2").feature("plot6").setIndex("legends", "H2O", 0);
    model.result("pg2").run();
    model.result().dataset().remove("singlephase4_ds1");
    model.result().dataset().remove("singlephase6_ds1");
    model.result().dataset().remove("singlephase8_ds1");
    model.result().dataset().remove("singlephase10_ds1");
    model.result().dataset().remove("singlephase12_ds1");
    model.result().dataset().remove("singlephase1_ds1");
    model.result().dataset().remove("singlephase3_ds1");
    model.result().dataset().remove("singlephase5_ds1");
    model.result().dataset().remove("singlephase7_ds1");
    model.result().dataset().remove("singlephase9_ds1");
    model.result().dataset().remove("singlephase11_ds1");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u751f\u6210\u7113\uff0c298 K");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_carbon_dioxide_Gas12(298.15[K],1.0133E5[Pa])", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_carbon_monoxide_Gas14(298.15[K],1.0133E5[Pa])", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_hydrogen_Gas16(298.15[K],1.0133E5[Pa])", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_nitrogen_Gas18(298.15[K],1.0133E5[Pa])", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_oxygen_Gas110(298.15[K],1.0133E5[Pa])", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "EnthalpyF_water_Gas112(298.15[K],1.0133E5[Pa])", 5);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").label("\u70ed\u5bb9\uff0c300 K");
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_carbon_dioxide_Gas11(300[K],1.0133E5[Pa])", 0);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_carbon_monoxide_Gas13(300[K],1.0133E5[Pa])", 1);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_hydrogen_Gas15(300[K],1.0133E5[Pa])", 2);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_nitrogen_Gas17(300[K],1.0133E5[Pa])", 3);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_oxygen_Gas19(300[K],1.0133E5[Pa])", 4);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "HeatCapacityCp_water_Gas111(300[K],1.0133E5[Pa])", 5);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev3").label("\u70ed\u5bb9\uff0c2000 K");
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_carbon_dioxide_Gas11(2000[K],1.0133E5[Pa])", 0);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_carbon_monoxide_Gas13(2000[K],1.0133E5[Pa])", 1);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_hydrogen_Gas15(2000[K],1.0133E5[Pa])", 2);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_nitrogen_Gas17(2000[K],1.0133E5[Pa])", 3);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_oxygen_Gas19(2000[K],1.0133E5[Pa])", 4);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "HeatCapacityCp_water_Gas111(2000[K],1.0133E5[Pa])", 5);
    model.result().evaluationGroup("eg1").run();

    model.sol("sol1").runAll();

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, CO (tcs)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"tcs.c_wCO"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcs.tflux_wCOr", "tcs.tflux_wCOz"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").label("\u6d53\u5ea6, CO, 3D (tcs)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"tcs.c_wCO"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, O2 (tcs)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"tcs.c_wO2"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tcs.tflux_wO2r", "tcs.tflux_wO2z"});
    model.result("pg5").feature("arws1").set("xnumber", 10);
    model.result("pg5").feature("arws1").set("ynumber", 10);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").create("sel1", "Selection");
    model.result("pg5").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").label("\u6d53\u5ea6, O2, 3D (tcs)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"tcs.c_wO2"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").label("\u6d53\u5ea6, CO2 (tcs)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"tcs.c_wCO2"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"tcs.tflux_wCO2r", "tcs.tflux_wCO2z"});
    model.result("pg7").feature("arws1").set("xnumber", 10);
    model.result("pg7").feature("arws1").set("ynumber", 10);
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").feature("arws1").create("sel1", "Selection");
    model.result("pg7").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev1");
    model.result("pg8").label("\u6d53\u5ea6, CO2, 3D (tcs)");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"tcs.c_wCO2"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").label("\u6d53\u5ea6, H2 (tcs)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"tcs.tflux_wH2r", "tcs.tflux_wH2z"});
    model.result("pg9").feature("arws1").set("xnumber", 10);
    model.result("pg9").feature("arws1").set("ynumber", 10);
    model.result("pg9").feature("arws1").set("color", "black");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "rev1");
    model.result("pg10").label("\u6d53\u5ea6, H2, 3D (tcs)");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").label("\u6d53\u5ea6, H2O (tcs)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"tcs.c_wH2O"});
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result("pg11").set("typeintitle", true);
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("expr", new String[]{"tcs.tflux_wH2Or", "tcs.tflux_wH2Oz"});
    model.result("pg11").feature("arws1").set("xnumber", 10);
    model.result("pg11").feature("arws1").set("ynumber", 10);
    model.result("pg11").feature("arws1").set("color", "black");
    model.result("pg11").feature("arws1").create("sel1", "Selection");
    model.result("pg11").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "rev1");
    model.result("pg12").label("\u6d53\u5ea6, H2O, 3D (tcs)");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"tcs.c_wH2O"});
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").set("data", "dset1");
    model.result("pg13").label("\u6d53\u5ea6, N2 (tcs)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").set("prefixintitle", "");
    model.result("pg13").set("expressionintitle", false);
    model.result("pg13").set("typeintitle", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"tcs.c_wN2"});
    model.result("pg13").feature("surf1").set("colortable", "Prism");
    model.result("pg13").set("typeintitle", true);
    model.result("pg13").create("arws1", "ArrowSurface");
    model.result("pg13").feature("arws1").set("expr", new String[]{"tcs.tflux_wN2r", "tcs.tflux_wN2z"});
    model.result("pg13").feature("arws1").set("xnumber", 10);
    model.result("pg13").feature("arws1").set("ynumber", 10);
    model.result("pg13").feature("arws1").set("color", "black");
    model.result("pg13").feature("arws1").create("sel1", "Selection");
    model.result("pg13").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "rev1");
    model.result("pg14").label("\u6d53\u5ea6, N2, 3D (tcs)");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"tcs.c_wN2"});
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").set("titletype", "custom");
    model.result("pg14").set("typeintitle", false);
    model.result("pg14").set("prefixintitle", "");
    model.result("pg14").set("expressionintitle", false);
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").label("\u901f\u5ea6 (spf)");
    model.result("pg15").set("dataisaxisym", "off");
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").label("\u8868\u9762");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("expr", "spf.U");
    model.result("pg15").feature("surf1").set("colortable", "Rainbow");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").label("\u538b\u529b (spf)");
    model.result("pg16").set("dataisaxisym", "off");
    model.result("pg16").set("frametype", "spatial");
    model.result("pg16").feature().create("con1", "Contour");
    model.result("pg16").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg16").feature("con1").set("showsolutionparams", "on");
    model.result("pg16").feature("con1").set("expr", "p");
    model.result("pg16").feature("con1").set("number", 40);
    model.result("pg16").feature("con1").set("levelrounding", false);
    model.result("pg16").feature("con1").set("colortable", "Rainbow");
    model.result("pg16").feature("con1").set("smooth", "internal");
    model.result("pg16").feature("con1").set("showsolutionparams", "on");
    model.result("pg16").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg17").set("frametype", "spatial");
    model.result("pg17").feature().create("surf1", "Surface");
    model.result("pg17").feature("surf1").label("\u8868\u9762");
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("expr", "spf.U");
    model.result("pg17").feature("surf1").set("colortable", "Rainbow");
    model.result("pg17").feature("surf1").set("smooth", "internal");
    model.result("pg17").feature("surf1").set("showsolutionparams", "on");
    model.result("pg17").feature("surf1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(4, 5, 6, 7, 8);
    model.result().create("pg18", "PlotGroup2D");
    model.result("pg18").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg18").set("dataisaxisym", "off");
    model.result("pg18").set("frametype", "spatial");
    model.result("pg18").feature().create("line1", "Line");
    model.result("pg18").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg18").feature("line1").set("showsolutionparams", "on");
    model.result("pg18").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg18").feature("line1").set("linetype", "tube");
    model.result("pg18").feature("line1").set("colortable", "Rainbow");
    model.result("pg18").feature("line1").set("smooth", "internal");
    model.result("pg18").feature("line1").set("showsolutionparams", "on");
    model.result("pg18").feature("line1").set("data", "parent");
    model.result("pg18").feature("line1").feature().create("hght1", "Height");
    model.result("pg18").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg18").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg18").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result().create("pg19", "PlotGroup2D");
    model.result("pg19").label("\u6e29\u5ea6 (ht)");
    model.result("pg19").set("dataisaxisym", "off");
    model.result("pg19").feature().create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("solutionparams", "parent");
    model.result("pg19").feature("surf1").set("expr", "T");
    model.result("pg19").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("data", "parent");
    model.result().move("pg3", 2);
    model.result("pg3").tag("pg3");
    model.result().move("pg4", 3);
    model.result("pg4").tag("pg4");
    model.result().move("pg5", 4);
    model.result("pg5").tag("pg5");
    model.result().move("pg6", 5);
    model.result("pg6").tag("pg6");
    model.result().move("pg7", 6);
    model.result("pg7").tag("pg7");
    model.result().move("pg8", 7);
    model.result("pg8").tag("pg8");
    model.result().move("pg9", 8);
    model.result("pg9").tag("pg9");
    model.result().move("pg10", 9);
    model.result("pg10").tag("pg10");
    model.result().move("pg11", 10);
    model.result("pg11").tag("pg11");
    model.result().move("pg12", 11);
    model.result("pg12").tag("pg12");
    model.result().move("pg13", 12);
    model.result("pg13").tag("pg13");
    model.result().move("pg14", 13);
    model.result("pg14").tag("pg14");
    model.result().move("pg15", 14);
    model.result("pg15").tag("pg15");
    model.result().move("pg16", 15);
    model.result("pg16").tag("pg16");
    model.result().move("pg17", 16);
    model.result("pg17").tag("pg17");
    model.result().move("pg18", 17);
    model.result("pg18").tag("pg18");
    model.result().move("pg19", 18);
    model.result("pg19").tag("pg19");
    model.result().move("pg3", 2);
    model.result("pg3").tag("pg3");
    model.result().move("pg4", 3);
    model.result("pg4").tag("pg4");
    model.result().move("pg5", 4);
    model.result("pg5").tag("pg5");
    model.result().move("pg6", 5);
    model.result("pg6").tag("pg6");
    model.result().move("pg7", 6);
    model.result("pg7").tag("pg7");
    model.result().move("pg8", 7);
    model.result("pg8").tag("pg8");
    model.result().move("pg9", 8);
    model.result("pg9").tag("pg9");
    model.result().move("pg10", 9);
    model.result("pg10").tag("pg10");
    model.result().move("pg11", 10);
    model.result("pg11").tag("pg11");
    model.result().move("pg12", 11);
    model.result("pg12").tag("pg12");
    model.result().move("pg13", 12);
    model.result("pg13").tag("pg13");
    model.result().move("pg14", 13);
    model.result("pg14").tag("pg14");
    model.result().move("pg15", 14);
    model.result("pg15").tag("pg15");
    model.result().move("pg16", 15);
    model.result("pg16").tag("pg16");
    model.result().move("pg17", 16);
    model.result("pg17").tag("pg17");
    model.result().move("pg18", 17);
    model.result("pg18").tag("pg18");
    model.result().move("pg19", 18);
    model.result("pg19").tag("pg19");
    model.result().dataset().move("rev1", 1);
    model.result().dataset("rev1").tag("rev2");
    model.result().dataset().move("edg1", 2);
    model.result().dataset("edg1").tag("edg3");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "mir1");
    model.result().dataset("cln1").set("method", "pointdir");
    model.result().dataset("cln1").set("pdpoint", new String[]{"0", "Pl+20*Di"});
    model.result().dataset("cln1").set("spacevars", new String[]{"r_mirr20"});
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").set("data", "mir1");
    model.result().dataset("cln2").set("method", "pointdir");
    model.result().dataset("cln2").set("pdpoint", new String[]{"0", "Pl+50*Di"});
    model.result().dataset("cln2").set("spacevars", new String[]{"r_mirr50"});
    model.result("pg15").run();
    model.result("pg15").set("data", "mir1");
    model.result("pg15").set("titletype", "manual");
    model.result("pg15")
         .set("title", "\u8868\u9762\uff1a\u901f\u5ea6\u5927\u5c0f \u6d41\u7ebf\uff1a\u901f\u5ea6\u573a");
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").run();
    model.result("pg15").create("str1", "Streamline");
    model.result("pg15").feature("str1").set("expr", new String[]{"ht.ur", "ht.uz"});
    model.result("pg15").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg15").feature("str1").set("posmethod", "uniform");
    model.result("pg15").feature("str1").set("udist", 0.035);
    model.result("pg15").feature("str1").set("color", "gray");
    model.result("pg15").run();
    model.result("pg16").run();
    model.result("pg16").set("data", "mir1");
    model.result("pg16").run();
    model.result("pg18").run();
    model.result("pg18").set("data", "mir1");
    model.result("pg18").run();
    model.result("pg7").run();
    model.result().duplicate("pg20", "pg7");
    model.result("pg20").run();
    model.result("pg20").label("\u8d28\u91cf\u5206\u6570\uff0cCO2");
    model.result("pg20").set("data", "mir1");
    model.result("pg20").set("edgecolor", "white");
    model.result("pg20").set("titletype", "manual");
    model.result("pg20")
         .set("title", "\u8868\u9762\uff1a\u8d28\u91cf\u5206\u6570 \u6d41\u7ebf\uff1a\u603b\u901a\u91cf");
    model.result("pg20").run();
    model.result("pg20").feature("surf1").set("expr", "wCO2");
    model.result("pg20").run();
    model.result("pg19").run();
    model.result("pg19").set("edges", false);
    model.result("pg19").set("titletype", "manual");
    model.result("pg19").set("title", "\u8868\u9762\uff1a\u6e29\u5ea6");
    model.result("pg19").set("showlegendsunit", true);
    model.result("pg19").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u4e2d\u5fc3\u7ebf\u6570\u636e");
    model.result().table("tbl1").importData("round_jet_burner_chnAclY.fav");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("z/Di = 20\uff0c\u5f84\u5411\u6570\u636e");
    model.result().table("tbl2").importData("round_jet_burner_chnAd20Y.fav");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("z/Di = 50\uff0c\u5f84\u5411\u6570\u636e");
    model.result().table("tbl3").importData("round_jet_burner_chnAd50Y.fav");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("z/Di = 20\uff0c\u5f84\u5411\u901f\u5ea6\u6570\u636e");
    model.result().table("tbl4").importData("round_jet_burner_seq1420.dat");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").label("z/Di = 50\uff0c\u5f84\u5411\u901f\u5ea6\u6570\u636e");
    model.result().table("tbl5").importData("round_jet_burner_seq1450.dat");
    model.result().create("pg21", "PlotGroup1D");
    model.result("pg21").run();
    model.result("pg21").create("lngr1", "LineGraph");
    model.result("pg21").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg21").feature("lngr1").set("linewidth", "preference");
    model.result("pg21").feature("lngr1").selection().set(1);
    model.result("pg21").feature("lngr1").set("expr", "T/T0");
    model.result("pg21").feature("lngr1").set("xdata", "expr");
    model.result("pg21").feature("lngr1").set("xdataexpr", "(z-Pl)/Di");
    model.result("pg21").feature("lngr1").set("linecolor", "black");
    model.result("pg21").feature("lngr1").set("legend", true);
    model.result("pg21").feature("lngr1").set("legendmethod", "manual");
    model.result("pg21").feature("lngr1").setIndex("legends", "\u6a21\u578b\u503c", 0);
    model.result("pg21").run();
    model.result("pg21").create("tblp1", "Table");
    model.result("pg21").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg21").feature("tblp1").set("linewidth", "preference");
    model.result("pg21").feature("tblp1").set("xaxisdata", 1);
    model.result("pg21").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg21").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg21").feature("tblp1").set("preprocx", "linear");
    model.result("pg21").feature("tblp1").set("scalingx", "1/(Di*1000)");
    model.result("pg21").feature("tblp1").set("preprocy", "linear");
    model.result("pg21").feature("tblp1").set("scalingy", "1/T0");
    model.result("pg21").feature("tblp1").set("linestyle", "none");
    model.result("pg21").feature("tblp1").set("linecolor", "black");
    model.result("pg21").feature("tblp1").set("linemarker", "square");
    model.result("pg21").feature("tblp1").set("legend", true);
    model.result("pg21").feature("tblp1").set("legendmethod", "manual");
    model.result("pg21").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c\u503c", 0);
    model.result("pg21").run();
    model.result("pg21").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684 T");
    model.result("pg21").set("xlabelactive", true);
    model.result("pg21").set("xlabel", "(z-Pl)/Di");
    model.result("pg21").set("ylabelactive", true);
    model.result("pg21").set("ylabel", "T/T0");
    model.result("pg21").set("axislimits", true);
    model.result("pg21").set("xmin", -10);
    model.result("pg21").set("xmax", 120);
    model.result("pg21").set("ymin", 0.5);
    model.result("pg21").set("ymax", 8);
    model.result("pg21").set("legendlayout", "outside");
    model.result("pg21").set("titletype", "manual");
    model.result("pg21").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u6e29\u5ea6");
    model.result("pg21").run();
    model.result("pg21").set("titletype", "none");
    model.result("pg21").run();
    model.result("pg21").feature("lngr1").set("legend", false);
    model.result("pg21").run();
    model.result("pg21").feature("tblp1").set("legend", false);
    model.result("pg21").run();
    model.result("pg21").set("titletype", "manual");
    model.result("pg21").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u6e29\u5ea6");
    model.result("pg21").run();
    model.result("pg21").feature("lngr1").set("legend", true);
    model.result("pg21").run();
    model.result("pg21").feature("tblp1").set("legend", true);
    model.result().create("pg22", "PlotGroup1D");
    model.result("pg22").run();
    model.result("pg22").set("data", "none");
    model.result("pg22").create("lngr1", "LineGraph");
    model.result("pg22").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg22").feature("lngr1").set("linewidth", "preference");
    model.result("pg22").feature("lngr1").set("data", "cln1");
    model.result("pg22").feature("lngr1").set("expr", "T/T0");
    model.result("pg22").feature("lngr1").set("xdata", "expr");
    model.result("pg22").feature("lngr1").set("xdataexpr", "r_mirr20/Di");
    model.result("pg22").feature("lngr1").set("linecolor", "black");
    model.result("pg22").feature("lngr1").set("legend", true);
    model.result("pg22").feature("lngr1").set("legendmethod", "manual");
    model.result("pg22").feature("lngr1").setIndex("legends", "z/Di = 20\uff0c\u6a21\u578b", 0);
    model.result("pg22").feature().duplicate("lngr2", "lngr1");
    model.result("pg22").run();
    model.result("pg22").feature("lngr2").set("data", "cln2");
    model.result("pg22").feature("lngr2").set("xdataexpr", "r_mirr50/Di");
    model.result("pg22").feature("lngr2").set("linestyle", "dashed");
    model.result("pg22").feature("lngr2").setIndex("legends", "z/Di = 50\uff0c\u6a21\u578b", 0);
    model.result("pg22").run();
    model.result("pg22").run();
    model.result("pg22").create("tblp1", "Table");
    model.result("pg22").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg22").feature("tblp1").set("linewidth", "preference");
    model.result("pg22").feature("tblp1").set("table", "tbl2");
    model.result("pg22").feature("tblp1").set("xaxisdata", 1);
    model.result("pg22").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg22").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg22").feature("tblp1").set("preprocx", "linear");
    model.result("pg22").feature("tblp1").set("scalingx", "1/(Di*1000)");
    model.result("pg22").feature("tblp1").set("preprocy", "linear");
    model.result("pg22").feature("tblp1").set("scalingy", "1/T0");
    model.result("pg22").feature("tblp1").set("linestyle", "none");
    model.result("pg22").feature("tblp1").set("linecolor", "black");
    model.result("pg22").feature("tblp1").set("linemarker", "square");
    model.result("pg22").feature("tblp1").set("legend", true);
    model.result("pg22").feature("tblp1").set("legendmethod", "manual");
    model.result("pg22").feature("tblp1").setIndex("legends", "z/Di = 20\uff0c\u5b9e\u9a8c", 0);
    model.result("pg22").feature().duplicate("tblp2", "tblp1");
    model.result("pg22").run();
    model.result("pg22").feature("tblp2").set("table", "tbl3");
    model.result("pg22").feature("tblp2").set("linemarker", "triangle");
    model.result("pg22").feature("tblp2").set("markerpos", "interp");
    model.result("pg22").feature("tblp2").setIndex("legends", "z/Di = 50\uff0c\u5b9e\u9a8c", 0);
    model.result("pg22").run();
    model.result("pg22").label("T @ z/Di = 20, 50");
    model.result("pg22").set("titletype", "manual");
    model.result("pg22").set("title", "\u7ba1\u51fa\u53e3\u4e0b\u6e38\u7684\u6e29\u5ea6");
    model.result("pg22").set("xlabelactive", true);
    model.result("pg22").set("xlabel", "r/Di");
    model.result("pg22").set("ylabelactive", true);
    model.result("pg22").set("ylabel", "T/T0");
    model.result("pg22").set("axislimits", true);
    model.result("pg22").set("xmin", -10);
    model.result("pg22").set("xmax", 10);
    model.result("pg22").set("ymin", 0.5);
    model.result("pg22").set("ymax", 8);
    model.result("pg22").set("legendlayout", "outside");
    model.result("pg22").run();
    model.result("pg22").set("titletype", "none");
    model.result("pg22").run();
    model.result("pg22").feature("lngr1").set("legend", false);
    model.result("pg22").run();
    model.result("pg22").feature("lngr2").set("legend", false);
    model.result("pg22").run();
    model.result("pg22").feature("tblp1").set("legend", false);
    model.result("pg22").run();
    model.result("pg22").feature("tblp2").set("legend", false);
    model.result("pg22").run();
    model.result("pg22").set("titletype", "manual");
    model.result("pg22").set("title", "\u7ba1\u51fa\u53e3\u4e0b\u6e38\u7684\u6e29\u5ea6");
    model.result("pg22").run();
    model.result("pg22").feature("lngr1").set("legend", true);
    model.result("pg22").run();
    model.result("pg22").feature("lngr2").set("legend", true);
    model.result("pg22").run();
    model.result("pg22").feature("tblp1").set("legend", true);
    model.result("pg22").run();
    model.result("pg22").feature("tblp2").set("legend", true);
    model.result("pg22").run();
    model.result().duplicate("pg23", "pg22");
    model.result("pg23").run();
    model.result("pg23").label("uz @ z/Di = 20, 50");
    model.result("pg23").run();
    model.result("pg23").feature("lngr1").set("expr", "w/Ujet");
    model.result("pg23").run();
    model.result("pg23").feature("lngr2").set("expr", "w/Ujet");
    model.result("pg23").run();
    model.result("pg23").feature("tblp1").set("xaxisdata", 2);
    model.result("pg23").feature("tblp1").set("table", "tbl4");
    model.result("pg23").feature("tblp1").set("plotcolumns", new int[]{9});
    model.result("pg23").feature("tblp1").set("scalingy", "1/Ujet");
    model.result("pg23").run();
    model.result("pg23").feature("tblp2").set("xaxisdata", 2);
    model.result("pg23").feature("tblp2").set("table", "tbl5");
    model.result("pg23").feature("tblp2").set("plotcolumns", new int[]{9});
    model.result("pg23").feature("tblp2").set("scalingy", "1/Ujet");
    model.result("pg23").run();
    model.result("pg23").set("title", "\u7ba1\u51fa\u53e3\u4e0b\u6e38\u7684\u8f74\u5411\u901f\u5ea6");
    model.result("pg23").set("ylabel", "uz/Ujet");
    model.result("pg23").set("ymin", -0.25);
    model.result("pg23").set("ymax", 1.25);
    model.result("pg23").run();
    model.result("pg23").set("titletype", "none");
    model.result("pg23").run();
    model.result("pg23").feature("lngr1").set("legend", false);
    model.result("pg23").run();
    model.result("pg23").feature("lngr2").set("legend", false);
    model.result("pg23").run();
    model.result("pg23").feature("tblp1").set("legend", false);
    model.result("pg23").run();
    model.result("pg23").feature("tblp2").set("legend", false);
    model.result("pg23").run();
    model.result("pg23").set("titletype", "manual");
    model.result("pg23").set("title", "\u7ba1\u51fa\u53e3\u4e0b\u6e38\u7684\u8f74\u5411\u901f\u5ea6");
    model.result("pg23").run();
    model.result("pg23").feature("lngr1").set("legend", true);
    model.result("pg23").run();
    model.result("pg23").feature("lngr2").set("legend", true);
    model.result("pg23").run();
    model.result("pg23").feature("tblp1").set("legend", true);
    model.result("pg23").run();
    model.result("pg23").feature("tblp2").set("legend", true);
    model.result("pg21").run();
    model.result().duplicate("pg24", "pg21");
    model.result("pg24").run();
    model.result("pg24").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684 CO \u548c N2");
    model.result("pg24").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u8d28\u91cf\u5206\u6570");
    model.result("pg24").set("ylabel", "wCO, wN2");
    model.result("pg24").set("ymin", -0.05);
    model.result("pg24").set("ymax", 1);
    model.result("pg24").run();
    model.result("pg24").run();
    model.result("pg24").feature("lngr1").set("expr", "wCO");
    model.result("pg24").feature("lngr1").setIndex("legends", "CO\uff0c\u6a21\u578b", 0);
    model.result("pg24").run();
    model.result("pg24").feature("tblp1").set("plotcolumns", new int[]{14});
    model.result("pg24").feature("tblp1").set("scalingy", 1);
    model.result("pg24").feature("tblp1").setIndex("legends", "CO\uff0c\u5b9e\u9a8c", 0);
    model.result("pg24").run();
    model.result("pg24").run();
    model.result("pg24").feature().duplicate("lngr2", "lngr1");
    model.result("pg24").run();
    model.result("pg24").feature("lngr2").set("expr", "wN2");
    model.result("pg24").feature("lngr2").set("linestyle", "dashed");
    model.result("pg24").feature("lngr2").setIndex("legends", "N2\uff0c\u6a21\u578b", 0);
    model.result("pg24").run();
    model.result("pg24").feature().duplicate("tblp2", "tblp1");
    model.result("pg24").run();
    model.result("pg24").feature("tblp2").set("plotcolumns", new int[]{8});
    model.result("pg24").feature("tblp2").set("linemarker", "triangle");
    model.result("pg24").feature("tblp2").set("markerpos", "interp");
    model.result("pg24").feature("tblp2").setIndex("legends", "N2\uff0c\u5b9e\u9a8c", 0);
    model.result("pg24").run();
    model.result("pg24").run();
    model.result("pg24").set("titletype", "none");
    model.result("pg24").run();
    model.result("pg24").feature("lngr1").set("legend", false);
    model.result("pg24").run();
    model.result("pg24").feature("lngr2").set("legend", false);
    model.result("pg24").run();
    model.result("pg24").feature("tblp1").set("legend", false);
    model.result("pg24").run();
    model.result("pg24").feature("tblp2").set("legend", false);
    model.result("pg24").run();
    model.result("pg24").set("titletype", "manual");
    model.result("pg24").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u8d28\u91cf\u5206\u6570");
    model.result("pg24").run();
    model.result("pg24").feature("lngr1").set("legend", true);
    model.result("pg24").run();
    model.result("pg24").feature("lngr2").set("legend", true);
    model.result("pg24").run();
    model.result("pg24").feature("tblp1").set("legend", true);
    model.result("pg24").run();
    model.result("pg24").feature("tblp2").set("legend", true);
    model.result("pg24").run();
    model.result().duplicate("pg25", "pg24");
    model.result("pg25").run();
    model.result("pg25").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684 H2 \u548c H2O");
    model.result("pg25").run();
    model.result("pg25").feature("lngr1").set("expr", "wH2");
    model.result("pg25").feature("lngr1").setIndex("legends", "H2\uff0c\u6a21\u578b", 0);

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg25").run();
    model.result("pg25").feature("tblp1").set("plotcolumns", new int[]{10});
    model.result("pg25").feature("tblp1").setIndex("legends", "H2\uff0c\u5b9e\u9a8c", 0);
    model.result("pg25").run();
    model.result("pg25").feature("lngr2").set("expr", "wH2O");
    model.result("pg25").feature("lngr2").setIndex("legends", "H2O\uff0c\u6a21\u578b", 0);
    model.result("pg25").run();
    model.result("pg25").feature("tblp2").set("plotcolumns", new int[]{12});
    model.result("pg25").feature("tblp2").setIndex("legends", "H2O\uff0c\u5b9e\u9a8c", 0);
    model.result("pg25").run();
    model.result("pg25").set("ylabel", "wH2\uff0cwH2O");
    model.result("pg25").set("ymax", 0.15);
    model.result("pg25").set("ymin", -0.02);
    model.result("pg25").run();
    model.result("pg25").set("titletype", "none");
    model.result("pg25").run();
    model.result("pg25").feature("lngr1").set("legend", false);
    model.result("pg25").run();
    model.result("pg25").feature("lngr2").set("legend", false);
    model.result("pg25").run();
    model.result("pg25").feature("tblp1").set("legend", false);
    model.result("pg25").run();
    model.result("pg25").feature("tblp2").set("legend", false);
    model.result("pg25").run();
    model.result("pg25").set("titletype", "manual");
    model.result("pg25").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u8d28\u91cf\u5206\u6570");
    model.result("pg25").run();
    model.result("pg25").feature("lngr1").set("legend", true);
    model.result("pg25").run();
    model.result("pg25").feature("lngr2").set("legend", true);
    model.result("pg25").run();
    model.result("pg25").feature("tblp1").set("legend", true);
    model.result("pg25").run();
    model.result("pg25").feature("tblp2").set("legend", true);
    model.result("pg25").run();
    model.result().duplicate("pg26", "pg25");
    model.result("pg26").run();
    model.result("pg26").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684 O2 \u548c CO2");
    model.result("pg26").run();
    model.result("pg26").feature("lngr1").set("expr", "wO2");
    model.result("pg26").feature("lngr1").setIndex("legends", "O2\uff0c\u6a21\u578b", 0);
    model.result("pg26").run();
    model.result("pg26").feature("tblp1").set("plotcolumns", new int[]{6});
    model.result("pg26").feature("tblp1").setIndex("legends", "O2\uff0c\u5b9e\u9a8c", 0);
    model.result("pg26").run();
    model.result("pg26").feature("lngr2").set("expr", "wCO2");
    model.result("pg26").feature("lngr2").setIndex("legends", "CO2\uff0c\u6a21\u578b", 0);
    model.result("pg26").run();
    model.result("pg26").feature("tblp2").set("plotcolumns", new int[]{16});
    model.result("pg26").feature("tblp2").setIndex("legends", "CO2\uff0c\u5b9e\u9a8c", 0);
    model.result("pg26").run();
    model.result("pg26").set("ylabel", "wO2\uff0cwCO2");
    model.result("pg26").set("ymin", -0.05);
    model.result("pg26").set("ymax", 0.4);
    model.result("pg26").run();
    model.result("pg26").set("titletype", "none");
    model.result("pg26").run();
    model.result("pg26").feature("lngr1").set("legend", false);
    model.result("pg26").run();
    model.result("pg26").feature("lngr2").set("legend", false);
    model.result("pg26").run();
    model.result("pg26").feature("tblp1").set("legend", false);
    model.result("pg26").run();
    model.result("pg26").feature("tblp2").set("legend", false);
    model.result("pg26").run();
    model.result("pg26").set("titletype", "manual");
    model.result("pg26").set("title", "\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u8d28\u91cf\u5206\u6570");
    model.result("pg26").run();
    model.result("pg26").feature("lngr1").set("legend", true);
    model.result("pg26").run();
    model.result("pg26").feature("lngr2").set("legend", true);
    model.result("pg26").run();
    model.result("pg26").feature("tblp1").set("legend", true);
    model.result("pg26").run();
    model.result("pg26").feature("tblp2").set("legend", true);

    model.title("\u5706\u5f62\u55b7\u5c04\u71c3\u70e7\u5668\u4e2d\u7684\u5408\u6210\u6c14\u71c3\u70e7");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u4e00\u4e2a\u5706\u5f62\u55b7\u5c04\u71c3\u70e7\u5668\u4e2d\u672a\u7ecf\u8fc7\u9884\u6df7\u7684\u5408\u6210\u6c14\u7684\u6e4d\u6d41\u71c3\u70e7\u3002\n\n\u5408\u6210\u6c14\u662f\u4e00\u79cd\u6c14\u4f53\u6df7\u5408\u7269\uff0c\u4e3b\u8981\u5305\u542b\u6c22\u6c14\u3001\u4e00\u6c27\u5316\u78b3\u548c\u4e8c\u6c27\u5316\u78b3\u7b49\u3002\u5176\u540d\u79f0\u6765\u6e90\u4e8e\u5b83\u7528\u4e8e\u5408\u6210\u5929\u7136\u6c14\u3002\u5728\u8be5\u6a21\u578b\u4e2d\uff0c\u5408\u6210\u6c14\u4ece\u4e00\u6839\u7ba1\u9053\u6ce8\u5165\u5f00\u53e3\u533a\uff0c\u540c\u65f6\u7a7a\u6c14\u5728\u7f13\u6162\u6d41\u52a8\u3002\u5728\u7ba1\u9053\u4e2d\uff0c\u672a\u7ecf\u8fc7\u9884\u6df7\u7684\u5408\u6210\u6c14\u4e0e\u5468\u56f4\u7684\u7a7a\u6c14\u6df7\u5408\u5e76\u53cd\u5e94\uff0c\u5728\u51fa\u53e3\u5f62\u6210\u6e4d\u6d41\u706b\u7130\u3002\u8be5\u6a21\u578b\u7ec4\u5408\u4e86\u201c\u53cd\u5e94\u6d41\u201d\u63a5\u53e3\u548c\u201c\u6d41\u4f53\u4f20\u70ed\u201d\u63a5\u53e3\u8fdb\u884c\u6c42\u89e3\u3002\u55b7\u5c04\u7684\u6e4d\u6d41\u901a\u8fc7 k-\u03c9 \u6e4d\u6d41\u6a21\u578b\u8fdb\u884c\u5efa\u6a21\uff0c\u6e4d\u6d41\u53cd\u5e94\u4f7f\u7528\u6da1\u8017\u6563\u6a21\u578b\u5efa\u6a21\u3002\u5f97\u5230\u7684\u901f\u5ea6\u3001\u6e29\u5ea6\u548c\u7269\u8d28\u8d28\u91cf\u5206\u6570\u4e0e\u5b9e\u9a8c\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("round_jet_burner.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
