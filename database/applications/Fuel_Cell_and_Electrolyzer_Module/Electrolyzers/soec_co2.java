/*
 * soec_co2.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:06 by COMSOL 6.3.0.290. */
public class soec_co2 {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("we", "WaterElectrolyzer", "geom1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Oxide");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("OperationMode", "Electrolyzer");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings").set("TRHE", "700[degC]");
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
    model.study("std1").feature("cdi").setSolveFor("/physics/we", true);
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
    model.study("std1").feature("stat").setSolveFor("/physics/we", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_in", "1073[K]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("epsg", "0.4", "\u7535\u6781\u5b54\u9699\u7387");
    model.param().set("epsl", "0.4", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("taug", "3", "\u7535\u6781\u8fc2\u66f2\u5ea6");
    model.param().set("dc", "500[um]", "\u9634\u6781\u539a\u5ea6");
    model.param().set("dm", "100[um]", "\u819c\u539a\u5ea6");
    model.param().set("da", "100[um]", "\u9633\u6781\u539a\u5ea6");
    model.param().set("dg", "1[mm]", "\u6c14\u4f53\u6d41\u9053\u9ad8\u5ea6");
    model.param().set("di", "500[um]", "\u4e92\u8fde\u5c42\u539a\u5ea6");
    model.param().set("L", "20[mm]", "\u5e73\u9762 SOEC \u957f\u5ea6");
    model.param().set("W", "3.7[mm]", "\u5e73\u9762 SOEC \u539a\u5ea6");
    model.param().set("Mflux_in", "0.001[kg/s]", "\u6c14\u4f53\u8d28\u91cf\u901a\u91cf");
    model.param().set("E_app", "0.5[V]", "SOEC \u5de5\u4f5c\u7535\u4f4d");
    model.param().set("kc", "11[W/m/K]", "\u9634\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("ka", "6[W/m/K]", "\u9633\u6781\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("km", "2.7[W/m/K]", "\u7535\u89e3\u8d28\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("ki", "1.1[W/m/K]", "\u4e92\u8fde\u5c42\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("x0_H2O", "0.498", "H2O \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_CO2", "0.5", "CO2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_CO", "0.001", "CO \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param().set("x0_H2", "0.001", "H2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param()
         .set("w0_H2O", "x0_H2O*18[g/mol]/(x0_H2O*18[g/mol]+x0_CO2*44[g/mol]+x0_CO*28[g/mol]+x0_H2*2[g/mol])", "H2O \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param()
         .set("w0_CO2", "x0_CO2*44[g/mol]/(x0_H2O*18[g/mol]+x0_CO2*44[g/mol]+x0_CO*28[g/mol]+x0_H2*2[g/mol])", "CO2 \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param()
         .set("w0_CO", "x0_CO*28[g/mol]/(x0_H2O*18[g/mol]+x0_CO2*44[g/mol]+x0_CO*28[g/mol]+x0_H2*2[g/mol])", "CO \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param().set("x0_N2", "0.79", "N2 \u7684\u521d\u59cb\u6469\u5c14\u5206\u6570");
    model.param()
         .set("w0_N2", "x0_N2*28[g/mol]/(x0_N2*28[g/mol]+0.21*32[g/mol])", "N2 \u7684\u521d\u59cb\u8d28\u91cf\u5206\u6570");
    model.param()
         .set("kappag_GDE", "1e-10[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.param().set("S", "1e9[m^2/m^3]", "\u7535\u6781\u6bd4\u8868\u9762\u79ef");
    model.param()
         .set("i0_ref_HER", "1[A/m^2]", "H2O \u7535\u89e3\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_COER", "1[A/m^2]", "CO2 \u7535\u89e3\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_OER", "1[A/m^2]", "\u6790\u6c27\u53cd\u5e94\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "W"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "di", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dg", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "da", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dm", 3);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dc", 4);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "dg", 5);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("k_wgsr", "0.0171*exp(-103191[J/mol]/(R_const*T)) [mol/m^3/s]", "\u6c34\u6c14\u53d8\u6362\u53cd\u5e94\u5e38\u6570");

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().set(5);

    model.component("comp1").physics("we").selection().set(2, 3, 4, 5, 6);
    model.component("comp1").physics("we").prop("H2GasMixture").set("CO2", true);
    model.component("comp1").physics("we").prop("H2GasMixture").set("CO", true);
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("we").prop("O2GasMixture").set("N2", true);
    model.component("comp1").physics("we").prop("O2GasMixture").set("GasPhaseDiffusion", true);
    model.component("comp1").physics("we").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("we").create("mem1", "Membrane", 2);
    model.component("comp1").physics("we").feature("mem1").selection().set(4);
    model.component("comp1").physics("we").create("h2gde1", "H2GasDiffusionElectrode", 2);
    model.component("comp1").physics("we").feature("h2gde1").selection().set(5);
    model.component("comp1").physics("we").feature("h2gde1").set("epsl", "epsl");
    model.component("comp1").physics("we").feature("h2gde1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("we").feature("h2gde1").set("epsg", "epsg");
    model.component("comp1").physics("we").feature("h2gde1")
         .set("taug", new String[]{"taug", "0", "0", "0", "taug", "0", "0", "0", "taug"});
    model.component("comp1").physics("we").feature("h2gde1")
         .set("kappag", new String[]{"kappag_GDE", "0", "0", "0", "kappag_GDE", "0", "0", "0", "kappag_GDE"});
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1")
         .label("\u6c22\u6c14\u6269\u6563\u7535\u6781\u53cd\u5e94\uff1a\u6c34\u7535\u89e3");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_HER");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("alphaa", 0.5);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("Av", "S");
    model.component("comp1").physics("we").feature("h2gde1").create("h2gder2", "H2GasDiffusionElectrodeReaction", 2);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder2")
         .label("\u6c22\u6c14\u6269\u6563\u7535\u6781\u53cd\u5e94\uff1a\u4e8c\u6c27\u5316\u78b3\u7535\u89e3");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder2").set("nuCO2", -1);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder2").set("nuCO", 1);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder2").set("i0_ref", "i0_ref_COER");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder2").set("Av", "S");
    model.component("comp1").physics("we").create("o2gde1", "O2GasDiffusionElectrode", 2);
    model.component("comp1").physics("we").feature("o2gde1").selection().set(3);
    model.component("comp1").physics("we").feature("o2gde1").set("epsl", "epsl");
    model.component("comp1").physics("we").feature("o2gde1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("we").feature("o2gde1").set("epsg", "epsg");
    model.component("comp1").physics("we").feature("o2gde1")
         .set("taug", new String[]{"taug", "0", "0", "0", "taug", "0", "0", "0", "taug"});
    model.component("comp1").physics("we").feature("o2gde1")
         .set("kappag", new String[]{"kappag_GDE", "0", "0", "0", "kappag_GDE", "0", "0", "0", "kappag_GDE"});
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_ref_OER");
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("Av", "S");
    model.component("comp1").physics("we").create("h2fch1", "H2FlowChannel", 2);
    model.component("comp1").physics("we").feature("h2fch1").selection().set(6);
    model.component("comp1").physics("we").feature("h2fch1").set("GasPermeabilityModel", "StraightChannels");
    model.component("comp1").physics("we").feature("h2fch1").set("H_ch", "dg");
    model.component("comp1").physics("we").feature("h2fch1").set("W_ch", "dg");
    model.component("comp1").physics("we").create("o2fch1", "O2FlowChannel", 2);
    model.component("comp1").physics("we").feature("o2fch1").selection().set(2);
    model.component("comp1").physics("we").feature("o2fch1").set("GasPermeabilityModel", "StraightChannels");
    model.component("comp1").physics("we").feature("o2fch1").set("H_ch", "dg");
    model.component("comp1").physics("we").feature("o2fch1").set("W_ch", "dg");
    model.component("comp1").physics("we").feature("ecph1").create("inito2dom1", "InitialValuesO2Domains", 2);
    model.component("comp1").physics("we").feature("ecph1").feature("inito2dom1").selection().set(3);
    model.component("comp1").physics("we").feature("ecph1").feature("inito2dom1").set("initphis", "E_app");
    model.component("comp1").physics("we").feature("ecph1").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").selection().set(12);
    model.component("comp1").physics("we").feature("ecph1").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("we").feature("ecph1").feature("pot1").selection().set(6);
    model.component("comp1").physics("we").feature("ecph1").feature("pot1").set("phisbnd", "E_app");
    model.component("comp1").physics("we").feature("h2gasph1").feature("init1").set("x0H2O", "x0_H2O");
    model.component("comp1").physics("we").feature("h2gasph1").feature("init1").set("x0CO2", "x0_CO2");
    model.component("comp1").physics("we").feature("h2gasph1").feature("init1").set("x0CO", "x0_CO");
    model.component("comp1").physics("we").feature("h2gasph1").create("wgsr1", "WaterGasShiftReaction", 2);
    model.component("comp1").physics("we").feature("h2gasph1").feature("wgsr1").set("k_wgsr", "k_wgsr");
    model.component("comp1").physics("we").feature("h2gasph1").feature("wgsr1").set("pref", "1[Pa]");
    model.component("comp1").physics("we").feature("h2gasph1").create("h2in1", "H2Inlet", 1);
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").selection().set(11);
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("J0H2O", "Mflux_in*w0_H2O");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("J0CO2", "Mflux_in*w0_CO2");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("J0CO", "Mflux_in*w0_CO");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("w0bndH2O", "w0_H2O");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("w0bndCO2", "w0_CO2");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("w0bndCO", "w0_CO");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1")
         .set("FlowBoundaryCondition", "TotalMassFlowRate");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("J0", "Mflux_in");
    model.component("comp1").physics("we").feature("h2gasph1").create("h2out1", "H2Outlet", 1);
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2out1").selection().set(21);
    model.component("comp1").physics("we").feature("o2gasph1").feature("init1").set("x0N2", "x0_N2");
    model.component("comp1").physics("we").feature("o2gasph1").create("o2in1", "O2Inlet", 1);
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1").selection().set(3);
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1").set("J0N2", "Mflux_in*w0_N2");
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1").set("w0bndN2", "w0_N2");
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1")
         .set("FlowBoundaryCondition", "TotalMassFlowRate");
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2in1").set("J0", "Mflux_in");
    model.component("comp1").physics("we").feature("o2gasph1").create("o2out1", "O2Outlet", 1);
    model.component("comp1").physics("we").feature("o2gasph1").feature("o2out1").selection().set(17);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_in");
    model.component("comp1").physics("ht").feature("solid1").label("\u56fa\u4f53\uff1a\u4e92\u8fde\u90e8\u4ef6");
    model.component("comp1").physics("ht").feature("fluid1").label("\u6d41\u4f53\uff1a\u6d41\u9053");
    model.component("comp1").physics("ht").feature("fluid1").selection().set(2, 6);
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure", "we.pA");
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"we.u", "we.v", "0"});
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.we.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "root.comp1.we.rho");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "root.comp1.we.Cp_mix");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp1").physics("ht").create("porous1", "PorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("porous1")
         .label("\u591a\u5b54\u4ecb\u8d28\uff1a\u9634\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").physics("ht").feature("porous1").selection().set(5);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("minput_pressure", "we.pA");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("u", new String[]{"we.u", "we.v", "0"});
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("k_mat", "root.comp1.we.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.we.rho");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp1.we.Cp_mix");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "epsg");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_b", new String[]{"kc", "0", "0", "0", "kc", "0", "0", "0", "kc"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").create("porous2", "PorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("porous2")
         .label("\u591a\u5b54\u4ecb\u8d28\uff1a\u9633\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").physics("ht").feature("porous2").selection().set(3);
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("minput_pressure", "we.pA");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("u", new String[]{"we.u", "we.v", "0"});
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("k_mat", "root.comp1.we.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("rho_mat", "root.comp1.we.rho");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("Cp_mat", "root.comp1.we.Cp_mix");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro", "epsg");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1")
         .set("k_b", new String[]{"ka", "0", "0", "0", "ka", "0", "0", "0", "ka"});
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid2").label("\u56fa\u4f53\uff1a\u9694\u819c");
    model.component("comp1").physics("ht").feature("solid2").selection().set(4);
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"km", "0", "0", "0", "km", "0", "0", "0", "km"});
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(3, 11);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(17, 21);
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 1);
    model.component("comp1").physics("ht").feature("pc1").selection().set(2, 15);

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 2);

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
    model.component("comp1").material("mat1").selection().set(3, 4, 5);
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
    model.component("comp1").material("mat2").selection().set(1, 7);

    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(2, 4, 6, 8, 10, 12, 14, 15);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 100);
    model.component("comp1").mesh("mesh1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis2").selection().set(1, 13, 16, 22);
    model.component("comp1").mesh("mesh1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis3").selection().set(3, 11, 17, 21);
    model.component("comp1").mesh("mesh1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis4").selection().set(7, 19);
    model.component("comp1").mesh("mesh1").feature("dis4").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis5").selection().set(9, 20);
    model.component("comp1").mesh("mesh1").feature("dis5").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis5").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("dis5").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("dis5").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis6").selection().set(5, 18);
    model.component("comp1").mesh("mesh1").feature("dis6").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("dis6").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("dis6").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("dis6").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("dis6").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T_in", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T_in", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_app", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.5 1 1.5", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (we)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"we.phis"});
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"we.Isx", "we.Isy"});
    model.result("pg1").feature("arws1").set("arrowbase", "center");
    model.result("pg1").feature("arws1").set("color", "gray");
    model.result("pg1").feature("arws1").create("filt1", "Filter");
    model.result("pg1").feature("arws1").feature("filt1").set("expr", "isdefined(root.comp1.we.phis)");
    model.result("pg1").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (we)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"we.phil"});
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"we.Ilx", "we.Ily"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg2").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2 (we)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"we.xH2"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"we.tfluxH2x", "we.tfluxH2y"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, O2 (we)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"we.xO2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"we.tfluxO2x", "we.tfluxO2y"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2O (we)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"we.xH2O"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"we.tfluxH2Ox", "we.tfluxH2Oy"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, N2 (we)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"we.xN2"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"we.tfluxN2x", "we.tfluxN2y"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, CO2 (we)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 CO2:");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"we.xCO2"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"we.tfluxCO2x", "we.tfluxCO2y"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 3, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, CO (we)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 CO:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"we.xCO"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"we.tfluxCOx", "we.tfluxCOy"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 3, 0);
    model.result("pg9").label("\u538b\u529b (we)");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"we.p"});
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"we.u", "we.v"});
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("recover", "pprint");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u6e29\u5ea6 (ht)");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "T-T_in");
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u8868\u9762\uff1a\u6e29\u5ea6\u53d8\u5316 (K)");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg11").label("\u6c34\u6c14\u53d8\u6362\u53cd\u5e94\u901f\u7387");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "we.r_wgsr");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03");
    model.result("pg12").setIndex("looplevelinput", "last", 0);
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\uff0c\u9634\u6781\u4fa7");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u7535\u6781\u957f\u5ea6 (m)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12")
         .set("ylabel", "\u6cbf y \u65b9\u5411\u7684\u79ef\u5206\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().set(10);
    model.result("pg12").feature("lngr1").set("expr", "genproj1(we.iv_h2gder1)");
    model.result("pg12").feature("lngr1").set("unit", "A/cm^2");
    model.result("pg12").feature("lngr1").set("linewidth", 2);
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "H2O", 0);
    model.result("pg12").run();
    model.result("pg12").create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr2").set("linewidth", "preference");
    model.result("pg12").feature("lngr2").selection().set(10);
    model.result("pg12").feature("lngr2").set("expr", "genproj1(we.iv_h2gder2)");
    model.result("pg12").feature("lngr2").set("unit", "A/cm^2");
    model.result("pg12").feature("lngr2").set("linewidth", 2);
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("legendmethod", "manual");
    model.result("pg12").feature("lngr2").setIndex("legends", "CO2", 0);
    model.result("pg12").run();
    model.result("pg12").run();

    model
         .title("\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u6c60\u4e2d\u7684\u6c34\u548c\u4e8c\u6c27\u5316\u78b3\u5171\u7535\u89e3");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u6c60\u6a21\u62df\u6c34\u548c\u4e8c\u6c27\u5316\u78b3\u7684\u5171\u7535\u89e3\uff0c\u5176\u4e2d\u6db5\u76d6\u4e86\u6c22\u6c14\u548c\u6c27\u6c14\u6269\u6563\u7535\u6781\u4e2d\u7684\u8d28\u91cf\u5e73\u8861\u548c\u6c14\u4f53\u6d41\u52a8\u4e4b\u95f4\u7684\u5b8c\u5168\u8026\u5408\u3001\u6c22\u6c14\u548c\u6c27\u6c14\u6d41\u9053\u4e2d\u7684\u52a8\u91cf\u5e73\u8861\u3001\u6574\u4e2a\u7535\u6c60\u7684\u80fd\u91cf\u5e73\u8861\u3001\u6c27\u79bb\u5b50\u643a\u5e26\u7684\u79bb\u5b50\u6d41\u5e73\u8861\u4ee5\u53ca\u7535\u5b50\u7535\u6d41\u5e73\u8861\u3002\u6b64\u5916\uff0c\u6c22\u6c14\u6269\u6563\u7535\u6781\u548c\u6c22\u6c14\u6d41\u9053\u4e2d\u5305\u542b\u53ef\u9006\u7684\u6c34\u6c14\u53d8\u6362\u53cd\u5e94\u3002\n\u672c\u6a21\u578b\u4e0d\u4ec5\u8ba1\u7b97\u6c14\u4f53\u6269\u6563\u7535\u6781\u548c\u6c14\u6d41\u901a\u9053\u4e2d\u5404\u79cd\u7269\u8d28\u7684\u7a7a\u95f4\u5206\u5e03\uff0c\u8fd8\u4f7f\u7528\u5e7f\u4e49\u6295\u5f71\u7b97\u5b50\u6765\u8ba1\u7b97\u6cbf\u7535\u6781\u957f\u5ea6\u65b9\u5411\u7684\u603b\u7535\u6d41\u5bc6\u5ea6\u7684\u7a7a\u95f4\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("soec_co2.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
