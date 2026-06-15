/*
 * ht_pem.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:08 by COMSOL 6.3.0.290. */
public class ht_pem {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");
    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp").field("velocity").field("ua");
    model.component("comp1").physics("fp").field("velocity").component(new String[]{"ua", "va", "wa"});
    model.component("comp1").physics("fp").field("pressure").field("pa");
    model.component("comp1").physics().create("fp2", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp2").field("velocity").field("uc");
    model.component("comp1").physics("fp2").field("velocity").component(new String[]{"uc", "vc", "wc"});
    model.component("comp1").physics("fp2").field("pressure").field("pc");

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "0.02[m]", "\u7535\u6c60\u957f\u5ea6");
    model.param().set("H_ch", "1e-3[m]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("W_ch", "0.7874e-3[m]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_rib", "0.90932e-3[m]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("H_gdl", "380e-6[m]", "GDL \u5bbd\u5ea6");
    model.param().set("H_electrode", "50e-6[m]", "\u591a\u5b54\u7535\u6781\u539a\u5ea6");
    model.param().set("H_membrane", "100e-6[m]", "\u819c\u539a\u5ea6");
    model.param()
         .set("eps_gas_gdl", "0.4", "GDL \u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570\uff08\u5b54\u9699\u7387\uff09");
    model.param()
         .set("eps_l_cl", "0.3", "\u50ac\u5316\u5242\u5c42\u7535\u89e3\u8d28\uff08\u79bb\u805a\u7269\uff09\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("eps_gas_cl", "0.3", "\u50ac\u5316\u5242\u5c42\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param().set("kappa_gdl", "1.18e-11[m^2]", "GDL \u6e17\u900f\u7387");
    model.param().set("kappa_cl", "kappa_gdl/5", "\u50ac\u5316\u5242\u5c42\u6e17\u900f\u7387");
    model.param().set("sigma_gdl", "222[S/m]", "GDL \u7535\u5bfc\u7387");
    model.param().set("sigma_m", "9.825[S/m]", "\u819c\u7684\u7535\u5bfc\u7387");
    model.param().set("T", "180+273.15[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("p_ref", "1[atm]", "\u53c2\u8003\u538b\u529b");
    model.param().set("V_cell", "0.95[V]", "\u7535\u6c60\u7535\u538b");
    model.param()
         .set("i0_ref_c", "1e-3[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param()
         .set("i0_ref_a", "1e2[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("alpha_c", "1", "\u4f20\u9012\u7cfb\u6570\uff0c\u9634\u6781");
    model.param().set("Av", "1e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef");
    model.param().set("T_hum", "28[degC]", "\u52a0\u6e7f\u6e29\u5ea6");
    model.param()
         .set("p_vapor", "0.61121*exp((18.678-(T_hum-0[degC])/234.5[K])*((T_hum-0[degC])/(257.14+T_hum-0[degC])))[kPa]", "\u6c34\u7684\u5206\u538b");
    model.param().set("x_H2O_in", "p_vapor/p_ref", "\u5165\u53e3\u6c34\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("x_H2_in", "1-x_H2O_in", "\u5165\u53e3\u6c22\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("x_O2_in", "0.21*(1-x_H2O_in)", "\u5165\u53e3\u6c27\u7684\u6469\u5c14\u5206\u6570");
    model.param()
         .set("I_stoich", "1[A/cm^2]*L*(W_rib+W_ch)", "\u7528\u4e8e\u6c14\u4f53\u6d41\u91cf\u8ba1\u7b97\u7684\u5de5\u4f5c\u7535\u6d41");
    model.param().set("lambda_anode", "1.2", "\u9633\u6781\u5316\u5b66\u8ba1\u91cf");
    model.param().set("lambda_cathode", "2", "\u9634\u6781\u5316\u5b66\u8ba1\u91cf");
    model.param()
         .set("U_in_anode", "lambda_anode*I_stoich/(2*F_const)*(R_const*T)/p_ref/x_H2_in/(H_ch*W_ch)", "\u5165\u53e3\u901f\u5ea6\uff0c\u9633\u6781");
    model.param()
         .set("U_in_cathode", "lambda_cathode*I_stoich/(4*F_const)*(R_const*T)/p_ref/x_O2_in/(H_ch*W_ch)", "\u5165\u53e3\u901f\u5ea6\uff0c\u9634\u6781");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u9633\u6781\u901a\u9053");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_ch", "L", "H_ch"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"W_rib/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").label("\u9633\u6781 GDL");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"(W_ch+W_rib)", "L", "H_ch"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "H_gdl", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "H_ch"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9633\u6781 GDE");
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "H_electrode", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "H_ch+H_gdl"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4").label("\u819c");
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "H_membrane", 2);
    model.component("comp1").geom("geom1").feature("blk4")
         .set("pos", new String[]{"0", "0", "H_ch+H_gdl+H_electrode"});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").feature().duplicate("blk5", "blk4");
    model.component("comp1").geom("geom1").feature("blk5").label("\u9634\u6781 GDE");
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "W_ch+W_rib", 0);
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "H_electrode", 2);
    model.component("comp1").geom("geom1").feature("blk5")
         .set("pos", new String[]{"0", "0", "H_ch+H_gdl+H_electrode+H_membrane"});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").feature().duplicate("blk6", "blk5");
    model.component("comp1").geom("geom1").feature("blk6").label("\u9634\u6781 GDL");
    model.component("comp1").geom("geom1").feature("blk6").setIndex("size", "H_gdl", 2);
    model.component("comp1").geom("geom1").feature("blk6")
         .set("pos", new String[]{"0", "0", "H_ch+H_gdl+H_electrode+H_membrane+H_electrode"});
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").feature().duplicate("blk7", "blk6");
    model.component("comp1").geom("geom1").feature("blk7").label("\u9634\u6781\u901a\u9053");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new String[]{"W_ch", "L", "H_ch"});
    model.component("comp1").geom("geom1").feature("blk7").setIndex("pos", "W_rib/2", 0);
    model.component("comp1").geom("geom1").feature("blk7")
         .set("pos", new String[]{"W_rib/2", "0", "H_ch+H_gdl+H_electrode+H_membrane+H_electrode+H_gdl"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u9633\u6781\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(23);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u9634\u6781\u5165\u53e3");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(27);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u9633\u6781\u51fa\u53e3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(30);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u9634\u6781\u51fa\u53e3");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(31);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u9633\u6781\u5ba4");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_blk1_dom", "geom1_blk2_dom", "geom1_blk3_dom"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9634\u6781\u5ba4");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_blk5_dom", "geom1_blk6_dom", "geom1_blk7_dom"});

