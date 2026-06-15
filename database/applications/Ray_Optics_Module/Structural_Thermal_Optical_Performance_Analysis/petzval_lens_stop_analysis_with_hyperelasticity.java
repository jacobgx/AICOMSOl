/*
 * petzval_lens_stop_analysis_with_hyperelasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:23 by COMSOL 6.3.0.290. */
public class petzval_lens_stop_analysis_with_hyperelasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Structural_Thermal_Optical_Performance_Analysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");

    model.component("comp1").geom("geom1").label("Petzval \u900f\u955c STOP \u5206\u6790\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").insertFile("petzval_lens_stop_analysis_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 19);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 32);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 37);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 55);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 60);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 68);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 73);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 84);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 90);

    model.param().label("\u900f\u955c\u89c4\u683c\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_N_BK7", "82[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0cSchott N-BK7");
    model.param("par2").set("nu_N_BK7", "0.206", "\u6cca\u677e\u6bd4\uff0cSchott N-BK7");
    model.param("par2").set("rho_N_BK7", "2.51[g/cm^3]", "\u5bc6\u5ea6\uff0cSchott N-BK7");
    model.param("par2").set("alpha1_N_BK7", "7.1E-06[1/K]", "CTE -30/70[degC], Schott N-BK7");
    model.param("par2").set("alpha2_N_BK7", "8.3E-06[1/K]", "CTE 20/300[degC], Schott N-BK7");
    model.param("par2").set("Cp_N_BK7", "0.858[J/(g*K)]", "\u70ed\u5bb9\uff0cSchott N-BK7");
    model.param("par2").set("k_N_BK7", "1.114[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cSchott N-BK7");
    model.param("par2").set("E_S_BAH32", "90.4[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0cOhara S-BAH32");
    model.param("par2").set("nu_S_BAH32", "0.260", "\u6cca\u677e\u6bd4\uff0cOhara S-BAH32");
    model.param("par2").set("rho_S_BAH32", "3.26[g/cm^3]", "\u5bc6\u5ea6\uff0cOhara S-BAH32");
    model.param("par2").set("alpha1_S_BAH32", "6.9E-06[1/K]", "CTE -30/70[degC], Ohara S-BAH32");
    model.param("par2").set("alpha2_S_BAH32", "7.8E-06[1/K]", "CTE 100/300[degC], Ohara S-BAH32");
    model.param("par2").set("Cp_S_BAH32", "0.850[J/(g*K)]", "\u70ed\u5bb9\uff0cOhara S-BAH32 [[GUESS]]");
    model.param("par2").set("k_S_BAH32", "0.921[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cOhara S-BAH32");
    model.param("par2").set("E_N_SK2", "78[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0cSchott N-SK2");
    model.param("par2").set("nu_N_SK2", "0.263", "\u6cca\u677e\u6bd4\uff0cSchott N-SK2");
    model.param("par2").set("rho_N_SK2", "3.55[g/cm^3]", "\u5bc6\u5ea6\uff0cSchott N-SK2");
    model.param("par2").set("alpha1_N_SK2", "6.0E-06[1/K]", "CTE -30/70[degC], Schott N-SK2");
    model.param("par2").set("alpha2_N_SK2", "7.1E-06[1/K]", "CTE 20/300[degC], Schott N-SK2");
    model.param("par2").set("Cp_N_SK2", "0.595[J/(g*K)]", "\u70ed\u5bb9\uff0cSchott N-SK2");
    model.param("par2").set("k_N_SK2", "0.776[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cSchott N-SK2");
    model.param("par2").set("E_N_SF5", "87[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0cSchott N-SF5");
    model.param("par2").set("nu_N_SF5", "0.237", "\u6cca\u677e\u6bd4\uff0cSchott N-SF5");
    model.param("par2").set("rho_N_SF5", "2.86[g/cm^3]", "\u5bc6\u5ea6\uff0cSchott N-SF5");
    model.param("par2").set("alpha1_N_SF5", "7.94E-06[1/K]", "CTE -30/70[degC], Schott N-SF5");
    model.param("par2").set("alpha2_N_SF5", "9.21E-06[1/K]", "CTE 20/300[degC], Schott N-SF5");
    model.param("par2").set("Cp_N_SF5", "0.770[J/(g*K)]", "\u70ed\u5bb9\uff0cSchott N-SF5");
    model.param("par2").set("k_N_SF5", "1.000[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cSchott N-SF5");
    model.param("par2").set("E_RTV", "15[ksi]", "\u6768\u6c0f\u6a21\u91cf\uff0cRTV");
    model.param("par2").set("nu_RTV", "0.485", "\u6cca\u677e\u6bd4\uff0cRTV");
    model.param("par2").set("rho_RTV", "1.25[g/cm^3]", "\u5bc6\u5ea6\uff0cRTV");
    model.param("par2").set("alpha_RTV", "200E-06[1/K]", "CTE, RTV");
    model.param("par2").set("Cp_RTV", "1.250[J/(g*K)]", "\u70ed\u5bb9\uff0cRTV");
    model.param("par2").set("k_RTV", "0.250[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0cRTV");
    model.param().create("par3");
    model.param("par3").label("\u5e38\u89c4\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("T0", "-25.0[degC]", "\u6807\u79f0\u76f8\u673a\u6e29\u5ea6");
    model.param("par3").set("theta_x1", "0.0[deg]", "\u89c6\u573a\u89d2 1\uff0cx \u5206\u91cf");
    model.param("par3").set("theta_y1", "0.0[deg]", "\u89c6\u573a\u89d2 1\uff0cy \u5206\u91cf");
    model.param("par3").set("theta_x2", "0.0[deg]", "\u89c6\u573a\u89d2 2\uff0cx \u5206\u91cf");
    model.param("par3").set("theta_y2", "3.5[deg]", "\u89c6\u573a\u89d2 2\uff0cy \u5206\u91cf");
    model.param("par3").set("theta_x3", "0.0[deg]", "\u89c6\u573a\u89d2 3\uff0cx \u5206\u91cf");
    model.param("par3").set("theta_y3", "7.0[deg]", "\u89c6\u573a\u89d2 3\uff0cy \u5206\u91cf");
    model.param("par3").set("N_ring", "15", "\u516d\u8fb9\u73af\u6570");
    model.param("par3").set("P_nom", "41.5[mm]", "\u6807\u79f0\u5165\u5c04\u5149\u77b3\u76f4\u5f84");
    model.param("par3")
         .set("vx1", "tan(theta_x1)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 1\uff0cx \u5206\u91cf");
    model.param("par3")
         .set("vy1", "tan(theta_y1)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 1\uff0cy \u5206\u91cf");
    model.param("par3")
         .set("vx2", "tan(theta_x2)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 2\uff0cx \u5206\u91cf");
    model.param("par3")
         .set("vy2", "tan(theta_y2)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 2\uff0cy \u5206\u91cf");
    model.param("par3")
         .set("vx3", "tan(theta_x3)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 3\uff0cx \u5206\u91cf");
    model.param("par3")
         .set("vy3", "tan(theta_y3)", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0c\u89c6\u573a 3\uff0cy \u5206\u91cf");
    model.param("par3").set("vz", "1", "\u5c04\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par3").set("z_stop", "Tc_1+T_1+Tc_2+T_2", "\u5149\u9611 z \u5750\u6807");
    model.param("par3")
         .set("z_image", "Tc_1+T_1+Tc_2+T_2+Tc_3+T_3+Tc_4+T_4+Tc_5+T_5+Tc_6+T_6", "\u50cf\u5e73\u9762 z \u5750\u6807");
    model.param("par3").set("P_1", "-1.142", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 1");
    model.param("par3").set("P_2", "-0.080", "\u5149\u77b3\u504f\u79fb\u5e38\u6570 2");
    model.param("par3")
         .set("P_fac1", "P_1+P_2*sin(sqrt(theta_x1^2+theta_y1^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 1");
    model.param("par3")
         .set("P_fac2", "P_1+P_2*sin(sqrt(theta_x2^2+theta_y2^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 2");
    model.param("par3")
         .set("P_fac3", "P_1+P_2*sin(sqrt(theta_x3^2+theta_y3^2))", "\u5149\u77b3\u504f\u79fb\u56e0\u5b50\uff0c\u89c6\u573a 3");
    model.param("par3")
         .set("dx1", "(dz+P_fac1*z_stop)*tan(theta_x1)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 1\uff0cx \u5750\u6807");
    model.param("par3")
         .set("dy1", "(dz+P_fac1*z_stop)*tan(theta_y1)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 1\uff0cy \u5750\u6807");
    model.param("par3")
         .set("dx2", "(dz+P_fac2*z_stop)*tan(theta_x2)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 2\uff0cx \u5750\u6807");
    model.param("par3")
         .set("dy2", "(dz+P_fac2*z_stop)*tan(theta_y2)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 2\uff0cy \u5750\u6807");
    model.param("par3")
         .set("dx3", "(dz+P_fac3*z_stop)*tan(theta_x3)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 3\uff0cx \u5750\u6807");
    model.param("par3")
         .set("dy3", "(dz+P_fac3*z_stop)*tan(theta_y3)", "\u5149\u77b3\u4e2d\u5fc3\uff0c\u89c6\u573a 3\uff0cy \u5750\u6807");
    model.param("par3").set("dz", "-30[mm]", "\u5149\u77b3\u4e2d\u5fc3\uff0cz \u5750\u6807");
    model.param("par3").set("alpha_B", "23.4e-6[1/K]", "\u955c\u7b52\u7684\u6807\u79f0 CTE");
    model.param("par3").set("alpha_S_1", "alpha1_N_BK7", "\u900f\u955c\u4e0e\u652f\u67b6 1 \u63a5\u89e6\u7684 CTE");
    model.param("par3").set("alpha_S_2", "alpha1_N_SK2", "\u900f\u955c\u4e0e\u652f\u67b6 2 \u63a5\u89e6\u7684 CTE");
    model.param("par3").set("alpha_S_3", "alpha1_N_SF5", "\u900f\u955c\u4e0e\u652f\u67b6 3 \u63a5\u89e6\u7684 CTE");
    model.param("par3")
         .set("tS_1_i", "d0_1/2*(1-nu_RTV)*(alpha_B-alpha_S_1)/(alpha_RTV-alpha_B-nu_RTV*(alpha_S_1-alpha_B))", "\u652f\u67b6 1 \u539a\u5ea6");
    model.param("par3")
         .set("tS_2_i", "d0_4/2*(1-nu_RTV)*(alpha_B-alpha_S_2)/(alpha_RTV-alpha_B-nu_RTV*(alpha_S_2-alpha_B))", "\u652f\u67b6 2 \u539a\u5ea6");
    model.param("par3")
         .set("tS_3_i", "d0_6/2*(1-nu_RTV)*(alpha_B-alpha_S_3)/(alpha_RTV-alpha_B-nu_RTV*(alpha_S_3-alpha_B))", "\u652f\u67b6 3 \u539a\u5ea6");
    model.param().set("tS_1", "tS_1_i");
    model.param().set("tS_2", "tS_2_i");
    model.param().set("tS_3", "tS_3_i");

    model.component("comp1").selection().create("uni1", "Union");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("uni1").label("\u955c\u7b52\u548c\u63a2\u6d4b\u5668");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_pi11_uni1_dom", "geom1_pi12_uni1_dom", "geom1_pi13_uni1_dom", "geom1_uni1_dom"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u900f\u955c\u548c\u652f\u67b6");
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_csel10_dom", "geom1_sel1"});

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().set(57);
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").selection().set(181);
    model.component("comp1").cpl().duplicate("aveop3", "aveop2");
    model.component("comp1").cpl("aveop3").selection().set(183);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_csel6_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(21);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("geom1_csel10_dom");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmin", "2[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("gop").selection().named("geom1_sel1");
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AirEdlen1953", 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties").setIndex("Text", "T0", 0);
    model.component("comp1").physics("gop").feature("mp1")
         .set("RefractiveIndexDomains", "GetDispersionModelFromMaterial");
    model.component("comp1").physics("gop").feature("mp1").setIndex("minput_temperature_src", "fromCommonDef", 0);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel7_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u5149\u9611");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel8_bnd");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u50cf");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_csel9_bnd");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx1", "dy1", "dz"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new String[]{"nix", "niy", "niz"});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "20.75[mm]");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", 15, 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "vz"});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1").setIndex("lambda0vals", "475[nm] 550[nm] 625[nm]", 0);
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"dx2", "dy2", "dz"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "vz"});
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").set("qcc", new String[]{"dx3", "dy3", "dz"});
    model.component("comp1").physics("gop").feature("relg3").set("L0", new String[]{"vx3", "vy3", "vz"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "3s");
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_pi14_sel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Schott N-BK7 Glass");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2.51[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "0.858[J/(g*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.03961212E+00", "2.31792344E-01", "1.01046945E+00", "6.00069867E-03", "2.00179144E-02", "1.03560653E+02"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"1.86E-6", "1.31E-8", "-1.37E-11", "4.34E-7", "6.27E-10", "0.17"});
    model.component("comp1").material("mat1").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "82.0[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.206");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.665"}, 
         {"2325", "0.793"}, 
         {"1970", "0.933"}, 
         {"1530", "0.992"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.998"}, 
         {"460", "0.997"}, 
         {"436", "0.997"}, 
         {"420", "0.997"}, 
         {"405", "0.997"}, 
         {"400", "0.997"}, 
         {"390", "0.996"}, 
         {"380", "0.993"}, 
         {"370", "0.991"}, 
         {"365", "0.988"}, 
         {"350", "0.967"}, 
         {"334", "0.905"}, 
         {"320", "0.77"}, 
         {"310", "0.574"}, 
         {"300", "0.292"}, 
         {"290", "0.063"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.36"}, 
         {"2325", "0.56"}, 
         {"1970", "0.84"}, 
         {"1530", "0.98"}, 
         {"1060", "0.997"}, 
         {"700", "0.996"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.996"}, 
         {"500", "0.994"}, 
         {"460", "0.993"}, 
         {"436", "0.992"}, 
         {"420", "0.993"}, 
         {"405", "0.993"}, 
         {"400", "0.992"}, 
         {"390", "0.989"}, 
         {"380", "0.983"}, 
         {"370", "0.977"}, 
         {"365", "0.971"}, 
         {"350", "0.92"}, 
         {"334", "0.78"}, 
         {"320", "0.52"}, 
         {"310", "0.25"}, 
         {"300", "0.05"}});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat1").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("Schott N-KZFS5 Glass");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3.04[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "0.73[J/(g*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.95[W/(m*K)]", "0", "0", "0", "0.95[W/(m*K)]", "0", "0", "0", "0.95[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.38E-6[1/K]", "0", "0", "0", "6.38E-6[1/K]", "0", "0", "0", "6.38E-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.47460789E+00", "1.93584488E-01", "1.26589974E+00", "9.86143816E-03", "4.45477583E-02", "1.06436258E+02"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"4.54E-6", "1.19E-8", "2.93E-12", "6.89E-7", "8.6E-10", "0.23"});
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "89.0[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.243");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.657"}, 
         {"2325", "0.826"}, 
         {"1970", "0.963"}, 
         {"1530", "0.988"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.997"}, 
         {"620", "0.997"}, 
         {"580", "0.997"}, 
         {"546", "0.997"}, 
         {"500", "0.994"}, 
         {"460", "0.99"}, 
         {"436", "0.986"}, 
         {"420", "0.983"}, 
         {"405", "0.978"}, 
         {"400", "0.976"}, 
         {"390", "0.967"}, 
         {"380", "0.95"}, 
         {"370", "0.928"}, 
         {"365", "0.91"}, 
         {"350", "0.793"}, 
         {"334", "0.372"}, 
         {"320", "0.017"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.35"}, 
         {"2325", "0.62"}, 
         {"1970", "0.91"}, 
         {"1530", "0.97"}, 
         {"1060", "0.998"}, 
         {"700", "0.994"}, 
         {"660", "0.992"}, 
         {"620", "0.992"}, 
         {"580", "0.993"}, 
         {"546", "0.992"}, 
         {"500", "0.985"}, 
         {"460", "0.974"}, 
         {"436", "0.965"}, 
         {"420", "0.958"}, 
         {"405", "0.946"}, 
         {"400", "0.94"}, 
         {"390", "0.92"}, 
         {"380", "0.88"}, 
         {"370", "0.83"}, 
         {"365", "0.79"}, 
         {"350", "0.56"}, 
         {"334", "0.08"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("Schott N-SK2 Glass");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "3.55[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "0.595[J/(g*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.776[W/(m*K)]", "0", "0", "0", "0.776[W/(m*K)]", "0", "0", "0", "0.776[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.0E-6[1/K]", "0", "0", "0", "6.0E-6[1/K]", "0", "0", "0", "6.0E-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.28189012E+00", "2.57738258E-01", "9.68186040E-01", "7.27191640E-03", "2.42823527E-02", "1.10377773E+02"});
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat3").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"3.8E-6", "1.41E-8", "2.28E-11", "6.44E-7", "8.03E-11", "0.108"});
    model.component("comp1").material("mat3").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "78.0[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.263");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.815"}, 
         {"2325", "0.896"}, 
         {"1970", "0.971"}, 
         {"1530", "0.995"}, 
         {"1060", "0.998"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.996"}, 
         {"460", "0.993"}, 
         {"436", "0.993"}, 
         {"420", "0.994"}, 
         {"405", "0.994"}, 
         {"400", "0.994"}, 
         {"390", "0.992"}, 
         {"380", "0.988"}, 
         {"370", "0.976"}, 
         {"365", "0.967"}, 
         {"350", "0.905"}, 
         {"334", "0.752"}, 
         {"320", "0.504"}, 
         {"310", "0.276"}, 
         {"300", "0.102"}, 
         {"290", "0.02"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.6"}, 
         {"2325", "0.76"}, 
         {"1970", "0.93"}, 
         {"1530", "0.988"}, 
         {"1060", "0.995"}, 
         {"700", "0.995"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.995"}, 
         {"500", "0.99"}, 
         {"460", "0.983"}, 
         {"436", "0.982"}, 
         {"420", "0.984"}, 
         {"405", "0.985"}, 
         {"400", "0.984"}, 
         {"390", "0.979"}, 
         {"380", "0.97"}, 
         {"370", "0.94"}, 
         {"365", "0.92"}, 
         {"350", "0.78"}, 
         {"334", "0.49"}, 
         {"320", "0.18"}, 
         {"310", "0.04"}});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat3").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").label("Schott N-SF5 Glass");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2.86[g/cm^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "0.77[J/(g*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.52481889E+00", "1.87085527E-01", "1.42729015E+00", "1.12547560E-02", "5.88995392E-02", "1.29141675E+02"});
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat4").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-2.51E-7", "1.07E-8", "-2.4E-11", "7.85E-7", "1.15E-9", "0.278"});
    model.component("comp1").material("mat4").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "87.0[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.237");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.758"}, 
         {"2325", "0.831"}, 
         {"1970", "0.95"}, 
         {"1530", "0.99"}, 
         {"1060", "0.998"}, 
         {"700", "0.996"}, 
         {"660", "0.995"}, 
         {"620", "0.995"}, 
         {"580", "0.996"}, 
         {"546", "0.995"}, 
         {"500", "0.99"}, 
         {"460", "0.982"}, 
         {"436", "0.973"}, 
         {"420", "0.963"}, 
         {"405", "0.928"}, 
         {"400", "0.905"}, 
         {"390", "0.826"}, 
         {"380", "0.642"}, 
         {"370", "0.276"}, 
         {"365", "0.116"}});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.5"}, 
         {"2325", "0.63"}, 
         {"1970", "0.88"}, 
         {"1530", "0.975"}, 
         {"1060", "0.994"}, 
         {"700", "0.989"}, 
         {"660", "0.987"}, 
         {"620", "0.988"}, 
         {"580", "0.991"}, 
         {"546", "0.988"}, 
         {"500", "0.976"}, 
         {"460", "0.956"}, 
         {"436", "0.935"}, 
         {"420", "0.91"}, 
         {"405", "0.83"}, 
         {"400", "0.78"}, 
         {"390", "0.62"}, 
         {"380", "0.33"}, 
         {"370", "0.04"}});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat4").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat4").selection().named("geom1_csel4_dom");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").label("Aluminum 6063-T83");
    model.component("comp1").material("mat5").set("family", "aluminum");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat5").selection().named("uni1");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").selection().named("geom1_csel10_dom");
    model.component("comp1").material("mat6").label("RTV");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", new String[]{"E_RTV"});
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", new String[]{"nu_RTV"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"rho_RTV"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_RTV"});

    model.study("std1").create("stat", "Stationary");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 215");
    model.study("std1").feature("rtrac").set("geometricNonlinearity", true);
    model.study("std1").feature("rtrac").setSolveFor("/physics/solid", false);
    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"gop/wall3"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().named("geom1_sel1");
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result().dataset("ip1").set("genmethod", "threepoint");
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(x)", 0, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(y)", 0, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop1(z)", 0, 2);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(x)", 1, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(y)", 1, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop2(z)", 1, 2);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(x)", 2, 0);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(y)", 2, 1);
    model.result().dataset("ip1").setIndex("genpoints", "aveop3(z)", 2, 2);
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("view", "new");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").set("legendactive", true);
    model.result("pg3").set("legendprecision", 4);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "gop.prf");
    model.result("pg3").feature("rtrj1").feature("col1").set("unit", "um");
    model.result("pg3").feature("rtrj1").feature("col1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg3").feature("rtrj1").feature("filt1").set("logicalexpr", "at(0,qx>0.1[mm])");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.T");
    model.result("pg3").feature("surf1").set("unit", "degC");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -27.5);
    model.result("pg3").feature("surf1").set("rangecolormax", 52.5);
    model.result("pg3").feature("surf1").set("colortable", "WaveLight");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_comsel1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "x>0.5[mm] || y<-0.5[mm]");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "gray");
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("sel1").selection().named("geom1_csel5_bnd");
    model.result("pg3").run();
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "solid.mises");
    model.result("pg3").feature("slc1").set("unit", "MPa");
    model.result("pg3").feature("slc1").set("quickxnumber", 1);
    model.result("pg3").feature("slc1").set("rangecoloractive", true);
    model.result("pg3").feature("slc1").set("rangecolormax", 10);
    model.result("pg3").feature("slc1").set("colortable", "HeatCamera");
    model.result("pg3").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg3").feature("slc1").create("sel1", "Selection");
    model.result("pg3").feature("slc1").feature("sel1").selection().named("uni1");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").feature("filt1").set("expr", "y>0");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("slc2", "slc1");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").set("quickplane", "zx");
    model.result("pg3").feature("slc2").set("quickynumber", 1);
    model.result("pg3").feature("slc2").set("inheritplot", "slc1");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").feature("filt1").set("expr", "x<0");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("slc3", "slc1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("slc3").feature("sel1").selection().named("uni2");
    model.result("pg3").run();
    model.result("pg3").feature("slc3").set("inheritplot", "slc2");
    model.result("pg3").feature("slc3").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("slc3").feature("tran1").set("transparency", 0.25);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("slc4", "slc2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("slc4").feature("sel1").selection().named("uni2");
    model.result("pg3").run();
    model.result("pg3").feature("slc4").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("slc4").feature("tran1").set("transparency", 0.25);
    model.result("pg3").run();

    model.view("view8").set("showgrid", false);

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "w");
    model.result("pg4").feature("surf1").set("unit", "um");
    model.result("pg4").feature("surf1").set("rangecolormin", -125);
    model.result("pg4").feature("surf1").set("rangecolormax", 125);
    model.result("pg4").feature("surf1").set("colortable", "TrafficLight");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 25);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf2").feature("def1").set("scale", 25);
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("colortable", "AuroraAustralis");
    model.result("pg4").feature("slc1").set("colortabletrans", "none");
    model.result("pg4").feature("slc1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("slc1").feature("def1").set("scale", 25);
    model.result("pg4").run();
    model.result("pg4").feature("slc2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("slc3").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("slc4").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u70b9\u5217\u56fe\uff0c\u6807\u79f0");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u70b9\u5217\u56fe\uff1a\u6807\u79f0\u7126\u5e73\u9762");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("spot1", "SpotDiagram");
    model.result("pg5").feature("spot1").set("data", "ip1");
    model.result("pg5").feature("spot1").set("layout", "rectangular");
    model.result("pg5").feature("spot1").set("columns", 1);
    model.result("pg5").feature("spot1").set("origin", "area");
    model.result("pg5").feature("spot1").set("paddingvert", 1);
    model.result("pg5").feature("spot1").set("spotcoordsactive", true);
    model.result("pg5").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg5").feature("spot1").set("spotcoordsprecision", 6);
    model.result("pg5").feature("spot1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("spot1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg5").feature("spot1").feature("col1").set("unit", "nm");
    model.result("pg5").feature("spot1").feature("col1").set("rangecoloractive", true);
    model.result("pg5").feature("spot1").feature("col1").set("rangecolormin", 450);
    model.result("pg5").feature("spot1").feature("col1").set("rangecolormax", 650);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u70b9\u5217\u56fe\uff0c\u6700\u4f73\u805a\u7126");
    model.result("pg6").set("title", "\u70b9\u5217\u56fe\uff1a\u6700\u4f73\u805a\u7126\u5e73\u9762");
    model.result("pg6").set("view", "new");
    model.result("pg6").run();
    model.result("pg6").feature("spot1").set("data", "ray1");
    model.result("pg6").feature("spot1").set("filterreleaseactive", true);
    model.result("pg6").feature("spot1").set("normal", "userdefined");
    model.result("pg6").feature("spot1").set("transverse", "userdefined");
    model.result().dataset().create("ip2", "IntersectionPoint3D");
    model.result("pg6").feature("spot1").set("data", "ip2");
    model.result().dataset("ip2").set("data", "ray1");
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "1.0388910459514041E-5[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.7634697690533704E-5[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76774684279246[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "1.1388910459514042E-5[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.7634697690533704E-5[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76774684279246[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "1.0388910459514041E-5[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.6634697690533704E-5[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76774684279246[mm]", 2, 2);
    model.result("pg6").feature("spot1").run();
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "1.1207263282980673E-5[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.7989082133566465E-5[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79243371789278[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "1.2207263282980673E-5[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.7989082133566465E-5[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79243371789278[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "1.1207263282980673E-5[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-1.6989082133566464E-5[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79243371789278[mm]", 2, 2);
    model.result("pg6").feature("spot1").run();
    model.result("pg6").set("ispendingzoom", true);
    model.result("pg6").feature("spot1").set("filterreleaseactive", false);
    model.result("pg6").run();

    model.title("Petzval \u900f\u955c STOP \u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5728\u5747\u5300\u6e29\u5ea6\u4e0b\uff0c\u5bf9\u5149\u5b66\u7cfb\u7edf\u8fdb\u884c\u7ed3\u6784-\u70ed-\u5149\u5b66\u6027\u80fd (STOP) \u7efc\u5408\u5206\u6790\u7684\u8fc7\u7a0b\u3002\u6a21\u578b\u57fa\u4e8e\u201cPetzval \u900f\u955c\u201d\u6559\u7a0b\uff0c\u5e76\u7ed3\u5408\u4e86\u7b80\u5355\u7684\u955c\u7b52\u51e0\u4f55\u8bbe\u8ba1\uff1b\u4e0d\u4ec5\u63ed\u793a\u4e86\u5149\u5b66\u7cfb\u7edf\u5185\u4ea7\u751f\u7684\u4f4d\u79fb\u548c\u5e94\u529b\u573a\uff0c\u8fd8\u5448\u73b0\u4e86\u6807\u79f0\u548c\u6700\u4f73\u7126\u70b9\u56fe\u3002\u6b64\u6a21\u578b\u662f\u8be5\u7814\u7a76\u7684\u591a\u4e2a\u6269\u5c55\u7684\u57fa\u7840\u3002");

    model.label("petzval_lens_stop_analysis.mph");

    model.result("pg6").run();

    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().named("geom1_csel10_dom");
    model.component("comp1").physics("solid").feature("hmm1").set("Compressibility_NeoHookean", "Incompressible");
    model.component("comp1").physics("solid").feature("hmm1").set("muLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").set("muLame", "40[MPa]");
    model.component("comp1").physics("solid").feature("hmm1").create("te1", "ThermalExpansion", 3);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature("spot1").set("filterreleaseactive", true);
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "2.2297051401956867E-6[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.1136213210134686E-5[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76962319424155[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "3.229705140195687E-6[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.1136213210134686E-5[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76962319424155[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "2.2297051401956867E-6[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.0136213210134686E-5[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "161.76962319424155[mm]", 2, 2);
    model.result("pg6").feature("spot1").run();
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "2.6831562803987397E-6[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.1742945615311697E-5[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79427800809884[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "3.6831562803987394E-6[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.1742945615311697E-5[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79427800809884[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "2.6831562803987397E-6[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-2.0742945615311696E-5[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "162.79427800809884[mm]", 2, 2);
    model.result("pg6").feature("spot1").run();
    model.result("pg6").set("ispendingzoom", true);
    model.result("pg6").feature("spot1").set("filterreleaseactive", false);
    model.result("pg6").run();

    model.title("\u91c7\u7528\u8d85\u5f39\u6027\u6750\u6599\u7684 Petzval \u900f\u955c STOP \u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u57fa\u4e8e\u201cPetzval \u900f\u955c STOP \u5206\u6790\u201d\u6559\u7a0b\uff0c\u6f14\u793a\u4f7f\u7528\u975e\u7ebf\u6027\u6750\u6599\u6a21\u578b\u5bf9\u5149\u5b66\u7cfb\u7edf\u8fdb\u884c\u7ed3\u6784-\u70ed-\u5149\u5b66\u6027\u80fd (STOP) \u7efc\u5408\u5206\u6790\u7684\u8fc7\u7a0b\u3002\u900f\u955c\u652f\u67b6\u91c7\u7528\u8d85\u5f39\u6027\u6750\u6599\u7279\u5f81\u8fdb\u884c\u5efa\u6a21\u3002\u6a21\u578b\u4e0d\u4ec5\u63ed\u793a\u4e86\u5149\u5b66\u7cfb\u7edf\u5185\u4ea7\u751f\u7684\u4f4d\u79fb\u548c\u5e94\u529b\u573a\uff0c\u8fd8\u5448\u73b0\u4e86\u6807\u79f0\u548c\u6700\u4f73\u7126\u70b9\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("petzval_lens_stop_analysis_with_hyperelasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
