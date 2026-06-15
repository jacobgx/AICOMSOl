/*
 * ccp_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class ccp_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Capacitively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ptp", "ColdPlasmaTimePeriodic", "geom1");

    model.study().create("std1");
    model.study("std1").create("tper", "TimePeriodic");
    model.study("std1").feature("tper").set("ftplistmethod", "manual");
    model.study("std1").feature("tper").set("solnum", "auto");
    model.study("std1").feature("tper").set("notsolnum", "auto");
    model.study("std1").feature("tper").set("outputmap", new String[]{});
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").setSolveFor("/physics/ptp", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.067, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("p0", "0.3[torr]");
    model.param().descr("p0", "\u538b\u529b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Pin", "if(p0==0.03[torr],11.6,9.4)");
    model.component("comp1").variable("var1").descr("Pin", "\u8f93\u5165\u529f\u7387");

    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "0.1[m^2]");
    model.component("comp1").physics("ptp").prop("EEDFSettings").set("eedf", "Maxwellian");
    model.component("comp1").physics("ptp").prop("DiffusionModel").set("DiffusionModel", "FicksLaw");
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "He_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").feature("He").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("He").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("Hes").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("Hes").set("Dfs", "0.8[m^2/s]");
    model.component("comp1").physics("ptp").feature("He_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("He_1p").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("He_1p").set("MobilitySpecification", "SpecifyLookupMobility");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("MobilityEFieldSpecification", "RedEfieldVMobilityData");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("redefield_mueXdata", new double[]{0, 16.355637845572303, 49.31499277960864, 82.77426901878985, 116.74104927557597, 151.22303127558592, 186.22802950209407, 221.7639769669908, 257.8389270085989, 294.4610551167666, 331.6386607856338, 369.3801693945127, 407.6941341172799, 446.5892378607576, 486.0742952324525, 526.1582545381981, 566.8501998100398, 608.1593528649187, 650.0950753945659, 692.6668710870831, 735.8843877807471, 779.757419650409, 824.2959094271558, 869.509950651516, 915.4097899609753, 962.0058294120773, 1009.3086288378076, 1057.3289082407193, 1106.0775502223398, 1155.5656024494756, 1205.8042801578351, 1256.8049686937486, 1308.5792260943147, 1361.1387857067853, 1414.495558847616, 1468.6616375018548, 1523.6492970635536, 1579.470999117567, 1636.1393942637985, 1693.6673249840485, 1752.0678285524139, 1811.3541399900234, 1871.5396950642294, 1932.6381333336292, 1994.6633012390653, 2057.629255241595, 2121.5502650080334, 2186.440816644854, 2252.315615981, 2319.1895919007366, 2387.077899726811, 2455.9959246550657, 2525.9592852411256, 2596.9838369398767, 2669.085675698812, 2742.2811416057502, 2816.586822591823, 2892.0195581908283, 2968.596443355495, 3046.334832331483, 3125.252342590483, 3205.366858822658, 3286.6965369897553, 3369.2598084397696, 3453.0753840839243, 3538.1622586370595, 3624.5397149222513, 3712.2273282407236, 3801.24497080835, 3891.6128162589835, 3983.3513442163203, 4076.4813449352364, 4171.023924013138, 4267.000507173313, 4364.432845120374, 4463.343018469473, 4563.753442750326, 4665.686873487459, 4769.166411356646, 4874.215507420488, 4980.857968442906, 5089.117962284304, 5199.020023378633, 5310.5890582936445, 5423.850351375089, 5538.82957047706, 5655.552772778675, 5774.046410689683, 5894.337337844731, 6016.452815189559, 6140.4205171588565, 6266.268537947754, 6394.025397878829, 6523.720049865828, 6655.38188597474, 6789.040744084638, 6924.726914650313, 7062.471147566286, 7202.304659135687, 7344.259139144701, 7488.366758044376, 7634.660174240982, 7783.172541497212, 7933.937516446141, 8086.989266218104, 8242.362476184077, 8400.092357816282, 8560.214656667991, 8722.765660474177, 8887.782207375389, 9055.301694266236, 9225.362085270444, 9398.001920344266, 9573.260324010955, 9751.177014226974, 9931.792311383537, 10115.147147443937, 10301.283075219535, 10490.242277787127, 10682.067578048649, 10876.802448435497, 11074.491020760837, 11275.178096220936, 11478.909155548085, 11685.73036931807, 11895.688608412893, 12108.83145464336, 12325.207211532657, 12544.86491526223, 12767.854345785427, 12994.226038108538, 13224.0312937432, 13457.32219233271, 13694.15160345488, 13934.573198603342, 14178.641463350143, 14426.411709695576, 14677.940088600666, 14933.283602714511, 15192.500119291284, 15455.648383304806, 15722.78803076193, 15993.979602216961, 16269.284556491835, 16548.765284605022, 16832.485123909802, 17120.508372447704, 17412.900303522645, 17709.727180490678, 18011.056271777423, 18316.95586612512, 18627.495288064765, 18942.744913630373, 19262.77618630568, 19587.66163321601, 19917.474881564398, 20252.29067531778, 20592.184892144578, 20937.234560612833, 21287.517877644434, 21643.114226237667, 22004.104193457773, 22370.569588698763, 22742.593462224326, 23120.26012398901, 23503.65516274576, 23892.865465439736, 24287.97923690333, 24689.086019840706, 25096.27671512549, 25509.643602396864, 25929.280360974542, 26355.28209108949, 26787.74533543477, 27226.768101043584, 27672.44988150316, 28124.8916794995, 28584.19602971033, 29050.46702203641, 29523.810325198727, 30004.333210678826, 30492.144577033607, 30987.354974571186, 31490.07663040744, 32000.423473896204, 32518.511162453426, 33044.4571077624, 33578.38050238573, 34120.40234677535, 34670.64547669747, 35229.234591066364, 35796.29628020685, 36371.95905454159, 36956.353373714956, 37549.6116761591, 38151.868409107956, 38763.2600590656, 39383.92518273622, 40014.00443842847, 40653.640617928, 41302.97867886068});
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("redefield_mueYdata", new double[]{0, 5.594307642166517E22, 3.2217408316920203E22, 2.4867516291893296E22, 2.0939595695178425E22, 1.8398021353930442E22, 1.657897070846091E22, 1.5192686893876136E22, 1.4089832999119581E22, 1.3184575644765805E22, 1.242360148212415E22, 1.1771811526228757E22, 1.1205025695067779E22, 1.070596851218632E22, 1.0261923213496161E22, 9.863292083582194E21, 9.502676311585862E21, 9.174267377069082E21, 8.873432503184342E21, 8.596425027540357E21, 8.340177500128271E21, 8.102150951433886E21, 7.880223150068156E21, 7.672604461710534E21, 7.477773595121583E21, 7.29442790680701E21, 7.121444518442084E21, 6.957849571210519E21, 6.802793677476692E21, 6.655532144958612E21, 6.515408913753527E21, 6.381843409158063E21, 6.254319704411236E21, 6.132377528298823E21, 6.015604757386976E21, 5.903631111475921E21, 5.796122830690527E21, 5.692778158430247E21, 5.593323489758597E21, 5.497510072320521E21, 5.405111168428022E21, 5.315919603960027E21, 5.229745643227176E21, 5.146415139739384E21, 5.065767921486544E21, 4.987656376347791E21, 4.911944208937018E21, 4.838505344838497E21, 4.767222961998272E21, 4.6979886321770236E21, 4.630701557970359E21, 4.5652678930614956E21, 4.5016001351741766E21, 4.4396165827028546E21, 4.379240847265612E21, 4.320401415496013E21, 4.263031254295447E21, 4.2070674545364854E21, 4.152450908863252E21, 4.099126019794133E21, 4.047040434811214E21, 3.996144805533546E21, 3.94639256842533E21, 3.897739744796913E21, 3.850144758121871E21, 3.8035682669235E21, 3.7579730116845534E21, 3.71332367440857E21, 3.669586749613682E21, 3.626730425673965E21, 3.584724475539922E21, 3.543540155973205E21, 3.5031501145216983E21, 3.463528303540574E21, 3.424649900636692E21, 3.3864912349757665E21, 3.3490297189477416E21, 3.3122437847351694E21, 3.2761128253740066E21, 3.240617139934403E21, 3.205737882485372E21, 3.1714570145378284E21, 3.1377572606888336E21, 3.1046220672150447E21, 3.072035563386184E21, 3.039982525289254E21, 3.008448341973177E21, 2.977418983739367E21, 2.946880972419468E21, 2.9168213534940316E21, 2.887227669919059E21, 2.858087937537802E21, 2.8293906219652953E21, 2.801124616842372E21, 2.77327922336434E21, 2.745844130996585E21, 2.7188093992963985E21, 2.6921654407670714E21, 2.665903004675195E21, 2.640013161767931E21, 2.614487289831565E21, 2.5893170600371337E21, 2.564494424022754E21, 2.540011601665999E21, 2.5158610695033837E21, 2.492035549756484E21, 2.468527999927621E21, 2.4453316029303966E21, 2.422439757722865E21, 2.3998460704132453E21, 2.377544345810284E21, 2.355528579392178E21, 2.3337929496697595E21, 2.3123318109211407E21, 2.2911396862767894E21, 2.2702112611350054E21, 2.2495413768894687E21, 2.22912502495141E21, 2.2089573410501058E21, 2.1890335997966085E21, 2.169349209496444E21, 2.1498997071977662E21, 2.1306807539625613E21, 2.1116881303490627E21, 2.092917732094261E21, 2.0743655659862517E21, 2.0560277459164156E21, 2.0379004891023658E21, 2.0199801124730723E21, 2.0022630292077543E21, 1.9847457454210917E21, 1.967424856987387E21, 1.950297046496844E21, 1.933359080337517E21, 1.916607805896891E21, 1.9000401488773293E21, 1.883653110719735E21, 1.8674437661307977E21, 1.8514092607083268E21, 1.835546808660622E21, 1.8198536906151984E21, 1.8043272515128638E21, 1.7889648985832956E21, 1.7737640993983174E21, 1.7587223799993847E21, 1.7438373230961007E21, 1.7291065663324535E21, 1.714527800617682E21, 1.7000987685193622E21, 1.6858172627155542E21, 1.6716811245035512E21, 1.6576882423631614E21, 1.6438365505715708E21, 1.6301240278682092E21, 1.6165486961670955E21, 1.6031086193148845E21, 1.589801901892626E21, 1.5766266880595227E21, 1.5635811604367598E21, 1.550663539030097E21, 1.5378720801893264E21, 1.5252050756032882E21, 1.512660851329036E21, 1.5002377668536694E21, 1.4879342141876483E21, 1.4757486169882868E21, 1.4636794297123904E21, 1.4517251367965725E21, 1.4398842518646835E21, 1.4281553169607313E21, 1.4165369018068923E21, 1.4050276030852073E21, 1.3936260437422915E21, 1.3823308723162044E21, 1.3711407622845953E21, 1.360054411433242E21, 1.3490705412444877E21, 1.338187896304537E21, 1.3274052437292915E21, 1.316721372607528E21, 1.3061350934614416E21, 1.2956452377233441E21, 1.2852506572283658E21, 1.2749502237222799E21, 1.2647428283841957E21, 1.2546273813632949E21, 1.2446028113294936E21, 1.234668065037164E21, 1.2248221069017452E21, 1.2150639185886024E21, 1.205392498613936E21, 1.1958068619570916E21, 1.1863060396840636E21, 1.1768890785817265E21, 1.1675550408024542E21, 1.1583030035187892E21, 1.1491320585878452E21, 1.1400413122251038E21, 1.1310298846872205E21, 1.12209690996374E21, 1.113241535477207E21});
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "He+=>He");
    model.component("comp1").physics("ptp").feature("sr1").selection().all();
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("gammai", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("ebari", 0);
    model.component("comp1").physics("ptp").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Hes=>He");
    model.component("comp1").physics("ptp").feature("sr2").set("gammaf", 1);
    model.component("comp1").physics("ptp").feature("pes1").set("T", "300[K]");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("ptp").feature("pes1").set("SpecifyElectronDensityAndEnergy", "SpecifyMueOnly");
    model.component("comp1").physics("ptp").feature("pes1")
         .set("mue", new String[]{"3.33e24[1/(V*m*s)]/ptp.Nn", "0", "0", "0", "3.33e24[1/(V*m*s)]/ptp.Nn", "0", "0", "0", "3.33e24[1/(V*m*s)]/ptp.Nn"});
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").feature("wall1").set("re", "5/11");
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 0);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(1);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("ptp").feature("mct1").set("Source", "RF");
    model.component("comp1").physics("ptp").feature("mct1").selection().set(2);
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "Pin");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("tper").set("useparam", true);
    model.study("std1").feature("tper").setIndex("pname", "p0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "Pa", 0);
    model.study("std1").feature("tper").setIndex("pname", "p0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "Pa", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "0.3 0.1 0.03", 0);
    model.study("std1").feature("tper").setIndex("punit", "torr", 0);
    model.study("std1").setGenConv(false);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "ptp.xdintop_ptp1(ptp.Jix_wHe_1p/ptp.xdim)");
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1")
         .set("descr", "\u65f6\u95f4\u5e73\u5747\u79bb\u5b50\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u65f6\u95f4\u5e73\u5747\u79bb\u5b50\u6d41\u5bc6\u5ea6");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u65f6\u95f4\u5e73\u5747\u7535\u79bb\u7387");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("expr", "ptp.Re_av");
    model.result("pg2").feature("lngr1")
         .set("descr", "\u65f6\u95f4\u5e73\u5747\u7535\u79bb\u7387 (1/(m<sup>3</sup>s)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u65f6\u95f4\u5e73\u5747\u6fc0\u53d1\u7387");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "ptp.xdintop_ptp1(ptp.R_wHes*N_A_const/ptp.xdim)");
    model.result("pg3").feature("lngr1")
         .set("descr", "\u65f6\u95f4\u5e73\u5747\u6fc0\u53d1\u7387 (1/(m<sup>3</sup>s)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u65f6\u95f4\u5e73\u5747\u7535\u5b50\u529f\u7387\u6c89\u79ef");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "ptp.Pcap_ele_ions_av-ptp.Pcap_ions_av");
    model.result("pg4").feature("lngr1")
         .set("descr", "\u65f6\u95f4\u5e73\u5747\u7535\u5b50\u529f\u7387\u6c89\u79ef");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u65f6\u95f4\u5e73\u5747\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "ptp.neav");
    model.result("pg5").feature("lngr1").set("descractive", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u65f6\u95f4\u5e73\u5747\u79bb\u5b50\u5bc6\u5ea6");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "ptp.n_wHe_1p_av");
    model.result("pg6").feature("lngr1").set("descr", "\u6570\u5bc6\u5ea6");
    model.result("pg6").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").set("comp", "ptp_xdim");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").label("\u7535\u4f4d");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "ptp.mct1.V");
    model.result("pg7").feature("lngr1").set("descr", "\u7535\u52bf");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result().dataset().create("cpt1", "CutPoint1D");
    model.result().dataset("cpt1").set("pointx", "0.067/2");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").set("expr", new String[]{"ptp.neav"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747"});
    model.result().numerical("pev1").set("unit", new String[]{"1/m^3"});
    model.result().numerical("pev1").setIndex("expr", "(3/2)*ptp.Teav", 1);
    model.result().numerical("pev1")
         .setIndex("descr", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf\uff0c\u5468\u671f\u5e73\u5747", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").set("data", "dset2");
    model.result().numerical("av1").selection().all();
    model.result().numerical("av1").setIndex("expr", "atxd0(0.067,ptp.nJt)", 0);
    model.result().numerical("av1").setIndex("unit", "mA/cm^2", 0);
    model.result().numerical("av1").setIndex("descr", "\u5468\u671f\u5e73\u5747\u79bb\u5b50\u6d41\u5bc6\u5ea6", 0);
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").appendResult();
    model.result("pg1").run();

    model.title("\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u53ef\u4ee5\u91cd\u73b0\u5df2\u53d1\u5e03\u7684\u4e00\u7ef4\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u57fa\u51c6\u6a21\u578b\u7684\u7ed3\u679c\u3002\u6a21\u578b\u7531\u6052\u5b9a\u7535\u6d41\u9a71\u52a8\uff0c\u800c\u975e\u6052\u5b9a\u7535\u538b\u9a71\u52a8\u3002\u79bb\u5b50\u6d41\u3001\u529f\u7387\u6c89\u79ef\u3001\u7535\u5b50\u5bc6\u5ea6\u3001\u79bb\u5b50\u5bc6\u5ea6\u548c\u79bb\u5b50\u901a\u91cf\u90fd\u4e0e\u53d1\u5e03\u7684\u6570\u636e\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("ccp_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
