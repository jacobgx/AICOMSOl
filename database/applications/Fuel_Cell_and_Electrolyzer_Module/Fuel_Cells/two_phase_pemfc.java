/*
 * two_phase_pemfc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:11 by COMSOL 6.3.0.290. */
public class two_phase_pemfc {

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
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");
    model.component("comp1").physics().create("phtr", "PhaseTransportFreePorous", "geom1");
    model.component("comp1").physics("phtr").field("volumefraction").field("s_g");
    model.component("comp1").physics("phtr").field("volumefraction").component(new String[]{"s_g", "s_w"});

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 3);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();
    model.component("comp1").multiphysics().create("nsd1", "NavierStokesDarcyCoupling", 2);
    model.component("comp1").multiphysics("nsd1").set("NavierStokes_physics", "spf");
    model.component("comp1").multiphysics("nsd1").set("Darcy_physics", "dl");
    model.component("comp1").multiphysics("nsd1").selection().all();
    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 3);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

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
    model.study("std1").feature("cdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/dl", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/mfpm1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nsd1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/mfmm1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/fc", true);
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nsd1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfmm1", true);

    model.component("comp1").geom("geom1").insertFile("two_phase_pemfc_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("adjsel3");

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("alphaa_ORR", "4-alphac_ORR", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("alphac_ORR", "1", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("Av_HOR", "1e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef\uff0c\u6c22\u6c27\u5316");
    model.param("par2").set("Av_ORR", "1e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef\uff0c\u6c27\u8fd8\u539f");
    model.param("par2")
         .set("i0_ref_HOR", "100[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("i0_ref_ORR", "1e-4[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("perm_gdl", "1e-11[m^2]", "gdl \u7684\u6c34\u529b\u6e17\u900f\u7387");
    model.param("par2").set("por_gdl", "0.7", "gdl \u4e2d\u7684\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("RH", "100[%]", "\u8fdb\u6c14\u7684\u76f8\u5bf9\u6e7f\u5ea6");
    model.param("par2").set("sigmas_gdl_IP", "5000[S/m]", "gdl \u7684\u9762\u5185\u7535\u5bfc\u7387");
    model.param("par2").set("sigmas_gdl_TP", "70[S/m]", "gdl \u7684\u7a7f\u5c42\u7535\u5bfc\u7387");
    model.param("par2")
         .set("D_eddy", "1e-5[m^2/s]", "\u901a\u9053\u4e2d\u7684\u4e24\u76f8\u6da1\u6d41\u6269\u6563\u7cfb\u6570");
    model.param("par2").set("T", "70[degC]", "\u7535\u6c60\u5de5\u4f5c\u6e29\u5ea6");
    model.param("par2").set("H_cl", "8[um]", "\u50ac\u5316\u5c42 (GDE) \u539a\u5ea6");
    model.param("par2").set("I_avg_cell", "1[A/cm^2]", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("A_cell", "H_act*W_act", "\u7535\u6c60\u9762\u79ef");
    model.param("par2").set("I_cell", "I_avg_cell*A_cell", "\u7535\u6c60\u7535\u6d41");
    model.param("par2").set("p_BC", "0.075[bar]", "Brooks-Corey \u5165\u53e3\u538b\u529b");
    model.param("par2").set("lambda_BC", "3", "Brooks-Corey \u53c2\u6570");
    model.param("par2")
         .set("sw_res_100_aw", "1e-3", "100% \u76f8\u5bf9\u6e7f\u5ea6\u4e0b\u7684\u6b8b\u4f59\u9971\u548c\u5ea6");
    model.param("par2").set("kw", "100000[mol/(m^3*s)]", "\u51b7\u51dd\u901f\u7387\u5e38\u6570");
    model.param("par2")
         .set("M_O2", "32[g/mol]*I_cell/(4*F_const)", "\u7535\u6c60\u4e2d\u6c27\u7684\u8d28\u91cf\u6d88\u8017");
    model.param("par2")
         .set("M_H2", "2[g/mol]*I_cell/(2*F_const)", "\u7535\u6c60\u4e2d\u6c22\u7684\u8d28\u91cf\u6d88\u8017");
    model.param("par2").set("stoich_cath", "2.5", "\u6c27\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("stoich_an", "2", "\u6c22\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("wO2_in", "0.18", "\u5165\u53e3\u5904\u6c27\u7684\u8d28\u91cf\u5206\u6570");
    model.param("par2").set("wH2_in", "0.2", "\u5165\u53e3\u5904\u6c22\u7684\u8d28\u91cf\u5206\u6570");
    model.param("par2")
         .set("M_an", "stoich_an*M_H2/wH2_in", "\u9633\u6781\u5165\u53e3\u7684\u8d28\u91cf\u6d41\u7387");
    model.param("par2")
         .set("M_cath", "stoich_cath*M_O2/wO2_in", "\u9634\u6781\u5165\u53e3\u7684\u8d28\u91cf\u6d41\u7387");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("geom1_unisel2");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("U", "dl.u");
    model.component("comp1").variable("var1").set("V", "dl.v");
    model.component("comp1").variable("var1").set("W", "dl.w");
    model.component("comp1").variable("var1").set("pA", "dl.pA");
    model.component("comp1").variable("var1").set("epsg_gdl", "s_g*por_gdl");
    model.component("comp1").variable("var1").set("sw_res", "sw_res_100_aw*max(min(fc.aw,1),eps)", "1");
    model.component("comp1").variable("var1").set("r_c", "kw*(a_H2O_g-a_H2O_l)");
    model.component("comp1").variable("var1")
         .set("a_H2O_l", "if(s_w<sw_res_100_aw, s_w/sw_res_100_aw,1)*fc.p_vap/1[atm]");
    model.component("comp1").variable("var1").set("a_H2O_g", "fc.pH2O/1[atm]");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 3);
    model.component("comp1").variable("var2").selection().named("geom1_unisel1");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("U", "u");
    model.component("comp1").variable("var2").set("V", "v");
    model.component("comp1").variable("var2").set("W", "w");
    model.component("comp1").variable("var2").set("pA", "spf.pA");

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
    model.component("comp1").material("mat1").selection().named("geom1_sel3");
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("geom1_unisel4");

    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O_l", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O_l", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_sel3");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("aw0", "RH");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_sel4");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_TP"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "epsg_gdl");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_sel2");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_IP", "0", "0", "0", "sigmas_gdl_TP"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_gdl");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("geom1_sel7");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "H_cl");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_ref_HOR");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "Av_HOR");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("geom1_sel6");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "H_cl");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_ref_ORR");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("alphaa", "alphaa_ORR");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "Av_ORR");
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("h2fch1").selection().named("geom1_sel5_dom");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("geom1_sel1_dom");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("geom1_difsel2");
    model.component("comp1").physics("fc").feature("ecph1").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("Its", "-I_cell");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").selection().named("geom1_difsel1");
    model.component("comp1").physics("fc").feature("h2gasph1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("fc").feature("h2gasph1").set("minput_pressure", "pA");
    model.component("comp1").physics("fc").feature("h2gasph1").set("u", new String[]{"U", "V", "W"});
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("geom1_sel9");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("geom1_sel11");
    model.component("comp1").physics("fc").feature("h2gasph1").create("wce1", "WaterCondensationEvaporation", 3);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("wce1").selection().named("geom1_sel4");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("wce1")
         .set("CondensationEvaporationRateType", "userdef");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("wce1").set("r_ce", "r_c");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("o2gasph1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("fc").feature("o2gasph1").set("minput_pressure", "pA");
    model.component("comp1").physics("fc").feature("o2gasph1").set("u", new String[]{"U", "V", "W"});
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_sel8");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("geom1_sel10");
    model.component("comp1").physics("fc").feature("o2gasph1").create("wce1", "WaterCondensationEvaporation", 3);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("wce1").selection().named("geom1_sel2");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("wce1")
         .set("CondensationEvaporationRateType", "userdef");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("wce1").set("r_ce", "r_c");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T");
    model.component("comp1").physics("spf").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel8");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("multipleInlets", false);
    model.component("comp1").physics("spf").feature("inl1").set("mfr", "M_cath");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().named("geom1_sel9");
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl2").set("multipleInlets", false);
    model.component("comp1").physics("spf").feature("inl2").set("mfr", "M_an");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_unisel6");
    model.component("comp1").physics("dl").selection().named("geom1_unisel2");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "por_gdl");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"perm_gdl", "0", "0", "0", "perm_gdl", "0", "0", "0", "perm_gdl"});
    model.component("comp1").physics("phtr").selection().named("geom1_unisel3");
    model.component("comp1").physics("phtr").feature("fluid1").create("tm1", "TurbulentMixing", 3);
    model.component("comp1").physics("phtr").feature("fluid1").feature("tm1").set("nuT", "D_eddy");
    model.component("comp1").physics("phtr").feature("fluid1").feature("tm1").set("ScT", 1);
    model.component("comp1").physics("phtr").feature("porous1").selection().named("geom1_unisel2");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("capillarypressuremodel", "BrooksCorey");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("pec", "p_BC");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("lambdap", "lambda_BC");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("rhoint_s_g_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("mu_s_g_mat", "root.comp1.fc.mu");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("rhoint_s_w_mat", "root.comp1.fc.rhoH2O_l");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("mu_s_w_mat", "root.comp1.fc.muH2O_l");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("sr_s_w", "sw_res");
    model.component("comp1").physics("phtr").create("init2", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init2").selection().named("geom1_unisel2");
    model.component("comp1").physics("phtr").feature("init2").setIndex("s0", "sw_res_100_aw*RH", 1);
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 2);
    model.component("comp1").physics("phtr").feature("sa1").selection().named("geom1_unisel5");
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").create("of1", "Outflow", 2);
    model.component("comp1").physics("phtr").feature("of1").selection().named("geom1_unisel6");
    model.component("comp1").physics("phtr").create("ms1", "MassSource", 3);
    model.component("comp1").physics("phtr").feature("ms1").selection().named("geom1_unisel2");
    model.component("comp1").physics("phtr").feature("ms1").set("MassTransferToOtherPhases", true);
    model.component("comp1").physics("phtr").feature("ms1").setIndex("qs_s_g_src", "root.comp1.fc.Q_g", 0);
    model.component("comp1").physics("phtr").feature("ms1").setIndex("qs_s_w_src", "root.comp1.fc.Q_l", 0);
    model.component("comp1").physics("phtr").create("bms1", "BoundaryMassSource", 2);
    model.component("comp1").physics("phtr").feature("bms1").selection().named("geom1_unisel4");
    model.component("comp1").physics("phtr").feature("bms1").setIndex("qb_s_g_src", "root.comp1.fc.N_g", 0);
    model.component("comp1").physics("phtr").feature("bms1").setIndex("qb_s_w_src", "root.comp1.fc.N_l", 0);

    model.component("comp1").multiphysics("mfmm1").set("DispersedPhase", "LiquidDropletsBubbles");
    model.component("comp1").multiphysics("mfmm1").set("rhoc_mat", "root.comp1.fc.rho");
    model.component("comp1").multiphysics("mfmm1").set("muc_mat", "root.comp1.fc.mu");
    model.component("comp1").multiphysics("mfmm1").set("rho_s_w_mat", "root.comp1.fc.rhoH2O_l");
    model.component("comp1").multiphysics("mfmm1").set("mu_s_w_mat", "root.comp1.fc.muH2O_l");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_unisel5");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_sel1_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_ribch");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").selection().named("geom1_adjsel3");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("hmax", "H_ch/3");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmax", "W_rib/10");
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("cpf1", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").named("geom1_boxsel2");
    model.component("comp1").mesh("mesh1").run("cpf1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(7, 8, 9);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().named("geom1_unisel2");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run("swe2");
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("geom1_sel5_dom");
    model.component("comp1").mesh("mesh1").feature("swe3").selection("sourceface").named("geom1_sel9");
    model.component("comp1").mesh("mesh1").feature("swe3").selection("targetface").named("geom1_sel11");
    model.component("comp1").mesh("mesh1").feature("swe3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hmax", "W_ribch");
    model.component("comp1").mesh("mesh1").feature("swe3").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size2").selection().named("geom1_adjsel3");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size2").set("hmax", "H_ch/3");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("cdi2", "CurrentDistributionInitialization");
    model.study("std1").feature().move("cdi2", 1);
    model.study("std1").feature("cdi2").set("initType", "secondary");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 2);
    model.study("std1").feature("stat").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/phtr", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"phtr/ms1", "phtr/bms1"});
    model.study("std1").feature("time").set("tlist", "0 1 10 60");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "fc.phis0_ec1");
    model.component("comp1").probe("var1").set("descr", "\u8fb9\u754c\u7535\u4f4d");
    model.component("comp1").probe("var1").set("descractive", true);

    model.study("std1").feature("stat").set("probesel", "none");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 4, 0);
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
    model.result("pg3").setIndex("looplevel", 4, 0);
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
    model.result("pg4").setIndex("looplevel", 4, 0);
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
    model.result("pg5").setIndex("looplevel", 4, 0);
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
    model.result("pg6").setIndex("looplevel", 4, 0);
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
    model.result("pg7").setIndex("looplevel", 4, 0);
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
    model.result("pg8").setIndex("looplevel", 4, 0);
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
    model.result("pg9").setIndex("looplevel", 4, 0);
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
    model.result("pg10").setIndex("looplevel", 4, 0);
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
    model.result("pg11").setIndex("looplevel", 4, 0);
    model.result("pg11").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset1");
    model.result("pg12").setIndex("looplevel", 4, 0);
    model.result("pg12").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg12").create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").set("expr", new String[]{"fc.aw"});
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u901f\u5ea6 (spf)");
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("slc1", "Slice");
    model.result("pg13").feature("slc1").label("\u5207\u9762");
    model.result("pg13").feature("slc1").set("showsolutionparams", "on");
    model.result("pg13").feature("slc1").set("expr", "spf.U");
    model.result("pg13").feature("slc1").set("smooth", "internal");
    model.result("pg13").feature("slc1").set("showsolutionparams", "on");
    model.result("pg13").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 20, 21, 22, 24, 25, 26, 40, 41, 42, 43, 44, 46, 48, 50, 52, 53, 54, 56, 57, 58, 60, 62, 63, 65, 67, 68, 69, 71, 72, 73, 75, 76, 77, 79, 80, 81, 82, 83, 84, 85, 86, 88, 90, 92, 94, 95, 96, 98, 99, 100, 102, 104, 105, 107, 110, 111, 112, 114, 115, 116, 121, 122);
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").label("\u538b\u529b (spf)");
    model.result("pg14").set("data", "surf1");
    model.result("pg14").setIndex("looplevel", 4, 0);
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u8868\u9762");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("expr", "p");
    model.result("pg14").feature("surf1").set("colortable", "Dipole");
    model.result("pg14").feature("surf1").set("smooth", "internal");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result("pg14").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u901f\u5ea6 (dl)");
    model.result("pg15").set("titletype", "custom");
    model.result("pg15").feature().create("str1", "Streamline");
    model.result("pg15").feature("str1").set("showsolutionparams", "on");
    model.result("pg15").feature("str1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg15").feature("str1").set("posmethod", "start");
    model.result("pg15").feature("str1").set("pointtype", "arrow");
    model.result("pg15").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg15").feature("str1").set("smooth", "internal");
    model.result("pg15").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("showsolutionparams", "on");
    model.result("pg15").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("showsolutionparams", "on");
    model.result("pg15").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("showsolutionparams", "on");
    model.result("pg15").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("showsolutionparams", "on");
    model.result("pg15").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg15").feature("str1").set("data", "parent");
    model.result("pg15").feature("str1").selection().geom("geom1", 2);
    model.result("pg15").feature("str1").selection()
         .set(27, 28, 29, 32, 33, 34, 35, 36, 37, 39, 47, 51, 61, 64, 89, 93, 103, 106, 118, 120);
    model.result("pg15").feature("str1").feature().create("col1", "Color");
    model.result("pg15").feature("str1").feature("col1").set("expr", "dl.U");
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").label("\u538b\u529b (dl)");
    model.result("pg16").feature().create("surf1", "Surface");
    model.result("pg16").feature("surf1").label("\u8868\u9762");
    model.result("pg16").feature("surf1").set("showsolutionparams", "on");
    model.result("pg16").feature("surf1").set("expr", "p2");
    model.result("pg16").feature("surf1").set("smooth", "internal");
    model.result("pg16").feature("surf1").set("showsolutionparams", "on");
    model.result("pg16").feature("surf1").set("data", "parent");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg17").feature().create("slc1", "Slice");
    model.result("pg17").feature("slc1").label("\u5207\u9762");
    model.result("pg17").feature("slc1").set("showsolutionparams", "on");
    model.result("pg17").feature("slc1").set("expr", "s_g");
    model.result("pg17").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg17").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg17").feature("slc1").set("smooth", "internal");
    model.result("pg17").feature("slc1").set("showsolutionparams", "on");
    model.result("pg17").feature("slc1").set("data", "parent");
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg18").feature().create("surf1", "Surface");
    model.result("pg18").feature("surf1").label("\u8868\u9762");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("expr", "s_g");
    model.result("pg18").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg18").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg18").feature("surf1").set("smooth", "internal");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").set("edges", false);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("slc1").set("quickplane", "xy");
    model.result("pg13").feature("slc1").set("quickzmethod", "coord");
    model.result("pg13").feature("slc1").set("quickz", "H_ch/2+H_mem/2+H_gdl");
    model.result("pg13").feature().duplicate("slc2", "slc1");
    model.result("pg13").run();
    model.result("pg13").feature("slc2").set("titletype", "none");
    model.result("pg13").feature("slc2").set("quickz", "-(H_ch/2+H_mem/2+H_gdl)");
    model.result("pg13").feature("slc2").set("colortable", "Prism");
    model.result("pg13").run();
    model.result("pg13").set("edges", false);
    model.result("pg13").run();
    model.result("pg17").run();
    model.result().remove("pg17");
    model.result().remove("pg18");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").create("vol1", "Volume");
    model.result("pg17").feature("vol1").set("expr", "s_w");
    model.result("pg17").feature("vol1").set("descr", "\u4f53\u79ef\u5206\u6570");
    model.result("pg17").feature("vol1").create("sel1", "Selection");
    model.result("pg17").feature("vol1").feature("sel1").selection().named("geom1_unisel2");
    model.result("pg17").run();
    model.result("pg17").feature().duplicate("vol2", "vol1");
    model.result("pg17").run();
    model.result("pg17").feature("vol2").set("titletype", "none");
    model.result("pg17").feature("vol2").set("colortable", "Prism");
    model.result("pg17").run();
    model.result("pg17").feature("vol2").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg17").run();
    model.result("pg17").set("edges", false);
    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").run();
    model.result("pg18").selection().geom("geom1", 2);
    model.result("pg18").selection().named("geom1_sel6");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "fc.nIl");
    model.result("pg18").feature("surf1").set("descr", "\u6cd5\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg18").run();
    model.result("pg18").set("edges", false);
    model.result("pg18").run();

    model.title("\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u71c3\u6599\u7535\u6c60\u4e2d\u7684\u4e24\u76f8\u6d41");

    model
         .description("\u672c\u6559\u7a0b\u7814\u7a76\u5728\u5177\u6709\u5e73\u884c\u6d41\u9053\u7684\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u71c3\u6599\u7535\u6c60\u4e2d\uff0c\u6c34\u51b7\u51dd\u73b0\u8c61\u7684\u5f71\u54cd\u3002\n\n\u6a21\u578b\u5206\u6790\u663e\u793a\uff0c\u51b7\u51dd\u53ef\u80fd\u662f\u7531\u9634\u6781\u4ea7\u751f\u6c34\u6216\u8005\u4ece\u6c14\u4f53\u6df7\u5408\u7269\u4e2d\u53bb\u9664\u5176\u4ed6\u53cd\u5e94\u7269\uff08H2 \u6216 O2\uff09\u6240\u5bfc\u81f4\u7684\u3002\n\n\u4ea7\u751f\u7684\u4e24\u76f8\u6d41\u4e0e\u7535\u6c60\u7535\u6d41\u5206\u5e03\u548c\u8d28\u91cf\u4f20\u9012\u5b8c\u5168\u8026\u5408\uff0c\u5e76\u5305\u542b\u5728\u6d41\u9053\u548c\u6c14\u4f53\u6269\u6563\u5c42\u4e2d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("two_phase_pemfc.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
