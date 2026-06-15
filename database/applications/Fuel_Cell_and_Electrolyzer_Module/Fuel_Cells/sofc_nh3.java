/*
 * sofc_nh3.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:10 by COMSOL 6.3.0.290. */
public class sofc_nh3 {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Oxide");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "700[degC]");
    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp").field("velocity").field("ua");
    model.component("comp1").physics("fp").field("velocity").component(new String[]{"ua", "va", "wa"});
    model.component("comp1").physics("fp").field("pressure").field("pa");
    model.component("comp1").physics().create("fp2", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp2").field("velocity").field("uc");
    model.component("comp1").physics("fp2").field("velocity").component(new String[]{"uc", "vc", "wc"});
    model.component("comp1").physics("fp2").field("pressure").field("pc");
    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/fc", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/fp", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/fp2", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ht", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fp2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A_plate", "L*D", "\u7535\u6c60\u9762\u79ef");
    model.param().set("D", "1[m]", "\u7535\u6c60\u6df1\u5ea6");
    model.param().set("d_pore", "0.5[um]", "\u7535\u6781\u4e2d\u7684\u5b54\u9699\u76f4\u5f84");
    model.param().set("da", "500[um]", "\u9633\u6781\u539a\u5ea6");
    model.param().set("dc", "100[um]", "\u9634\u6781\u539a\u5ea6");
    model.param().set("dg", "1[mm]", "\u6c14\u4f53\u6d41\u9053\u9ad8\u5ea6");
    model.param().set("di", "500[um]", "\u4e92\u8fde\u5c42\u539a\u5ea6");
    model.param().set("din", "4*dg", "\u5165\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("dm", "100[um]", "\u819c\u539a\u5ea6");
    model.param().set("epsg", "0.4", "\u7535\u6781\u5b54\u9699\u7387");
    model.param().set("epsl", "0.4", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("I_avg", "I_avg_init", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("I_avg_final", "1[A/cm^2]", "\u626b\u63cf\u4e2d\u7684\u6700\u7ec8\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("I_avg_init", "0.01[A/cm^2]", "\u626b\u63cf\u4e2d\u7684\u521d\u59cb\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("I_flow", "max(I_avg,0.1[A/cm^2])*A_plate", "\u7528\u4e8e\u5316\u5b66\u8ba1\u91cf\u6d41\u91cf\u8ba1\u7b97\u7684\u603b\u7535\u6d41");
    model.param().set("I_tot", "I_avg*A_plate", "\u603b\u7535\u6c60\u7535\u6d41");
    model.param()
         .set("i0_ref_H2", "1[A/m^2]", "\u6c22\u6c27\u5316\u53cd\u5e94\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_O2", "1[A/m^2]", "\u6c27\u8fd8\u539f\u53cd\u5e94\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("j_N2_cath", "j_O2_cath*x0_N2_cath/x0_O2_cath", "\u6c2e\u6469\u5c14\u6d41\u7387");
    model.param().set("j_NH3_an", "stoich_NH3*I_flow/(3*F_const)", "\u6c28\u6469\u5c14\u6d41\u7387");
    model.param().set("j_O2_cath", "stoich_O2*I_flow/(4*F_const)", "\u6c27\u6469\u5c14\u6d41\u7387");
    model.param().set("ka", "11[W/m/K]", "\u9633\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param()
         .set("kappag_GDE", "1e-10[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.param().set("kc", "6[W/m/K]", "\u9634\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("ki", "1.1[W/m/K]", "\u4e92\u8fde\u5c42\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("km", "2.7[W/m/K]", "\u7535\u89e3\u8d28\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("L", "20[mm]", "\u5e73\u9762 SOEC \u957f\u5ea6");
    model.param().set("M_aux", "RelMolecularMass_ammonia12", "\u6c28\u6469\u5c14\u8d28\u91cf");
    model.param().set("m_cath", "m_O2_cath+m_N2_cath", "\u9634\u6781\u603b\u8d28\u91cf\u6d41\u7387");
    model.param().set("m_N2_cath", "j_N2_cath*28[g/mol]", "\u6c2e\u8d28\u91cf\u6d41\u7387");
    model.param().set("m_NH3_an", "j_NH3_an*17[g/mol]", "\u6c28\u8d28\u91cf\u6d41\u901f");
    model.param().set("m_O2_cath", "j_O2_cath*32[g/mol]", "\u6c27\u8d28\u91cf\u6d41\u7387");
    model.param().set("nu_aux", "FullerDiffusionVolume_ammonia11", "\u6c28\u5bcc\u52d2\u6269\u6563\u4f53\u79ef");
    model.param().set("S", "1e8[m^2/m^3]", "\u7535\u6781\u6bd4\u8868\u9762\u79ef");
    model.param().set("stoich_NH3", "1.25", "\u6c28\u6d41\u91cf\u7684\u5316\u5b66\u8ba1\u91cf");
    model.param().set("stoich_O2", "4", "\u6c27\u6d41\u91cf\u7684\u5316\u5b66\u8ba1\u91cf");
    model.param().set("T_in", "973[K]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("Tb_aux", "NormalBoilingPointTemperature_ammonia13", "\u6c28\u7684\u6cb8\u70b9\u6e29\u5ea6");
    model.param().set("taug", "3", "\u7535\u6781\u8fc2\u66f2\u5ea6");
    model.param().set("W", "3.7[mm]", "\u5e73\u9762 SOEC \u539a\u5ea6");
    model.param()
         .set("w0_N2_cath", "x0_N2_cath*28[g/mol]/(x0_N2_cath*28[g/mol]+x0_O2_cath*32[g/mol])", "\u5165\u53e3\u5904 N2 \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param().set("w0_NH3_an", "0.95", "\u5165\u53e3\u5904 NH3 \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param().set("w0_N2_an", "0.01", "\u5165\u53e3\u5904 N2 \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param().set("w0_H2O_an", "0.01", "\u5165\u53e3\u5904 H2O \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param().set("x0_H2_an", "1-x0_N2_an-x0_H2O_an-x0_NH3_an", "H2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_H2O_an", "0.0001", "H2O \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_N2_an", "0.7498", "N2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_N2_cath", "0.79", "N2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_NH3_an", "0.0001", "NH3 \u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_O2_cath", "1-x0_N2_cath", "O2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "W"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "di", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dg", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "da", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dm", 3);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dc", 4);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dg", 5);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"din", "dg"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-din", "di"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"din", "dg"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-din", "di+dg+da+dm+dc"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("r_NH3", "4e15*exp(-196200[J/mol]/(R_const*T))[mol/m^3/s]*fc.paux[1/atm]", "\u53cd\u5e94\u901f\u7387\uff0c\u6c28\u7684\u70ed\u5206\u89e3");
    model.component("comp1").variable("var1")
         .set("H_aux", "EnthalpyF_ammonia_Gas11(T,fc.pA)", "\u6c28\u7684\u6469\u5c14\u6807\u51c6\u751f\u6210\u7113");
    model.component("comp1").variable("var1")
         .set("S_aux", "EntropyF_ammonia_Gas12(T,fc.pA)", "\u6c28\u7684\u6469\u5c14\u6807\u51c6\u71b5");
    model.component("comp1").variable("var1")
         .set("Cp_aux", "HeatCapacityCp_ammonia_Gas13(T,fc.pA)", "\u6c28\u7684\u6469\u5c14\u70ed\u5bb9");
    model.component("comp1").variable("var1")
         .set("mu_aux", "Viscosity_ammonia_Gas15(T,fc.pA)", "\u6c28\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.component("comp1").variable("var1")
         .set("k_aux", "ThermalConductivity_ammonia_Gas14(T,fc.pA)", "\u6c28\u7684\u5bfc\u70ed\u7cfb\u6570");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"ammonia", "7664-41-7", "H3N", "COMSOL"}});
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
    model.thermodynamics().feature("pp1").feature().create("const1", "CompoundConstant");
    model.thermodynamics().feature("pp1").feature("const1").label("\u5bcc\u52d2\u6269\u6563\u4f53\u79ef 1");
    model.thermodynamics().feature("pp1").feature("const1").set("funcname", "FullerDiffusionVolume_ammonia11");
    model.thermodynamics().feature("pp1").feature("const1").set("property", "FullerDiffusionVolume");
    model.thermodynamics().feature("pp1").feature("const1")
         .set("propertydescr", "\u5bcc\u52d2\u6269\u6563\u4f53\u79ef");
    model.thermodynamics().feature("pp1").feature("const1").set("unit", "cm^3");
    model.thermodynamics().feature("pp1").feature("const1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("const1").set("value", "20.699999999999999");
    model.thermodynamics().feature("pp1").feature("const1").set("valueWithUnit", "20.699999999999999 cm^3");
    model.thermodynamics().feature("pp1").feature("const1").set("compound", "ammonia");
    model.thermodynamics().feature("pp1").feature("const1").comments("User defined");
    model.thermodynamics().feature("pp1").feature().create("const2", "CompoundConstant");
    model.thermodynamics().feature("pp1").feature("const2").label("\u6469\u5c14\u8d28\u91cf 1");
    model.thermodynamics().feature("pp1").feature("const2").set("funcname", "RelMolecularMass_ammonia12");
    model.thermodynamics().feature("pp1").feature("const2").set("property", "RelMolecularMass");
    model.thermodynamics().feature("pp1").feature("const2").set("propertydescr", "\u6469\u5c14\u8d28\u91cf");
    model.thermodynamics().feature("pp1").feature("const2").set("unit", "g/mol");
    model.thermodynamics().feature("pp1").feature("const2").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("const2").set("value", "17.030999999999999");
    model.thermodynamics().feature("pp1").feature("const2").set("valueWithUnit", "17.030999999999999 g/mol");
    model.thermodynamics().feature("pp1").feature("const2").set("compound", "ammonia");
    model.thermodynamics().feature("pp1").feature("const2").comments("From chemical formula H3N");
    model.thermodynamics().feature("pp1").feature().create("const3", "CompoundConstant");
    model.thermodynamics().feature("pp1").feature("const3").label("\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("const3")
         .set("funcname", "NormalBoilingPointTemperature_ammonia13");
    model.thermodynamics().feature("pp1").feature("const3").set("property", "NormalBoilingPointTemperature");
    model.thermodynamics().feature("pp1").feature("const3")
         .set("propertydescr", "\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6");
    model.thermodynamics().feature("pp1").feature("const3").set("unit", "K");
    model.thermodynamics().feature("pp1").feature("const3").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("const3").set("value", "239.84999999999999");
    model.thermodynamics().feature("pp1").feature("const3").set("valueWithUnit", "239.84999999999999 K");
    model.thermodynamics().feature("pp1").feature("const3").set("compound", "ammonia");
    model.thermodynamics().feature("pp1").feature("const3")
         .comments("The CRC handbook of thermal engineering. Responsibility: editor-in-chief, Frank Kreith., CRC Press, c2000");
    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u751f\u6210\u7113 1");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("funcname", "EnthalpyF_ammonia_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "EnthalpyF");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u751f\u6210\u7113");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "J/mol");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("compounds", new String[]{"ammonia"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"EnthalpyF_ammonia_Gas11_Dtemperature", "EnthalpyF_ammonia_Gas11_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u751f\u6210\u71b5 1");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("funcname", "EntropyF_ammonia_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "EntropyF");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u751f\u6210\u71b5");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("compounds", new String[]{"ammonia"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"EntropyF_ammonia_Gas12_Dtemperature", "EntropyF_ammonia_Gas12_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u70ed\u5bb9 (Cp) 1");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("funcname", "HeatCapacityCp_ammonia_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "J/mol/K");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("compounds", new String[]{"ammonia"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityCp_ammonia_Gas13_Dtemperature", "HeatCapacityCp_ammonia_Gas13_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u5bfc\u70ed\u7cfb\u6570 1");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("funcname", "ThermalConductivity_ammonia_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("compounds", new String[]{"ammonia"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_ammonia_Gas14_Dtemperature", "ThermalConductivity_ammonia_Gas14_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5").label("\u9ecf\u5ea6 1");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("funcname", "Viscosity_ammonia_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("compounds", new String[]{"ammonia"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mole");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_ammonia_Gas15_Dtemperature", "Viscosity_ammonia_Gas15_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("fc").selection().set(1, 2, 4, 5, 6, 7, 8);
    model.component("comp1").physics("fc").prop("H2GasMixture").set("N2", true);
    model.component("comp1").physics("fc").prop("H2GasMixture").set("aux", true);
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "T_in");
    model.component("comp1").physics("fc").create("mem1", "Membrane", 2);
    model.component("comp1").physics("fc").feature("mem1").selection().set(6);
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("h2gde1").selection().set(5);
    model.component("comp1").physics("fc").feature("h2gde1").set("epsl", "epsl");
    model.component("comp1").physics("fc").feature("h2gde1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("fc").feature("h2gde1").set("epsg", "epsg");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("taug", new String[]{"taug", "0", "0", "0", "taug", "0", "0", "0", "taug"});
    model.component("comp1").physics("fc").feature("h2gde1").set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("fc").feature("h2gde1").set("d_pore", "d_pore");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "S");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("o2gde1").selection().set(7);
    model.component("comp1").physics("fc").feature("o2gde1").set("epsl", "epsl");
    model.component("comp1").physics("fc").feature("o2gde1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "epsg");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("taug", new String[]{"taug", "0", "0", "0", "taug", "0", "0", "0", "taug"});
    model.component("comp1").physics("fc").feature("o2gde1").set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("fc").feature("o2gde1").set("d_pore", "d_pore");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_ref_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphaa", 0.5);
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "S");
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 2);
    model.component("comp1").physics("fc").feature("h2fch1").selection().set(1, 4);
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 2);
    model.component("comp1").physics("fc").feature("o2fch1").selection().set(2, 8);
    model.component("comp1").physics("fc").feature("ecph1").create("inito2dom1", "InitialValuesO2Domains", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").selection().set(7);
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(12);
    model.component("comp1").physics("fc").feature("ecph1").create("ec1", "ElectrodeCurrent", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").selection().set(18);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("Its", "-I_tot");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("d", "D");
    model.component("comp1").physics("fc").feature("h2gasph1").set("Maux", "M_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("H_aux", "H_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("S_aux", "S_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("Cp_aux", "Cp_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("nu_aux", "nu_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("mu_aux", "mu_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("k_aux", "k_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").set("Tb_aux", "Tb_aux");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("x0H2O", "x0_H2O_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("x0N2", "x0_N2_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("x0aux", "x0_NH3_an");
    model.component("comp1").physics("fc").feature("h2gasph1").create("reac1", "ReactionSources", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("reac1").selection().set(5);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("reac1").set("SourceType", "Molar");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("reac1").set("rH2", "1.5*r_NH3");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("reac1").set("rN2", "0.5*r_NH3");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("reac1").set("raux", "-r_NH3");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().set(1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("J0aux", "m_NH3_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("w0bndH2O", "w0_H2O_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("w0bndN2", "w0_N2_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("w0bndaux", "w0_NH3_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("d", "D");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().set(23, 24);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0N2", "x0_N2_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().set(4);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("J0N2", "m_N2_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("w0bndN2", "w0_N2_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("d", "D");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().set(26, 27);
    model.component("comp1").physics("fp").selection().set(1, 4, 5);
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("fp").feature("porous1").selection().set(5);
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "epsg");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappag_GDE", "0", "0", "0", "kappag_GDE", "0", "0", "0", "kappag_GDE"});
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("fp").feature("inl1").selection().set(1);
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("fp").feature("inl1").set("mfr", "m_NH3_an");
    model.component("comp1").physics("fp").feature("inl1").set("Dbnd", "D");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("fp").feature("out1").selection().set(23);
    model.component("comp1").physics("fp2").selection().set(2, 7, 8);
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp2").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("fp2").feature("porous1").selection().set(7);
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p", "epsg");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappag_GDE", "0", "0", "0", "kappag_GDE", "0", "0", "0", "kappag_GDE"});
    model.component("comp1").physics("fp2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("fp2").feature("inl1").selection().set(4);
    model.component("comp1").physics("fp2").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("fp2").feature("inl1").set("mfr", "m_cath");
    model.component("comp1").physics("fp2").feature("inl1").set("Dbnd", "D");
    model.component("comp1").physics("fp2").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("fp2").feature("out1").selection().set(27);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_in");
    model.component("comp1").physics("ht").feature("fluid1").selection().set(1, 2, 4, 8);
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.fc.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "root.comp1.fc.Cp_mix");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp1").physics("ht").create("porous1", "PorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("porous1").selection().set(5);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("k_mat", "root.comp1.fc.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp1.fc.Cp_mix");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "epsg");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_b", new String[]{"ka", "0", "0", "0", "ka", "0", "0", "0", "ka"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").create("porous2", "PorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("porous2").selection().set(7);
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("k_mat", "root.comp1.fc.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("rho_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("Cp_mat", "root.comp1.fc.Cp_mix");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro", "epsg");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1")
         .set("k_b", new String[]{"kc", "0", "0", "0", "kc", "0", "0", "0", "kc"});
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid2").selection().set(6);
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"km", "0", "0", "0", "km", "0", "0", "0", "km"});
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(1, 4);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(23, 24, 26, 27);
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 1);
    model.component("comp1").physics("ht").feature("pc1").selection().set(8, 21);

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 2);
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics().create("nitf2", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf2").set("Fluid_physics", "fp2");
    model.component("comp1").multiphysics().create("rfh1", "ReactingFlowH2GasPhase", 2);
    model.component("comp1").multiphysics().create("rfo1", "ReactingFlowO2GasPhase", 2);
    model.component("comp1").multiphysics("rfo1").set("Fluid_physics", "fp2");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "fc.phis0_ec1");
    model.component("comp1").probe("var1").set("table", "new");
    model.component("comp1").probe("var1").set("window", "window1");
    model.component("comp1").probe("var1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(14);
    model.component("comp1").probe("point1").set("expr", "T");
    model.component("comp1").probe("point1").set("table", "new");
    model.component("comp1").probe("point1").set("window", "window1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Yttria-Stabilized Zirconia, 8YSZ, (ZrO2)0.92-(Y2O3)0.08");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "log_sigmaT_vs_invT");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0.8202666666666667", "2.2431906614786"}, 
         {"0.8526222222222222", "2.0972762645914402"}, 
         {"0.8912", "1.922178988326849"}, 
         {"0.9335111111111112", "1.7373540856031133"}, 
         {"0.9820444444444445", "1.5233463035019446"}, 
         {"1.0268444444444444", "1.319066147859922"}, 
         {"1.0766222222222224", "1.0856031128404666"}, 
         {"1.1463111111111113", "0.745136186770428"}, 
         {"1.2160000000000002", "0.3754863813229572"}, 
         {"1.296888888888889", "-0.03307392996108938"}, 
         {"1.384", "-0.5097276264591439"}, 
         {"1.4860444444444445", "-1.073929961089494"}, 
         {"1.6042666666666667", "-1.735408560311284"}, 
         {"1.7424000000000002", "-2.5136186770428006"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 2);
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1/K"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg", "0", "0", "0", "(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg", "0", "0", "0", "(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Electrolytes for solid oxide fuel cells, J. Fergus, Journal of Power Sources 162 (2006) 30\u201340.\n\nConductivity values averaged from Figure 2.\n");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("T_reg", "max(min(T,1/0.8203e-3),1/1.74e-3)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .descr("T_reg", "Temperature (regularized to valid range)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").selection().set(5, 6, 7);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().set(3, 9);

    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(8, 10, 12, 14, 16, 18, 20, 21);
    model.component("comp1").mesh("mesh1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis2").selection().set(7, 19, 22, 28);
    model.component("comp1").mesh("mesh1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis3").selection().set(1, 4, 9, 17, 23, 27);
    model.component("comp1").mesh("mesh1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis3").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("dis3").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("dis3").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis4").selection().set(13, 25);
    model.component("comp1").mesh("mesh1").feature("dis4").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis5").selection().set(15, 26);
    model.component("comp1").mesh("mesh1").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis5").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("dis5").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("dis5").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis6").selection().set(11, 24);
    model.component("comp1").mesh("mesh1").feature("dis6").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis6").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("dis6").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("dis6").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("dis6").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("dis7", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis7").selection().set(2, 3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("dis7").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ech1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf2", false);
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "A_plate", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat2").setIndex("pname", "A_plate", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat2").setIndex("pname", "I_avg", 0);
    model.study("std1").feature("stat2")
         .setIndex("plistarr", "I_avg_init I_avg_final/30 range(I_avg_final/10, I_avg_final/10, I_avg_final)", 0);
    model.study("std1").feature("stat2").setIndex("punit", "A/cm^2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s3").feature("fc1").set("maxiter", 100);
    model.sol("sol1").feature("s3").feature("fc1").set("termonres", false);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 12, 0);
    model.result("pg2").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"fc.phis"});
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"fc.Isx", "fc.Isy"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg2").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 12, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"fc.phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"fc.Ilx", "fc.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result("pg3").feature("arws1").create("filt1", "Filter");
    model.result("pg3").feature("arws1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg3").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 12, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xH2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 12, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, O2 (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 12, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, H2O (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 12, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, N2 (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 12, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, aux (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 aux:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"fc.xaux"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"fc.tfluxauxx", "fc.tfluxauxy"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u901f\u5ea6 (fp)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "fp.U");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u538b\u529b (fp)");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").feature().create("con1", "Contour");
    model.result("pg10").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg10").feature("con1").set("showsolutionparams", "on");
    model.result("pg10").feature("con1").set("expr", "pa");
    model.result("pg10").feature("con1").set("number", 40);
    model.result("pg10").feature("con1").set("levelrounding", false);
    model.result("pg10").feature("con1").set("smooth", "internal");
    model.result("pg10").feature("con1").set("showsolutionparams", "on");
    model.result("pg10").feature("con1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u901f\u5ea6 (fp2)");
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u8868\u9762");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("expr", "fp2.U");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u538b\u529b (fp2)");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("con1", "Contour");
    model.result("pg12").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg12").feature("con1").set("showsolutionparams", "on");
    model.result("pg12").feature("con1").set("expr", "pc");
    model.result("pg12").feature("con1").set("number", 40);
    model.result("pg12").feature("con1").set("levelrounding", false);
    model.result("pg12").feature("con1").set("smooth", "internal");
    model.result("pg12").feature("con1").set("showsolutionparams", "on");
    model.result("pg12").feature("con1").set("data", "parent");
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").label("\u6e29\u5ea6 (ht)");
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("solutionparams", "parent");
    model.result("pg13").feature("surf1").set("expr", "T");
    model.result("pg13").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("solutionparams", "parent");
    model.result("pg14").feature("surf1").set("expr", "nitf1.T");
    model.result("pg14").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg14").feature("surf1").set("smooth", "internal");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result("pg14").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg14").feature("surf1").feature("sel1").selection().set(1, 4, 5);
    model.result("pg14").feature().create("surf2", "Surface");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg14").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg14").feature("surf2").set("showsolutionparams", "on");
    model.result("pg14").feature("surf2").set("solutionparams", "parent");
    model.result("pg14").feature("surf2").set("expr", "nitf1.T");
    model.result("pg14").feature("surf2").set("smooth", "internal");
    model.result("pg14").feature("surf2").set("showsolutionparams", "on");
    model.result("pg14").feature("surf2").set("data", "parent");
    model.result("pg14").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg14").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg14").feature("surf2").feature("sel1").selection().set(3, 6, 9);
    model.result("pg14").feature("surf2").set("inheritplot", "surf1");
    model.result("pg14").feature().create("arws1", "ArrowSurface");
    model.result("pg14").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg14").feature("arws1").set("showsolutionparams", "on");
    model.result("pg14").feature("arws1").set("solutionparams", "parent");
    model.result("pg14").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg14").feature("arws1").set("xnumber", 30);
    model.result("pg14").feature("arws1").set("ynumber", 30);
    model.result("pg14").feature("arws1").set("arrowtype", "cone");
    model.result("pg14").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg14").feature("arws1").set("showsolutionparams", "on");
    model.result("pg14").feature("arws1").set("data", "parent");
    model.result("pg14").feature("arws1").feature().create("col1", "Color");
    model.result("pg14").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg14").feature("arws1").feature("col1").set("expr", "fp.U");
    model.result("pg14").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg14").feature("arws1").feature("filt1").set("expr", "fp.U>nitf1.Uave");
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2)");
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("solutionparams", "parent");
    model.result("pg15").feature("surf1").set("expr", "nitf2.T");
    model.result("pg15").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result("pg15").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg15").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg15").feature("surf1").feature("sel1").selection().set(2, 7, 8);
    model.result("pg15").feature().create("surf2", "Surface");
    model.result("pg15").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg15").feature("surf2").set("showsolutionparams", "on");
    model.result("pg15").feature("surf2").set("solutionparams", "parent");
    model.result("pg15").feature("surf2").set("expr", "nitf2.T");
    model.result("pg15").feature("surf2").set("smooth", "internal");
    model.result("pg15").feature("surf2").set("showsolutionparams", "on");
    model.result("pg15").feature("surf2").set("data", "parent");
    model.result("pg15").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg15").feature("surf2").feature("sel1").selection().geom("geom1", 2);
    model.result("pg15").feature("surf2").feature("sel1").selection().set(3, 6, 9);
    model.result("pg15").feature("surf2").set("inheritplot", "surf1");
    model.result("pg15").feature().create("arws1", "ArrowSurface");
    model.result("pg15").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg15").feature("arws1").set("showsolutionparams", "on");
    model.result("pg15").feature("arws1").set("solutionparams", "parent");
    model.result("pg15").feature("arws1").set("expr", new String[]{"nitf2.ux", "nitf2.uy"});
    model.result("pg15").feature("arws1").set("xnumber", 30);
    model.result("pg15").feature("arws1").set("ynumber", 30);
    model.result("pg15").feature("arws1").set("arrowtype", "cone");
    model.result("pg15").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg15").feature("arws1").set("showsolutionparams", "on");
    model.result("pg15").feature("arws1").set("data", "parent");
    model.result("pg15").feature("arws1").feature().create("col1", "Color");
    model.result("pg15").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg15").feature("arws1").feature("col1").set("expr", "fp2.U");
    model.result("pg15").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg15").feature("arws1").feature("filt1").set("expr", "fp2.U>nitf2.Uave");
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("legendpos", "middleright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "Voltage (V)", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "Temperature (K)", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("surf1").set("expr", "T-T_in");
    model.result("pg13").run();
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("paramindicator", "I_avg=eval(I_avg,A/cm^2)  A/cm<sup>2</sup>");
    model.result("pg13").run();
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").run();
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").run();
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "r_NH3");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").setIndex("looplevel", 1, 0);
    model.result("pg16").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").run();

    model.title("\u6c28\u71c3\u6599\u56fa\u4f53\u6c27\u5316\u7269\u71c3\u6599\u7535\u6c60");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u4e86\u6c28\u71c3\u6599\u56fa\u4f53\u6c27\u5316\u7269\u71c3\u6599\u7535\u6c60\uff0c\u6240\u9700\u7684\u6c22\u6c14\u662f\u901a\u8fc7\u6c28\u7684\u70ed\u5206\u89e3\u63d0\u4f9b\u7684\uff0c\u8fd9\u662f\u4e00\u79cd\u5438\u70ed\u53cd\u5e94\u3002\u6a21\u578b\u4e2d\u6db5\u76d6\u4e86\u6c22\u6c14\u548c\u6c27\u6c14\u6269\u6563\u7535\u6781\u4e2d\u7684\u8d28\u91cf\u5e73\u8861\u4e0e\u6c14\u4f53\u6d41\u52a8\u4e4b\u95f4\u7684\u5b8c\u5168\u8026\u5408\u3001\u6c22\u6c14\u548c\u6c27\u6c14\u6c14\u6d41\u901a\u9053\u4e2d\u7684\u52a8\u91cf\u5e73\u8861\u3001\u6574\u4e2a\u7535\u6c60\u7684\u80fd\u91cf\u5e73\u8861\u3001\u7531\u6c27\u5316\u7269\u79bb\u5b50\u643a\u5e26\u7684\u79bb\u5b50\u6d41\u5e73\u8861\uff0c\u4ee5\u53ca\u7535\u5b50\u6d41\u5e73\u8861\u3002\u6c22\u6c14\u6269\u6563\u7535\u6781\u4e2d\u5305\u542b\u6c28\u7684\u70ed\u5206\u89e3\u53cd\u5e94\u3002\n\n\u8be5\u6a21\u578b\u8ba1\u7b97\u6c14\u4f53\u6269\u6563\u7535\u6781\u548c\u6c14\u6d41\u901a\u9053\u4e2d\u5404\u79cd\u7269\u8d28\u7684\u7a7a\u95f4\u5206\u5e03\u60c5\u51b5\uff0c\u5e76\u63ed\u793a\u4e86\u5728\u8f83\u4f4e\u7684\u5916\u52a0\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6\u4e0b\uff0c\u56fa\u4f53\u6c27\u5316\u7269\u71c3\u6599\u7535\u6c60\u4f1a\u88ab\u51b7\u5374\uff0c\u800c\u5728\u8f83\u9ad8\u7684\u5916\u52a0\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6\u4e0b\u5219\u4f1a\u88ab\u52a0\u70ed\u3002\n\n\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u6f14\u793a\u4e86\u5728\u6c22\u6c14\u6df7\u5408\u7269\u4e2d\u4f7f\u7528\u8f85\u52a9\u7269\u8d28\u7684\u6548\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("sofc_nh3.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
