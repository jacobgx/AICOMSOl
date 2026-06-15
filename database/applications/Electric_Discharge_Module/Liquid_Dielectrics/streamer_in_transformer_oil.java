/*
 * streamer_in_transformer_oil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:51 by COMSOL 6.3.0.290. */
public class streamer_in_transformer_oil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Liquid_Dielectrics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("edis", "ElectricDischarge", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/edis", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{15, 30});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.1, 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.3, 3});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 27});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new double[]{0.3, 2});
    model.component("comp1").geom("geom1").feature("e1").set("pos", new int[]{0, 27});
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"0.1", "0.6-1/1000"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 24.4});
    model.component("comp1").geom("geom1").feature("r3").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r3").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 0.03, 0);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("e1", "r2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Vapp");
    model.component("comp1").func("an1").set("expr", "130*(exp(-t/100)-exp(-t/10))");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").set("fununit", "kV");
    model.component("comp1").func("an1").setIndex("argunit", "ns", 0);
    model.component("comp1").func("an1").setIndex("plotargs", "90[ns]", 0, 2);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").prop("PhysicalModel").set("Gas", false);
    model.component("comp1").physics("edis").prop("PhysicalModel").set("Liquid", true);
    model.component("comp1").physics("edis").prop("Stabilization").set("IsotropicDiffusion", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportLiquids", "ChargeTransportLiquids", "Charge transport in liquids");
    model.component("comp1").material("mat1").label("Transformer Oil");
    model.component("comp1").material("mat1").set("phase", "liquid");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"2.2", "0", "0", "0", "2.2", "0", "0", "0", "2.2"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .label("Charge transport in liquids");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .set("mu_e", new String[]{"1e-4[m^2/V/s]", "0", "0", "0", "1e-4[m^2/V/s]", "0", "0", "0", "1e-4[m^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .set("mu_p", new String[]{"1e-9[m^2/V/s]", "0", "0", "0", "1e-9[m^2/V/s]", "0", "0", "0", "1e-9[m^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .set("mu_n", new String[]{"1e-9[m^2/V/s]", "0", "0", "0", "1e-9[m^2/V/s]", "0", "0", "0", "1e-9[m^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .set("beta_ep", "1.64e-17[m^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids")
         .set("beta_pn", "1.64e-17[m^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("tau_a", "200[ns]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("n_ioni", "1e23[1/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("a", "3[\u00c5]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("m_eff", "0.1*me_const");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("phi_Delta", "7[V]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportLiquids").set("phi_gamma", "0.07[V]");

    model.component("comp1").physics("edis").feature("liquid1").feature("init1").setIndex("n0_e", "1E6[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("liquid1").feature("init1").setIndex("n0_p", "1E6[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("liquid1").feature("init1").setIndex("n0_n", "1E6[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("liquid1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("liquid1").feature("ece1").selection().set(14, 17, 18);
    model.component("comp1").physics("edis").feature("liquid1").feature("ece1").set("V0", "Vapp(t)");
    model.component("comp1").physics("edis").feature("liquid1").feature("ece1").set("pBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("liquid1").feature("ece1").set("item.n0_p", "1E6[1/cm^3]");
    model.component("comp1").physics("edis").feature("liquid1").create("ece2", "Electrode", 1);
    model.component("comp1").physics("edis").feature("liquid1").feature("ece2").selection().set(2, 11);
    model.component("comp1").physics("edis").feature("liquid1").feature("ece2").set("eBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("liquid1").feature("ece2").set("item.n0_e", "1E6[1/cm^3]");
    model.component("comp1").physics("edis").feature("liquid1").feature("ece2").set("nBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("liquid1").feature("ece2").set("item.n0_n", "1E6[1/cm^3]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 400);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1/300");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(17);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,10,90)");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "expr");
    model.sol("sol1").feature("t1").set("maxstepexpressionbdf", "min(0.1[ns],comp1.edis.minDRT*0.8)");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 3, 4);
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "edis.normE");
    model.result("pg1").feature("surf2").set("unit", "kV/mm");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(3, 5);
    model.result("pg2").feature("lngr1").set("expr", "edis.Ez");
    model.result("pg2").feature("lngr1").set("unit", "kV/mm");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "edis.n_e");
    model.result("pg3").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u53d8\u538b\u5668\u6cb9\u4e2d\u7684\u6b63\u6d41\u6ce8\u4f20\u64ad");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u96f7\u7535\u51b2\u51fb\u7535\u538b\u4f5c\u7528\u4e0b\uff0c\u6b63\u6d41\u6ce8\u5728\u53d8\u538b\u5668\u6cb9\u4e2d\u7684\u4f20\u64ad\u8fc7\u7a0b\uff0c\u5e76\u5f97\u5230\u4e86\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6\u548c\u7535\u573a\u5206\u5e03\u3002\u6a21\u62df\u7684\u6d41\u6ce8\u534a\u5f84\u4e0e\u6d4b\u91cf\u503c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("streamer_in_transformer_oil.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