    model.component("comp1").physics("fp").label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41 - \u9633\u6781");
    model.component("comp1").physics("fp").selection().named("uni1");
    model.component("comp1").physics("fp2").label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41 - \u9634\u6781");
    model.component("comp1").physics("fp2").selection().named("uni2");

    model.component("comp1").multiphysics().create("rfh1", "ReactingFlowH2GasPhase", 3);
    model.component("comp1").multiphysics().create("rfo1", "ReactingFlowO2GasPhase", 3);
    model.component("comp1").multiphysics("rfo1").set("Fluid_physics", "fp2");

    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_blk4_dom");
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("h2gde1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_blk2_dom");
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("h2fch1").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("o2gde1").selection().named("geom1_blk5_dom");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_blk6_dom");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("geom1_blk7_dom");
    model.component("comp1").physics("fc").feature("icph1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("fc").feature("icph1")
         .set("sigmal", new String[]{"sigma_m", "0", "0", "0", "sigma_m", "0", "0", "0", "sigma_m"});
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").feature("h2gde1").set("epsl", "eps_l_cl");
    model.component("comp1").physics("fc").feature("h2gde1").set("epsg", "eps_gas_cl");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_a");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "eps_gas_gdl");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").feature("o2gde1").set("epsl", "eps_l_cl");
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "eps_gas_cl");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_ref_c");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphaa", "4-alpha_c");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "eps_gas_gdl");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(3, 33);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(16, 35);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "V_cell");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("T_hum", "T_hum");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("sel1");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("sel3");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T_hum");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("sel2");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("sel4");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().named("geom1_blk2_dom");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "eps_gas_gdl");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_gdl", "0", "0", "0", "kappa_gdl", "0", "0", "0", "kappa_gdl"});
    model.component("comp1").physics("fp").create("porous2", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous2").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("fp").feature("porous2").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous2").feature("pm1").set("epsilon_p", "eps_gas_cl");
    model.component("comp1").physics("fp").feature("porous2").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous2").feature("pm1")
         .set("kappa", new String[]{"kappa_cl", "0", "0", "0", "kappa_cl", "0", "0", "0", "kappa_cl"});
    model.component("comp1").physics("fp").feature("wallbc1").set("constraintType", "symmetricConstraint");
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp").feature("inl1").set("Uavfdf", "U_in_anode");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().named("sel3");
    model.component("comp1").physics("fp").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("fp").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("fp").feature("sym1").selection().set(1, 4, 36, 37);
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("fp2").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp2").feature("porous1").selection().named("geom1_blk6_dom");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p", "eps_gas_gdl");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_gdl", "0", "0", "0", "kappa_gdl", "0", "0", "0", "kappa_gdl"});
    model.component("comp1").physics("fp2").create("porous2", "PorousMedium", 3);
    model.component("comp1").physics("fp2").feature("porous2").selection().named("geom1_blk5_dom");
    model.component("comp1").physics("fp2").feature("porous2").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous2").feature("pm1").set("epsilon_p", "eps_gas_cl");
    model.component("comp1").physics("fp2").feature("porous2").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous2").feature("pm1")
         .set("kappa", new String[]{"kappa_cl", "0", "0", "0", "kappa_cl", "0", "0", "0", "kappa_cl"});
    model.component("comp1").physics("fp2").feature("wallbc1").set("constraintType", "symmetricConstraint");
    model.component("comp1").physics("fp2").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("fp2").feature("sym1").selection().set(10, 13, 39, 40);
    model.component("comp1").physics("fp2").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp2").feature("inl1").selection().named("sel2");
    model.component("comp1").physics("fp2").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp2").feature("inl1").set("Uavfdf", "U_in_cathode");
    model.component("comp1").physics("fp2").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp2").feature("out1").selection().named("sel4");
    model.component("comp1").physics("fp2").feature("out1").set("NormalFlow", true);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 17, 33, 36, 48, 51);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "W_ch/8");
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(13, 65);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature().duplicate("edg3", "edg2");
    model.component("comp1").mesh("mesh1").feature("edg3").selection().set(1, 57);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("edg4", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg4").selection().set(4, 59);
    model.component("comp1").mesh("mesh1").feature("edg4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").create("edg5", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg5").selection().set(10, 63);
    model.component("comp1").mesh("mesh1").feature("edg5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("edg6", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg6").selection().set(7, 61);
    model.component("comp1").mesh("mesh1").feature("edg6").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("edg7", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg7").selection().set(29, 34, 45, 49);
    model.component("comp1").mesh("mesh1").feature("edg7").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2, 5, 8, 11, 14, 23, 27);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_ch");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("type", "integral");
    model.component("comp1").probe("dom1").selection().named("geom1_blk3_dom");
    model.component("comp1").probe("dom1").set("expr", "fc.iv_h2gder1/((W_ch+W_rib)*L)/1e4");

    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").create("cdi2", "CurrentDistributionInitialization");
    model.study("std1").feature().move("cdi2", 1);
    model.study("std1").feature("cdi2").set("initType", "secondary");
    model.study("std1").feature("cdi2").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("cdi2").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").feature("stat").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/fp2", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/fp", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").set("useparam", true);
    model.study("std1").feature("stat3").setIndex("pname", "L", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "m", 0);
    model.study("std1").feature("stat3").setIndex("pname", "L", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "m", 0);
    model.study("std1").feature("stat3").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "range(0.95,-0.05,0.85) range(0.8,-0.1,0.4)", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 8, 0);
    model.result("pg2").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"fc.phis"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"fc.Isx", "fc.Isy", "fc.Isz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 8, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("expr", new String[]{"fc.phil"});
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"fc.Ilx", "fc.Ily", "fc.Ilz"});
    model.result("pg3").feature("arwv1").set("arrowbase", "center");
    model.result("pg3").feature("arwv1").set("color", "gray");
    model.result("pg3").feature("arwv1").create("filt1", "Filter");
    model.result("pg3").feature("arwv1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg3").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2, \u6d41\u7ebf (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y", "fc.tfluxH2z"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col", "Color");
    model.result("pg4").feature("str1").feature("col").set("expr", "fc.xH2");
    model.result("pg4").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg4").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").setIndex("looplevel", 8, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2, \u8868\u9762 (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"fc.xH2"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 8, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, O2, \u6d41\u7ebf (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg6").feature("str1").set("posmethod", "start");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col", "Color");
    model.result("pg6").feature("str1").feature("col").set("expr", "fc.xO2");
    model.result("pg6").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 8, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, O2, \u8868\u9762 (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 8, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy", "fc.tfluxH2Oz"});
    model.result("pg8").feature("str1").set("posmethod", "start");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg8").feature("str1").create("col", "Color");
    model.result("pg8").feature("str1").feature("col").set("expr", "fc.xH2O");
    model.result("pg8").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg8").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 8, 0);
    model.result("pg9").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (fc)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 8, 0);
    model.result("pg10").label("\u6469\u5c14\u5206\u6570, N2, \u6d41\u7ebf (fc)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y", "fc.tfluxN2z"});
    model.result("pg10").feature("str1").set("posmethod", "start");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result("pg10").feature("str1").create("col", "Color");
    model.result("pg10").feature("str1").feature("col").set("expr", "fc.xN2");
    model.result("pg10").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg10").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").setIndex("looplevel", 8, 0);
    model.result("pg11").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u901f\u5ea6 (fp)");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("slc1", "Slice");
    model.result("pg12").feature("slc1").label("\u5207\u9762");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("expr", "fp.U");
    model.result("pg12").feature("slc1").set("smooth", "internal");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(2, 3, 5, 9, 17, 18, 22, 24, 32, 33);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u538b\u529b (fp)");
    model.result("pg13").set("data", "surf1");
    model.result("pg13").setIndex("looplevel", 8, 0);
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("expr", "pa");
    model.result("pg13").feature("surf1").set("colortable", "Dipole");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result("pg13").feature("surf1").feature().create("tran1", "Transparency");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").label("\u901f\u5ea6 (fp2)");
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").feature().create("slc1", "Slice");
    model.result("pg14").feature("slc1").label("\u5207\u9762");
    model.result("pg14").feature("slc1").set("showsolutionparams", "on");
    model.result("pg14").feature("slc1").set("expr", "fp2.U");
    model.result("pg14").feature("slc1").set("smooth", "internal");
    model.result("pg14").feature("slc1").set("showsolutionparams", "on");
    model.result("pg14").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 1");
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(11, 12, 14, 16, 20, 21, 26, 29, 34, 35);
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u538b\u529b (fp2)");
    model.result("pg15").set("data", "surf2");
    model.result("pg15").setIndex("looplevel", 8, 0);
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").label("\u8868\u9762");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("expr", "pc");
    model.result("pg15").feature("surf1").set("colortable", "Dipole");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result("pg15").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u6781\u5316\u56fe");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u7535\u6c60\u7684\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("switchxy", true);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", "H_ch+H_gdl+H_electrode+H_membrane/2");
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").run();
    model.result("pg16").set("data", "cpl1");
    model.result("pg16").create("con1", "Contour");
    model.result("pg16").feature("con1").set("expr", "fc.Ilz");
    model.result("pg16").feature("con1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg16").feature("con1").set("number", 10);
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").label("\u819c\u7535\u6d41\u5bc6\u5ea6");

    model
         .title("\u9ad8\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60\u7684\u8d28\u91cf\u4f20\u9012\u5206\u6790");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u9ad8\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60 (PEMFC) \u4e2d\u53cd\u5e94\u7269\u548c\u6c34\u7684\u4f20\u9012\u3002\u6a21\u578b\u5305\u542b\u6d41\u9053\u3001\u6c14\u4f53\u6269\u6563\u5c42 (GDL) \u548c\u591a\u5b54\u7535\u6781\u4e2d\u7684\u8d28\u91cf\u548c\u52a8\u91cf\u4f20\u9012\u73b0\u8c61\uff0c\u4ee5\u53ca GDL\u3001\u591a\u5b54\u7535\u6781\u548c\u805a\u5408\u7269\u819c\u4e2d\u7684\u7535\u5316\u5b66\u7535\u6d41\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("ht_pem.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
