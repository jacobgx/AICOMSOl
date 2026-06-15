/*
 * pem_mea_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:10 by COMSOL 6.3.0.290. */
public class pem_mea_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

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
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

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
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_CL", "10[um]", "\u50ac\u5316\u5c42\u539a\u5ea6");
    model.param().set("L_GDL", "200[um]", "\u6c14\u4f53\u6269\u6563\u5c42\u539a\u5ea6");
    model.param().set("L_mem", "30[um]", "\u819c\u539a\u5ea6");
    model.param().set("L_MPL", "20[um]", "\u5fae\u5b54\u5c42\u539a\u5ea6");
    model.param().set("a_CL", "5e7[m^2/m^3]", "\u6bd4\u9762\u79ef\uff0c\u50ac\u5316\u5c42");
    model.param().set("epsl_CL", "0.2", "\u79bb\u805a\u7269\u4f53\u79ef\u5206\u6570\uff0c\u50ac\u5316\u5c42");
    model.param().set("epss_CL", "0.4", "\u7535\u6781\u4f53\u79ef\u5206\u6570\uff0c\u50ac\u5316\u5c42");
    model.param().set("epss_GDL", "0.4", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param().set("epss_MPL", "0.4", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u5fae\u5b54\u5c42");
    model.param()
         .set("epsg_CL", "1-epss_CL-epsl_CL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u50ac\u5316\u5c42");
    model.param().set("epsg_MPL", "1-epss_MPL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u5fae\u5b54\u5c42");
    model.param()
         .set("epsg_GDL", "1-epss_GDL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param().set("sigmas_CL", "25[S/m]", "\u7535\u5bfc\u7387\uff0c\u50ac\u5316\u5c42");
    model.param().set("sigmas_GDL", "100[S/m]", "\u7535\u5bfc\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param().set("sigmas_MPL", "50[S/m]", "\u7535\u5bfc\u7387\uff0c\u5fae\u5b54\u5c42");
    model.param().set("kappag_CL", "1e-12[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u50ac\u5316\u5c42");
    model.param()
         .set("kappag_GDL", "1e-10[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param().set("kappag_MPL", "1e-10[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u5fae\u5b54\u5c42");
    model.param().set("E_cell", "1[V]", "\u7535\u6c60\u7535\u4f4d");
    model.param().set("i0_H2", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param().set("i0_O2", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param().set("alphaa_O2", "3", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param().set("T0", "70[degC]", "\u5916\u90e8/\u521d\u59cb\u6e29\u5ea6");
    model.param().set("RH_an", "85[%]", "\u5165\u53e3\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u9633\u6781");
    model.param().set("RH_cath", "85[%]", "\u5165\u53e3\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u9634\u6781");
    model.param().set("kappa_CL", "0.3", "\u70ed\u5bfc\u7387\uff0c\u50ac\u5316\u5c42");
    model.param().set("kappa_MPL", "0.3", "\u70ed\u5bfc\u7387\uff0c\u5fae\u5b54\u5c42");
    model.param().set("kappa_GDL", "0.3", "\u70ed\u5bfc\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").label("\u9633\u6781 GDL");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_GDL", 1);
    model.component("comp1").geom("geom1").feature("i1").set("selresult", true);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("i2", "Interval");
    model.component("comp1").geom("geom1").feature("i2").label("\u9633\u6781 MPL");
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "L_GDL", 0);
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "L_GDL+L_MPL", 1);
    model.component("comp1").geom("geom1").feature("i2").set("selresult", true);
    model.component("comp1").geom("geom1").run("i2");
    model.component("comp1").geom("geom1").create("i3", "Interval");
    model.component("comp1").geom("geom1").feature("i3").label("\u9633\u6781 CL");
    model.component("comp1").geom("geom1").feature("i3").setIndex("coord", "L_GDL+L_MPL", 0);
    model.component("comp1").geom("geom1").feature("i3").setIndex("coord", "L_GDL+L_MPL+L_CL", 1);
    model.component("comp1").geom("geom1").feature("i3").set("selresult", true);
    model.component("comp1").geom("geom1").run("i3");
    model.component("comp1").geom("geom1").create("i4", "Interval");
    model.component("comp1").geom("geom1").feature("i4").label("\u819c");
    model.component("comp1").geom("geom1").feature("i4").setIndex("coord", "L_GDL+L_MPL+L_CL", 0);
    model.component("comp1").geom("geom1").feature("i4").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem", 1);
    model.component("comp1").geom("geom1").feature("i4").set("selresult", true);
    model.component("comp1").geom("geom1").run("i4");
    model.component("comp1").geom("geom1").create("i5", "Interval");
    model.component("comp1").geom("geom1").feature("i5").label("\u9634\u6781 CL");
    model.component("comp1").geom("geom1").feature("i5").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem", 0);
    model.component("comp1").geom("geom1").feature("i5").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem+L_CL", 1);
    model.component("comp1").geom("geom1").feature("i5").set("selresult", true);
    model.component("comp1").geom("geom1").run("i5");
    model.component("comp1").geom("geom1").create("i6", "Interval");
    model.component("comp1").geom("geom1").feature("i6").label("\u9634\u6781 MPL");
    model.component("comp1").geom("geom1").feature("i6").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem+L_CL", 0);
    model.component("comp1").geom("geom1").feature("i6").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem+L_CL+L_MPL", 1);
    model.component("comp1").geom("geom1").feature("i6").set("selresult", true);
    model.component("comp1").geom("geom1").run("i6");
    model.component("comp1").geom("geom1").create("i7", "Interval");
    model.component("comp1").geom("geom1").feature("i7").label("\u9634\u6781 GDL");
    model.component("comp1").geom("geom1").feature("i7").setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem+L_CL+L_MPL", 0);
    model.component("comp1").geom("geom1").feature("i7")
         .setIndex("coord", "L_GDL+L_MPL+L_CL+L_mem+L_CL+L_MPL+L_GDL", 1);
    model.component("comp1").geom("geom1").feature("i7").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("uni1", "Union");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("uni1").label("\u9633\u6781\u6c14\u5ba4");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_i1_dom", "geom1_i2_dom", "geom1_i3_dom"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9634\u6781\u6c14\u5ba4");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_i5_dom", "geom1_i6_dom", "geom1_i7_dom"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u79bb\u805a\u7269\u57df");
    model.component("comp1").selection("uni3")
         .set("input", new String[]{"geom1_i3_dom", "geom1_i4_dom", "geom1_i5_dom"});
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("CL");
    model.component("comp1").selection("uni4").set("input", new String[]{"geom1_i3_dom", "geom1_i5_dom"});
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("MPL");
    model.component("comp1").selection("uni5").set("input", new String[]{"geom1_i2_dom", "geom1_i6_dom"});
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("GDL");
    model.component("comp1").selection("uni6").set("input", new String[]{"geom1_i1_dom", "geom1_i7_dom"});

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
    model.component("comp1").material("mat1").selection().named("uni3");
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.component("comp1").material("mat2").selection().geom("geom1", 0);
    model.component("comp1").material("mat2").selection().set(4, 5);

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2_mem", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("O2_mem", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 1);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_i4_dom");
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 1);
    model.component("comp1").physics("fc").feature("h2gde1").selection().named("geom1_i3_dom");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 1);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_i2_dom");
    model.component("comp1").physics("fc").create("h2gdl2", "H2GasDiffusionLayer", 1);
    model.component("comp1").physics("fc").feature("h2gdl2").selection().named("geom1_i1_dom");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 1);
    model.component("comp1").physics("fc").feature("o2gde1").selection().named("geom1_i5_dom");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 1);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_i6_dom");
    model.component("comp1").physics("fc").create("o2gdl2", "O2GasDiffusionLayer", 1);
    model.component("comp1").physics("fc").feature("o2gdl2").selection().named("geom1_i7_dom");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T0");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("sigmas", new String[]{"sigmas_CL", "0", "0", "0", "sigmas_CL", "0", "0", "0", "sigmas_CL"});
    model.component("comp1").physics("fc").feature("h2gde1").set("epsl", "epsl_CL");
    model.component("comp1").physics("fc").feature("h2gde1").set("epsg", "epsg_CL");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("kappag", new String[]{"kappag_CL", "0", "0", "0", "kappag_CL", "0", "0", "0", "kappag_CL"});
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").feature("h2gdl1").label("\u6c22\u6c14\u6269\u6563\u5c42 1 - MPL");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_MPL", "0", "0", "0", "sigmas_MPL", "0", "0", "0", "sigmas_MPL"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "epsg_MPL");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("kappag", new String[]{"kappag_MPL", "0", "0", "0", "kappag_MPL", "0", "0", "0", "kappag_MPL"});
    model.component("comp1").physics("fc").feature("h2gdl2").label("\u6c22\u6c14\u6269\u6563\u5c42 2 - GDL");
    model.component("comp1").physics("fc").feature("h2gdl2")
         .set("sigmas", new String[]{"sigmas_GDL", "0", "0", "0", "sigmas_GDL", "0", "0", "0", "sigmas_GDL"});
    model.component("comp1").physics("fc").feature("h2gdl2").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").feature("h2gdl2")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"sigmas_CL", "0", "0", "0", "sigmas_CL", "0", "0", "0", "sigmas_CL"});
    model.component("comp1").physics("fc").feature("o2gde1").set("epsl", "epsl_CL");
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "epsg_CL");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("kappag", new String[]{"kappag_CL", "0", "0", "0", "kappag_CL", "0", "0", "0", "kappag_CL"});
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("alphaa", "alphaa_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").feature("o2gdl1").label("\u6c27\u6c14\u6269\u6563\u5c42 1 - MPL");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_MPL", "0", "0", "0", "sigmas_MPL", "0", "0", "0", "sigmas_MPL"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_MPL");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("kappag", new String[]{"kappag_MPL", "0", "0", "0", "kappag_MPL", "0", "0", "0", "kappag_MPL"});
    model.component("comp1").physics("fc").feature("o2gdl2").label("\u6c27\u6c14\u6269\u6563\u5c42 2 - GDL");
    model.component("comp1").physics("fc").feature("o2gdl2")
         .set("sigmas", new String[]{"sigmas_GDL", "0", "0", "0", "sigmas_GDL", "0", "0", "0", "sigmas_GDL"});
    model.component("comp1").physics("fc").feature("o2gdl2").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").feature("o2gdl2")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(1);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(8);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_cell");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("RH_hum", "RH_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("T_hum", "T0");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 0);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().set(1);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("RH_hum", "RH_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T0");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 0);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().set(8);
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 1);
    model.component("comp1").physics("ht").feature("solid2").label("\u56fa\u4f53 2 - GDL");
    model.component("comp1").physics("ht").feature("solid2").selection().named("uni6");
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"kappa_GDL", "0", "0", "0", "kappa_GDL", "0", "0", "0", "kappa_GDL"});
    model.component("comp1").physics("ht").create("solid3", "SolidHeatTransferModel", 1);
    model.component("comp1").physics("ht").feature("solid3").label("\u56fa\u4f53 3 - MPL");
    model.component("comp1").physics("ht").feature("solid3").selection().named("uni5");
    model.component("comp1").physics("ht").feature("solid3").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3")
         .set("k", new String[]{"kappa_MPL", "0", "0", "0", "kappa_MPL", "0", "0", "0", "kappa_MPL"});
    model.component("comp1").physics("ht").create("solid4", "SolidHeatTransferModel", 1);
    model.component("comp1").physics("ht").feature("solid4").label("\u56fa\u4f53 4 - CL");
    model.component("comp1").physics("ht").feature("solid4").selection().named("uni4");
    model.component("comp1").physics("ht").feature("solid4").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid4")
         .set("k", new String[]{"kappa_CL", "0", "0", "0", "kappa_CL", "0", "0", "0", "kappa_CL"});
    model.component("comp1").physics("ht").feature("solid1").label("\u56fa\u4f53 1 - \u819c");
    model.component("comp1").physics("ht").feature("solid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid4").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid4").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 8);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().named("uni4");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L_CL", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L_CL", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "RH_an", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.95 0.5", 0);
    model.study("std1").feature("param").setIndex("pname", "L_CL", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "L_CL", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "RH_cath", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0.95 0.5", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L_CL", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "L_CL", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1.0,-0.01,0.91) range(0.9,-0.05,0.5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"fc.phis"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"fc.phil"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2 (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"fc.xH2"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, O2 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"fc.xO2"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2O (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"fc.xH2O"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, N2 (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"fc.xN2"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u538b\u529b (fc)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"fc.p"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"fc.aw"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").label("\u6e29\u5ea6 (ht)");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg9").feature().create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("expr", "T");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").set("data", "parent");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg1").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u6781\u5316\u56fe");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().set(8);
    model.result("pg10").feature("ptgr1").set("expr", "fc.phis");
    model.result("pg10").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg10").feature("ptgr1").set("xdata", "expr");
    model.result("pg10").feature("ptgr1").set("xdataexpr", "fc.nIs");
    model.result("pg10").feature("ptgr1").set("xdatadescr", "\u6cd5\u5411\u7535\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("autopoint", false);
    model.result("pg10").feature("ptgr1").create("filt1", "PointGraphFilter");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").feature("filt1").set("expr", "fc.nIs>0");
    model.result("pg10").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u79bb\u805a\u7269\u7535\u5bfc\u7387");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevelinput", "last", 0);
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").selection().all();
    model.result("pg11").feature("lngr1").set("expr", "fc.sigmalxx");
    model.result("pg11").feature("lngr1").set("descr", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\uff0cxx \u5206\u91cf");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "x");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").run();
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg10").feature("ptgr1")
         .set("legendpattern", "RH<sub>an</sub>=eval(RH_an), RH<sub>cath</sub>=eval(RH_cath)");
    model.result("pg10").run();
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg8").feature("lngr1")
         .set("legendpattern", "RH<sub>an</sub>=eval(RH_an), RH<sub>cath</sub>=eval(RH_cath)");
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "middleleft");
    model.result("pg8").run();
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg11").feature("lngr1")
         .set("legendpattern", "RH<sub>an</sub>=eval(RH_an), RH<sub>cath</sub>=eval(RH_cath)");
    model.result("pg11").run();
    model.result("pg11").set("legendpos", "lowermiddle");
    model.result("pg11").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").set("legendpos", "lowermiddle");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("unit", "\u00b0C");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg9").feature("lngr1")
         .set("legendpattern", "RH<sub>an</sub>=eval(RH_an), RH<sub>cath</sub>=eval(RH_cath)");
    model.result("pg9").run();

    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .createPlot("pg12");

    model.result("pg12").run();
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "\u5438\u6c34\u91cf");
    model.result("pg12").set("xlabel", "a<sub>w</sub> (1)");
    model.result("pg12").set("ylabel", "\\lambda) (1)");
    model.result("pg12").run();
    model.result().remove("pg12");

    model
         .title("\u805a\u5408\u7269\u7535\u89e3\u8d28\u71c3\u6599\u7535\u6c60\u819c\u7535\u6781\u7ec4\u4ef6\u4e2d\u7684\u4f20\u9012\u73b0\u8c61");

    model
         .description("\u672c\u6559\u7a0b\u6a21\u62df\u8fdb\u6c14\u76f8\u5bf9\u6e7f\u5ea6\u5bf9\u4f4e\u6e29\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c\u7535\u6781\u7ec4\u4ef6\u6027\u80fd\u7684\u5f71\u54cd\u3002\u6a21\u578b\u6db5\u76d6\u4e86\u6e7f\u5ea6\u76f8\u5173\u7684\u79bb\u805a\u7269\uff08\u7535\u89e3\u8d28\uff09\u7535\u5bfc\u7387\u3001\u6c14\u76f8\u8d28\u91cf\u4f20\u9012\u3001\u6c34\u79bb\u805a\u7269\u4f20\u9012\u4ee5\u53ca\u4f20\u70ed\u8fc7\u7a0b\uff0c\u5e76\u8003\u8651\u4e86\u6c22\u5728\u79bb\u805a\u7269\u819c\u4e0a\u7684\u4ea4\u53c9\u6269\u6563\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("pem_mea_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
