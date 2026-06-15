/*
 * pemfc_serpentine_flow_field.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class pemfc_serpentine_flow_field {

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

    model.component("comp1").geom("geom1").insertFile("pemfc_serpentine_flow_field_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel2");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("W_plate_min", "10[mm]");
    model.param().set("N_ch", "1");
    model.param().set("N_repeat", "1");

    model.component("comp1").geom("geom1").run("unisel2");

    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("stoich_H2", "1.2", "\u6c22\u6d41\u91cf\u7684\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("stoich_O2", "2.5", "\u6c27\u6d41\u91cf\u7684\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("RH_an", "25[%]", "\u5165\u53e3\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u9633\u6781\u4fa7");
    model.param("par2").set("RH_cath", "75[%]", "\u5165\u53e3\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u9634\u6781\u4fa7");
    model.param("par2").set("T", "70[degC]", "\u7535\u6c60\u6e29\u5ea6");
    model.param("par2")
         .set("I_avg_init", "0.01[A/cm^2]", "\u626b\u63cf\u4e2d\u7684\u521d\u59cb\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2")
         .set("I_avg_final", "1[A/cm^2]", "\u626b\u63cf\u4e2d\u7684\u6700\u7ec8\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("L_CL", "10[um]", "\u50ac\u5316\u5c42\u539a\u5ea6");
    model.param("par2")
         .set("i0_H2_ref", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("i0_O2_ref", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("a_CL", "5e7[m^2/m^3]", "\u6bd4\u9762\u79ef\uff0c\u50ac\u5316\u5c42");
    model.param("par2")
         .set("sigmas_GDL_IP", "5000[S/m]", "\u9762\u5185\u7535\u5bfc\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("sigmas_GDL_TP", "200[S/m]", "\u7a7f\u9762\u7535\u5bfc\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("kappag_GDL", "5e-12[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("alphaa_O2", "3", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2")
         .set("epsg_GDL", "1-epss_GDL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("epss_GDL", "0.4", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("I_avg", "I_avg_init", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("A_plate", "L_plate*W_plate", "\u7535\u6c60\u9762\u79ef");
    model.param("par2")
         .set("I_flow", "max(I_avg,0.1[A/cm^2])*A_plate", "\u7528\u4e8e\u5316\u5b66\u8ba1\u91cf\u6d41\u91cf\u8ba1\u7b97\u7684\u603b\u7535\u6d41");
    model.param("par2").set("I_tot", "I_avg*A_plate", "\u603b\u7535\u6c60\u7535\u6d41");
    model.param("par2").set("Rc", "1e-7[ohm*m^2]", "\u63a5\u89e6\u7535\u963b");
    model.param("par2").set("j_H2", "stoich_H2*I_flow/(2*F_const)", "\u6c22\u6469\u5c14\u6d41\u7387");
    model.param("par2")
         .set("j_H2O_an", "j_H2*x_H2O_an/x_H2", "\u84b8\u6c7d\u6469\u5c14\u6d41\u7387\uff0c\u9633\u6781\u4fa7");
    model.param("par2")
         .set("j_H2O_cath", "j_O2*x_H2O_cath/x_O2_cath", "\u84b8\u6c7d\u6469\u5c14\u6d41\u7387\uff0c\u9634\u6781\u4fa7");
    model.param("par2").set("j_N2", "j_O2*x_N2_cath/x_O2_cath", "\u6c2e\u6469\u5c14\u6d41\u7387");
    model.param("par2").set("j_O2", "stoich_O2*I_flow/(4*F_const)", "\u6c27\u6469\u5c14\u6d41\u7387");
    model.param("par2").set("m_an", "m_H2O_an+m_H2", "\u9633\u6781\u603b\u8d28\u91cf\u6d41\u7387");
    model.param("par2").set("m_cath", "m_O2+m_N2+m_H2O_cath", "\u9634\u6781\u603b\u8d28\u91cf\u6d41\u7387");
    model.param("par2").set("m_H2", "j_H2*2[g/mol]", "\u6c22\u8d28\u91cf\u6d41\u7387");
    model.param("par2")
         .set("m_H2O_an", "j_H2O_an*18[g/mol]", "\u84b8\u6c7d\u8d28\u91cf\u6d41\u7387\uff0c\u9633\u6781\u4fa7");
    model.param("par2")
         .set("m_H2O_cath", "j_H2O_cath*18[g/mol]", "\u84b8\u6c7d\u8d28\u91cf\u6d41\u7387\uff0c\u9634\u6781\u4fa7");
    model.param("par2").set("m_N2", "j_N2*28[g/mol]", "\u6c2e\u8d28\u91cf\u6d41\u7387");
    model.param("par2").set("m_O2", "j_O2*32[g/mol]", "\u6c27\u8d28\u91cf\u6d41\u7387");
    model.param("par2")
         .set("w_H2O_an", "m_H2O_an/m_an", "\u9633\u6781\u6d41\u4e2d\u6c34\u7684\u8d28\u91cf\u5206\u6570");
    model.param("par2")
         .set("w_H2O_cath", "m_H2O_cath/m_cath", "\u9634\u6781\u6d41\u4e2d\u6c34\u7684\u8d28\u91cf\u5206\u6570");
    model.param("par2").set("w_N2", "m_N2/m_cath", "\u9634\u6781\u6d41\u4e2d\u6c2e\u7684\u8d28\u91cf\u5206\u6570");
    model.param("par2").set("x_H2", "1-x_H2O_an", "\u9633\u6781\u6d41\u4e2d\u6c22\u7684\u6469\u5c14\u5206\u6570");
    model.param("par2").set("T_hum", "T", "\u52a0\u6e7f\u6e29\u5ea6");
    model.param("par2")
         .set("p_vapor", "0.61121*exp((18.678-(T_hum-0[degC])/234.5[K])*((T_hum-0[degC])/(257.14+T_hum-0[degC])))[kPa]", "\u84b8\u6c7d\u538b");
    model.param("par2")
         .set("x_H2O_an", "RH_an*p_vapor/1[atm]", "\u9633\u6781\u6d41\u4e2d\u84b8\u6c7d\u7684\u6469\u5c14\u5206\u6570");
    model.param("par2")
         .set("x_H2O_cath", "RH_cath*p_vapor/1[atm]", "\u9634\u6781\u6d41\u4e2d\u84b8\u6c7d\u7684\u6469\u5c14\u5206\u6570");
    model.param("par2")
         .set("x_N2_cath", "1-x_O2_cath-x_H2O_cath", "\u9634\u6781\u6d41\u4e2d\u6c2e\u7684\u6469\u5c14\u5206\u6570");
    model.param("par2")
         .set("x_O2_cath", "0.21*(1-x_H2O_cath)", "\u9634\u6781\u6d41\u4e2d\u6c27\u7684\u6469\u5c14\u5206\u6570");
    model.param("par2").set("stoich_H2", "3");
    model.param("par2").set("RH_an", "50[%]");

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

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("aw0", "(RH_cath+RH_an)/2");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T_hum");
    model.component("comp1").physics("fc").feature("mem1").feature("wadh2side1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("fc").feature("mem1").feature("wado2side1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_intsel1");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_TP"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_intsel2");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL_TP", "0", "0", "0", "sigmas_GDL_TP", "0", "0", "0", "sigmas_GDL_IP"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("h2fch1").selection().named("geom1_intsel3");
    model.component("comp1").physics("fc").feature("h2fch1").set("GasPermeabilityModel", "StraightChannels");
    model.component("comp1").physics("fc").feature("h2fch1").set("H_ch", "H_ch");
    model.component("comp1").physics("fc").feature("h2fch1").set("W_ch", "W_ch");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("geom1_intsel4");
    model.component("comp1").physics("fc").feature("o2fch1").set("GasPermeabilityModel", "StraightChannels");
    model.component("comp1").physics("fc").feature("o2fch1").set("H_ch", "H_ch");
    model.component("comp1").physics("fc").feature("o2fch1").set("W_ch", "W_ch");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("geom1_boxsel8");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_H2_ref");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("geom1_boxsel9");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_O2_ref");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("alphaa", 3);
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").feature("ecph1").create("inito2dom1", "InitialValuesO2Domains", 3);
    model.component("comp1").physics("fc").feature("ecph1").feature("inito2dom1").selection().all();
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("geom1_difsel1");
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").set("IncludeContactResistance", true);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").set("Rc", "Rc");
    model.component("comp1").physics("fc").feature("ecph1").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").selection().named("geom1_difsel2");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("Its", "-I_tot");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("IncludeContactResistance", true);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("Rc", "Rc");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("x0H2O", "x_H2O_an");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("J0H2O", "m_H2O_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("w0bndH2O", "w_H2O_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1")
         .set("FlowBoundaryCondition", "TotalMassFlowRate");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").set("J0", "m_an");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("geom1_boxsel6");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0H2O", "x_H2O_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("x0N2", "x_N2_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_boxsel5");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("J0H2O", "m_H2O_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("J0N2", "m_N2");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("w0bndH2O", "w_H2O_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("w0bndN2", "w_N2");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1")
         .set("FlowBoundaryCondition", "TotalMassFlowRate");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").set("J0", "m_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("geom1_boxsel7");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "W_ch/2");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_intsel5");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel12");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_ch/1.1");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_difsel4");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_unisel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_ext1_edg");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "W_rib/10");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_difsel3");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "W_ch/2.1");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().named("geom1_blk1_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").selection().named("geom1_ext2_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("stat").label("\u7a33\u6001 - \u4ec5\u538b\u529b");
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u7a33\u6001 - \u6240\u6709\u7269\u7406\u573a");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "I_avg", 0);
    model.study("std1").feature("stat2")
         .setIndex("plistarr", "I_avg_init range(I_avg_final/10, I_avg_final/10, I_avg_final)", 0);
    model.study("std1").feature("stat2").setIndex("punit", "A/cm^2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").set("control", "user");
    model.sol("sol1").feature("v2").feature("comp1_fc_mu0").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_phil").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_phis").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wH2O_H2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wH2O_O2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wN2_O2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_ecph1_ec1_phis0").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_h2gasph1_h2in1_wbndH2O").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_o2gasph1_o2in1_wbndH2O").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_o2gasph1_o2in1_wbndN2").set("solvefor", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"fc.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"fc.Isx", "fc.Isy", "fc.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"fc.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"fc.Ilx", "fc.Ily", "fc.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2, \u6d41\u7ebf (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y", "fc.tfluxH2z"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "fc.xH2");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2, \u8868\u9762 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xH2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, O2, \u6d41\u7ebf (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "fc.xO2");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, O2, \u8868\u9762 (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy", "fc.tfluxH2Oz"});
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").feature("str1").create("col", "Color");
    model.result("pg7").feature("str1").feature("col").set("expr", "fc.xH2O");
    model.result("pg7").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 11, 0);
    model.result("pg9").label("\u6469\u5c14\u5206\u6570, N2, \u6d41\u7ebf (fc)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y", "fc.tfluxN2z"});
    model.result("pg9").feature("str1").set("posmethod", "start");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").create("col", "Color");
    model.result("pg9").feature("str1").feature("col").set("expr", "fc.xN2");
    model.result("pg9").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg9").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 11, 0);
    model.result("pg10").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").setIndex("looplevel", 11, 0);
    model.result("pg11").label("\u538b\u529b (fc)");
    model.result("pg11").create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").set("expr", new String[]{"fc.p"});
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"fc.u", "fc.v", "fc.w"});
    model.result("pg11").feature("str1").set("posmethod", "start");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset1");
    model.result("pg12").setIndex("looplevel", 11, 0);
    model.result("pg12").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg12").create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").set("expr", new String[]{"fc.aw"});
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("posmethod", "selection");
    model.result("pg3").feature("str1").selection().named("geom1_boxsel4");
    model.result("pg3").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("posmethod", "selection");
    model.result("pg5").feature("str1").selection().named("geom1_boxsel5");
    model.result("pg5").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg5").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u6781\u5316\u56fe");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").set("expr", new String[]{"fc.phis0_ec1"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg13").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg13").run();
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u901a\u9053\u7684\u6c34\u6d3b\u5ea6");
    model.result("pg14").selection().geom("geom1", 2);
    model.result("pg14").selection().named("geom1_ext1_bnd");
    model.result("pg14").set("edges", false);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "fc.aw");
    model.result("pg14").feature("surf1").set("descr", "\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u819c\u7684\u6c34\u6d3b\u5ea6");
    model.result("pg15").selection().named("geom1_ext2_bnd");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").set("expr", "fc.aw_mem");
    model.result("pg15").feature("surf1").set("descr", "\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result().duplicate("pg16", "pg15");
    model.result("pg16").run();
    model.result("pg16").label("\u819c\u7684\u7535\u5bfc\u7387");
    model.result("pg16").run();
    model.result("pg16").feature("surf1").set("expr", "fc.sigmalzz");
    model.result("pg16").feature("surf1").set("descr", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\uff0czz \u5206\u91cf");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result().duplicate("pg17", "pg16");
    model.result("pg17").run();
    model.result("pg17").label("\u8de8\u819c\u7684\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg17").selection().named("geom1_boxsel9");
    model.result("pg17").run();
    model.result("pg17").feature("surf1").set("expr", "fc.nIl");
    model.result("pg17").feature("surf1").set("descr", "\u6cd5\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg17").run();
    model.result("pg17").run();
    model.result().duplicate("pg18", "pg17");
    model.result("pg18").run();
    model.result("pg18").label("\u8de8\u819c\u7684\u6c34\u901a\u91cf");
    model.result("pg18").run();
    model.result("pg18").feature("surf1").set("expr", "-fc.r_abs_dsp");
    model.result("pg18").run();

    model.param().set("W_plate_min", "20[mm]");
    model.param().set("N_ch", "2");
    model.param().set("N_repeat", "2");

    model.component("comp1").geom("geom1").run("unisel2");

    model.param("par2").set("stoich_H2", "1.2");
    model.param("par2").set("RH_an", "25[%]");

    model.sol("sol1").copySolution("sol4");
    model.sol("sol4").label("\u89e3 - \u8f83\u5c0f\u7684\u51e0\u4f55");
    model.sol("sol1").createAutoSequence("std1");
    model.sol("sol1").feature("v2").set("control", "user");
    model.sol("sol1").feature("v2").feature("comp1_fc_mu0").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_phil").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_phis").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wH2O_H2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wH2O_O2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_wN2_O2").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_ecph1_ec1_phis0").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_h2gasph1_h2in1_wbndH2O").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_o2gasph1_o2in1_wbndH2O").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_fc_o2gasph1_o2in1_wbndN2").set("solvefor", false);

    model.study("std1").feature("stat2").set("plot", true);
    model.study("std1").feature("stat2").set("plotgroup", "pg14");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg13").run();
    model.result("pg14").run();
    model.result("pg15").run();
    model.result("pg16").run();
    model.result("pg17").run();
    model.result("pg18").run();
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").feature().remove("mslc1");
    model.result("pg12").run();
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "fc.aw");
    model.result("pg12").feature("surf1").set("descr", "\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09");
    model.result("pg12").run();
    model.result("pg12").set("edges", false);
    model.result("pg12").run();

    model
         .title("\u5177\u6709\u86c7\u5f62\u6d41\u573a\u7684\u4f4e\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60");

    model
         .description("\u672c\u6559\u7a0b\u63a2\u8ba8\u4e86\u5728\u4f7f\u7528\u86c7\u5f62\u6d41\u573a\u6a21\u5f0f\u65f6\uff0c\u4f4e\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60 (PEMFC) \u4e2d\u7684\u7535\u6d41\u5206\u5e03\u60c5\u51b5\u3002\n\n\u7535\u6c60\u4ee5\u9006\u6d41\u6a21\u5f0f\u8fd0\u884c\uff0c\u6c27\u548c\u6c22\u7684\u5165\u53e3\u6d41\u5206\u522b\u4f4d\u4e8e\u76f8\u5bf9\u7684\u4e24\u4fa7\u3002\n\n\u672c\u4f8b\u91c7\u7528\u76f8\u5bf9\u6e7f\u5ea6\u8f83\u5c0f\u7684\u8fdb\u6c14\u6210\u5206\uff0c\u4ee5\u4fbf\u7535\u6c60\u4f9d\u9760\u81ea\u589e\u6e7f\u6765\u5b9e\u73b0\u826f\u597d\u7684\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pemfc_serpentine_flow_field.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
