/*
 * sofc_unit_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:10 by COMSOL 6.3.0.290. */
public class sofc_unit_cell {

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
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Oxide");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "700[degC]");
    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp").field("velocity").field("u_c");
    model.component("comp1").physics("fp").field("velocity").component(new String[]{"u_c", "v_c", "w_c"});
    model.component("comp1").physics("fp").field("pressure").field("p_c");
    model.component("comp1").physics().create("fp2", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp2").field("velocity").field("u_a");
    model.component("comp1").physics("fp2").field("velocity").component(new String[]{"u_a", "v_a", "w_a"});
    model.component("comp1").physics("fp2").field("pressure").field("p_a");

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
    model.param().set("p_atm", "1[atm]", "\u5927\u6c14\u538b");
    model.param().set("T", "800[degC]", "\u6e29\u5ea6");
    model.param().set("perm_a", "1e-14[m^2]", "\u9633\u6781\u6e17\u900f\u7387");
    model.param().set("perm_c", "1e-14[m^2]", "\u9634\u6781\u6e17\u900f\u7387");
    model.param().set("V_cell", "1[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("i0_a", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("i0_c", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("Sa_a", "5e9[1/m]", "\u6bd4\u8868\u9762\u79ef\uff0c\u9633\u6781");
    model.param().set("Sa_c", "5e9[1/m]", "\u6bd4\u8868\u9762\u79ef\uff0c\u9634\u6781");
    model.param()
         .set("fl_a", "0.2", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\u6821\u6b63\u56e0\u5b50\uff0c\u9633\u6781");
    model.param()
         .set("fl_c", "0.2", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\u6821\u6b63\u56e0\u5b50\uff0c\u9634\u6781");
    model.param().set("kseff_a", "1000[S/m]", "\u56fa\u4f53\u6709\u6548\u7535\u5bfc\u7387\uff0c\u9633\u6781");
    model.param().set("kseff_c", "1000[S/m]", "\u56fa\u4f53\u6709\u6548\u7535\u5bfc\u7387\uff0c\u9634\u6781");
    model.param().set("e_por", "0.4", "\u5b54\u9699\u7387");
    model.param().set("MH2", "2[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cH2");
    model.param().set("MO2", "32[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cO2");
    model.param().set("MN2", "28[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cN2");
    model.param().set("W_channel", "0.5e-3[m]", "\u6c14\u4f53\u6d41\u9053\u5bbd\u5ea6");
    model.param().set("W_rib", "0.5e-3[m]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("H_gde", "1e-4[m]", "\u6c14\u4f53\u6269\u6563\u7535\u6781\u539a\u5ea6");
    model.param().set("H_electrolyte", "1e-4[m]", "\u7535\u89e3\u8d28\u539a\u5ea6");
    model.param().set("H_channel", "0.5e-3[m]", "\u6c14\u4f53\u6d41\u9053\u9ad8\u5ea6");
    model.param().set("L", "10e-3[m]", "\u6d41\u9053\u957f\u5ea6");
    model.param().set("I_max", "1.5[A/cm^2]*L*(W_rib+W_channel)", "\u7535\u6c60\u9762\u79ef");
    model.param()
         .set("lambda_O2", "2", "\u6c27\u5316\u5b66\u8ba1\u91cf\uff08\u7528\u4e8e\u8ba1\u7b97\u6d41\u7387\uff09");
    model.param()
         .set("lambda_H2", "2", "\u6c22\u5316\u5b66\u8ba1\u91cf\uff08\u7528\u4e8e\u8ba1\u7b97\u6d41\u7387\uff09");
    model.param().set("xO2_in", "0.21", "\u5165\u53e3\u4e0a\u6e38\u6c27\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("xN2_in", "1-xO2_in", "\u5165\u53e3\u4e0a\u6e38\u6c2e\u7684\u6469\u5c14\u5206\u6570");
    model.param().set("m_H2", "lambda_H2*I_max/(2*F_const)*MH2", "\u6c22\u7684\u5165\u53e3\u6d41\u7387");
    model.param().set("m_O2", "lambda_O2*I_max/(4*F_const)*MO2", "\u6c27\u7684\u5165\u53e3\u6d41\u7387");
    model.param().set("m_N2", "m_O2*xN2_in*MN2/(xO2_in*MO2)", "\u6c2e\u7684\u5165\u53e3\u6d41\u7387");
    model.param().set("d_pore", "500[nm]", "\u5b54\u9699\u76f4\u5f84");
    model.param().set("gamma_O2", "0.25", "\u5206\u538b\u6307\u6570\uff0c\u6c27");
    model.param().set("gamma_H2", "0.11", "\u5206\u538b\u6307\u6570\uff0c\u6c22");
    model.param().set("gamma_H2O", "0.67", "\u5206\u538b\u6307\u6570\uff0c\u6c34");
    model.param().set("alpha_a_H2", "1", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c22\u6c27\u5316");
    model.param().set("alpha_c_H2", "0.1", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c22\u6c27\u5316");
    model.param().set("alpha_a_O2", "0.1", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param().set("alpha_c_O2", "0.4", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param().set("A_cell", "L*(W_rib+W_channel)");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_channel+W_rib", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "H_gde", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"W_channel+W_rib", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "H_electrolyte", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "-H_electrolyte"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"W_channel+W_rib", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("size", "H_gde", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"0", "-H_electrolyte-H_gde"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"W_channel", "H_channel"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"W_rib/2", "H_gde"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"W_channel", "H_channel"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"W_rib/2", "-H_gde-H_electrolyte-H_channel"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u9633\u6781\u6d41\u9053");
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u9633\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u9634\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u9634\u6781\u6d41\u9053");
    model.component("comp1").selection("sel4").set(5);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u819c");
    model.component("comp1").selection("sel5").set(2);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u9633\u6781\u6d41\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9634\u6781\u6d41\u57df");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel3", "sel4"});

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

    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("sel5");
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("h2fch1").selection().named("sel1");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("sel4");
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("h2gde1").selection().named("sel2");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("sigmas", new String[]{"kseff_a", "0", "0", "0", "kseff_a", "0", "0", "0", "kseff_a"});
    model.component("comp1").physics("fc").feature("h2gde1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("fc").feature("h2gde1").set("fl", "fl_a");
    model.component("comp1").physics("fc").feature("h2gde1").set("epsg", "e_por");
    model.component("comp1").physics("fc").feature("h2gde1").set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("fc").feature("h2gde1").set("d_pore", "d_pore");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_a");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("rgenericH2", "gamma_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("rgenericH2O", "gamma_H2O");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("alphaa", "alpha_a_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("alphac", "alpha_c_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "Sa_a");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("o2gde1").selection().named("sel3");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"kseff_c", "0", "0", "0", "kseff_c", "0", "0", "0", "kseff_c"});
    model.component("comp1").physics("fc").feature("o2gde1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("fc").feature("o2gde1").set("fl", "fl_c");
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "e_por");
    model.component("comp1").physics("fc").feature("o2gde1").set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("fc").feature("o2gde1").set("d_pore", "d_pore");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_c");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("rgenericO2", "gamma_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphaa", "alpha_a_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphac", "alpha_c_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "Sa_c");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(3, 20);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(10, 22);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "V_cell");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().set(11);

    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").label("\u9633\u6781\u5165\u53e3");
    model.component("comp1").selection("sel6").set(11);

    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("sel6");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().set(29);

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").label("\u9633\u6781\u51fa\u53e3");
    model.component("comp1").selection("sel7").set(29);

    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("sel7");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0N2", "xN2_in");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().set(30);

    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").label("\u9634\u6781\u5165\u53e3");
    model.component("comp1").selection("sel8").set(30);

    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("sel8");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("J0N2", "m_N2");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("w0bndN2", 0.8);
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().set(15);

    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").label("\u9634\u6781\u51fa\u53e3");
    model.component("comp1").selection("sel9").set(15);

    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("sel9");
    model.component("comp1").physics("fp")
         .label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8 - \u9634\u6781");
    model.component("comp1").physics("fp").selection().named("uni2");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty").set("pref", "p_atm");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().named("sel3");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "e_por");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"perm_c", "0", "0", "0", "perm_c", "0", "0", "0", "perm_c"});
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").selection().named("sel8");
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("fp").feature("inl1").set("mfr", "m_O2+m_N2");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().named("sel9");
    model.component("comp1").physics("fp").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("fp2")
         .label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8 - \u9633\u6781");
    model.component("comp1").physics("fp2").selection().named("uni1");
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty").set("pref", "p_atm");
    model.component("comp1").physics("fp2").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp2").feature("porous1").selection().named("sel2");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p", "e_por");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"perm_a", "0", "0", "0", "perm_a", "0", "0", "0", "perm_a"});
    model.component("comp1").physics("fp2").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp2").feature("inl1").selection().named("sel6");
    model.component("comp1").physics("fp2").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("fp2").feature("inl1").set("mfr", "m_H2");
    model.component("comp1").physics("fp2").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp2").feature("out1").selection().named("sel7");
    model.component("comp1").physics("fp2").feature("out1").set("NormalFlow", true);

    model.component("comp1").multiphysics().create("rfh1", "ReactingFlowH2GasPhase", 3);
    model.component("comp1").multiphysics("rfh1").set("Fluid_physics", "fp2");
    model.component("comp1").multiphysics().create("rfo1", "ReactingFlowO2GasPhase", 3);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2, 10, 15, 18, 24, 27);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "W_channel/8");
    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 4, 7, 11, 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(12, 17, 22, 26);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7, 34);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(1, 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(4, 32);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_channel");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6\u63a2\u9488");
    model.component("comp1").probe("dom1").set("probename", "I_cell");
    model.component("comp1").probe("dom1").selection().named("sel2");
    model.component("comp1").probe("dom1").set("expr", "fc.ivtot");
    model.component("comp1").probe("dom1").set("descr", "\u7535\u6781\u53cd\u5e94\u6e90");
    model.component("comp1").probe("dom1").set("expr", "fc.ivtot*H_gde");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe("dom1").set("descr", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");

    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfo1", false);
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
    model.study("std1").feature("stat3").setIndex("pname", "p_atm", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat3").setIndex("pname", "p_atm", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat3").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "range(1,-0.1,0.5)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s4").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg2").create("mslc1", "Multislice");

    return model;
  }

  public static Model run2(Model model) {
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
    model.result("pg3").setIndex("looplevel", 6, 0);
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
    model.result("pg4").setIndex("looplevel", 6, 0);
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
    model.result("pg5").setIndex("looplevel", 6, 0);
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
    model.result("pg6").setIndex("looplevel", 6, 0);
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
    model.result("pg7").setIndex("looplevel", 6, 0);
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
    model.result("pg8").setIndex("looplevel", 6, 0);
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
    model.result("pg9").setIndex("looplevel", 6, 0);
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
    model.result("pg10").setIndex("looplevel", 6, 0);
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
    model.result("pg11").setIndex("looplevel", 6, 0);
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
    model.result().dataset("surf1").selection().set(7, 8, 9, 10, 16, 18, 21, 22, 25, 28);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u538b\u529b (fp)");
    model.result("pg13").set("data", "surf1");
    model.result("pg13").setIndex("looplevel", 6, 0);
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("expr", "p_c");
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
    model.result().dataset("surf2").selection().set(1, 2, 3, 6, 12, 13, 19, 20, 23, 26);
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u538b\u529b (fp2)");
    model.result("pg15").set("data", "surf2");
    model.result("pg15").setIndex("looplevel", 6, 0);
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").label("\u8868\u9762");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("expr", "p_a");
    model.result("pg15").feature("surf1").set("colortable", "Dipole");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result("pg15").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u6781\u5316\u66f2\u7ebf");
    model.result("pg16").set("titletype", "none");
    model.result("pg16").set("xlabelactive", true);
    model.result("pg16").set("xlabel", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg16").set("showlegends", false);
    model.result("pg16").create("glob1", "Global");
    model.result("pg16").feature("glob1").set("markerpos", "datapoints");
    model.result("pg16").feature("glob1").set("linewidth", "preference");
    model.result("pg16").feature("glob1").setIndex("expr", "V_cell", 0);
    model.result("pg16").feature("glob1").set("xdata", "expr");
    model.result("pg16").feature("glob1").set("xdataexpr", "I_cell");
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup1D");
    model.result("pg17").run();
    model.result("pg17").label("\u529f\u7387 vs.\u7535\u6d41");
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("xlabelactive", true);
    model.result("pg17").set("xlabel", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg17").set("ylabelactive", true);
    model.result("pg17").set("ylabel", "\u5e73\u5747\u7535\u6c60\u529f\u7387 (W/m<sup>2</sup>)");
    model.result("pg17").set("showlegends", false);
    model.result("pg17").create("glob1", "Global");
    model.result("pg17").feature("glob1").set("markerpos", "datapoints");
    model.result("pg17").feature("glob1").set("linewidth", "preference");
    model.result("pg17").feature("glob1").setIndex("expr", "V_cell*I_cell", 0);
    model.result("pg17").feature("glob1").setIndex("descr", "\u5e73\u5747\u7535\u6c60\u529f\u7387", 0);
    model.result("pg17").feature("glob1").set("xdata", "expr");
    model.result("pg17").feature("glob1").set("xdataexpr", "I_cell");
    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").run();
    model.result("pg18").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg18").selection().geom("geom1", 2);
    model.result("pg18").selection().geom("geom1", 2);
    model.result("pg18").selection().set(9);
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "fc.Ilz");
    model.result("pg18").feature("surf1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg18").run();

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("I_tot", "intop_cc(fc.nIs)", "\u603b\u7535\u6c60\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("P_phil", "-(d(fc.phil,x)*fc.Ilx+d(fc.phil,y)*fc.Ily+d(fc.phil,z)*fc.Ilz)", "\u5c40\u90e8\u529f\u7387\u635f\u8017\uff0c\u79bb\u5b50\u4f20\u8f93");
    model.component("comp1").variable("var1")
         .set("P_phis", "-(d(fc.phis,x)*fc.Isx+d(fc.phis,y)*fc.Isy+d(fc.phis,z)*fc.Isz)", "\u5c40\u90e8\u529f\u7387\u635f\u8017\uff0c\u7535\u5b50\u4f20\u8f93");
    model.component("comp1").variable("var1")
         .set("P_act_h2", "fc.iv_h2gder1*fc.eta_h2gder1", "\u5c40\u90e8\u529f\u7387\u635f\u8017\uff0c\u6c22\u6c14\u6c27\u5316\u6d3b\u5316");
    model.component("comp1").variable("var1")
         .set("P_act_o2", "fc.iv_o2gder1*fc.eta_o2gder1", "\u5c40\u90e8\u529f\u7387\u635f\u8017\uff0c\u6c27\u6c14\u6c27\u5316\u6d3b\u5316");
    model.component("comp1").variable("var1")
         .set("eta_phil", "(intop_h2_gde(P_phil)+intop_mem(P_phil)+intop_o2_gde(P_phil))/I_tot", "\u7535\u6c60\u5e73\u5747\u7535\u89e3\u8d28\u4f20\u8f93\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("eta_phis", "(intop_h2_gde(P_phis)+intop_o2_gde(P_phis))/I_tot", "\u7535\u6c60\u5e73\u5747\u7535\u5b50\u4f20\u8f93\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("eta_act_h2", "intop_h2_gde(P_act_h2)/I_tot", "\u7535\u6c60\u5e73\u5747\u6c22\u6c14\u6c27\u5316\u6d3b\u5316\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("eta_act_o2", "intop_o2_gde(P_act_o2)/I_tot", "\u7535\u6c60\u5e73\u5747\u6c27\u8fd8\u539f\u6d3b\u5316\u8fc7\u7535\u4f4d");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_cc");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(10, 22);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_h2_gde");
    model.component("comp1").cpl("intop2").selection().named("sel2");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_o2_gde");
    model.component("comp1").cpl("intop3").selection().named("sel3");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intop_mem");
    model.component("comp1").cpl("intop4").selection().named("sel5");

    model.component("comp1").probe("dom1").genResult("none");

    model.sol("sol1").updateSolution();

    model.result("pg2").run();
    model.result().create("pg19", "PlotGroup1D");
    model.result("pg19").run();
    model.result("pg19").label("\u8fc7\u7535\u4f4d\u6bd4\u8f83");
    model.result("pg19").create("glob1", "Global");
    model.result("pg19").feature("glob1").set("markerpos", "datapoints");
    model.result("pg19").feature("glob1").set("linewidth", "preference");
    model.result("pg19").feature("glob1").set("expr", new String[]{"eta_phil"});
    model.result("pg19").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u5e73\u5747\u7535\u89e3\u8d28\u4f20\u8f93\u8fc7\u7535\u4f4d"});
    model.result("pg19").feature("glob1").set("expr", new String[]{"eta_phil", "eta_phis"});
    model.result("pg19").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u5e73\u5747\u7535\u89e3\u8d28\u4f20\u8f93\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u7535\u5b50\u4f20\u8f93\u8fc7\u7535\u4f4d"});
    model.result("pg19").feature("glob1").set("expr", new String[]{"eta_phil", "eta_phis", "eta_act_h2"});
    model.result("pg19").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u5e73\u5747\u7535\u89e3\u8d28\u4f20\u8f93\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u7535\u5b50\u4f20\u8f93\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u6c22\u6c14\u6c27\u5316\u6d3b\u5316\u8fc7\u7535\u4f4d"});
    model.result("pg19").feature("glob1")
         .set("expr", new String[]{"eta_phil", "eta_phis", "eta_act_h2", "eta_act_o2"});
    model.result("pg19").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60\u5e73\u5747\u7535\u89e3\u8d28\u4f20\u8f93\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u7535\u5b50\u4f20\u8f93\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u6c22\u6c14\u6c27\u5316\u6d3b\u5316\u8fc7\u7535\u4f4d", "\u7535\u6c60\u5e73\u5747\u6c27\u8fd8\u539f\u6d3b\u5316\u8fc7\u7535\u4f4d"});
    model.result("pg19").feature("glob1").set("xdataexpr", "I_cell");
    model.result("pg19").feature("glob1").set("xdatadescr", "\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6\u63a2\u9488");
    model.result("pg19").run();
    model.result("pg19").feature("glob1").set("legendmethod", "manual");
    model.result("pg19").feature("glob1").setIndex("legends", "\u7535\u89e3\u8d28\u4f20\u9012", 0);
    model.result("pg19").feature("glob1").setIndex("legends", "\u7535\u5b50\u4f20\u9012", 1);
    model.result("pg19").feature("glob1").setIndex("legends", "\u6c22\u6c27\u5316\u6d3b\u5316", 2);
    model.result("pg19").feature("glob1").setIndex("legends", "\u6c27\u8fd8\u539f\u6d3b\u5316", 3);
    model.result("pg19").run();
    model.result("pg19").set("xlabelactive", true);
    model.result("pg19").set("xlabel", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg19").set("ylabelactive", true);
    model.result("pg19").set("ylabel", "\u8fc7\u7535\u4f4d (V)");
    model.result("pg19").set("titletype", "none");
    model.result("pg19").set("legendpos", "upperleft");
    model.result("pg19").run();

    model
         .title("\u56fa\u4f53\u6c27\u5316\u7269\u71c3\u6599\u7535\u6c60\u4e2d\u7684\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u56fa\u4f53\u6c27\u5316\u7269\u71c3\u6599\u7535\u6c60 (SOFC) \u4e2d\u7684\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\u3002\u6a21\u578b\u5305\u542b\u9634\u6781\u548c\u9633\u6781\u7684\u8d28\u91cf\u5e73\u8861\u3001\u6c14\u9053\u4e2d\u7684\u52a8\u91cf\u5e73\u8861\u3001\u591a\u5b54\u7535\u6781\u4e2d\u7684\u6c14\u4f53\u6d41\u52a8\u3001\u6c27\u79bb\u5b50\u643a\u5e26\u7684\u79bb\u5b50\u6d41\u5e73\u8861\u4ee5\u53ca\u7535\u5b50\u6d41\u5e73\u8861\u4e4b\u95f4\u7684\u5168\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("sofc_unit_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
