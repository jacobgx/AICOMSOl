/*
 * soec_thermodynamics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:06 by COMSOL 6.3.0.290. */
public class soec_thermodynamics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").insertFile("soec_thermodynamics_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("kappa", "1e-10[m^2]", "\u7535\u6781\u6e17\u900f\u7387");
    model.param("par2").set("por", "0.4", "\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("por_l", "0.4", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("I_avg", "10^4[A/m^2]", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("stoich", "1.2", "\u5165\u53e3\u84b8\u6c7d\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("A_cell", "W_cell*D_cell", "\u7535\u6c60\u9762\u79ef");
    model.param("par2")
         .set("Mflux_in", "stoich*18[g/mol]*I_avg*A_cell/(2*F_const)", "\u603b\u5165\u53e3\uff08\u84b8\u6c7d\uff09\u8d28\u91cf\u901a\u91cf");
    model.param("par2").set("T", "800[degC]", "\u7535\u89e3\u69fd\u6e29\u5ea6");
    model.param("par2").set("sigma_l", "5.2[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param("par2").set("sigma_s", "1000[S/m]", "\u7535\u6781\u7535\u5bfc\u7387");
    model.param("par2")
         .set("i0_H2", "0.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u53cd\u5e94");
    model.param("par2")
         .set("i0_O2", "0.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u53cd\u5e94");
    model.param("par2").set("S", "1e9[m^2/m^3]", "\u7535\u6781\u6bd4\u8868\u9762\u79ef");
    model.param("par2")
         .set("Rc", "1e-7[ohm*m^2]", "\u7535\u6781\u4e0e\u96c6\u6d41\u4f53\u7684\u63a5\u89e6\u7535\u963b");
    model.param("par2")
         .set("d_pore", "1e-6[m]", "\u6c14\u4f53\u6269\u6563\u7535\u6781\u7684\u5b54\u9699\u76f4\u5f84");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"water", "7732-18-5", "H2O", "COMSOL"}, {"hydrogen", "1333-74-0", "H2", "COMSOL"}});
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
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf - H2 \u548c H2O");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2");
    model.component("comp1").physics("chem").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics("chem").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "hydrogen", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.5", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "water", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.5", 1);
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp1").physics().move("chem", 0);
    model.component("comp1").physics("chem").label("\u5316\u5b66 - H2 \u548c H2O");
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("useElectrochemistry", true);
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("Ect_src", "root.comp1.cd.Ect");
    model.component("comp1").physics("chem").create("er1", "ElectrodeReactionChem", 3);
    model.component("comp1").physics("chem").feature("er1").set("formula", "H2O+2e<=>H2+O(ads)");
    model.component("comp1").physics("chem").feature("er1").set("Eeq_mat", "Automatic");
    model.component("comp1").physics("chem").feature("er1").set("i0_ref", "i0_H2");
    model.component("comp1").physics("chem").feature("er1").set("alphaa", 1.5);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "1e-5[mol/m^2]", 0, 0);

    model.thermodynamics().feature().create("pp2", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp2")
         .set("compoundlist", new String[][]{{"oxygen", "7782-44-7", "O2", "COMSOL"}});
    model.thermodynamics().feature("pp2").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp2").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp2").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp2").set("manager_version", "1.0");
    model.thermodynamics().feature("pp2").set("packagename", "pp2");
    model.thermodynamics().feature("pp2").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp2").set("managerindex", "0");
    model.thermodynamics().feature("pp2").set("packageid", "COMSOL2");
    model.thermodynamics().feature("pp2").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp2").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp2").set("LiquidCard", "None");
    model.thermodynamics().feature("pp2").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp2").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp2").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp2").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp2").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp2").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp2").storePersistenceData();
    model.thermodynamics().feature("pp2").set("WarningState", false);
    model.thermodynamics().feature("pp2").set("Warning", new String[]{""});
    model.thermodynamics().feature("pp2").label("\u6c14\u4f53\u7cfb\u7edf - O2");

    model.component("comp1").physics().create("chem2", "Chemistry", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem2").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem2").feature("spec1").set("specName", "O2");
    model.component("comp1").physics("chem2").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("chem2").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics("chem2").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem2").prop("mixture").set("PropertyPackage", "pp2");
    model.component("comp1").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "oxygen", 0);
    model.component("comp1").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "1.0", 0);
    model.component("comp1").physics("chem2").prop("mixture").set("mixture", "gas");
    model.component("comp1").physics().move("chem2", 1);
    model.component("comp1").physics("chem2").label("\u5316\u5b66 - O2");
    model.component("comp1").physics("chem2").prop("TPFeatureInput").set("useElectrochemistry", true);
    model.component("comp1").physics("chem2").prop("TPFeatureInput").set("Ect_src", "root.comp1.cd.Ect");
    model.component("comp1").physics("chem2").create("er1", "ElectrodeReactionChem", 3);
    model.component("comp1").physics("chem2").feature("er1").set("formula", "O2+4e<=>2O(ads)");
    model.component("comp1").physics("chem2").feature("er1").set("Eeq_mat", "Automatic");
    model.component("comp1").physics("chem2").feature("er1").set("i0_ref", "i0_O2");
    model.component("comp1").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", 1, 0, 0);
    model.component("comp1").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "1e-5[mol/m^2]", 0, 0);
    model.component("comp1").physics("cd").selection().set(1, 2, 3);
    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma_l", "0", "0", "0", "sigma_l", "0", "0", "0", "sigma_l"});
    model.component("comp1").physics("cd").create("pce1", "PorousElectrode", 3);
    model.component("comp1").physics("cd").feature("pce1")
         .label("\u591a\u5b54\u7535\u6781 - H2 \u548c H2O\uff08\u9634\u6781\uff09");
    model.component("comp1").physics("cd").feature("pce1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("cd").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("pce1")
         .set("sigmal", new String[]{"sigma_l", "0", "0", "0", "sigma_l", "0", "0", "0", "sigma_l"});
    model.component("comp1").physics("cd").feature("pce1").set("epsl", "por_l");
    model.component("comp1").physics("cd").feature("pce1").set("sigmas_mat", "userdef");
    model.component("comp1").physics("cd").feature("pce1")
         .set("sigmas", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("cd").feature("pce1").set("epss", "1-por_l");
    model.component("comp1").physics("cd").feature("pce1").feature("per1").set("Eeq", "chem.Eeq_er1");
    model.component("comp1").physics("cd").feature("pce1").feature("per1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cd").feature("pce1").feature("per1").set("ilocmat", "chem.iloc_er1");
    model.component("comp1").physics("cd").feature("pce1").feature("per1").set("Av", "S");
    model.component("comp1").physics("cd").feature().duplicate("pce2", "pce1");
    model.component("comp1").physics("cd").feature("pce2")
         .label("\u591a\u5b54\u7535\u6781 - O2\uff08\u9633\u6781\uff09");
    model.component("comp1").physics("cd").feature("pce2").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("cd").feature("pce2").feature("per1").set("Eeq", "chem2.Eeq_er1");
    model.component("comp1").physics("cd").feature("pce2").feature("per1").set("ilocmat", "chem2.iloc_er1");
    model.component("comp1").physics("cd").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("cd").feature("egnd1").selection().named("geom1_sel3");
    model.component("comp1").physics("cd").feature("egnd1").set("IncludeContactResistance", true);
    model.component("comp1").physics("cd").feature("egnd1").set("Rc", "Rc");
    model.component("comp1").physics("cd").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("cd").feature("ec1").selection().named("geom1_sel4");
    model.component("comp1").physics("cd").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("ec1").set("Ias", "I_avg");
    model.component("comp1").physics("cd").feature("ec1").set("IncludeContactResistance", true);
    model.component("comp1").physics("cd").feature("ec1").set("Rc", "Rc");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "H_ch*0.8");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(5, 6, 7);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4, 8);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_ext1_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "H_ch/10");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature().duplicate("dis3", "dis1");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").set("reverse", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("col1", "Color");
    model.result("pg2").feature("arwv1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("expr", new String[]{"phis"});
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"cd.Isx", "cd.Isy", "cd.Isz"});
    model.result("pg3").feature("arwv1").set("arrowbase", "center");
    model.result("pg3").feature("arwv1").set("color", "gray");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"cd.Isx", "cd.Isy", "cd.Isz"});
    model.result("pg4").feature("arwv1").set("arrowbase", "center");
    model.result("pg4").feature("arwv1").set("color", "gray");
    model.result("pg4").feature("arwv1").create("col1", "Color");
    model.result("pg4").feature("arwv1").feature("col1").set("expr", "root.comp1.cd.IsMag");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics().create("br", "PorousMediaFlowBrinkman", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/br", true);
    model.study("std1").feature("stat").setSolveFor("/physics/br", true);

    model.component("comp1").physics("br").prop("ShapeProperty").set("order_fluid", "1");
    model.component("comp1").physics("br").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("tcs", "ConcentratedSpeciesInPorousMedia", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);

    model.component("comp1").physics("tcs").prop("TransportMechanism").set("MassTransferInPorousMedia", "1");

    model.component("comp1").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 3);

    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nirf1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nirf1", true);

    model.component("comp1").multiphysics("nirf1").set("Fluid_physics", "br");
    model.component("comp1").multiphysics("nirf1").set("Species_physics", "tcs");

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set();

    model.component("comp1").multiphysics("nirf1").set("temperature", "T");

    model.component("comp1").material("pmat1").selection().named("geom1_blk3_dom");
    model.component("comp1").material("pmat1").set("porosity", "por");
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa"});

    model.component("comp1").physics("br").selection().set(3, 4, 5, 6, 7, 8);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u6c14\u4f53\u57df");
    model.component("comp1").selection("sel1").set(3, 4, 5, 6, 7, 8);

    model.component("comp1").physics("br").selection().named("sel1");
    model.component("comp1").physics("br").prop("PhysicalModelProperty").set("StokesFlowProp", false);
    model.component("comp1").physics("br").create("fp1", "FluidProperties", 3);
    model.component("comp1").physics("br").feature("fp1").selection().named("geom1_ext1_dom");
    model.component("comp1").physics("br").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("br").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("br").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("br").feature("inl1").set("mfr", "Mflux_in");
    model.component("comp1").physics("br").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("br").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("br").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("tcs").selection().named("sel1");
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "MaxwellStefan");
    model.component("comp1").physics("tcs").field("massfraction").component(1, "wH2");
    model.component("comp1").physics("tcs").field("massfraction").component(2, "wH2O");
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_wH2_src", "root.comp1.chem.M_H2", 0);
    model.component("comp1").physics("tcs").feature("sp1").setIndex("M_wH2O_src", "root.comp1.chem.M_H2O", 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_H2_H2O", 0, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .set("FluidDiffusivityModelType", "BruggemanModel");
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1").set("d_pore", "d_pore");
    model.component("comp1").physics("tcs").create("pec1", "PorousElectrodeCoupling", 3);
    model.component("comp1").physics("tcs").feature("pec1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("tcs").feature("pec1").feature("rc1")
         .set("iv_src", "root.comp1.cd.pce1.per1.iv");
    model.component("comp1").physics("tcs").feature("pec1").feature("rc1").set("nm", 2);
    model.component("comp1").physics("tcs").feature("pec1").feature("rc1").setIndex("Vi", 1, 0);
    model.component("comp1").physics("tcs").feature("pec1").feature("rc1").setIndex("Vi", -1, 1);
    model.component("comp1").physics("tcs").create("in1", "Inflow", 2);
    model.component("comp1").physics("tcs").feature("in1").selection().named("geom1_sel1");
    model.component("comp1").physics("tcs").feature("in1").set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("tcs").feature("in1").setIndex("J0", "Mflux_in", 1);
    model.component("comp1").physics("tcs").create("out1", "Outflow", 2);
    model.component("comp1").physics("tcs").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("tcs").create("cdm1", "Fluid", 3);
    model.component("comp1").physics("tcs").feature("cdm1").selection().named("geom1_ext1_dom");
    model.component("comp1").physics("tcs").feature("cdm1").setIndex("DiffusivityFrom", "comp1.chem.D_H2_H2O", 0, 0);
    model.component("comp1").physics("tcs").feature("init1").set("MixtureSpecification", "MoleFractions");
    model.component("comp1").physics("tcs").feature("init1").setIndex("x0", "0.95*(1-x/(W_cell*stoich))", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wH2", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wH2O", 1, 0);

    model.study("std1").feature("stat").setSolveFor("/physics/br", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nirf1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/cd", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/nirf1", false);
    model.study("std1").create("stat3", "Stationary");

    model.sol().remove("sol1");
    model.sol().remove("sol2");

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s4").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("col1", "Color");
    model.result("pg2").feature("arwv1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("expr", new String[]{"phis"});
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"cd.Isx", "cd.Isy", "cd.Isz"});
    model.result("pg3").feature("arwv1").set("arrowbase", "center");
    model.result("pg3").feature("arwv1").set("color", "gray");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"cd.Isx", "cd.Isy", "cd.Isz"});
    model.result("pg4").feature("arwv1").set("arrowbase", "center");
    model.result("pg4").feature("arwv1").set("color", "gray");
    model.result("pg4").feature("arwv1").create("col1", "Color");
    model.result("pg4").feature("arwv1").feature("col1").set("expr", "root.comp1.cd.IsMag");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (br)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("\u5207\u9762");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("expr", "br.U");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(7, 8, 9, 10, 13, 14, 15, 17, 18, 21, 23, 24, 25, 26, 28, 30, 31, 32, 33, 35, 37, 38, 39, 40, 41, 43, 44, 47, 49, 51, 52, 55);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u538b\u529b (br)");
    model.result("pg6").set("data", "surf1");
    model.result("pg6").set("frametype", "spatial");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").label("\u6d53\u5ea6, H2, \u6d41\u7ebf (tcs)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1")
         .set("expr", new String[]{"tcs.tflux_wH2x", "tcs.tflux_wH2y", "tcs.tflux_wH2z"});
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").feature("str1").create("col", "Color");
    model.result("pg7").feature("str1").feature("col").set("expr", "root.comp1.tcs.c_wH2");
    model.result("pg7").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg7").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg7").feature("str1").set("linetype", "ribbon");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").label("\u6d53\u5ea6, H2, \u8868\u9762 (tcs)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"tcs.c_wH2"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").label("\u6d53\u5ea6, H2O, \u6d41\u7ebf (tcs)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1")
         .set("expr", new String[]{"tcs.tflux_wH2Ox", "tcs.tflux_wH2Oy", "tcs.tflux_wH2Oz"});
    model.result("pg9").feature("str1").set("posmethod", "start");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").create("col", "Color");
    model.result("pg9").feature("str1").feature("col").set("expr", "root.comp1.tcs.c_wH2O");
    model.result("pg9").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg9").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg9").feature("str1").set("linetype", "ribbon");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").label("\u6d53\u5ea6, H2O, \u8868\u9762 (tcs)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"tcs.c_wH2O"});
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickzmethod", "coord");
    model.result("pg5").feature("slc1").set("quickz", "H_cell-H_ch/2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg5").feature("str1").selection().named("geom1_sel1");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowdistr", "equalinvtime");
    model.result("pg5").feature("str1").set("color", "black");
    model.result("pg5").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u5bc6\u5ea6");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "br.rho");
    model.result("pg11").feature("surf1").set("descr", "\u5bc6\u5ea6");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u9ecf\u5ea6");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "br.mu");
    model.result("pg12").feature("surf1").set("descr", "\u52a8\u529b\u9ecf\u5ea6");
    model.result("pg12").run();
    model.result("pg8").run();
    model.result("pg10").run();
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u6469\u5c14\u5206\u6570\u548c\u901a\u91cf\uff0cH2");
    model.result("pg13").set("edges", false);
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1")
         .set("expr", new String[]{"tcs.tflux_wH2x", "tcs.tflux_wH2y", "tcs.tflux_wH2z"});
    model.result("pg13").feature("str1").set("descr", "\u603b\u901a\u91cf");
    model.result("pg13").feature("str1").set("selnumber", 30);
    model.result("pg13").feature("str1").selection().named("geom1_sel2");
    model.result("pg13").feature("str1").set("pointtype", "arrow");
    model.result("pg13").feature("str1").set("arrowdistr", "equalinvtime");
    model.result("pg13").feature("str1").set("color", "black");
    model.result("pg13").feature("str1").create("sel1", "Selection");
    model.result("pg13").feature("str1").feature("sel1").selection().named("geom1_ext1_dom");
    model.result("pg13").run();
    model.result("pg13").create("vol1", "Volume");
    model.result("pg13").feature("vol1").set("expr", "tcs.x_wH2");
    model.result("pg13").feature("vol1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg13").feature("vol1").create("sel1", "Selection");
    model.result("pg13").feature("vol1").feature("sel1").selection().named("geom1_blk3_dom");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u6a2a\u622a\u9762\u4e0a\u7684\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg14").set("edges", false);
    model.result("pg14").create("slc1", "Slice");
    model.result("pg14").feature("slc1").set("expr", "cd.Ilz");
    model.result("pg14").feature("slc1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg14").feature("slc1").set("quickplane", "xy");
    model.result("pg14").feature("slc1").set("quickzmethod", "coord");
    model.result("pg14").feature("slc1").set("quickz", "H_gde+H_el/2");
    model.result("pg14").run();

    model.title("\u4f7f\u7528\u70ed\u529b\u5b66\u5206\u6790\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u69fd");

    model
         .description("\u672c\u4f8b\u5bf9\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u6c60\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u6c34\u84b8\u6c14\u5728\u9634\u6781\u8fd8\u539f\u6210\u6c22\u6c14\uff0c\u9633\u6781\u4ea7\u751f\u4e86\u6c27\u6c14\u3002\u7535\u89e3\u69fd\u4e2d\u7684\u7535\u6d41\u5206\u5e03\u4e0e\u9634\u6781\u4e0a\u6c22\u548c\u6c34\u7684\u8d28\u91cf\u4f20\u9012\u548c\u52a8\u91cf\u4f20\u9012\u76f8\u8026\u5408\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u70ed\u529b\u5b66\u201d\u548c\u201c\u5316\u5b66\u201d\u8282\u70b9\u81ea\u52a8\u5b9a\u4e49\u9634\u6781\u6c14\u4f53\u6df7\u5408\u7269\u7684\u5c5e\u6027\uff0c\u4ee5\u53ca\u7535\u6781\u53cd\u5e94\u7684\u5e73\u8861\u7535\u4f4d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("soec_thermodynamics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
