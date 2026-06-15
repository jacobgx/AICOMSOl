/*
 * positive_glow_corona.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:49 by COMSOL 6.3.0.290. */
public class positive_glow_corona {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Corona_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("edis", "ElectricDischarge", "geom1");

    model.study().create("std1");
    model.study("std1").create("esi", "ElectrostaticsInitialization");
    model.study("std1").feature("esi").set("solnum", "auto");
    model.study("std1").feature("esi").set("notsolnum", "auto");
    model.study("std1").feature("esi").set("outputmap", new String[]{});
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").setSolveFor("/physics/edis", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/edis", true);

    model.param().set("ri", "0.1[cm]");
    model.param().descr("ri", "Inner Radius");
    model.param().set("ro", "2.1[cm]");
    model.param().descr("ro", "Outer Radius");
    model.param().set("t", "5[us]");
    model.param().descr("t", "Simulation Starting Time");
    model.param().set("V0", "rm1(t/1[us])*1[kV]");
    model.param().descr("V0", "Applied Voltage");

    model.func().create("rm1", "Ramp");
    model.func("rm1").set("slope", 3);
    model.func("rm1").set("cutoffactive", true);
    model.func("rm1").set("cutoff", 30);
    model.func("rm1").set("smoothzonecutoffactive", true);
    model.func("rm1").set("smoothzonecutoff", 1);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "ri", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "ro", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(2);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("eBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("nBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").create("ece2", "Electrode", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").selection().set(1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("pBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").create("pi1", "Photoionization", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").info().create("matinfo1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportGases", "ChargeTransportGases", "Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func()
         .create("pw1", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func()
         .create("pw2", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func()
         .create("pw3", "Piecewise");
    model.component("comp1").material("mat1").label("Air [Morrow and Lowke, 1997]");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").set("phase", "gas");
    model.component("comp1").material("mat1").set("orientation", "Morrow and Lowke, 1997");
    model.component("comp1").material("mat1").set("info", new String[][]{{"matinfo1", "", ""}});
    model.component("comp1").material("mat1").info("matinfo1").label("Information 1");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .label("Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1").label("Piecewise 1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1")
         .set("funcname", "alphaN");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1").set("arg", "EN");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1")
         .set("pieces", new String[][]{{"0", "1.5e-15", "6.619e-17*exp(-5.593e-15/EN)"}, {"1.5e-15", "1.5e-13", "2e-16*exp(-7.248e-15/EN)"}});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1")
         .set("argunit", "V*cm^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw1")
         .set("fununit", "cm^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2")
         .set("funcname", "yita2N");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2").set("arg", "EN");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2")
         .set("pieces", new String[][]{{"0", "4.755e-16", "0"}, {"4.755e-16", "1.05e-15", "6.089e-4*EN-2.893e-19"}, {"1.05e-15", "1.05e-13", "8.889e-5*EN+2.567e-19"}});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2")
         .set("argunit", "V*cm^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw2")
         .set("fununit", "cm^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("funcname", "yita3NN");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("expr", "4.7778e-59*(EN+1e-22)^(-1.2749)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("args", new String[]{"EN"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("fununit", "cm^5");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("argunit", new String[]{"V*cm^2"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("plotargs", new String[][]{{"EN", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3")
         .set("funcname", "w_e");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3").set("arg", "EN");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3")
         .set("pieces", new String[][]{{"0", "2.6e-17", "6.87e22*EN + 3.38e4"}, 
         {"2.6e-17", "1e-16", "7.2973e21*EN + 1.63e6"}, 
         {"1e-16", "2e-15", "1.03e22*EN + 1.3e6"}, 
         {"2e-15", "1e-14", "7.4e21*EN + 7.1e6"}, 
         {"", "", ""}});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3")
         .set("argunit", "V*cm^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("pw3")
         .set("fununit", "cm/s");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_e", new String[]{"w_e(EN)/normE", "0", "0", "0", "w_e(EN)/normE", "0", "0", "0", "w_e(EN)/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("mu_e", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_p", new String[]{"2.34[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.34[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.34[cm^2/V/s]*1[atm]/pA"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("mu_p", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_n", new String[]{"2.7[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.7[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.7[cm^2/V/s]*1[atm]/pA"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("mu_n", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("alpha", "alphaN(EN)*N");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("alpha", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("eta", "yita2N(EN)*N+yita3NN(EN)*N^2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("eta", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_ep", "2e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("beta_ep", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_pn", "2e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("beta_pn", "Reference: Morrow, R., and J. J. Lowke. \"Streamer propagation in air.\" Journal of Physics D: Applied Physics 30, no. 4 (1997): 614.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("normE", "\u7535\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("N", "\u4e2d\u6027\u6570\u5bc6\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("EN", "\u7ea6\u5316\u7535\u573a");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("electricfield");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("pressure");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 300);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("type", "integral");
    model.component("comp1").probe("dom1").set("expr", "abs(edis.Jc_er*edis.Er/V0)");
    model.component("comp1").probe("dom1").set("unit", "mA/m");
    model.component("comp1").probe("dom1").set("probename", "i_e");
    model.component("comp1").probe("dom1").set("descractive", true);
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").set("probename", "i_p");
    model.component("comp1").probe("dom2").set("expr", "abs(edis.Jc_pr*edis.Er/V0)");
    model.component("comp1").probe().duplicate("dom3", "dom2");
    model.component("comp1").probe("dom3").set("probename", "i_n");
    model.component("comp1").probe("dom3").set("expr", "abs(edis.Jc_nr*edis.Er/V0)");
    model.component("comp1").probe().duplicate("dom4", "dom3");
    model.component("comp1").probe("dom4").set("probename", "i_d");
    model.component("comp1").probe("dom4").set("expr", "edis.Jdr*edis.Er/V0");
    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "i_dom");
    model.component("comp1").probe("var1").set("expr", "i_e+i_p+i_n+i_d");
    model.component("comp1").probe("var1").set("unit", "mA/m");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("probename", "i_bnd");
    model.component("comp1").probe("var2").set("expr", "edis.I0_1/edis.d");
    model.component("comp1").probe("var2").set("unit", "mA/m");
    model.component("comp1").probe("var2").set("descractive", true);

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(5,2,15) range(17,0.2,19)");
    model.study("std1").showAutoSequences("all");
    model.study("std1").setGenPlots(false);

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "3[ns]");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");
    model.component("comp1").probe("dom3").genResult("none");
    model.component("comp1").probe("dom4").genResult("none");
    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("linemarker", "cycle");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "none");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("data", "dset1");
    model.result("pg2").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").feature("lngr1").setIndex("looplevel", new int[]{9, 10}, 0);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "edis.n_e");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("linestyle", "dotted");
    model.result("pg2").feature("lngr1").set("linemarker", "cyclereset");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autodescr", true);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "edis.n_p");
    model.result("pg2").feature("lngr2").set("linestyle", "solid");
    model.result("pg2").feature().duplicate("lngr3", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("expr", "edis.n_n");
    model.result("pg2").feature("lngr3").set("linestyle", "dashed");
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result().dataset().create("rev1", "Revolve1D");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("unit", "\u00b5C/m^3");
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u6b63\u8f89\u5149\u7535\u6655\u653e\u7535");

    model
         .description("\u7535\u6655\u653e\u7535\u901a\u5e38\u53ea\u51fa\u73b0\u5728\u7279\u5b9a\u533a\u57df\uff0c\u5e76\u8868\u73b0\u51fa\u7a33\u5b9a\u7684\u53d1\u5149\u7279\u6027\u3002\u4f9d\u636e\u6781\u6027\u7684\u4e0d\u540c\uff0c\u7a33\u5b9a\u8f89\u5149\u7535\u6655\u653e\u7535\u7684\u5c5e\u6027\u4f1a\u6709\u6240\u53d8\u5316\u3002\u6b63\u8f89\u5149\u7535\u6655\uff0c\u4e5f\u79f0\u4e3a\u8d6b\u59c6\u65af\u5766\u8f89\u5149\u6216\u8d85\u7535\u6655\uff0c\u5448\u73b0\u51fa\u76f4\u6d41 (DC) \u7279\u6027\uff0c\u5e76\u4f34\u968f\u7a33\u5b9a\u7684\u7535\u6d41\u8109\u51b2\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u653e\u7535\u201d\u63a5\u53e3\u6a21\u62df\u540c\u8f74\u5706\u67f1\u7535\u6781\u5185\u7684\u6b63\u8f89\u5149\u7535\u6655\u653e\u7535\u73b0\u8c61\uff0c\u786e\u5b9a\u4e86\u7535\u573a\u52a8\u529b\u5b66\u548c\u5e26\u7535\u7c92\u5b50\u7684\u6d53\u5ea6\u5206\u5e03\uff0c\u5e76\u8ba1\u7b97\u4e86\u603b\u653e\u7535\u7535\u6d41\uff0c\u4ee5\u53ca\u7535\u5b50\u6d41\u548c\u79bb\u5b50\u6d41\u6210\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("positive_glow_corona.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
