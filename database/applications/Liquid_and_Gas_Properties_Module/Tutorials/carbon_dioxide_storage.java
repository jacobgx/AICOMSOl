/*
 * carbon_dioxide_storage.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:38 by COMSOL 6.3.0.290. */
public class carbon_dioxide_storage {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Liquid_and_Gas_Properties_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("phtr", "PhaseTransportPorousMedia", new String[][]{{"s1", "s2"}});
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 3);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("x_well", "5440[m]", "x-coordinate well");
    model.param().set("y_well", "3300[m]", "y-coordinate well");
    model.param().set("r_injection", "15[kg/s]", "Injection rate");
    model.param().set("rho0", "1040[kg/m^3]", "Brine reference density");
    model.param().set("alpha", "6e-4[1/K]", "Brine thermal expansion coefficient");
    model.param().set("beta", "4e-10[1/Pa]", "Brine compressibility");
    model.param().set("T0", "360[K]", "Brine reference temperature");
    model.param().set("p0", "1e5[Pa]", "Brine reference pressure");
    model.param().set("s0_b", "0.2", "Residual brine saturation");
    model.param().set("s0_co2", "0", "Residual CO2 saturation");
    model.param().set("pc_e", "1e4[Pa]", "Entry pressure");
    model.param().set("lambda", "2", "Brooks-Corey parameter");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"carbon dioxide", "124-38-9", "CO2", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "PengRobinson");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "BrokawHighPressure");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u5bc6\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("funcname", "Density_carbon_dioxide_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Density_carbon_dioxide_Gas11_Dtemperature", "Density_carbon_dioxide_Gas11_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("funcname", "HeatCapacityCp_carbon_dioxide_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_carbon_dioxide_Gas12_Dtemperature", "HeatCapacityCp_carbon_dioxide_Gas12_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u70ed\u5bb9\u6bd4 (Cp/Cv) 1");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "HeatCapacityRatioCpCv_carbon_dioxide_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_carbon_dioxide_Gas13_Dtemperature", "HeatCapacityRatioCpCv_carbon_dioxide_Gas13_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("funcname", "ThermalConductivity_carbon_dioxide_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_carbon_dioxide_Gas14_Dtemperature", "ThermalConductivity_carbon_dioxide_Gas14_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("funcname", "Viscosity_carbon_dioxide_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("compounds", new String[]{"carbon dioxide"});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_carbon_dioxide_Gas15_Dtemperature", "Viscosity_carbon_dioxide_Gas15_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
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
         .createMaterial("comp1", "pp1", "Gas", new String[]{"carbon dioxide"}, new String[]{"1"}, new String[]{"density", "heatcapacitycp", "heatcapacityratiocpcv", "thermalconductivity", "viscosity"}, new String[][]{{"density", "Densitypp1"}, {"heatcapacitycp", "HeatCapacityCppp1"}, {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp1"}, {"thermalconductivity", "ThermalConductivitypp1"}, {"viscosity", "Viscositypp1"}}, "Identical", new String[][]{{"0", "273", "400", "30", "1e5", "4e7", "80"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"68", "273", "373", "20", "101325", "201325", "15"}, 
         {"48", "273", "373", "20", "101325", "201325", "15"}, 
         {"52", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mole"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Temp", "(-3000[m]-z)*0.03[K/m]+100[degC]");
    model.component("comp1").variable("var1").descr("Temp", "Geothermal temperature distribution");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "carbon_dioxide_storage_porper.csv");
    model.component("comp1").func("int1").setIndex("funcs", "porosity", 0, 0);
    model.component("comp1").func("int1").setIndex("fununit", 1, 0);
    model.component("comp1").func("int1").setIndex("argunit", "m", 0);
    model.component("comp1").func("int1").setIndex("argunit", "m", 1);
    model.component("comp1").func("int1").setIndex("argunit", "m", 2);
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("defvars", true);
    model.component("comp1").func("int1").set("frame", "mesh");
    model.component("comp1").func("int1").set("reinterp", true);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "carbon_dioxide_storage_porper.csv");
    model.component("comp1").func("int2").setIndex("funcs", "permeability", 0, 0);
    model.component("comp1").func("int2").setIndex("funcs", 2, 0, 1);
    model.component("comp1").func("int2").setIndex("fununit", "mD", 0);
    model.component("comp1").func("int2").setIndex("argunit", "m", 0);
    model.component("comp1").func("int2").setIndex("argunit", "m", 1);
    model.component("comp1").func("int2").setIndex("argunit", "m", 2);
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("defvars", true);
    model.component("comp1").func("int2").set("frame", "mesh");
    model.component("comp1").func("int2").set("reinterp", true);
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "density_brine");
    model.component("comp1").func("an1").set("expr", "rho0-rho0*alpha*(T-T0)+rho0*beta*(p-p0)");
    model.component("comp1").func("an1").set("args", "T, p");
    model.component("comp1").func("an1").set("fununit", "kg/m^3");
    model.component("comp1").func("an1").setIndex("argunit", "K", 0);
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 1);
    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0.05);
    model.component("comp1").func("rect1").set("upper", 25);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "carbon_dioxide_storage.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"x_well", "y_well", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", -3200, 2);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"x_well", "y_well", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", -2500, 2);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 100, 500});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"x_well-50", "y_well-50", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", -3200, 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1", "ls1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("imp1");
    model.component("comp1").geom("geom1").feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1(1)", 1, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("par1(2)", 1, 3);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("del2", 1);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.25, 0);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.75, 1);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("del3").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("del3").selection("input").set("pare1", 1, 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").set("family", "water");

    model.component("comp1").physics("phtr").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("phtr").prop("QuadratureSettings").set("AutomaticQuadrature", false);
    model.component("comp1").physics("phtr").prop("QuadratureSettings").set("IntegrationOrder", 4);
    model.component("comp1").physics("phtr").feature("pptp1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("phtr").feature("pptp1").set("minput_temperature", "Temp");
    model.component("comp1").physics("phtr").feature("pptp1").set("capillarypressuremodel", "BrooksCorey");
    model.component("comp1").physics("phtr").feature("pptp1").set("pec", "pc_e");
    model.component("comp1").physics("phtr").feature("pptp1").set("lambdap", "lambda");
    model.component("comp1").physics("phtr").feature("pptp1").set("phaselist_s1", "mat1");
    model.component("comp1").physics("phtr").feature("pptp1").set("phaselist_s2", "pp1mat1");
    model.component("comp1").physics("phtr").feature("pptp1").set("rhoint_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("pptp1").set("rhoint_s1", "density_brine(Temp,dl.pA)");
    model.component("comp1").physics("phtr").feature("pptp1").set("sr_s1", "s0_b");
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 2);
    model.component("comp1").physics("phtr").feature("sa1").selection().set(1, 2, 5, 12, 14, 21, 23, 24, 25);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1").set(1, 2, 5, 12, 14, 21, 23, 24, 25);

    model.component("comp1").physics("phtr").feature("sa1").selection().named("sel1");
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "porosity");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"permeability", "0", "0", "0", "permeability", "0", "0", "0", "permeability"});
    model.component("comp1").physics("dl").feature("init1").set("InitType", "H");
    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 2);
    model.component("comp1").physics("dl").feature("hh1").selection().named("sel1");

    model.component("comp1").multiphysics().create("wellmpe1", "Welle", 1);
    model.component("comp1").multiphysics("wellmpe1").selection().set(38);
    model.component("comp1").multiphysics("wellmpe1").set("M0", "r_injection*rect1(t/1[a])");
    model.component("comp1").multiphysics("wellmpe1").set("M0_s2", "r_injection*rect1(t/1[a])");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 120);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 8, 9, 11, 13, 19);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 9);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(38);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("zscale", 10);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,2.5,50)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1, 2, 3);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_s2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_s2").set("scaleval", "1");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2.5,50)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.005);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("stabcntrl", true);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("seDef", "Segregated");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.8);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1")
         .label("\u76f4\u63a5\uff0c\u591a\u5b54\u4ecb\u8d28\u591a\u76f8\u6d41 (mfpm1) (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("t1").feature("i1").set("maxlinit", 50);
    model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i1")
         .label("AMG\uff0c\u591a\u5b54\u4ecb\u8d28\u591a\u76f8\u6d41 (mfpm1)");
    model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.8);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").feature().remove("seDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("v1").set("scalemethod", "init");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 0.5);

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg1").set("defaultPlotID", "ResultDefaults_Phtr/icom2/pdef1/pcond1/pg1");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg2").set("defaultPlotID", "ResultDefaults_Phtr/icom2/pdef1/pcond1/pg2");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u901f\u5ea6 (dl)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("defaultPlotID", "PhysicsInterfaces_PorousMediaFlow/icom6/pdef1/pcond1/pg1");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("smooth", "internal");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "dl.U");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u538b\u529b (dl)");
    model.result("pg4").set("defaultPlotID", "PhysicsInterfaces_PorousMediaFlow/icom6/pdef1/pcond1/pg2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "p");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result("pg3").feature("str1").selection().geom("geom1", 2);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
    model.result("pg1").run();

    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("zscale", 5);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "porosity");
    model.result("pg5").feature("con1").set("number", 10);
    model.result("pg5").feature("con1").set("contourtype", "filled");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("paramindicator", "");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("con1").set("expr", "permeability");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("con1").set("expr", "porosity");
    model.result("pg5").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickx", 6000);
    model.result().dataset("cpl1").set("genparaactive", true);
    model.result().dataset("cpl1").set("genparadist", "range(-6000,500,9000)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("data", "cpl1");
    model.result("pg6").feature("con1").set("solutionparams", "parent");
    model.result("pg6").feature("con1").set("expr", "s2");
    model.result("pg6").feature("con1").set("number", 5);
    model.result("pg6").feature("con1").set("contourtype", "filled");

    model.component("comp1").view("view1").set("ssao", true);

    model.result("pg6").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().set(4, 9, 13, 19);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "s2");
    model.result("pg7").run();
    model.result("pg7").set("data", "surf1");
    model.result("pg7").setIndex("looplevel", 6, 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("paramindicator", "");
    model.result("pg7").set("plotarrayenable", true);
    model.result("pg7").set("arrayshape", "square");
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").feature("surf2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("data", "surf1");
    model.result("pg7").feature("surf2").setIndex("looplevel", 11, 0);
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature().duplicate("surf3", "surf2");
    model.result("pg7").feature("surf3").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").setIndex("looplevel", 16, 0);
    model.result("pg7").feature().duplicate("surf4", "surf3");
    model.result("pg7").feature("surf4").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").stepLast(0);
    model.result("pg7").run();

    model.view("view3").set("showgrid", false);

    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u5730\u8d28\u6784\u9020\u4e2d\u7684\u4e8c\u6c27\u5316\u78b3\u50a8\u5b58");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u632a\u5a01\u6d77\u5cb8\u90e8\u5206 Johansen \u5730\u5c42\u4e2d\u4e8c\u6c27\u5316\u78b3\u7684\u5730\u4e0b\u50a8\u5b58\u3002\u5728\u957f\u8fbe 25\u00a0\u5e74\u7684\u65f6\u95f4\u5185\uff0c\u4f7f\u7528\u6ce8\u5165\u4e95\u4ee5 15\u00a0kg/s \u7684\u901f\u7387\u6ce8\u5165\u4e8c\u6c27\u5316\u78b3\uff0c\u7136\u540e\u5c06\u4e95\u5173\u95ed\u3002\u8be5\u6a21\u578b\u7528\u4e8e\u8ba1\u7b97\u6ce8\u5165\u9636\u6bb5\u4ee5\u53ca\u6ce8\u5165\u4e95\u5173\u95ed\u540e 25\u00a0\u5e74\u95f4\uff0c\u4e8c\u6c27\u5316\u78b3\u5728\u4eff\u771f\u57df\u4e2d\u7684\u6269\u6563\u60c5\u51b5\u3002\u6a21\u578b\u4e2d\u5305\u542b\u6765\u81ea\u5728\u201c\u5f00\u653e\u6570\u636e\u5e93\u8bb8\u53ef\u8bc1\u201d(ODbL) \u4e0b\u63d0\u4f9b\u7684 Johansen \u6570\u636e\u96c6\u7684\u4fe1\u606f\u3002\u8be5\u6570\u636e\u5e93\u4e2d\u4e2a\u522b\u5185\u5bb9\u7684\u4efb\u4f55\u6743\u5229\u5747\u6839\u636e Database Contents License: http://opendatacommons.org/licenses/dbcl/1.0/ \u83b7\u5f97\u8bb8\u53ef\uff0c\u6570\u636e\u7531\u632a\u5a01\u77f3\u6cb9\u7406\u4e8b\u4f1a (NPD)\u3001\u5351\u5c14\u6839\u5927\u5b66 (UiB) \u548c SINTEF \u63d0\u4f9b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("carbon_dioxide_storage.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
