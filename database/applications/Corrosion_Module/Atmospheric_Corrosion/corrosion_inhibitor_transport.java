/*
 * corrosion_inhibitor_transport.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:40 by COMSOL 6.3.0.290. */
public class corrosion_inhibitor_transport {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Atmospheric_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cNa");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cNa", "cCl", "cH", "cOH", "cAl", "cAlOH", "cAlOH2", "cAlOH3", "cAlOH4", "cCe", 
         "cCo"});

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
    model.study("std1").feature("cdi").setSolveFor("/physics/chem", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/chem", true);
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_film", "100e-6[m]", "\u7535\u89e3\u8d28\u819c\u539a\u5ea6");
    model.param().set("DNa", "5.379e-13[mol*m^2/J/s]*R_const*T", "Na \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DCl", "8.197e-13[mol*m^2/J/s]*R_const*T", "Cl \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DH", "3.756e-12[mol*m^2/J/s]*R_const*T", "H \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DOH", "2.127e-12[mol*m^2/J/s]*R_const*T", "OH \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DAl", "2.184e-13[mol*m^2/J/s]*R_const*T", "Al \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DAlOH", "4.78e-13[mol*m^2/J/s]*R_const*T", "AlOH \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DAlOH2", "4.78e-13[mol*m^2/J/s]*R_const*T", "AlOH2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DAlOH3", "4.78e-13[mol*m^2/J/s]*R_const*T", "AlOH3 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DAlOH4", "4.78e-13[mol*m^2/J/s]*R_const*T", "AlOH4 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DCe", "1.6e-13[mol*m^2/J/s]*R_const*T", "AlCl \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DCo", "2.92e-13[mol*m^2/J/s]*R_const*T", "AlOHCl \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("c0Cl", "0.05[M]", "Cl \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c0H", "10^(-pH0)[M]", "H \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c0OH", "10^(-pKw+pH0)[M]", "OH \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("zNa", "1", "Na \u7684\u7535\u8377\u6570");
    model.param().set("zCl", "-1", "Cl \u7684\u7535\u8377\u6570");
    model.param().set("zH", "1", "H \u7684\u7535\u8377\u6570");
    model.param().set("zOH", "-1", "OH \u7684\u7535\u8377\u6570");
    model.param().set("zAl", "3", "Al \u7684\u7535\u8377\u6570");
    model.param().set("zAlOH", "2", "AlOH \u7684\u7535\u8377\u6570");
    model.param().set("zAlOH2", "1", "AlOH2 \u7684\u7535\u8377\u6570");
    model.param().set("zAlOH3", "0", "AlOH3 \u7684\u7535\u8377\u6570");
    model.param().set("zAlOH4", "-1", "AlOH4 \u7684\u7535\u8377\u6570");
    model.param().set("zCe", "3", "AlCl \u7684\u7535\u8377\u6570");
    model.param().set("zCo", "2", "AlOHCl \u7684\u7535\u8377\u6570");
    model.param().set("kfH2O", "1e-8[1/s]", "H2O \u7684\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kfAlOH", "4.2e4[m^3/mol/s]", "AlOH \u7684\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("krAlOH", "4.6e6[m^3/mol/s]", "AlOH \u7684\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kfAlOH2", "4.2e4[m^3/mol/s]", "AlOH2 \u7684\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("krAlOH2", "3.6e6[m^3/mol/s]", "AlOH2 \u7684\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kfAlOH3", "5.6e4[m^3/mol/s]", "AlOH3 \u7684\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("krAlOH3", "2.8e6[m^3/mol/s]", "AlOH3 \u7684\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("kfAlOH4", "1e-8[m^3/mol/s]", "AlOH4 \u7684\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("krAlOH4", "1[m^3/mol/s]", "AlOH4 \u7684\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("pH0", "2", "\u521d\u59cb\u672c\u4f53 pH");
    model.param()
         .set("pKw", "14.94-0.04209*(-273.15+T)/1[K]+1.718E-4*(-273.15+T)^2/1[K^2]", "\u6c34\u7684\u89e3\u79bb\u5e38\u6570");
    model.param().set("Kw", "10^(6-pKw)", "\u6c34\u7684\u89e3\u79bb\u5e73\u8861\u5e38\u6570");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("cCeCrit", "0.4[mM]", "Ce \u7684\u4e34\u754c\u6d53\u5ea6");
    model.param().set("S", "1500[um]", "\u5212\u75d5\u5927\u5c0f");
    model.param()
         .set("idl_AA2024", "-1.6[A/m^2]", "AA2024 \u7684\u6c27\u6269\u6563\u9650\u5236\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("A_orr_AA2024", "-108[mV]", "AA2024 \u7684\u6c27\u8fd8\u539f\u53cd\u5e94\u7684 Tafel \u659c\u7387");
    model.param()
         .set("A_orr_AlCoCe", "-168[mV]", "AlCoCe \u7684\u6c27\u8fd8\u539f\u53cd\u5e94\u7684 Tafel \u659c\u7387");
    model.param().set("Eeq_AA2024", "-0.53[V]", "\u5e73\u8861\u7535\u4f4d\uff0cAl");
    model.param()
         .set("idl_AlCoCe", "-1.6[A/m^2]", "AlCoCe \u6d82\u5c42\u4e0a\u7684\u6c27\u6269\u6563\u9650\u5236\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("nuCo", "-8.7", "\u5316\u5b66\u8ba1\u91cf\u7cfb\u6570\uff0cCo");
    model.param().set("nuAl", "-87", "\u5316\u5b66\u8ba1\u91cf\u7cfb\u6570\uff0cAl");
    model.param().set("nuCe", "-100-nuCo-nuAl", "\u5316\u5b66\u8ba1\u91cf\u7cfb\u6570\uff0cCe");
    model.param()
         .set("nAlCoCe", "-(nuCo*zCo+nuCe*zCe+nuAl*zAl)", "\u5316\u5b66\u8ba1\u91cf\u7cfb\u6570\uff0c\u6709\u6548");
    model.param().set("cH2O", "1[mol/m^3]", "\u6c34\u7684\u53c2\u8003\u6d53\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "1[cm]-S", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "1[cm]", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("i0_AlCoCe", "ipass_AlCoCe(pH)/(1-abs(ipass_AlCoCe(pH)/idl_AlCoCe))", "AlCoCe \u7684\u6c27\u8fd8\u539f\u53cd\u5e94\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("i0_AA2024", "ipass_AA2024(pH)/(1-abs(ipass_AA2024(pH)/idl_AA2024))", "AA2024 \u7684\u6c27\u8fd8\u539f\u53cd\u5e94\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("pH", "-log10(cH/1000[mol/m^3])", "\u7535\u89e3\u8d28\u7684 pH \u503c");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "corrosion_inhibitor_transport_ipass_AlCoCe_pH.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "ipass_AlCoCe");
    model.component("comp1").func("int1").setIndex("fununit", "A/m^2", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "corrosion_inhibitor_transport_ipass_AA2024_pH.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "ipass_AA2024");
    model.component("comp1").func("int2").setIndex("fununit", "A/m^2", 0);
    model.component("comp1").func("int2").setIndex("argunit", 1, 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("source", "file");
    model.component("comp1").func("int3").set("filename", "corrosion_inhibitor_transport_Eeq_AlCoCe_pH.txt");
    model.component("comp1").func("int3").importData();
    model.component("comp1").func("int3").set("funcname", "Eeq_AlCoCe");
    model.component("comp1").func("int3").setIndex("fununit", "V", 0);
    model.component("comp1").func("int3").setIndex("argunit", 1, 0);

    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "H2O<=>H+OH");
    model.component("comp1").physics("chem").feature("rch1").set("setKeq0", true);
    model.component("comp1").physics("chem").feature("rch1").set("kf", "kfH2O");
    model.component("comp1").physics("chem").feature("rch1").set("EquilibriumConstantSettings", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("Keq0", "Kw");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "Al+H2O<=>AlOH+H");
    model.component("comp1").physics("chem").feature("rch2").set("kf", "kfAlOH");
    model.component("comp1").physics("chem").feature("rch2").set("kr", "krAlOH");
    model.component("comp1").physics("chem").create("rch3", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch3").set("formula", "AlOH+H2O<=>AlOH2+H");
    model.component("comp1").physics("chem").feature("rch3").set("kf", "kfAlOH2");
    model.component("comp1").physics("chem").feature("rch3").set("kr", "krAlOH2");
    model.component("comp1").physics("chem").create("rch4", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch4").set("formula", "AlOH2+H2O<=>AlOH3+H");
    model.component("comp1").physics("chem").feature("rch4").set("kf", "kfAlOH3");
    model.component("comp1").physics("chem").feature("rch4").set("kr", "krAlOH3");
    model.component("comp1").physics("chem").create("rch5", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch5").set("formula", "AlOH3+H2O<=>AlOH4+H");
    model.component("comp1").physics("chem").feature("rch5").set("kf", "kfAlOH4");
    model.component("comp1").physics("chem").feature("rch5").set("kr", "krAlOH4");
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zNa", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zCl", 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zH", 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zOH", 3);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zAl", 4);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zAlOH", 5);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zAlOH2", 6);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zAlOH3", 7);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zAlOH4", 8);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zCe", 9);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "zCo", 10);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0Cl", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0H", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0OH", 3);
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "-Eeq_AA2024");
    model.component("comp1").physics("tcd").create("hcpce1", "HighlyConductivePorousElectrode", 1);
    model.component("comp1").physics("tcd").feature("hcpce1").selection().set(1);
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cNa", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCl", new String[]{"DCl", "0", "0", "0", "DCl", "0", "0", "0", "DCl"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cH", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cOH", new String[]{"DOH", "0", "0", "0", "DOH", "0", "0", "0", "DOH"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cAl", new String[]{"DAl", "0", "0", "0", "DAl", "0", "0", "0", "DAl"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cAlOH", new String[]{"DAlOH", "0", "0", "0", "DAlOH", "0", "0", "0", "DAlOH"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cAlOH2", new String[]{"DAlOH2", "0", "0", "0", "DAlOH2", "0", "0", "0", "DAlOH2"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cAlOH3", new String[]{"DAlOH3", "0", "0", "0", "DAlOH3", "0", "0", "0", "DAlOH3"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cAlOH4", new String[]{"DAlOH4", "0", "0", "0", "DAlOH4", "0", "0", "0", "DAlOH4"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCe", new String[]{"DCe", "0", "0", "0", "DCe", "0", "0", "0", "DCe"});
    model.component("comp1").physics("tcd").feature("hcpce1")
         .set("D_cCo", new String[]{"DCo", "0", "0", "0", "DCo", "0", "0", "0", "DCo"});
    model.component("comp1").physics("tcd").feature("hcpce1").set("epsl", 1);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94 - \u91d1\u5c5e\u6eb6\u89e3");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("nm", "nAlCoCe");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").setIndex("Vi0", "nuAl", 4);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").setIndex("Vi0", "nuCe", 9);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").setIndex("Vi0", "nuCo", 10);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("ilocmat", "ipass_AlCoCe(pH)");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per1").set("Av", "1/d_film");
    model.component("comp1").physics("tcd").feature("hcpce1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94 - \u6c27\u8fd8\u539f");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("nm", 4);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").setIndex("Vi0", 4, 3);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("Eeq", "Eeq_AlCoCe(pH)");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("i0", "i0_AlCoCe");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("Ac", "A_orr_AlCoCe");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("ilim", "idl_AlCoCe");
    model.component("comp1").physics("tcd").feature("hcpce1").feature("per2").set("Av", "1/d_film");
    model.component("comp1").physics("tcd").feature().duplicate("hcpce2", "hcpce1");
    model.component("comp1").physics("tcd").feature("hcpce2").selection().set(2);
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per1").set("nm", 3);
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per1").setIndex("Vi0", -1, 4);
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per1").setIndex("Vi0", 0, 9);
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per1").setIndex("Vi0", 0, 10);
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per1").set("ilocmat", "ipass_AA2024(pH)");
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per2").set("Eeq", "Eeq_AA2024");
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per2").set("i0", "i0_AA2024");
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per2").set("Ac", "A_orr_AA2024");
    model.component("comp1").physics("tcd").feature("hcpce2").feature("per2").set("ilim", "idl_AA2024");
    model.component("comp1").physics("tcd").create("reac1", "Reactions", 1);
    model.component("comp1").physics("tcd").feature("reac1").selection().set(1, 2);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cH_src", "root.comp1.chem.R_H", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cOH_src", "root.comp1.chem.R_OH", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cAl_src", "root.comp1.chem.R_Al", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cAlOH_src", "root.comp1.chem.R_AlOH", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cAlOH2_src", "root.comp1.chem.R_AlOH2", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cAlOH3_src", "root.comp1.chem.R_AlOH3", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cAlOH4_src", "root.comp1.chem.R_AlOH4", 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 1000);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemcount", 300);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("reverse", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "d_film", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "d_film", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "S", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1500[um]/2 1500[um]", 0);
    model.study("std1").feature("param").setIndex("pname", "d_film", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "d_film", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "pH0", 1);
    model.study("std1").feature("param").setIndex("plistarr", "2 6", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,1,10)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg2").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"cNa"});
    model.result("pg2").feature("lngr1").label("\u7269\u8d28 Na");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoexpr", false);
    model.result("pg2").feature("lngr1").set("autodescr", false);
    model.result("pg2").feature("lngr1").set("legendprefix", "Na ");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2);
    model.result("pg2").feature("lngr2").set("expr", new String[]{"cCl"});
    model.result("pg2").feature("lngr2").label("\u7269\u8d28 Cl");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoexpr", false);
    model.result("pg2").feature("lngr2").set("autodescr", false);
    model.result("pg2").feature("lngr2").set("legendprefix", "Cl ");
    model.result("pg2").feature("lngr2").set("descractive", true);
    model.result("pg2").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "x");
    model.result("pg2").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr3").selection().set(1, 2);
    model.result("pg2").feature("lngr3").set("expr", new String[]{"cH"});
    model.result("pg2").feature("lngr3").label("\u7269\u8d28 H");
    model.result("pg2").feature("lngr3").set("legend", true);
    model.result("pg2").feature("lngr3").set("autosolution", false);
    model.result("pg2").feature("lngr3").set("autoexpr", false);
    model.result("pg2").feature("lngr3").set("autodescr", false);
    model.result("pg2").feature("lngr3").set("legendprefix", "H ");
    model.result("pg2").feature("lngr3").set("descractive", true);
    model.result("pg2").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr4", "LineGraph");
    model.result("pg2").feature("lngr4").set("xdata", "expr");
    model.result("pg2").feature("lngr4").set("xdataexpr", "x");
    model.result("pg2").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr4").selection().set(1, 2);
    model.result("pg2").feature("lngr4").set("expr", new String[]{"cOH"});
    model.result("pg2").feature("lngr4").label("\u7269\u8d28 OH");
    model.result("pg2").feature("lngr4").set("legend", true);
    model.result("pg2").feature("lngr4").set("autosolution", false);
    model.result("pg2").feature("lngr4").set("autoexpr", false);
    model.result("pg2").feature("lngr4").set("autodescr", false);
    model.result("pg2").feature("lngr4").set("legendprefix", "OH ");
    model.result("pg2").feature("lngr4").set("descractive", true);
    model.result("pg2").feature("lngr4").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr5", "LineGraph");
    model.result("pg2").feature("lngr5").set("xdata", "expr");
    model.result("pg2").feature("lngr5").set("xdataexpr", "x");
    model.result("pg2").feature("lngr5").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr5").selection().set(1, 2);
    model.result("pg2").feature("lngr5").set("expr", new String[]{"cAl"});
    model.result("pg2").feature("lngr5").label("\u7269\u8d28 Al");
    model.result("pg2").feature("lngr5").set("legend", true);
    model.result("pg2").feature("lngr5").set("autosolution", false);
    model.result("pg2").feature("lngr5").set("autoexpr", false);
    model.result("pg2").feature("lngr5").set("autodescr", false);
    model.result("pg2").feature("lngr5").set("legendprefix", "Al ");
    model.result("pg2").feature("lngr5").set("descractive", true);
    model.result("pg2").feature("lngr5").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr6", "LineGraph");
    model.result("pg2").feature("lngr6").set("xdata", "expr");
    model.result("pg2").feature("lngr6").set("xdataexpr", "x");
    model.result("pg2").feature("lngr6").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr6").selection().set(1, 2);
    model.result("pg2").feature("lngr6").set("expr", new String[]{"cAlOH"});
    model.result("pg2").feature("lngr6").label("\u7269\u8d28 AlOH");
    model.result("pg2").feature("lngr6").set("legend", true);
    model.result("pg2").feature("lngr6").set("autosolution", false);
    model.result("pg2").feature("lngr6").set("autoexpr", false);
    model.result("pg2").feature("lngr6").set("autodescr", false);
    model.result("pg2").feature("lngr6").set("legendprefix", "AlOH ");
    model.result("pg2").feature("lngr6").set("descractive", true);
    model.result("pg2").feature("lngr6").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr7", "LineGraph");
    model.result("pg2").feature("lngr7").set("xdata", "expr");
    model.result("pg2").feature("lngr7").set("xdataexpr", "x");
    model.result("pg2").feature("lngr7").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr7").selection().set(1, 2);
    model.result("pg2").feature("lngr7").set("expr", new String[]{"cAlOH2"});
    model.result("pg2").feature("lngr7").label("\u7269\u8d28 AlOH2");
    model.result("pg2").feature("lngr7").set("legend", true);
    model.result("pg2").feature("lngr7").set("autosolution", false);
    model.result("pg2").feature("lngr7").set("autoexpr", false);
    model.result("pg2").feature("lngr7").set("autodescr", false);
    model.result("pg2").feature("lngr7").set("legendprefix", "AlOH2 ");
    model.result("pg2").feature("lngr7").set("descractive", true);
    model.result("pg2").feature("lngr7").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr8", "LineGraph");
    model.result("pg2").feature("lngr8").set("xdata", "expr");
    model.result("pg2").feature("lngr8").set("xdataexpr", "x");
    model.result("pg2").feature("lngr8").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr8").selection().set(1, 2);
    model.result("pg2").feature("lngr8").set("expr", new String[]{"cAlOH3"});
    model.result("pg2").feature("lngr8").label("\u7269\u8d28 AlOH3");
    model.result("pg2").feature("lngr8").set("legend", true);
    model.result("pg2").feature("lngr8").set("autosolution", false);
    model.result("pg2").feature("lngr8").set("autoexpr", false);
    model.result("pg2").feature("lngr8").set("autodescr", false);
    model.result("pg2").feature("lngr8").set("legendprefix", "AlOH3 ");
    model.result("pg2").feature("lngr8").set("descractive", true);
    model.result("pg2").feature("lngr8").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr9", "LineGraph");
    model.result("pg2").feature("lngr9").set("xdata", "expr");
    model.result("pg2").feature("lngr9").set("xdataexpr", "x");
    model.result("pg2").feature("lngr9").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr9").selection().set(1, 2);
    model.result("pg2").feature("lngr9").set("expr", new String[]{"cAlOH4"});
    model.result("pg2").feature("lngr9").label("\u7269\u8d28 AlOH4");
    model.result("pg2").feature("lngr9").set("legend", true);
    model.result("pg2").feature("lngr9").set("autosolution", false);
    model.result("pg2").feature("lngr9").set("autoexpr", false);
    model.result("pg2").feature("lngr9").set("autodescr", false);
    model.result("pg2").feature("lngr9").set("legendprefix", "AlOH4 ");
    model.result("pg2").feature("lngr9").set("descractive", true);
    model.result("pg2").feature("lngr9").set("descr", "\u6d53\u5ea6");
    model.result("pg2").create("lngr10", "LineGraph");
    model.result("pg2").feature("lngr10").set("xdata", "expr");
    model.result("pg2").feature("lngr10").set("xdataexpr", "x");
    model.result("pg2").feature("lngr10").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr10").selection().set(1, 2);
    model.result("pg2").feature("lngr10").set("expr", new String[]{"cCe"});
    model.result("pg2").feature("lngr10").label("\u7269\u8d28 Ce");
    model.result("pg2").feature("lngr10").set("legend", true);
    model.result("pg2").feature("lngr10").set("autosolution", false);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("lngr10").set("autoexpr", false);
    model.result("pg2").feature("lngr10").set("autodescr", false);
    model.result("pg2").feature("lngr10").set("legendprefix", "Ce ");
    model.result("pg2").feature("lngr10").set("descractive", true);
    model.result("pg2").feature("lngr10").set("descr", "\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u6d53\u5ea6, Na (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"cNa"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u6d53\u5ea6, Cl (tcd)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cCl"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u6d53\u5ea6, H (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cH"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u6d53\u5ea6, OH (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cOH"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u6d53\u5ea6, Al (tcd)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"cAl"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u6d53\u5ea6, AlOH (tcd)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1, 2);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"cAlOH"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").label("\u6d53\u5ea6, AlOH2 (tcd)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1, 2);
    model.result("pg9").feature("lngr1").set("expr", new String[]{"cAlOH2"});
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").label("\u6d53\u5ea6, AlOH3 (tcd)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "x");
    model.result("pg10").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg10").feature("lngr1").selection().set(1, 2);
    model.result("pg10").feature("lngr1").set("expr", new String[]{"cAlOH3"});
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").label("\u6d53\u5ea6, AlOH4 (tcd)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "x");
    model.result("pg11").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr1").selection().set(1, 2);
    model.result("pg11").feature("lngr1").set("expr", new String[]{"cAlOH4"});
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").label("\u6d53\u5ea6, Ce (tcd)");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "x");
    model.result("pg12").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg12").feature("lngr1").selection().set(1, 2);
    model.result("pg12").feature("lngr1").set("expr", new String[]{"cCe"});
    model.result("pg1").run();
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevelinput", "manual", 2);
    model.result("pg12").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg12").setIndex("looplevelinput", "manual", 1);
    model.result("pg12").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u6d53\u5ea6\uff0cCe (mol/m<sup>3</sup>)");
    model.result("pg12").set("legendpos", "upperleft");
    model.result("pg12").run();
    model.result("pg12").feature("lngr1").set("linestyle", "cycle");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg12").feature("lngr1").set("legendpattern", "t=eval(t,h) h");
    model.result("pg12").run();
    model.result("pg12").create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr2").set("linewidth", "preference");
    model.result("pg12").feature("lngr2").set("data", "dset1");
    model.result("pg12").feature("lngr2").setIndex("looplevelinput", "first", 0);
    model.result("pg12").feature("lngr2").selection().all();
    model.result("pg12").feature("lngr2").set("expr", "cCeCrit");
    model.result("pg12").feature("lngr2").set("linestyle", "dashdot");
    model.result("pg12").feature("lngr2").set("linecolor", "black");
    model.result("pg12").feature("lngr2").set("linewidth", 2);
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("legendmethod", "manual");
    model.result("pg12").feature("lngr2").setIndex("legends", "c<sub>critical</sub>", 0);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg12").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("pH \u503c");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").setIndex("looplevelinput", "manual", 2);
    model.result("pg13").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg13").setIndex("looplevelinput", "manual", 1);
    model.result("pg13").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "x \u5750\u6807 (m)");
    model.result("pg13").set("legendpos", "upperleft");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").selection().all();
    model.result("pg13").feature("lngr1").set("expr", "pH");
    model.result("pg13").feature("lngr1").set("linestyle", "cycle");
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg13").feature("lngr1").set("legendpattern", "t=eval(t,h) h");
    model.result("pg13").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "last", 2);
    model.result("pg2").setIndex("looplevelinput", "last", 1);
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("linestyle", "dotted");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("linestyle", "dotted");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("linestyle", "dotted");

    model.title("\u7f13\u8680\u5242\u4f20\u9012");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u8868\u9762\u4e0a\u6709 100 \u5fae\u7c73\u539a\u7535\u89e3\u8d28\u819c\u7684\u7531 Al-Co-Ce \u91d1\u5c5e\u6d82\u5c42\u548c\u94dd\u5408\u91d1\u7ec4\u6210\u7684\u7535\u5076\u7684\u5927\u6c14\u8150\u8680\u3002\n\n\u7f13\u8680\u5242\u4ece\u91d1\u5c5e\u6d82\u5c42\u4e2d\u91ca\u653e\u51fa\u6765\uff0c\u901a\u8fc7\u6269\u6563\u548c\u8fc1\u79fb\u4f20\u9012\u5230\u94dd\u8868\u9762\u7684\u5212\u75d5\u533a\u57df\u3002\u6a21\u578b\u4e2d\u8003\u8651\u4e86\u7535\u8377\u4f20\u8f93\u4ee5\u53ca\u6d89\u53ca 11\u00a0\u79cd\u7269\u8d28\u548c 5\u00a0\u4e2a\u5747\u76f8\u53cd\u5e94\u7684\u8d28\u91cf\u4f20\u9012\u3002\n\n\u6a21\u578b\u8ba1\u7b97\u4e86\u819c\u4e2d\u7269\u8d28\u7684\u77ac\u6001\u548c\u7a7a\u95f4\u5206\u5e03\uff0c\u8bc4\u4f30\u4e86\u5728\u94dd\u5212\u75d5\u8868\u9762\u4e0a\u79ef\u7d2f\u4e34\u754c\u7f13\u8680\u5242\u6d53\u5ea6\u6240\u9700\u7684\u65f6\u95f4\uff0c\u8fd8\u5c55\u793a\u4e86\u5212\u75d5\u5927\u5c0f\u548c\u7535\u89e3\u8d28\u521d\u59cb pH \u503c\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("corrosion_inhibitor_transport.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
