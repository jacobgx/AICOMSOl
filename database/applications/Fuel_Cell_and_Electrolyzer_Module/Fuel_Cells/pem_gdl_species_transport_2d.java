/*
 * pem_gdl_species_transport_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:10 by COMSOL 6.3.0.290. */
public class pem_gdl_species_transport_2d {

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
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");

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

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2.5e-4", "2e-3"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"1e-4", "2e-3"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"2.5e-4", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"2.5e-4", "2e-3"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"3.5e-4", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"1e-4", "5e-4"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-1e-4", "0"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r4");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"7e-4", "1.5e-3"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("arr1(1,1)", "arr1(1,2)", "r1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("arr1(2,1)", "arr1(2,2)", "r3");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 6, 7);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni2", 4, 5);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "1e-5");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("V_cell", "0.7[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("T", "353[K]", "\u6e29\u5ea6");
    model.param().set("kappa_s", "1000[S/m]", "\u7535\u5bfc\u7387\uff0c\u56fa\u76f8");
    model.param().set("kappa_p", "1e-13[m^2]", "GDL \u6e17\u900f\u7387");
    model.param().set("tortx", "1.1", "\u8fc2\u66f2\u5ea6\uff0cx \u65b9\u5411");
    model.param().set("torty", "2.8", "\u8fc2\u66f2\u5ea6\uff0cy \u65b9\u5411");
    model.param().set("p_ref", "1[atm]", "\u53c2\u8003\u538b\u529b");
    model.param().set("p_a_in", "p_ref+25[mbar]", "\u9633\u6781\u5165\u53e3\u538b\u529b");
    model.param().set("p_c_in", "p_ref+50[mbar]", "\u9634\u6781\u5165\u53e3\u538b\u529b");
    model.param().set("E_eq_a", "0[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u9633\u6781");
    model.param().set("E_eq_c", "1[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u9634\u6781");
    model.param().set("i0_a", "1e5[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param().set("i0_c", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("eps_mic", "0.2", "\u56e2\u805a\u4f53\u5185\u90e8\u7684\u5fae\u89c2\u5b54\u9699\u7387");
    model.param().set("eps_mac", "0.4", "\u56e2\u805a\u4f53\u4e4b\u95f4\u7684\u5b8f\u89c2\u5b54\u9699\u7387");
    model.param().set("S", "1e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef\uff0c\u56e2\u805a\u4f53");
    model.param().set("R_agg", "0.1[um]", "\u56e2\u805a\u4f53\u534a\u5f84");
    model.param().set("l_act", "10[um]", "\u6d3b\u6027 GDE \u5c42\u539a\u5ea6");
    model.param()
         .set("D_agg", "1.2e-10[m^2/s]*((1-eps_mac)*eps_mic)^1.5", "\u56e2\u805a\u4f53\u5185\u90e8\u7684\u6709\u6548\u6269\u6563\u7cfb\u6570");
    model.param()
         .set("K", "-6*F_const*D_agg/R_agg^2", "\u56e2\u805a\u4f53\u7535\u6d41\u5bc6\u5ea6\u5b50\u8868\u8fbe\u5f0f");
    model.param().set("Av", "3*(1-eps_mac)/R_agg", "\u6bd4\u8868\u9762\u79ef\uff0cGDE");
    model.param().set("xH2Oa_in", "0.42", "\u5165\u53e3\u6469\u5c14\u5206\u6570\uff0cH2O");
    model.param().set("xH2_in", "1-xH2Oa_in", "\u5165\u53e3\u6469\u5c14\u5206\u6570\uff0cH2");
    model.param().set("xH2Oc_in", "0.42", "\u5165\u53e3\u6469\u5c14\u5206\u6570\uff0cH2O");
    model.param().set("xO2_in", "0.21*(1-xH2Oc_in)", "\u5165\u53e3\u6469\u5c14\u5206\u6570\uff0cO2");
    model.param().set("xN2_in", "0.79*(1-xH2Oc_in)", "\u5165\u53e3\u6469\u5c14\u5206\u6570\uff0cN2");
    model.param()
         .set("KH2", "3.9e4[Pa*m^3/mol]", "\u4ea8\u5229\u5b9a\u5f8b\u5e38\u6570\uff0c\u56e2\u805a\u4f53\u4e2d\u7684 H2");
    model.param()
         .set("KO2", "3.2e4[Pa*m^3/mol]", "\u4ea8\u5229\u5b9a\u5f8b\u5e38\u6570\uff0c\u56e2\u805a\u4f53\u4e2d\u7684 O2");
    model.param().set("cH2_ref", "xH2_in*p_ref/KH2", "\u53c2\u8003\u6d53\u5ea6\uff0cH2");
    model.param().set("cO2_ref", "xO2_in*p_ref/KO2", "\u53c2\u8003\u6d53\u5ea6\uff0cO2");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u9633\u6781 GDL");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection("sel2").label("\u819c");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection("sel3").label("\u9634\u6781 GDL");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(10);
    model.component("comp1").selection("sel4").label("\u9633\u6781 GDE");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(13);
    model.component("comp1").selection("sel5").label("\u9634\u6781 GDE");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u9633\u6781 GDL \u53d8\u91cf");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("sel1");
    model.component("comp1").variable("var1").set("wReact", "fc.wH2");
    model.component("comp1").variable("var1").descr("wReact", "\u8d28\u91cf\u5206\u6570\uff0c\u53cd\u5e94\u7269");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u9634\u6781 GDL \u53d8\u91cf");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("sel3");
    model.component("comp1").variable("var2")
         .set("wReact", "fc.wO2", "\u8d28\u91cf\u5206\u6570\uff0c\u53cd\u5e94\u7269");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u9633\u6781 GDE \u53d8\u91cf");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().named("sel4");
    model.component("comp1").variable("var3").set("cH2_agg", "fc.p*fc.xH2/KH2");
    model.component("comp1").variable("var3")
         .descr("cH2_agg", "\u4ea8\u5229\u5b9a\u5f8b\u6c22\u9644\u805a\u7269\u6d53\u5ea6");
    model.component("comp1").variable("var3").set("eta_a", "fc.phis-fc.phil-E_eq_a");
    model.component("comp1").variable("var3").descr("eta_a", "\u9633\u6781\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var3").set("beta_a", "cH2_agg-cH2_ref*exp(-2*F_const*eta_a/(R_const*T))");
    model.component("comp1").variable("var3").set("lda_a", "sqrt(i0_a*S*R_agg^2/(2*F_const*cH2_ref*D_agg))");
    model.component("comp1").variable("var3")
         .descr("lda_a", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6\u5b50\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var3").set("i_a", "K*(1-lda_a*coth(lda_a))*beta_a*(R_agg/3)");
    model.component("comp1").variable("var3").descr("i_a", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u9634\u6781 GDE \u53d8\u91cf");
    model.component("comp1").variable("var4").selection().geom("geom1", 1);
    model.component("comp1").variable("var4").selection().named("sel5");
    model.component("comp1").variable("var4").set("cO2_agg", "fc.p*fc.xO2/KO2");
    model.component("comp1").variable("var4")
         .descr("cO2_agg", "\u4ea8\u5229\u5b9a\u5f8b\u6c27\u9644\u805a\u7269\u6d53\u5ea6");
    model.component("comp1").variable("var4").set("eta_c", "fc.phis-fc.phil-E_eq_c");
    model.component("comp1").variable("var4").descr("eta_c", "\u9634\u6781\u8fc7\u7535\u538b");
    model.component("comp1").variable("var4")
         .set("lda_c", "sqrt(i0_c*S*R_agg^2*exp(-F_const*eta_c/(2*R_const*T))/(4*F_const*cO2_ref*D_agg))");
    model.component("comp1").variable("var4")
         .descr("lda_c", "\u9634\u6781\u7535\u6d41\u5bc6\u5ea6\u5b50\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var4").set("i_c", "-2*K*(1-lda_c*coth(lda_c))*cO2_agg*(R_agg/3)");
    model.component("comp1").variable("var4").descr("i_c", "\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u5e73\u5747\uff0c\u96c6\u7535\u6781");
    model.component("comp1").cpl("aveop1").set("opname", "aveop_ca");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(8, 23, 24);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("MembraneCrossover", "MembraneCrossover", "Membrane crossover");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PolymerElectrolyteWaterTransport", "PolymerElectrolyteWaterTransport", "Polymer electrolyte water transport");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PolymerElectrolyteWaterAbsorptionDesorption", "PolymerElectrolyteWaterAbsorptionDesorption", "Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat1").label("Nafion\u00ae, EW 1100, Vapor Equilibrated, Protonated");
    model.component("comp1").material("mat1").set("family", "glass");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Ex situ measurements of through-plane thermal conductivities\nin a polymer electrolyte fuel cell,\nO Burheim, P Vie, J Pharoah, S. Kjelstrup, Journal of Power Sources 195 (2010) 249\u2013256");
    model.component("comp1").material("mat1").propertyGroup("def").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat1").propertyGroup("def").descr("lambda", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importedname", "nafion_1100_conductivity_vapor_eq.csv");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnametable", new String[][]{{"sigma_vs_T_and_RH", "1"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "sigma_vs_T_and_RH"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/cm"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1", "1"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Yoshitsugu Sone et al 1996 J. Electrochem. Soc. 143 1254\n(Conductivity data refers to the \"E-form\" values, Figure 5a and 5b)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").label("Membrane crossover");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("Psi_H2", "(2.2e-11*f+2.9e-12)[mol/cm/s/bar]*exp(21[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_H2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041\n");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("Psi_O2", "(1.9e-11*f+1.1e-12)[mol/cm/s/bar]*exp(22[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_O2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("Psi_N2", "Psi_O2");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_N2", "Approximated as equal to oxygen value (no reference).");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").descr("lambda", "Water uptake");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("EW", "1100[g/mol]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .label("Polymer electrolyte water transport");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("funcname", "lambda");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("table", new String[][]{{"0.00", "0.0"}, 
         {"0.14", "1.9"}, 
         {"0.37", "2.9"}, 
         {"0.45", "3.1"}, 
         {"0.51", "3.7"}, 
         {"0.57", "4.3"}, 
         {"0.79", "6.3"}, 
         {"0.80", "6.8"}, 
         {"0.81", "7.9"}, 
         {"0.88", "10.0"}, 
         {"0.91", "12.0"}, 
         {"0.96", "13.2"}, 
         {"1.00", "14.0"}});
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("alpha", "max(c0*Dmu/(R_const*T*(1-x0)),1e-9)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("alpha", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("xi", "if(lambda>1,1,lambda)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("xi", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("lambda", "lambda(phi)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("lambda", "Water uptake");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("EW", "1100[g/mol]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Dmu", "1.8e-5[cm^2/s]*f*exp(20[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Dmu", "Water diffusion coefficient");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("c0", "lambda/(V0*lambda+Vm)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("c0", "Water concentration");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("x0", "lambda/(lambda+1)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("x0", "Water mole fraction");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .label("Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .set("k_abs_dsp", "1.04e-7*exp(4.48*max(min(phi,0.85),0.25))[mol/cm^2/s]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .setPropertyInfo("k_abs_dsp", "Kientiz, Yamada, Nonoyama, Weber,\nJournal of Fuel Cell Science and Technology, Feb 2011, Vol. 8,  Article Number 011013");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .addInput("relativehumidity");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("MembraneCrossover", "MembraneCrossover", "Membrane crossover");
    model.component("comp1").material("mat2").propertyGroup()
         .create("PolymerElectrolyteWaterTransport", "PolymerElectrolyteWaterTransport", "Polymer electrolyte water transport");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("PolymerElectrolyteWaterAbsorptionDesorption", "PolymerElectrolyteWaterAbsorptionDesorption", "Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat2").label("Nafion\u00ae, EW 1100, Vapor Equilibrated, Protonated 1");
    model.component("comp1").material("mat2").set("family", "glass");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Ex situ measurements of through-plane thermal conductivities\nin a polymer electrolyte fuel cell,\nO Burheim, P Vie, J Pharoah, S. Kjelstrup, Journal of Power Sources 195 (2010) 249\u2013256");
    model.component("comp1").material("mat2").propertyGroup("def").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat2").propertyGroup("def").descr("lambda", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("source", "file");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importedname", "nafion_1100_conductivity_vapor_eq.csv");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnametable", new String[][]{{"sigma_vs_T_and_RH", "1"}});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 3);
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "sigma_vs_T_and_RH"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/cm"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1", "1"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Yoshitsugu Sone et al 1996 J. Electrochem. Soc. 143 1254\n(Conductivity data refers to the \"E-form\" values, Figure 5a and 5b)");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("relativehumidity");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").label("Membrane crossover");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .set("Psi_H2", "(2.2e-11*f+2.9e-12)[mol/cm/s/bar]*exp(21[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_H2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041\n");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .set("Psi_O2", "(1.9e-11*f+1.1e-12)[mol/cm/s/bar]*exp(22[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_O2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").set("Psi_N2", "Psi_O2");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_N2", "Approximated as equal to oxygen value (no reference).");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").descr("lambda", "Water uptake");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").set("EW", "1100[g/mol]");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").addInput("relativehumidity");
    model.component("comp1").material("mat2").propertyGroup("MembraneCrossover").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .label("Polymer electrolyte water transport");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("funcname", "lambda");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("table", new String[][]{{"0.00", "0.0"}, 
         {"0.14", "1.9"}, 
         {"0.37", "2.9"}, 
         {"0.45", "3.1"}, 
         {"0.51", "3.7"}, 
         {"0.57", "4.3"}, 
         {"0.79", "6.3"}, 
         {"0.80", "6.8"}, 
         {"0.81", "7.9"}, 
         {"0.88", "10.0"}, 
         {"0.91", "12.0"}, 
         {"0.96", "13.2"}, 
         {"1.00", "14.0"}});
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("alpha", "max(c0*Dmu/(R_const*T*(1-x0)),1e-9)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("alpha", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("xi", "if(lambda>1,1,lambda)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("xi", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("lambda", "lambda(phi)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("lambda", "Water uptake");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("EW", "1100[g/mol]");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Dmu", "1.8e-5[cm^2/s]*f*exp(20[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Dmu", "Water diffusion coefficient");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("c0", "lambda/(V0*lambda+Vm)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("c0", "Water concentration");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("x0", "lambda/(lambda+1)");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("x0", "Water mole fraction");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("relativehumidity");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .label("Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .set("k_abs_dsp", "1.04e-7*exp(4.48*max(min(phi,0.85),0.25))[mol/cm^2/s]");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .setPropertyInfo("k_abs_dsp", "Kientiz, Yamada, Nonoyama, Weber,\nJournal of Fuel Cell Science and Technology, Feb 2011, Vol. 8,  Article Number 011013");
    model.component("comp1").material("mat2").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .addInput("relativehumidity");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().set(10, 13);

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);
    model.component("comp1").physics("fc").prop("ReferencePressureLevel").set("pref", 0);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 2);
    model.component("comp1").physics("fc").feature("mem1").selection().named("sel2");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 2);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("sel1");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 2);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("sel3");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 1);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("sel4");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 1);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("sel5");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"kappa_s", "0", "0", "0", "kappa_s", "0", "0", "0", "kappa_s"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "eps_mac");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("taug", new String[]{"tortx", "0", "0", "0", "torty", "0", "0", "0", "tortx"});
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("kappag", new String[]{"kappa_p", "0", "0", "0", "kappa_p", "0", "0", "0", "kappa_p"});
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"kappa_s", "0", "0", "0", "kappa_s", "0", "0", "0", "kappa_s"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("DiffusionCorrModel", "Tortuosity");
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "eps_mac");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("taug", new String[]{"tortx", "0", "0", "0", "torty", "0", "0", "0", "tortx"});
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("kappag", new String[]{"kappa_p", "0", "0", "0", "kappa_p", "0", "0", "0", "kappa_p"});
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "l_act");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("ilocmat", "i_a");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "l_act");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("ilocmat", "i_c");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("ecph1").create("inito2dom1", "InitialValuesO2Domains", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").selection().set(3);
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").set("initphis", "V_cell");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(8, 23, 24);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(17);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "V_cell");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().set(1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1")
         .set("FlowBoundaryCondition", "Pressure");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("p0", "p_a_in");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in2", "H2Inlet", 1);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in2").selection().set(4);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("x0H2O", "xH2Oa_in");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("pinit", "p_ref");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().set(22);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("FlowBoundaryCondition", "Pressure");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("p0", "p_c_in");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in2", "O2Inlet", 1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in2").selection().set(21);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0H2O", "xH2Oc_in");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0N2", "xN2_in");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("pinit", "p_ref");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(10, 13);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "2.5e-5");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(5, 6, 16, 17);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "1e-5");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,-0.1,0.4)", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "V_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "tortx", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1.1 2.8", 0);
    model.study("std1").feature("param").setIndex("pname", "V_cell", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("pname", "V_cell", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("pname", "torty", 1);
    model.study("std1").feature("param").setIndex("plistarr", "1.1 2.8", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").setIndex("looplevel", 2, 2);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"fc.phis"});
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"fc.Isx", "fc.Isy"});
    model.result("pg1").feature("arws1").set("arrowbase", "center");
    model.result("pg1").feature("arws1").set("color", "gray");
    model.result("pg1").feature("arws1").create("filt1", "Filter");
    model.result("pg1").feature("arws1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg1").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").setIndex("looplevel", 2, 2);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"fc.phil"});
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"fc.Ilx", "fc.Ily"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg2").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").setIndex("looplevel", 2, 2);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2 (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"fc.xH2"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").setIndex("looplevel", 2, 2);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, O2 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 7, 0);
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").setIndex("looplevel", 2, 2);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2O (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 7, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").setIndex("looplevel", 2, 2);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, N2 (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").setIndex("looplevel", 2, 1);
    model.result("pg7").setIndex("looplevel", 2, 2);
    model.result("pg7").label("\u538b\u529b (fc)");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"fc.p"});
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"fc.u", "fc.v"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 7, 0);
    model.result("pg8").setIndex("looplevel", 2, 1);
    model.result("pg8").setIndex("looplevel", 2, 2);
    model.result("pg8").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"fc.aw"});
    model.result("pg1").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6781\u5316\u66f2\u7ebf");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u7535\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg9").set("switchxy", true);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "aveop_ca(-fc.nIs)", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "A/cm^2", 0);
    model.result("pg9").feature("glob1")
         .setIndex("descr", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/cm>sup>2</sup>)", 0);
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u5404\u5411\u540c\u6027\uff0c\\tau<sub>xx</sub>=1.1 & \\tau<sub>yy</sub>=1.1", 0);
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u5404\u5411\u5f02\u6027\uff0c\\tau<sub>xx</sub>=1.1 & \\tau<sub>yy</sub>=2.8", 1);
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u5404\u5411\u5f02\u6027\uff0c\\tau<sub>xx</sub>=2.8 & \\tau<sub>yy</sub>=1.1", 2);
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u5404\u5411\u540c\u6027\uff0c\\tau<sub>xx</sub>=2.8 & \\tau<sub>yy</sub>=2.8", 3);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("GDL \u7535\u6d41\u5bc6\u5ea6\u5206\u5e03");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 1, 2);
    model.result("pg10").setIndex("looplevel", 1, 1);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "fc.IsMag");
    model.result("pg10").feature("surf1").set("descr", "\u7535\u6781\u7535\u6d41\u5bc6\u5ea6\u5927\u5c0f");
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("rangecolormax", "1e4");
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").run();
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("expr", new String[]{"fc.Isx", "fc.Isy"});
    model.result("pg10").feature("arws1").set("descr", "\u7535\u6781\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf");
    model.result("pg10").feature("arws1").set("scaleactive", true);
    model.result("pg10").feature("arws1").set("scale", "5e-9");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").setIndex("looplevel", 2, 1);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u9633\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").set("data", "none");
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("legendpos", "lowerright");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").set("data", "dset3");
    model.result("pg11").feature("lngr1").setIndex("looplevelinput", "first", 2);
    model.result("pg11").feature("lngr1").setIndex("looplevelinput", "first", 1);
    model.result("pg11").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg11").feature("lngr1").selection().named("sel4");
    model.result("pg11").feature("lngr1").set("expr", "i_a");
    model.result("pg11").feature("lngr1").set("descr", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").feature("lngr1").set("expr", "i_a*Av*l_act");
    model.result("pg11").feature("lngr1").set("descractive", true);
    model.result("pg11").feature("lngr1").set("descr", "\u9633\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").feature("lngr1").set("xdataexpr", "y");
    model.result("pg11").feature("lngr1").set("xdatadescr", "y \u5750\u6807");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "manual");
    model.result("pg11").feature("lngr1")
         .setIndex("legends", "\u5404\u5411\u540c\u6027\uff0c\\tau<sub>xx</sub>=1.1 & \\tau<sub>yy</sub>=1.1", 0);
    model.result("pg11").feature().duplicate("lngr2", "lngr1");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").setIndex("looplevelinput", "last", 1);
    model.result("pg11").feature("lngr2")
         .setIndex("legends", "\u5404\u5411\u5f02\u6027\uff0c\\tau<sub>xx</sub>=1.1 & \\tau<sub>yy</sub>=2.8", 0);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").run();
    model.result("pg12").label("\u901f\u5ea6\u573a");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").setIndex("looplevel", 1, 2);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "fc.U");
    model.result("pg12").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").run();
    model.result("pg12").create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").set("expr", new String[]{"fc.u", "fc.v"});
    model.result("pg12").feature("arws1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg12").feature("arws1").set("scaleactive", true);
    model.result("pg12").feature("arws1").set("scale", 0.0035);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").run();
    model.result("pg13").label("\u53cd\u5e94\u7269\u8d28\u91cf\u5206\u6570");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").setIndex("looplevel", 1, 2);
    model.result("pg13").setIndex("looplevel", 1, 1);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "wReact");
    model.result("pg13").feature("surf1").set("colortable", "Prism");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").setIndex("looplevel", 2, 1);
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result().remove("pg2");
    model.result("pg3").run();

    model
         .title("\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60\u6c14\u4f53\u6269\u6563\u5c42\u4e2d\u7684\u7269\u8d28\u4f20\u9012");

    return model;
  }

  public static Model run3(Model model) {

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u8d28\u5b50\u4ea4\u6362\u819c (PEM) \u71c3\u6599\u7535\u6c60\uff08\u5177\u6709\u4e24\u4e2a\u538b\u529b\u4e0d\u540c\u7684\u76f8\u90bb\u6d41\u9053\uff09\u7684\u6c14\u4f53\u6269\u6563\u5c42 (GDL) \u4e2d\u7684\u7269\u8d28\u4f20\u9012\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("pem_gdl_species_transport_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
