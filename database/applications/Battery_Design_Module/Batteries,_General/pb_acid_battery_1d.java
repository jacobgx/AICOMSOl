/*
 * pb_acid_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class pb_acid_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("leadbat", "LeadAcidBattery", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/leadbat", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/leadbat", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_sep", "0.006[cm]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_pos", "0.07[cm]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_res", "0.176[cm]", "\u50a8\u5c42\u539a\u5ea6");
    model.param().set("L_neg", "0.075[cm]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("T", "298[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("sigma_pos", "80[S/cm]", "PbO2 \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("sigma_neg", "4.8e4[S/cm]", "Pb \u7684\u7535\u5b50\u7535\u5bfc\u7387");
    model.param().set("cl_ref", "4.89[mol/l]", "\u53c2\u8003\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("cl_init", "cl_ref", "\u521d\u59cb\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("ex", "1.5", "\u6709\u6548\u6db2\u76f8\u4f20\u9012\u53c2\u6570\u7684\u6307\u6570");
    model.param().set("exm", "0.5", "\u6709\u6548\u7535\u5bfc\u7387\u6307\u6570");
    model.param().set("ex_sep", "3.53", "\u9694\u819c\u5b54\u9699\u7387\u6307\u6570");
    model.param().set("eps_sep", "0.73", "\u9694\u819c\u6eb6\u6db2-\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_pos_max", "0.53", "\u5b8c\u5168\u5145\u7535\u7684\u5b54\u9699\u7387\uff0c\u6b63\u6781");
    model.param().set("eps_pos_min", "0.3466", "\u96f6\u7535\u8377\u7684\u5b54\u9699\u7387\uff0c\u6b63\u6781");
    model.param().set("eps_neg_max", "0.53", "\u5b8c\u5168\u5145\u7535\u7684\u5b54\u9699\u7387\uff0c\u8d1f\u6781");
    model.param().set("eps_neg_min", "0.3066", "\u96f6\u7535\u8377\u7684\u5b54\u9699\u7387\uff0c\u6b63\u6781");
    model.param().set("eps_pos_init", "eps_pos_max-0.01", "\u521d\u59cb\u5b54\u9699\u7387\uff0c\u6b63\u6781");
    model.param().set("eps_neg_init", "eps_neg_max-0.01", "\u521d\u59cb\u5b54\u9699\u7387\uff0c\u8d1f\u6781");
    model.param().set("a_max_pos", "2.3e5[cm^2/cm^3]", "\u6700\u5927\u8868\u9762\u79ef\uff0c\u6b63\u6781");
    model.param().set("a_max_neg", "2.3e4[cm^2/cm^3]", "\u6700\u5927\u8868\u9762\u79ef\uff0c\u8d1f\u6781");
    model.param().set("morph_pos", "2", "\u52a8\u529b\u5b66\u5f62\u6001\u53c2\u6570\uff0c\u6b63\u6781");
    model.param().set("morph_neg", "2", "\u52a8\u529b\u5b66\u5f62\u6001\u53c2\u6570\uff0c\u8d1f\u6781");
    model.param().set("i0_ref_pos", "3e-7[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("i0_ref_neg", "3e-6[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("alpha_a_pos", "1.21", "\u9633\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("alpha_a_neg", "1.55", "\u9633\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param().set("alpha_c_pos", "0.79", "\u9634\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("alpha_c_neg", "0.45", "\u9634\u6781\u7535\u8377\u4f20\u9012\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param()
         .set("gamma_pos", "0", "\u7535\u8377\u8f6c\u79fb\u53cd\u5e94\u6d53\u5ea6\u6307\u6570\uff0c\u6b63\u6781");
    model.param().set("gamma_neg", "0", "\u7535\u8377\u8f6c\u79fb\u53cd\u5e94\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("i0_O2", "1e-27[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6 (O2)");
    model.param().set("alpha_O2", "2", "\u4f20\u9012\u7cfb\u6570 (O2)");
    model.param().set("i0_H2", "1e-13[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6 (H2)");
    model.param().set("alpha_H2", "0.5", "\u4f20\u9012\u7cfb\u6570 (H2)");
    model.param().set("C_dl_pos", "2e-5[F/cm^2]", "\u53cc\u7535\u5c42\u7535\u5bb9\uff0c\u6b63\u6781");
    model.param().set("C_dl_neg", "2e-5[F/cm^2]", "\u53cc\u7535\u5c42\u7535\u5bb9\uff0c\u8d1f\u6781");
    model.param().set("A_cell", "14*14.4*11.25[cm^2]", "\u7535\u6c60\u603b\u9762\u79ef");
    model.param().set("I_C1", "60[A]/A_cell", "C1-\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("I_disch", "-I_C1*C_factor", "\u5916\u52a0\u653e\u7535\u7535\u6d41");
    model.param().set("C_factor", "1/20", "\u7535\u6d41\u500d\u7387\u56e0\u5b50");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "20*3600");
    model.func("step1").set("from", 1);
    model.func("step1").set("to", 0);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_res", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 2);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 3);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Sulfuric Acid (Lead-Acid Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "Dl_int1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"100", "1.776E-09"}, 
         {"200", "1.802E-09"}, 
         {"300", "1.828E-09"}, 
         {"400", "1.854E-09"}, 
         {"500", "1.88E-09"}, 
         {"600", "1.906E-09"}, 
         {"700", "1.932E-09"}, 
         {"800", "1.958E-09"}, 
         {"900", "1.984E-09"}, 
         {"1000", "2.01E-09"}, 
         {"1100", "2.036E-09"}, 
         {"1200", "2.062E-09"}, 
         {"1300", "2.088E-09"}, 
         {"1400", "2.114E-09"}, 
         {"1500", "2.14E-09"}, 
         {"1600", "2.166E-09"}, 
         {"1700", "2.192E-09"}, 
         {"1800", "2.218E-09"}, 
         {"1900", "2.244E-09"}, 
         {"2000", "2.27E-09"}, 
         {"2100", "2.296E-09"}, 
         {"2200", "2.322E-09"}, 
         {"2300", "2.348E-09"}, 
         {"2400", "2.374E-09"}, 
         {"2500", "2.4E-09"}, 
         {"2600", "2.426E-09"}, 
         {"2700", "2.452E-09"}, 
         {"2800", "2.478E-09"}, 
         {"2900", "2.504E-09"}, 
         {"3000", "2.53E-09"}, 
         {"3100", "2.556E-09"}, 
         {"3200", "2.582E-09"}, 
         {"3300", "2.608E-09"}, 
         {"3400", "2.634E-09"}, 
         {"3500", "2.66E-09"}, 
         {"3600", "2.686E-09"}, 
         {"3700", "2.712E-09"}, 
         {"3800", "2.738E-09"}, 
         {"3900", "2.764E-09"}, 
         {"4000", "2.79E-09"}, 
         {"4100", "2.816E-09"}, 
         {"4200", "2.842E-09"}, 
         {"4300", "2.868E-09"}, 
         {"4400", "2.894E-09"}, 
         {"4500", "2.92E-09"}, 
         {"4600", "2.946E-09"}, 
         {"4700", "2.972E-09"}, 
         {"4800", "2.998E-09"}, 
         {"4900", "3.024E-09"}, 
         {"5000", "3.05E-09"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"Dl_int1(c/1[mol/m^3])", "0", "0", "0", "Dl_int1(c/1[mol/m^3])", "0", "0", "0", "Dl_int1(c/1[mol/m^3])"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"100", "4.995038022"}, 
         {"200", "9.852242095"}, 
         {"300", "14.5697732"}, 
         {"400", "19.14600473"}, 
         {"500", "23.57952137"}, 
         {"600", "27.86911759"}, 
         {"700", "32.01379583"}, 
         {"800", "36.01276421"}, 
         {"900", "39.86543396"}, 
         {"1000", "43.57141651"}, 
         {"1100", "47.1305202"}, 
         {"1200", "50.54274671"}, 
         {"1300", "53.80828718"}, 
         {"1400", "56.92751803"}, 
         {"1500", "59.90099656"}, 
         {"1600", "62.72945619"}, 
         {"1700", "65.41380154"}, 
         {"1800", "67.95510329"}, 
         {"1900", "70.35459273"}, 
         {"2000", "72.61365628"}, 
         {"2100", "74.73382964"}, 
         {"2200", "76.71679195"}, 
         {"2300", "78.5643597"}, 
         {"2400", "80.27848055"}, 
         {"2500", "81.86122705"}, 
         {"2600", "83.31479024"}, 
         {"2700", "84.64147319"}, 
         {"2800", "85.84368446"}, 
         {"2900", "86.92393156"}, 
         {"3000", "87.88481433"}, 
         {"3100", "88.72901834"}, 
         {"3200", "89.45930825"}, 
         {"3300", "90.07852126"}, 
         {"3400", "90.5895605"}, 
         {"3500", "90.99538856"}, 
         {"3600", "91.29902097"}, 
         {"3700", "91.50351989"}, 
         {"3800", "91.61198773"}, 
         {"3900", "91.62756098"}, 
         {"4000", "91.55340409"}, 
         {"4100", "91.39270353"}, 
         {"4200", "91.14866186"}, 
         {"4300", "90.82449208"}, 
         {"4400", "90.42341203"}, 
         {"4500", "89.94863895"}, 
         {"4600", "89.40338429"}, 
         {"4700", "88.79084855"}, 
         {"4800", "88.11421643"}, 
         {"4900", "87.37665209"}, 
         {"5000", "86.58129459"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").label("Pb (Negative, Lead-Acid Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq_Pb_int1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"100", "-0.233101328"}, 
         {"200", "-0.250742068"}, 
         {"300", "-0.261341495"}, 
         {"400", "-0.268981573"}, 
         {"500", "-0.275051951"}, 
         {"600", "-0.280170578"}, 
         {"700", "-0.284659863"}, 
         {"800", "-0.288706846"}, 
         {"900", "-0.292428595"}, 
         {"1000", "-0.295902708"}, 
         {"1100", "-0.29918299"}, 
         {"1200", "-0.302308128"}, 
         {"1300", "-0.305306792"}, 
         {"1400", "-0.308200773"}, 
         {"1500", "-0.311007"}, 
         {"1600", "-0.313738876"}, 
         {"1700", "-0.31640719"}, 
         {"1800", "-0.319020755"}, 
         {"1900", "-0.321586867"}, 
         {"2000", "-0.324111638"}, 
         {"2100", "-0.326600246"}, 
         {"2200", "-0.329057119"}, 
         {"2300", "-0.331486078"}, 
         {"2400", "-0.33389045"}, 
         {"2500", "-0.336273153"}, 
         {"2600", "-0.338636768"}, 
         {"2700", "-0.340983588"}, 
         {"2800", "-0.343315667"}, 
         {"2900", "-0.345634854"}, 
         {"3000", "-0.347942826"}, 
         {"3100", "-0.350241107"}, 
         {"3200", "-0.35253109"}, 
         {"3300", "-0.35481406"}, 
         {"3400", "-0.357091197"}, 
         {"3500", "-0.3593636"}, 
         {"3600", "-0.361632287"}, 
         {"3700", "-0.363898211"}, 
         {"3800", "-0.366162264"}, 
         {"3900", "-0.368425284"}, 
         {"4000", "-0.370688063"}, 
         {"4100", "-0.372951346"}, 
         {"4200", "-0.375215843"}, 
         {"4300", "-0.377482226"}, 
         {"4400", "-0.379751136"}, 
         {"4500", "-0.382023185"}, 
         {"4600", "-0.384298958"}, 
         {"4700", "-0.386579016"}, 
         {"4800", "-0.388863897"}, 
         {"4900", "-0.391154119"}, 
         {"5000", "-0.393450181"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.8e4[S/cm]", "0", "0", "0", "4.8e4[S/cm]", "0", "0", "0", "4.8e4[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq_Pb_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "1[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").label("PbO2 (Positive, Lead-Acid Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq_PbO2_int1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"100", "1.565887432"}, 
         {"200", "1.583369855"}, 
         {"300", "1.594408052"}, 
         {"400", "1.602318438"}, 
         {"500", "1.608527137"}, 
         {"600", "1.613708714"}, 
         {"700", "1.618222856"}, 
         {"800", "1.622279294"}, 
         {"900", "1.626008821"}, 
         {"1000", "1.629497354"}, 
         {"1100", "1.63280373"}, 
         {"1200", "1.635969633"}, 
         {"1300", "1.639025446"}, 
         {"1400", "1.64199386"}, 
         {"1500", "1.64489218"}, 
         {"1600", "1.647733852"}, 
         {"1700", "1.650529494"}, 
         {"1800", "1.653287616"}, 
         {"1900", "1.656015136"}, 
         {"2000", "1.658717741"}, 
         {"2100", "1.661400167"}, 
         {"2200", "1.664066396"}, 
         {"2300", "1.666719813"}, 
         {"2400", "1.669363322"}, 
         {"2500", "1.671999436"}, 
         {"2600", "1.674630352"}, 
         {"2700", "1.677258004"}, 
         {"2800", "1.679884107"}, 
         {"2900", "1.682510196"}, 
         {"3000", "1.685137654"}, 
         {"3100", "1.687767735"}, 
         {"3200", "1.690401583"}, 
         {"3300", "1.693040252"}, 
         {"3400", "1.695684711"}, 
         {"3500", "1.698335865"}, 
         {"3600", "1.700994557"}, 
         {"3700", "1.703661578"}, 
         {"3800", "1.706337676"}, 
         {"3900", "1.709023557"}, 
         {"4000", "1.711719894"}, 
         {"4100", "1.714427328"}, 
         {"4200", "1.717146474"}, 
         {"4300", "1.719877921"}, 
         {"4400", "1.722622238"}, 
         {"4500", "1.725379974"}, 
         {"4600", "1.72815166"}, 
         {"4700", "1.730937812"}, 
         {"4800", "1.733738931"}, 
         {"4900", "1.736555504"}, 
         {"5000", "1.739388009"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"80[S/cm]", "0", "0", "0", "80[S/cm]", "0", "0", "0", "80[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq_PbO2_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "M. Cugnet, S. Laruelle, S. Grugeon, B. Sahut, J. Sabatier, J.M. Tarascon, and A. Oustaloup, \u201cA Mathematical Model for the Simulation of New and Aged Automotive Lead-Acid Batteries,\u201d J. Electrochemical Soc., vol. 156, pp.A974\u2013A985, 2009");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "1[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").selection().set(4);
    model.component("comp1").material("mat3").selection().set(1);

    model.component("comp1").physics("leadbat").create("ppe1", "PositivePorousElectrode", 1);
    model.component("comp1").physics("leadbat").feature("ppe1").selection().set(1);
    model.component("comp1").physics("leadbat").feature("ppe1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("leadbat").feature("ppe1").set("ex", "ex");
    model.component("comp1").physics("leadbat").feature("ppe1").set("epsilon_0", "eps_pos_min");
    model.component("comp1").physics("leadbat").feature("ppe1").set("epsilon_max", "eps_pos_max");
    model.component("comp1").physics("leadbat").feature("ppe1").set("exm", "exm");
    model.component("comp1").physics("leadbat").feature("ppe1").set("EqPotentialHandlingType", "FirstReaction");
    model.component("comp1").physics("leadbat").create("sep1", "Separator", 1);
    model.component("comp1").physics("leadbat").feature("sep1").selection().set(3);
    model.component("comp1").physics("leadbat").feature("sep1").set("epsilon_sep", "eps_sep");
    model.component("comp1").physics("leadbat").feature("sep1").set("ex", "ex_sep");
    model.component("comp1").physics("leadbat").create("npe1", "NegativePorousElectrode", 1);
    model.component("comp1").physics("leadbat").feature("npe1").selection().set(4);
    model.component("comp1").physics("leadbat").feature("npe1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("leadbat").feature("npe1").set("ex", "ex");
    model.component("comp1").physics("leadbat").feature("npe1").set("epsilon_0", "eps_neg_min");
    model.component("comp1").physics("leadbat").feature("npe1").set("epsilon_max", "eps_neg_max");
    model.component("comp1").physics("leadbat").feature("npe1").set("exm", "exm");
    model.component("comp1").physics("leadbat").feature("npe1").set("EqPotentialHandlingType", "FirstReaction");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1")
         .set("ElectrodeKinetics", "LeadAcidInsertionDischarge");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("i0_ref", "i0_ref_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("alphaa", "alpha_a_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("alphac", "alpha_c_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("gamma", "gamma_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("amax", "a_max_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per1").set("zetad", "morph_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("Eeq", 1.23);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("i0", "i0_O2*(cl/cl_ref)^2");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("alphaa", "alpha_O2");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("alphac", "alpha_O2");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2")
         .set("Av", "a_max_pos*(epsilon-eps_pos_min)/(eps_pos_max-eps_pos_min)");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("vHplus", -2);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("vHSO4Minus", 0);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("vH2O", 1);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("vPbO2", 0);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("vPbSO4", 0);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("per2").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("leadbat").feature("ppe1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("leadbat").feature("ppe1").feature("pdl1").set("Cdl", "C_dl_pos");
    model.component("comp1").physics("leadbat").feature("ppe1").feature("pdl1").set("av_dl", "a_max_pos");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1")
         .set("ElectrodeKinetics", "LeadAcidInsertionDischarge");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("i0_ref", "i0_ref_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("alphaa", "alpha_a_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("alphac", "alpha_c_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("gamma", "gamma_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("amax", "a_max_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per1").set("zetad", "morph_neg");
    model.component("comp1").physics("leadbat").feature("npe1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("Eeq", 0);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("i0", "i0_H2*cl/cl_ref");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("alphaa", "alpha_H2");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("alphac", "alpha_H2");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2")
         .set("Av", "a_max_neg*(epsilon-eps_neg_min)/(eps_neg_max-eps_neg_min)");
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("vHplus", -2);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("vHSO4Minus", 0);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("vPb", 0);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("vPbSO4", 0);
    model.component("comp1").physics("leadbat").feature("npe1").feature("per2").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("leadbat").feature("npe1")
         .create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("leadbat").feature("npe1").feature("pdl1").set("Cdl", "C_dl_neg");
    model.component("comp1").physics("leadbat").feature("npe1").feature("pdl1").set("av_dl", "a_max_neg");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").physics("leadbat").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("leadbat").feature("egnd1").selection().set(5);
    model.component("comp1").physics("leadbat").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("leadbat").feature("ecd1").selection().set(1);
    model.component("comp1").physics("leadbat").feature("ecd1").set("nis", "I_disch*step1(t/1[s])");
    model.component("comp1").physics("leadbat").create("init2", "init", 1);
    model.component("comp1").physics("leadbat").feature("init2").selection().set(1, 2);
    model.component("comp1").physics("leadbat").feature("init2").set("cl", "cl_init");
    model.component("comp1").physics("leadbat").feature("init2").set("epsilon", "eps_pos_init");
    model.component("comp1").physics("leadbat").feature("init1").set("cl", "cl_init");
    model.component("comp1").physics("leadbat").feature("init1").set("epsilon", "eps_neg_init");

    model.study("std1").feature("time").set("tlist", "range(0,1800,21*3600)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").selection().all();
    model.result("pg1").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (leadbat)");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (leadbat)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (leadbat)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg4").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (leadbat)");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7535\u538b C/20");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "C/20 \u653e\u7535 + 1 \u5c0f\u65f6\u677e\u5f1b\u671f\u95f4\u7684\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u65f6\u95f4 (h)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "t/3600");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u89e3\u8d28\u6d53\u5ea6 C/20");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "C/20 \u653e\u7535 + 1 \u5c0f\u65f6\u677e\u5f1b\u671f\u95f4\u7535\u89e3\u8d28\u6d53\u5ea6\u5206\u5e03");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8de8\u94c5\u9178\u7535\u6c60\u7684\u8ddd\u79bb [m]");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "c<sub>l</sub> [mol/m<sup>3</sup>]");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("data", "dset1");
    model.result("pg4").feature("lngr1").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").feature("lngr1").setIndex("interp", 3600, 0);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1")
         .setIndex("legends", "1 \u5c0f\u65f6\uff08\u653e\u7535 1 \u5c0f\u65f6\u540e\uff09", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").setIndex("interp", "10*3600", 0);
    model.result("pg4").feature("lngr2")
         .setIndex("legends", "10 \u5c0f\u65f6\uff08\u653e\u7535 10 \u5c0f\u65f6\u540e\uff09", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").setIndex("interp", "20*3600", 0);
    model.result("pg4").feature("lngr3")
         .setIndex("legends", "20 \u5c0f\u65f6\uff08\u653e\u7535\u7ed3\u675f\u65f6\uff09", 0);
    model.result("pg4").feature().duplicate("lngr4", "lngr3");
    model.result("pg4").run();
    model.result("pg4").feature("lngr4").setIndex("interp", "21*3600", 0);
    model.result("pg4").feature("lngr4")
         .setIndex("legends", "21 \u5c0f\u65f6\uff08\u677e\u5f1b 1 \u5c0f\u65f6\u540e\uff09", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6781 SOC\uff0cC/20 \u653e\u7535");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u8de8\u94c5\u9178\u7535\u6c60\u7684\u8ddd\u79bb (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "dset1");
    model.result("pg5").feature("lngr1").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").feature("lngr1").setIndex("interp", 3600, 0);
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "leadbat.soc");
    model.result("pg5").feature("lngr1").set("descr", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "1 h", 0);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").setIndex("interp", "10*3600", 0);
    model.result("pg5").feature("lngr2").setIndex("legends", "10 h", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").setIndex("interp", "20*3600", 0);
    model.result("pg5").feature("lngr3").setIndex("legends", "20 h", 0);
    model.result("pg5").run();

    model.param().set("C_factor", "20");
    model.param().descr("C_factor", "\u7535\u6d41\u4e58\u6027\u56e0\u5b50");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/leadbat", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/leadbat", true);
    model.study("std2").feature("time").set("tlist", "range(0,1,60)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").create("st1", "StopCondition");
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.intop1(comp1.phis)<1.5", 0);
    model.sol("sol3").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol3").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").selection().all();
    model.result("pg6").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg6").feature("ptgr1").selection().set(1);
    model.result("pg6").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (leadbat)");
    model.result("pg6").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u7535\u89e3\u8d28\u7535\u4f4d (leadbat) 1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (leadbat) 1");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg9").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg9").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (leadbat)");
    model.result("pg6").run();
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u89e3\u8d28\u6d53\u5ea6\uff0c20C \u653e\u7535");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u8de8\u94c5\u9178\u7535\u6c60\u7684\u8ddd\u79bb (m)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "c<sub>l</sub> (mol/m<sup>3</sup>)");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("SOC 20C");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u8de8\u94c5\u9178\u7535\u6c60\u7684\u8ddd\u79bb (m)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").set("data", "dset3");
    model.result("pg10").feature("lngr1").selection().all();
    model.result("pg10").feature("lngr1").set("expr", "leadbat.soc");
    model.result("pg10").feature("lngr1").set("descr", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg10").run();

    model.param().set("C_factor", "0");

    model.study().create("std3");
    model.study("std3").create("cdi", "CurrentDistributionInitialization");
    model.study("std3").feature("cdi").set("ftplistmethod", "manual");
    model.study("std3").feature("cdi").set("solnum", "auto");
    model.study("std3").feature("cdi").set("notsolnum", "auto");
    model.study("std3").feature("cdi").set("outputmap", new String[]{});
    model.study("std3").feature("cdi").set("ngenAUX", "1");
    model.study("std3").feature("cdi").set("goalngenAUX", "1");
    model.study("std3").feature("cdi").set("ngenAUX", "1");
    model.study("std3").feature("cdi").set("goalngenAUX", "1");
    model.study("std3").feature("cdi").setSolveFor("/physics/leadbat", true);
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").set("plotgroup", "Default");
    model.study("std3").feature("time").set("ftplistmethod", "manual");
    model.study("std3").feature("time").set("initialtime", "0");
    model.study("std3").feature("time").set("solnum", "auto");
    model.study("std3").feature("time").set("notsolnum", "auto");
    model.study("std3").feature("time").set("outputmap", new String[]{});
    model.study("std3").feature("time").setSolveFor("/physics/leadbat", true);
    model.study("std3").feature("time").set("tlist", "0 range(1,7*24*3600,365*24*3600)");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u7535\u6c60\u7535\u538b\u6bd4\u8f83");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg11").set("xlog", true);
    model.result("pg11").set("legendpos", "lowerright");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").set("data", "dset1");
    model.result("pg11").feature("ptgr1").selection().set(1);
    model.result("pg11").feature("ptgr1").set("expr", "phis");
    model.result("pg11").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "C/20 + 1 \u5c0f\u65f6\u677e\u5f1b", 0);
    model.result("pg11").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr2").set("data", "dset3");
    model.result("pg11").feature("ptgr2").setIndex("legends", "20C", 0);
    model.result("pg11").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr3").set("data", "dset5");
    model.result("pg11").feature("ptgr3").setIndex("legends", "\u81ea\u653e\u7535\uff08\u4e00\u5e74\uff09", 0);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("SOC \u81ea\u653e\u7535\u4e00\u5e74");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u8de8\u94c5\u9178\u7535\u6c60\u7684\u8ddd\u79bb (m)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("data", "dset5");
    model.result("pg12").feature("lngr1").selection().all();
    model.result("pg12").feature("lngr1").set("expr", "leadbat.soc");
    model.result("pg12").feature("lngr1").set("descr", "\u7535\u6781\u8377\u7535\u72b6\u6001");
    model.result("pg12").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result("pg4").run();

    model.title("\u94c5\u9178\u7535\u6c60\u7684\u653e\u7535\u4e0e\u81ea\u653e\u7535");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u94c5\u9178\u7535\u6c60\u7684\u653e\u7535\u8fc7\u7a0b\uff0c\u6bd4\u8f83\u4e86\u9ad8\u653e\u7535\u7387\u548c\u4f4e\u653e\u7535\u7387\uff0c\u5e76\u5229\u7528\u591a\u5b54\u7535\u6781\u4e2d\u7684\u526f\u53cd\u5e94\u7814\u7a76\u4e86\u7535\u6c60\u7684\u957f\u671f\u81ea\u653e\u7535\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("pb_acid_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
